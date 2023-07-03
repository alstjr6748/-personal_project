document.querySelectorAll('#page-content-wrapper').forEach(function(tbl){
	/*tbl.innerHTML = "";*/
});
document.querySelectorAll('#page-content-wrapper h3').forEach(function(elem){
	/*elem.remove();*/
});

/*document.getElementById('userInput').addEventListener('change', function(e){
	let date = document.querySelector('#userInput').value;

	let span = document.createElement('span');
	span.addEventListener('click',function(e){
		e.target.remove();
		console.log(e.target);
	})
	if(date == span){

		console.log(date);
	}
	let div = document.querySelector('.outer');
	span.innerText = date;

	div.appendChild(span);
})*/

let secs = 10;
let job = setInterval(function(){
	document.getElementById('time').innerText = secs--;
	if(secs < 0){
		clearInterval(job)
	}
}, 1000);


const str = `Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ullam deleniti odio incidunt sapiente voluptatibus quod omnis temporibus optio dolor assumenda autem eos soluta alias ipsam, saepe fugiat corrupti doloremque mollitia.`

const strAry = str.split(' ');

let outer = document.querySelector('div.outer');
strAry.forEach(function(word){
	let span = document.createElement('span');
	span.addEventListener('click',function(e){
		e.target.remove();
	})
	span.innerText = word;
	outer.appendChild(span);
})

/*document.getElementById('userInput').addEventListener('change', function(e){
	let userVal = this.value;
	document.querySelectorAll('.outer span').forEach(function(item){
		if(item.innerText == userVal){
			item.remove();
		}
	})
	this.value = "";
})*/

document.getElementById('userInput').addEventListener('change', function(e){
	let span = document.querySelectorAll('.outer span');
	let val = document.getElementById('userInput').value;
	for(let serach of span){
		if(val == serach.innerText){
			serach.remove();
		}
	}
})

