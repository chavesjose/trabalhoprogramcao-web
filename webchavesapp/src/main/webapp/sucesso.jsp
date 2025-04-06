<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro Realizado</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow p-4">
        <h2 class="text-success"><i class="fas fa-check-circle"></i> Produto Cadastrado com Sucesso!</h2>
        <p><strong>Nome:</strong> ${name}</p>
        <p><strong>Preço:</strong> R$ ${price}</p>
        <p><strong>Quantidade:</strong> ${qt}</p>
        <a href="cadastrarProduto.jsp" class="btn btn-primary">Voltar ao Cadastro</a>
    </div>
</div>

</body>
</html>
