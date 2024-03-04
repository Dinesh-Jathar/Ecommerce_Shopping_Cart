
<%@page import="com.shoppingcart.connection.Db_connection"%>
<%@page import="com.shoppingcart.dao.Product_Dao"%>
<%@page import="com.shoppingcart.dto.Cart"%>
<%@page import="java.util.*"%>
<%@page import="com.shoppingcart.dto.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
  User user= (User) session.getAttribute("auth");
    System.out.println(user);
    if(user!=null){
    	request.setAttribute("auth", user);
    }
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="index.css">
<title>Insert title here</title>
</head>
<%
ArrayList<Cart> cartList=(ArrayList<Cart>)session.getAttribute("cartList");
if(cartList!=null){
	  session.setAttribute("cartList", cartList);
}
%>
<body >
<nav>
 <div class="left">
            <a href="index.jsp"><img src="product_images/logo.jpg" alt="img"></a>
   </div>
        <div class="center">
            <ul id="cont">
            <li><a href="index.jsp">Home</a></li>
            <li><a href="cart.jsp">Cart</a><%
            		if(cartList != null && cartList.size() != 0) { %><span class="cart-count">${cartList.size()}</span> <%}%></li>
            <% if(user!=null){%>
            
             <li><a href="orders.jsp">Orders</a></li>
             <li><a href="Logout" >LogOut</a></li>
            <% }else{%>
                </ul>
                </div>
                <div class="right">
                    <a href="login.jsp" id="login"><button>LogIn</button></a>
                </div>
         <%} %>
</nav>
</html>