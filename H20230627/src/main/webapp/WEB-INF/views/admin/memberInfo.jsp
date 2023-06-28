<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<h3>memberInfo Page</h3>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<table class="table">
	<tr>
		<th>아이디</th>
		<td class="uid">${member.userId }</td>
	</tr>
	<tr>
		<th>패스워드</th>
		<td><input type="text" class="passwd" value="${member.userPw }" /></td>
	</tr>
	<tr>
		<th>이름</th>
		<td>${member.userName }</td>
	</tr>
	<tr>
		<th>생일</th>
		<td><fmt:formatDate pattern="yyyy-MM-dd" value="${member.userBirth }" /></td>
	</tr>
	<tr>
		<th>연락처</th>
		<td><input type="text" class="phone" value="${member.userPhone }" /></td>
	</tr>
	<tr>
		<th>주소</th>
		<td><input type="text" class="addr" value="${member.userAddr }" /></td>
	</tr>
	<tr>
		<th>사진</th>
		<td>
			<img class="image" width="100px" src="images/${member.userImg}" />
			<input type="file" id="image" style="display:none;" />
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<button id = "saveBtn">저장</button>
		</td>
	</tr>
</table>

<script>
	document.getElementById('image').addEventListener('change', function(e){
		// multipart/form-data (파일첨부)
		let formData = new FormData();
		formData.append('id', document.querySelector('td.uid').innerText);
		formData.append('image', e.target.files[0]);

		let xhtp = new XMLHttpRequest();
		xhtp.open('post', 'imageUpload.do');
		xhtp.send(formData);
		xhtp.onload = function(){
			let result = JSON.parse(xhtp.responseText);
			if(result.retCode == 'Success'){
				document.querySelector('img.image').src = 'images/' + result.path;
			} else if(result.retCode == 'Fail'){
				alert('처리중 에러');
			} else {
				alert('알 수 없는 처리코드');
			}
		}
	});
	// img 클릭 이벤트
	document.querySelector('img.image').addEventListener('click', function(e){
		document.getElementById('image').click();
	});

	document.querySelector('#saveBtn').addEventListener('click', function(e){
		let id = document.querySelector('td.uid').innerText;
		let pw = document.querySelector('input.passwd').value;
		let phone = document.querySelector('input.phone').value;
		let addr = document.querySelector('input.addr').value;

		let xhtp = new XMLHttpRequest();
		/* xhtp.open('get','memberModify.do?uid='+id+'&upw='+pw+"&uph="+phone+"&uad="+addr);
		xhtp.send();//서버요청 */
		xhtp.open('post','memberModify.do');
		xhtp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');	// Key / Value 방식으로 넘긴다는 의미
		xhtp.send('uid=' + id + '&upw=' + pw + '&uph=' + phone + '&uad=' + addr);	// 이게 Key/Value.
		xhtp.onload = function(){
			let result = JSON.parse(xhtp.responseText);
			if(result.retCode == 'Success'){
				alert("저장 성공.");
			} else if(result.retCode == 'Fail'){
				alert("저장 실패.");
			} else {
				alert("알 수 없는 코드입니다.");
			}
		}
	});
</script>