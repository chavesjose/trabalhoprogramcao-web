package com.chaves.webap;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = {"/LoginServlet", "/home.jsp", "/index.jsp"})
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Inicialização do filtro (caso necessário)
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Verifica se a sessão contém o usuário logado
        Object user = httpRequest.getSession().getAttribute("username");

        // Obtém a URI da requisição
        String requestURI = httpRequest.getRequestURI();

        // Debug: imprimir se o usuário está autenticado e qual a URI acessada
        System.out.println("Requisição para: " + requestURI);
        System.out.println("Usuário logado na sessão: " + (user != null ? user : "Nenhum"));

        // Verifica se o usuário está logado e se está acessando a página de login
        if (user == null) {
            // O usuário não está autenticado
            if (requestURI.endsWith("index.jsp") || requestURI.contains("login")) {
                // Permite o acesso à página de login ou registro
                System.out.println("Acesso permitido à página de login ou registro.");
                chain.doFilter(request, response);
            } else {
                // Se não estiver autenticado e não for a página de login, redireciona para a página de login
                System.out.println("Usuário não autenticado, redirecionando para index.jsp.");
                httpResponse.sendRedirect("index.jsp"); // Redireciona para a página de login
            }
        } else {
            // O usuário está autenticado
            if (requestURI.endsWith("index.jsp") || requestURI.contains("login")) {
                // Se o usuário já estiver autenticado e tentar acessar a página de login, redireciona para home
                System.out.println("Usuário autenticado, redirecionando para home.jsp.");
                httpResponse.sendRedirect("home.jsp");
            } else {
                // Caso contrário, continua o processamento da requisição
                System.out.println("Usuário autenticado, permitindo o acesso.");
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {
        // Destruição do filtro (caso necessário)
    }
}
