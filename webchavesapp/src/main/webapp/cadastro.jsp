<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cadastro de Usuário</title>
</head>
<body>
    <h2>Cadastro de Usuário</h2>
    <form action="CadastrarUsuarioServlet" method="POST">
   
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required><br>

    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br>

    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br>

    <label for="role">Role:</label>
    <select id="role" name="role" required>
        <option value="2">ROLE_USER</option>
        <option value="1">ROLE_ADMIN</option>
        <option value="3">ROLE_SELLER</option>
    </select><br>

    <input type="submit" value="Cadastrar">
</form>

    </form>
</body>
</html>
