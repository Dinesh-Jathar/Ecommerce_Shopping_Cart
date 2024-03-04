<%@page import="com.shoppingcart.connection.Db_connection"%>
<%@page import="com.shoppingcart.dao.Product_Dao"%>
<%@page import="com.shoppingcart.dto.Cart"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%
User userAuth= (User)session.getAttribute("auth");
session.setAttribute("userAuth",userAuth);

  ArrayList<Cart> list=(ArrayList<Cart>)session.getAttribute("cartList");
  List<Cart>products=null;
  if(list!=null){
	  Product_Dao dao=new Product_Dao(Db_connection.getConnection());
	  products=dao.getCartProducts(list);
	  double total= dao.getCartPrice(list);
	  System.out.println(list);
	  session.setAttribute("cartList", list);
	  session.setAttribute("total", total);
  }
%>
<style>
<%@include file="includes/index.css" %>
</style>
<script src="https://kit.fontawesome.com/c404421f7f.js" crossorigin="anonymous"></script>
<title>E-Cart</title>

</head>
<body>

<%@include file="includes/navbar.jsp" %>
   <div class="cart-top">
        <h1>TotalPrice : $ ${(total>0)?total:0}</h1>
        <h1></h1>
         <a href="CheckOutServlet"><button class="btn">CheckOut</button></a>
    </div>
    <table>
    <tr>
        <th>Name</th>
        <th>Catogery</th>
        <th>Price</th>
        <th>Quantity</th>
        <th>Buy</th>
        <th>Remove</th>
    </tr>
    <%
    if(list!=null){%>
    <%for(Cart items:products){ %>
    		 <tr>
             <td><%=items.getProduct_name()%></td>
             <td><%=items.getProduct_catogery()%></td>
             <td>$<%=items.getProduct_price()%></td>
             <td>
                 <form action="Quantity" method="get">
                    <a href="Quantity?action=dec&id=<%=items.getProduct_id()%>"><i class="fa-solid fa-minus cart-btn"></i></a>
                   <input type="text" readonly value="<%=items.getQuantity()%>" class="quan">
                     <a href="Quantity?action=inc&id=<%=items.getProduct_id()%>"><i class="fa-solid fa-plus cart-btn"></i></a>
                 </form>
             </td>
             <td><a href="Order?id=<%=items.getProduct_id()%>&&quantity=<%=items.getQuantity()%>"><button style="background-color:blue">Buy</button></a></td>
             <td>
                 <form action="RemoveCart">
                    <button value="<%=items.getProduct_id()%>" name="remove">Remove</button>
                 </form>
             </td>
         </tr>
   <% }
    }
    %>
    </table>
</body>
</html>