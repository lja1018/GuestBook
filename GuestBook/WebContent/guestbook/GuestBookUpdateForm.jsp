<%@ page import="java.util.regex.Pattern" %>
<%@ page import="java.util.regex.Matcher" %>
<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<input type='text' name='email' placeholder="이메일" value='${requestScope.guestbook.email}' maxlength="40" readonly="readonly"><br><br>
<input type='hidden' name='existpassword' value='${requestScope.guestbook.password}'>
<input type='password' name='inputpassword' placeholder="비밀번호" maxlength="100"><br><br>
<textarea name='contents' cols=70 rows=4 maxlength="148">${requestScope.guestbook.contents}</textarea><br><br>
등록일 : ${requestScope.guestbook.createdDate}<br><br>
수정일 : ${requestScope.guestbook.modifiedDate}<br><br>
<input type='submit' value='수정'>
<input type='button' value='삭제' onclick='location.href="delete?no=${requestScope.guestbook.no}"'>
<input type='button' value='취소' onclick='location.href="list"'>
</form>
<br>

<jsp:include page="/Tail.jsp"/>
</body>
</html>

<%!
public static boolean isValidEmail(String email) {
	boolean ret = false;
	String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
	Pattern p = Pattern.compile(regex);
	Matcher m = p.matcher(email);
	
	if(m.matches()) ret = true;

	return ret;
}
%>