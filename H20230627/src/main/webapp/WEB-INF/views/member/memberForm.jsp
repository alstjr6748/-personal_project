<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>


<script>
	let today = new Date('Apr 4, 2023, 12:00:00 AM');

	Date.prototype.timeFormat = function(){	//prototype => 메소드 정의해서 사용가능
		let yyyy = this.getFullYear();
		let mm = this.getMonth() + 1;
		let dd = this.getDate();

		return yyyy + "-" + ('0' + mm).slice(-2) + "-" + ('0' + dd).slice(-2);
	}
	today.timeFormat();
	console.log(today.timeFormat());
	
	$(document).ready(function(){
		$.ajax({
			url: 'memberListJson.do',
			method: 'get',
			success: function(result){
				console.log(result);
				result.forEach(item => {
					let tr = $('<tr />').append($('<td />').text(item.userId)
									          ,$('<td />').text(item.userName)
						                      ,$('<td />').text(new Date(item.userBirth).timeFormat())
									          ,$('<td />').text(item.userPhone)
									          ,$('<td />').append($('<img>').attr('src', 'images/' + item.userImg).attr('width', '25px'))
					)
					tr.on('click', modifyFnc);
					$('#list').append(tr);
				});
			},
			error: function(err){
				console.log(err);
			}
		});

		function modifyFnc(e) {
			console.log($(this).find('td:nth-child(1)').text());
			//$(this).children().eq(0).text();	밑에랑 같은 의미. tr의 children중에서 0번째 값의 text를 가져오겠다는 의미.
			let id = $(this).find('td:nth-child(1)').eq(0).text();

			$.ajax({
				url: 'memberInfoJson.do',
				method: 'post',
				data: {
					uid: id
				},
				success: function(result){
					console.log(result);
					$('#uid').val(result.userId);
					$('#upw').val(result.userPw);
					$('#uname').val(result.userName);
					$('#uphone').val(result.userPhone);
					$('#uaddr').val(result.userAddr);
					$('#ubirth').val(new Date(result.userBirth).timeFormat());
				},
				error: function(err){
					console.log(err);
				}
			})
		}
		// 등록버튼 클릭시
		$('form[name="myFrm"]').on('submit', submitForm)


		function submitForm(e){
			e.preventDefault();
			$.ajax({
				url:$('form[name="myFrm"]').attr('action'),
				method: 'post',
				data: $('form[name="myFrm"]').serialize(),
				// "uid=" + $('input[name="uid"]').val() + "&upw=" + $('input[name="upw"]').val() + "&uname=" + $('input[name="uname"]').val()
				// + "&uphone=" + $('input[name="uphone"]').val()
				success: function(result){
					let tr = $('<tr />').append($('<td />').text(result.userId)
									          ,$('<td />').text(result.userName)
						                      ,$('<td />').text(result.userBirth)
									          ,$('<td />').text(result.userPhone)
											  ,$('<td />').text(result.userPw)
											  ,$('<td />').text(result.userAddr)
					)
					$('#list').append(tr);
				},
				error:function(err){
					console.log(err);
				}
			})
		}
	})
</script>


<h3>Jquery (memberForm.jsp) Ajax : Single Page Appliction</h3>

<form name="myFrm" action="memberAdd.do">
	<table class="table">
		<tr>
			<th>아이디</th>
			<td><input type="text" name="uid" id="uid" value="newbie"></td>
		</tr>
		<tr>
			<th>패스워드</th>
			<td><input type="text" name="upw" id="upw" value="newbie"></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><input type="text" name="uname" id="uname" value="홍길동"></td>
		</tr>
		<tr>
			<th>연락처</th>
			<td><input type="text" name="uphone" id="uphone" value="010-1111-1234"></td>
		</tr>
		<tr>
			<th>주소</th>
			<td><input type="text" name="uaddr" id="uaddr" value="대구시 1번지"></td>
		</tr>
		<tr>
			<th>생년월일</th>
			<td><input type="text" name="ubirth" id="ubirth" value="1995-04-25"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="등록">
				<input type="reset" value="초기화">
				<input type="button" value="변경">
			</td>
		</tr>
	</table>
</form>
<br>
<table class="table">
	<thead>
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>생일</th>
			<th>연락처</th>
			<th>사진</th>
		</tr>
	</thead>
	<tbody id="list">

	</tbody>
</table>

