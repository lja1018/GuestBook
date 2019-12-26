<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록</title>
</head>
<body>
<jsp:include page="/Header.jsp"/>

<p><a href='add'>방명록 등록</a></p>
<c:forEach var="guestbook" items="${guestbooks}">
${guestbook.email} <a href='update?no=${guestbook.no}'>[수정]</a><br>
<font size="6">${guestbook.contents}</font><br><br>
</c:forEach>

<jsp:include page="/Tail.jsp"/>
</body>
</html>