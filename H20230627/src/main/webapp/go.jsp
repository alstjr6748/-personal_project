<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	시도: <select id="serach">

		 </select>
	<button id="searchBtn">조회</button>

	<div id="show">
		<table border="1">
			<thead>
				<tr>
					<th>센터id</th>
					<th>센터명</th>
					<th>연락처</th>
					<th>시도</th>
					<th>우편번호</th>
				</tr>
			</thead>
			<tbody id="list">

			</tbody>
		</table>
	</div>
	<script>
	let totalData = [];
		let url = `https://api.odcloud.kr/api/15077586/v1/centers?page=1&perPage=284&serviceKey=c2JhstVOrrGy%2BGLoB5vu1jz9bLdgK6sWOcqgK1Astx7Ioifonhu5v4Dg63oUFpM7xotQ8mLTkT0xydMIrCG6Hg%3D%3D`;

		let xhtp = new XMLHttpRequest();
		xhtp.open('get',url);
		xhtp.send();

		xhtp.onload = function(){
			let tbody = document.getElementById('list');
			let data = JSON.parse(xhtp.responseText);
			totalData = data.data;
			for(let i = 0; i < data.data.length; i++){
				tbody.appendChild(makeRow(data.data[i]));
			}
			let sidoAry = [];

			for(let sido of sidoAry){
				//<option value="서울특별시">서울특별시</option>
				let opt = document.createElement('option');
				opt.value = sido;
				opt.innerText = sidoAry[i];
				document.getElementById('search').appendChild(opt);
			}
			console.log(sidoAry);
		}
		// sido 정보 => sidoAry
		// totalData = > [{},{},{},{}...{}]


		let fields = ['id', 'centerName', 'phoneNumber', 'sido', 'zipCode'];
		function makeRow(obj={}){
			let tr = document.createElement('tr');
			for(let field of fields){
				let td = document.createElement('td');
				if(field == 'centerName'){
					let ahref = document.createElement('a');
					ahref.setAttribute('href', 'map.jsp?lat=' + obj.lat + '&lng=' + obj.lng + '&name=' + obj.centerName);
					ahref.target = "_blank";
					ahref.innerText = obj[field];
					td.appendChild(ahref);
					tr.appendChild(td);
				} else {
					td.innerText = obj[field];
					tr.appendChild(td);
				}
			}
			return tr;
		}

		document.getElementById('searchBtn').addEventListener('click', findFnc2);

			function findFnc2(){
				// 1.기준목록 clear 2.입력값과 비교(입력값:totalData) 3.tbody.appendChild(makeRow(obj))
				console.log(totalData);
				document.getElementById('list').innerHTML = "";

				for(let obj of totalData){
					if(obj.sido == document.getElementById('serach').value){
						document.getElementById('list').appendChild(makeRow(obj));
					}
				}
			}
			function findFnc1(){
				let trs = document.querySelectorAll('tbody tr');
				let ary = [];
				for(let tr of trs){
					if(tr.children[3].innerText == document.getElementById('serach').value){
							ary.push(tr);
					}
				}
				console.log(ary);
			 	document.getElementById('list').innerHTML = "";
				for(let tr of ary){
					document.getElementById('list').appendChild(tr);
				}
			}
	</script>
</body>
</html>