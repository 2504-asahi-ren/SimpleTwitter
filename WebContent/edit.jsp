<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>つぶやきの編集</title>
		<link href="./css/style.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<c:if test="${ not empty errorMessages }">
			<div class="errorMessages">
				<ul>
					<c:forEach items="${errorMessages}" var="errorMessage">
						<li><c:out value="${errorMessage}" />
					</c:forEach>
				</ul>
			</div>
		</c:if>

		<div class="form-area">
			<form action="editMessage" method="post">
				つぶやき<br />
				<pre>
				<textarea name="text" cols="100" rows="5" class="tweet-box"><c:out value="${message.text}" /></textarea>
				</pre>
				 <input name="id" value="${message.id}" id="id" type="hidden" />
				<br /><input type="submit" value="更新">(140文字まで)
				<a href="./">戻る</a>
			</form>

			<div class="copyright">Copyright(c)Ren Asahi</div>
		</div>
	</body>
</html>