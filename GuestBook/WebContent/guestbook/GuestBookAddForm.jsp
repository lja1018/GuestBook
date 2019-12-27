<%@ page import="java.util.regex.Pattern" %>
<%@ page import="java.util.regex.Matcher" %>
<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록 등록</title>
</head>
<body>
<jsp:include page="/Header.jsp"/>

<br>
<form action='add' method='post'>
<input type='text' name='email' pattern="[a-zA-Z0-9!#$%&'*+\/=?^_`{|}~.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*" required
		placeholder="이메일" maxlength="40"><br><br>
<input type='password' name='password' placeholder="비밀번호" maxlength="100"><br><br>
<textarea name='contents' cols=70 rows=4 placeholder="내용" maxlength="148"></textarea><br><br>
<input type='submit' value='추가'>
<input type='reset' value='취소'>
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