<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Product Details</title>
</head>
<body>
    <h1>Teste Java EE</h1>

    <%
        com.chaves.webap.model.product p = (com.chaves.webap.model.product) request.getAttribute("product");
        if (p != null) {
    %>
        <h2>Product Details</h2>
        <p><strong>ID:</strong> <%= p.getId() %></p>
        <p><strong>Name:</strong> <%= p.getName() %></p>
        <p><strong>Price:</strong> <%= p.getPrice() %></p>
    <%
        } else {
    %>
        <p>No product found. Please provide a valid ID.</p>
    <%
        }
    %>

    <form action="getwebapController" method="GET">
        <label for="id">Enter Product ID:</label>
        <input type="text" id="id" name="id">
        <button type="submit">Search</button>
    </form>
</body>
</html>
