<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastrar Produto</title>
    
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            max-width: 500px;
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
            margin-top: 50px;
        }
        .form-control {
            margin-bottom: 15px;
        }
    </style>
</head>
<body>

<div class="container">
    <h2 class="text-center mb-4"><i class="fas fa-box"></i> Cadastrar Produto</h2>

    <form action="cadastroprodutos" method="POST">
        <!-- Nome do Produto -->
        <div class="mb-3">
            <label for="name" class="form-label">Nome do Produto</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>

        <!-- Preço -->
        <div class="mb-3">
            <label for="price" class="form-label">Preço (R$)</label>
            <input type="number" class="form-control" id="price" name="price" step="0.01" required>
        </div>

        <!-- Quantidade -->
        <div class="mb-3">
            <label for="qt" class="form-label">Quantidade</label>
            <input type="number" class="form-control" id="qt" name="qt" required>
        </div>

        <!-- Botão de Cadastro -->
        <button type="submit" class="btn btn-success w-100">
            <i class="fas fa-save"></i> Cadastrar Produto
        </button>
    </form>
</div>

</body>
</html>
