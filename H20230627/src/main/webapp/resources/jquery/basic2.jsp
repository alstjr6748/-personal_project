<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
	<button>요소제거</button><br>
	<input id="userInput"><button id="addBtn">추가</button>
	<ul>
		<li>Apple</li>
		<li>Banana</li>
		<li>Cherry</li>
	</ul>
</body>
<script>
$(document).ready(function(){
	$('li:nth-child(1)').css('background','yellow');

	let li = $('<li />').text('Melon');

	$('ul').append(li);	//ul요소의 하위에 li 요소 추가

	// 버튼추가
	$('li').append($('<button />').text('삭제').on('click', delBtn));
	function delBtn(e){
		console.log(e);
		// e.currentTarget : DOM
		// $(e.currentTarget) : jquery 객체
		$(e.currentTarget).parent().remove();
	}

	// 이벤트:on
	//$('button').on('click', function(){
	//	$('li:nth-child(1)').remove();
	//});

	$('#addBtn').on('click', function(e){
		let li = $('<li />').text($('#userInput').val()).append($('<button />').text('삭제').on('click', delBtn));
		$('ul').append(li);  //뒤에 추가함
		//$('ul').prepend(li); 앞에 추가함
		//init
		$('#userInput').val('');
	})
})
</script>
</html>