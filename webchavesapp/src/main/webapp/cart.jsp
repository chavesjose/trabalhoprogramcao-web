<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Carrinho de Compras</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        /* CSS para esconder o título do Carrinho */
       .hidden {
           font-size: 19px;
        }
        
        /* Estilo para o card */
        .card {
    width: 50%;
    margin-top: 5%;
    margin-left: 30%;
    
    @media (max-width: 768px) {
    .card {
        width: 100%; /* Faz o cartão ocupar 100% da largura */
        margin-left: 0; /* Remove a margem esquerda */
        margin-top: 10%; /* Ajuste a margem superior se necessário */
    }
}
        
}
        /* Botão pequeno alinhado à direita */
        .btn-success.btn-sm {
            float: right;
        }

        /* Estilo para a tabela sem bordas */
        .table-borderless td,
        .table-borderless th {
            border: none;
        }

        /* Estilo opcional para o botão "Produtos" */
        .btn-primary {
            width: 200px;
             margin-left: 90%;
        }

        /* Remover o espaçamento extra do table */
        table.table {
            margin-bottom: 0;
        }
    </style>
    </style>
</head>
<body><div class="card">
<a href="homeprodutos" class="btn btn-primary btn-sm" style="width:50px;">+</a>

    <div class="container mt-4">
        <!-- Carrinho de Compras: Título oculto com a classe .hidden -->
        <h3 class="hidden">Carrinho de Compras</h3>

        <!-- Verificar se há produtos no carrinho -->
        <c:if test="${not empty cart}">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Nome</th>
                        <th scope="col">Preço</th>
                        <th scope="col">Ação</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Iterar sobre os produtos no carrinho -->
                    <c:forEach var="produto" items="${cart}">
                        <tr>
                            <td>${produto.id}</td>
                            <td>${produto.name}</td>
                            <td>${produto.price}</td>
                            <td>
                                <!-- Adicionar botão para remover o produto -->
                                <a href="removeProduct?id=${produto.id}" class="btn btn-danger">Remover</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <!-- Exibir total do carrinho -->
            <c:set var="total" value="0" />
            <c:forEach var="produto" items="${cart}">
                <c:set var="total" value="${total + produto.price}" />
            </c:forEach>
<hr>
            <h3>Total: ${total}</h3>

            <!-- Botão para finalizar a compra (pode redirecionar para uma página de checkout) -->
            <a href="checkout.jsp" class="btn btn-success">Finalizar Compra</a>
        </c:if>

        <!-- Caso não tenha produtos no carrinho -->
        <c:if test="${empty cart}">
            <p>O seu carrinho está vazio.</p>
            <a href="homeprodutos" class="btn btn-success">Voltar para a loja</a>
        </c:if>
        
        <hr>
    </div>
</div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.6/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
