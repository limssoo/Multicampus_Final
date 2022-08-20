<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Captcha Image</title>
</head>
<body>
	<img src="/captcha"><br>
	<form action="/captchacheck" method="post">
		<input type="text" name="userin">
		<input type="submit" value="전송">
	</form>
</body>
</html>