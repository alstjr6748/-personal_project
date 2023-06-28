console.log("main2.js");
// 버튼 - 클릭 이벤트 등록
document.getElementById('saveBtn').addEventListener('click', function(e){
	let name = document.getElementById('name').value;
	let age = document.getElementById('age').value;
	let score = document.getElementById('score').value;

	let obj = {
		name : name,
		age : age,
		score : score
	}

	let tr = document.createElement('tr');

	for(let prop in obj){
		let td = document.createElement('td');
		td.innerText = obj[prop];
		tr.appendChild(td); // tr 안에 td 안에 name 값을 넣음.
	}
	// 삭제버튼
	let td = document.createElement('td');
	let btn = document.createElement('button');
	btn.innerText = '삭제';
	// 버튼 삭제 이벤트
	btn.addEventListener('click', function(e){
		e.target.parentElement.parentElement.remove();
	});
	td.appendChild(btn);
	tr.appendChild(td);



	/*td = document.createElement('td');
	td.innerText = age;
	tr.appendChild(td); // tr 안에 td 안에 age 값을 넣음.

	td = document.createElement('td');
	td.innerText = score;
	tr.appendChild(td); // tr 안에 td 안에 score 값을 넣음.*/

	console.log(tr);

	document.getElementById('target').appendChild(tr);

	// 초기화작업(값을 빼고 싶을때)
	document.getElementById('name').value = "";
	age = document.getElementById('age').value = "";
	score = document.getElementById('score').value = "";
	document.getElementById('name').focus();

});