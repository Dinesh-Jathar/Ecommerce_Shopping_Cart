<%@page import="com.shoppingcart.connection.Db_connection"%>
<%@page import="java.sql.*"%>
<%@page import="java.io.IOException"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>

<%@include file="includes/index.css" %>

</style>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
<title>SignUp</title>
</head>
<body>
<%@include file="includes/navbar.jsp" %>
   <section>
        <div>
            <h2>Sign In</h2>
            <form action="SignIn" method="post">
            <label for="name">Enter Name</label>
            <input type="text" required name="name">
            <label for="mobile">Mobile No</label>
            <input type="number" name="mobNo" maxlength="10" required >
            <label for="email"> Enter email</label><br>
            <input type="email" required name="email"><br>
            <label for="password">Enter password</label><br>
            <input type="password" required name="password"><br>
            <button>Log in</button><br>
            <a href="login.jsp">Already Have Account?</a>
        </form>
        </div>
    </section>
</body>
</html>