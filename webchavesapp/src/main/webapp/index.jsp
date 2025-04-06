<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <style>
        /* Estilo para o corpo da página */
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        /* Container do formulário */
        .container {
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 30px;
            width: 100%;
            max-width: 400px;
        }

        /* Título do formulário */
        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }

        /* Estilo para os labels */
        label {
            font-size: 14px;
            color: #555;
            margin-bottom: 5px;
            display: block;
        }

        /* Estilo para os campos de entrada */
        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 16px;
        }

        /* Botão de envio */
        button[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        /* Estilo para o botão ao passar o mouse */
        button[type="submit"]:hover {
            background-color: #45a049;
        }

        /* Mensagem de erro */
        .error-message {
            color: red;
            text-align: center;
        }

    </style>
</head>
<body>

    <div class="container">
        <h2>Login</h2>
        
        <form action="LoginServlet" method="POST">
            <label for="username">Usuário:</label>
            <input type="text" id="username" name="username" required>
            <br><br>
            
            <label for="password">Senha:</label>
            <input type="password" id="password" name="password" required>
            <br><br>
            
            <button type="submit">Entrar</button>
        </form>
        
        <br>
        <!-- Exibe mensagem de erro caso as credenciais estejam incorretas -->
        <c:if test="${param.error == 'true'}">
            <p class="error-message">Credenciais inválidas, tente novamente.</p>
        </c:if>
        <!-- Exibe mensagem de erro caso o campo de login esteja vazio -->
        <c:if test="${param.error == 'empty_fields'}">
            <p class="error-message">Por favor, preencha todos os campos.</p>
        </c:if>
        <!-- Exibe mensagem de erro caso o método da requisição seja inválido -->
        <c:if test="${param.error == 'invalid_method'}">
            <p class="error-message">Método de requisição inválido. Apenas POST é permitido.</p>
        </c:if>
    </div>

</body>
</html>
