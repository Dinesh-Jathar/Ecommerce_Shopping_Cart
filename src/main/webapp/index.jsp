
<%@page import="com.shoppingcart.dto.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.shoppingcart.connection.Db_connection"%>
<%@page import="com.shoppingcart.dao.Product_Dao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
<%@include file="includes/index.css" %>

</style>

<%
 Product_Dao dao=new Product_Dao( Db_connection.getConnection());
 List <Product>list=dao.getAllProducts();
%>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="index.css">
<title>E-Commerce-Home</title>
</head>
<body >
<%@include file="includes/navbar.jsp" %>
<main>
    <div>
        <h1>All Products</h1>
    </div>
    <section>
    <%
      if(!list.isEmpty()){
    	 for(Product products:list){%>
    	        <div>
                <img src="product_images/<%=products.getProduct_img()%>" alt="">
                <h2><%=products.getProduct_name() %></h2>
                <h3>price: <%=products.getProduct_price()%>$</h3>
                <h3>catogery:<%=products.getProduct_catogery()%></h3>
                 <a href="Order?id=<%=products.getProduct_id()%>&&quantity=1"><button>buy</button></a><a href="AddToCart?id=<%=products.getProduct_id()%>"><button>addtocart</button></a>
            </div>
    	 <%}%>
    <%  }
    %>
    </section>
</main>
</body>
</html>