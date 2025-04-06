<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestão de Produtos</title>
    <!-- Incluir o CSS do Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">


</head>
<body>
    <div class="container mt-5">
        <h1 style="font-size:30px;">Gestão de Artigos</h1><hr>
        
       
       
       <a href="index.jsp" class="btn btn-danger btn-sm" style="width:100px;float:right; margin-right:2%; margin-top: -8%; "> <i class="fas fa-sign-in-alt"></i>Log-Out</a>
        
       
        <br>  <br>  
        <!-- Verificar se há produtos -->
        <c:if test="${not empty produtos}">
            <div class="row">
                <!-- Iterar sobre a lista de produtos -->
                <c:forEach var="produto" items="${produtos}">
                    <div class="col-md-4 mb-4">
                        <div class="card">
                            
                            <div class="card-body">
                                 <p class="card-text">ID: ${produto.id}</p><hr>
                                <h5 class="card-title">${produto.name}</h5><hr>
                                <p class="card-text">Preço: MZN ${produto.price}</p><hr>
                           
                                    <p class="card-text">Quant: ${produto.qt}</p>
                                <!-- Botão de adicionar -->
                                
                                 <a href="editarProduto?id=${produto.id}" class="btn btn-primary">Editar</a>
                                 <a href="addProduct?id=${produto.id}" class="btn btn-primary">Estoque</a>
                                 <a href="deleteProduct?id=${produto.id}" class="btn btn-danger"onclick="return confirm('Tem certeza que deseja apagar este produto?');">Apagar</a>
                                 
                           
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:if>
        
        <c:if test="${empty produtos}">
            <p>Não há produtos disponíveis.</p>
        </c:if>
    </div>

    <!-- Incluir o JavaScript do Bootstrap -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
