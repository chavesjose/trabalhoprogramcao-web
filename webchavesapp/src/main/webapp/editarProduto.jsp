<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <title>Editar Produto</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2>Editar Produto</h2>
        <form action="editar-produto" method="post">
            <input type="hidden" name="id" value="${produto.id}">
            
            <div class="mb-3">
                <label for="name" class="form-label">Nome</label>
                <input type="text" class="form-control" id="name" name="name" value="${produto.name}" required>
            </div>

            <div class="mb-3">
                <label for="price" class="form-label">Preço</label>
                <input type="number" step="0.01" class="form-control" id="price" name="price" value="${produto.price}" required>
            </div>

            <div class="mb-3">
                <label for="qt" class="form-label">Quantidade</label>
                <input type="number" class="form-control" id="qt" name="qt" value="${produto.qt}" required>
            </div>

            <button type="submit" class="btn btn-primary">Atualizar</button>
            <a href="index.jsp" class="btn btn-secondary">Cancelar</a>
        </form>
    </div>
</body>
</html>
