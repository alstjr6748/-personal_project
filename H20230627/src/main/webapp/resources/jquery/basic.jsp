<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Jquery basic</title>
<style>
	p:nth-child(2){
		color: red;
	}
</style>
<script src = "jquery-3.7.0.min.js"></script>
</head>
<body>
<p>Hello, World1</p>
<p>Hello, World2</p>
<script>
	$('p').css('background', 'yellow');
	$('p:nth-child(1)').after($('<p />').text('Hello,Wordl3'));
</script>
</body>
</html>