<%@page import="com.shoppingcart.dto.Order"%>
<%@page import="com.shoppingcart.dao.OrderDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%
User usertocheck = (User) request.getSession().getAttribute("auth");
List<Order>listOfOrder=null;
if(usertocheck!=null){
	listOfOrder=new OrderDao(Db_connection.getConnection()).userOrder(usertocheck.getUserName());
}else{
	response.sendRedirect("login.jsp");
}
%>
<style>
<%@include file="includes/index.css" %>
</style>
<meta charset="ISO-8859-1">
<title>Orders</title>
</head>
<body>
<Style>
h1{
text-align: center;
font-size: 3rem;
text-transform: uppercase;
}
</Style>
<%@include file="includes/navbar.jsp" %>
<h1>orders</h1>
</body>
    <table>
    <tr>
        <th>Date</th>
        <th>Name</th>
        <th>Catogery</th>
        <th>Price</th>
        <th>Quantity</th>
        <th>Cancel</th>
    </tr>    
     <% if(listOfOrder!=null) {
      for(Order o:listOfOrder){%>
      <tr>
    	 <td><%=o.getDate()%></td>
    	 <td><%=o.getProduct_name()%></td>
    	 <td><%=o.getProduct_catogery()%></td>
    	 <td><%=o.getProduct_price()%></td>
    	 <td><%=o.getQuantity()%></td>
    	 <td><a href="RemoveOrder?orderId=<%= o.getOrderId()%>"><button>Remove</button></a></td>
    	 </tr>
  <% }
    } %>
    </table>
</html>