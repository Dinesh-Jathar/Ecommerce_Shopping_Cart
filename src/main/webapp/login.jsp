
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
<%User checkUser = (User) request.getSession().getAttribute("auth");
if(checkUser!=null){
	response.sendRedirect("index.jsp");
}%>
<%@include file="includes/index.css" %>

</style>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
<title>Log-In-Page</title>
</head>
<body>
<%@include file="includes/navbar.jsp" %>
    <section>
        <div class="log">
            <form action="user-login" method="post">
            <label for="email"> Enter email</label><br>
            <input type="email" required name="email"><br>
            <label for="password">Enter password</label><br>
            <input type="password" required name="password"><br>
            <button>Log in</button><br>
            <a href="SignIn.jsp">New User? Create Account</a>
        </form>
        </div>
    </section>
    
</body>
</html>