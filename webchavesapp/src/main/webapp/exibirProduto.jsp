<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Produtos</title>
    <!-- Incluir o CSS do Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">


</head>
<body>
    <div class="container mt-5">
        <h1 style="font-size:30px;">Loja: Amigo-X</h1>
        
        <a href="cart.jsp" class="btn btn-success btn-sm" style="width:100px;float:right; margin-right:5% "><i class="fas fa-shopping-cart"></i>Carinho</a>
       
       
       <a href="index.jsp" class="btn btn-danger btn-sm" style="width:100px;float:right; margin-right:2% "> <i class="fas fa-sign-in-alt"></i>Login</a>
        <br>  <br>  
       
        <br>  <br>  
        <!-- Verificar se há produtos -->
        <c:if test="${not empty produtos}">
            <div class="row">
                <!-- Iterar sobre a lista de produtos -->
                <c:forEach var="produto" items="${produtos}">
                    <div class="col-md-4 mb-4">
                        <div class="card">
                            
                            <div class="card-body">
                                <h5 class="card-title">${produto.name}</h5>
                                <p class="card-text">Preço: MZN ${produto.price}</p>
                                <p class="card-text d-none">ID: ${produto.id}</p>
                                <!-- Botão de adicionar -->
                                
                                 <a href="addProduct?id=${produto.id}" class="btn btn-primary">Adicionar</a>
                                
                           
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:if>
        
        <c:if test="${empty produtos}">
            <a href="homeprodutos" class="btn btn-success">Produtos</a>
        </c:if>
    </div>

    <!-- Incluir o JavaScript do Bootstrap -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
