<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <title>Finalizar Compra</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            padding: 40px;
        }

        .container {
            max-width: 700px;
            background: white;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }

        h2 {
            margin-bottom: 30px;
            text-align: center;
        }

        .total {
            font-size: 1.3rem;
            font-weight: bold;
            margin-top: 20px;
        }

        .btn-block {
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Finalizar Compra</h2>

    <c:if test="${not empty cart}">
        <!-- Tabela de produtos no carrinho -->
        <table class="table table-bordered">
            <thead class="thead-light">
                <tr>
                    <th>Produto</th>
                    <th>Preço (MT)</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="total" value="0" />
                <c:forEach var="produto" items="${cart}">
                    <tr>
                        <td>${produto.name}</td>
                        <td>
                            <fmt:formatNumber value="${produto.price}" type="number" minFractionDigits="2"/> MT
                        </td>
                    </tr>
                    <c:set var="total" value="${total + produto.price}" />
                </c:forEach>
            </tbody>
        </table>

        <!-- Total -->
        <div class="total">
            Total: <fmt:formatNumber value="${total}" type="number" minFractionDigits="2"/> MT
        </div>

        <hr>

        <!-- Formulário para finalizar a compra -->
        <form action="finalizarPedido" method="post">
            <div class="form-group">
                <label for="nome">Nome Completo</label>
                <input type="text" class="form-control" id="nome" name="nome" required>
            </div>

            <div class="form-group">
                <label for="endereco">Endereço de Entrega</label>
                <textarea class="form-control" id="endereco" name="endereco" rows="3" required></textarea>
            </div>

            <div class="form-group">
                <label for="pagamento">Forma de Pagamento</label>
                <select class="form-control" id="pagamento" name="pagamento" required>
                    <option value="">Selecione</option>
                    <option value="cartao">Cartão de Crédito</option>
                    <option value="boleto">E-mola</option>
                    <option value="pix">Mpesa</option>
                </select>
            </div>

            <button type="submit" class="btn btn-success btn-block">Confirmar Pedido</button>
        </form>
    </c:if>

    <c:if test="${empty cart}">
        <p class="text-center">Seu carrinho está vazio.</p>
        <div class="text-center">
            <a href="homeprodutos" class="btn btn-primary">Voltar para Loja</a>
        </div>
    </c:if>
</div>

<!-- Script do Bootstrap para funcionalidades (se necessário) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.7/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
