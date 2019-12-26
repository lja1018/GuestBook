<%@page
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
${guestbook.no},
<a href='update?no=${guestbook.no}'>${guestbook.contents}</a>,
${guestbook.email},
${guestbook.createdDate}
<a href='delete?no=${guestbook.no}'>[삭제]</a><br>
</c:forEach>
<jsp:include page="/Tail.jsp"/>
</body>
</html>