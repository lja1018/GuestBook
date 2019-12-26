<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록 수정</title>
</head>
<body>
<jsp:include page="/Header.jsp"/>

<br>
<form action='update' method='post'>
<input type='hidden' name='no' value='${requestScope.guestbook.no}'>
이메일 : <input type='text' name='email' value='${requestScope.guestbook.email}'><br>
비밀번호 : <input type='password' name='password'><br>
내용 : <input type='text' name='contents' value='${requestScope.guestbook.contents}'><br>
등록일 : ${requestScope.guestbook.createdDate}<br>
수정일 : ${requestScope.guestbook.modifiedDate}<br>
<input type='submit' value='저장'>
<input type='button' value='삭제' onclick='location.href="delete?no=${requestScope.guestbook.no}";'>
<input type='button' value='취소' onclick='location.href="list"'>
</form>
<br>

<jsp:include page="/Tail.jsp"/>
</body>
</html>