document.querySelectorAll('#page-content-wrapper').forEach(function(tbl){
	/*tbl.innerHTML = "";*/
});
document.querySelectorAll('#page-content-wrapper h3').forEach(function(elem){
	/*elem.remove();*/
});

document.getElementById('userInput').addEventListener('change', function(e){
	let date = document.querySelector('#userInput').value;

	let span = document.createElement('span');
	span.addEventListener('click',function(e){
		e.target.remove();
		console.log(e.target);
	})
	let div = document.querySelector('.outer');
	span.innerText = date;

	div.appendChild(span);
})
