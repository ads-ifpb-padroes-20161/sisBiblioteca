<%-- 
    Document   : menus
    Created on : 25/08/2016, 11:30:39
    Author     : kieckegard
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>sisBiblioteca</title>
        <link rel="stylesheet" type="text/css" href="css/estilo.css">
    </head>
    <body>
        <header>
            <a href="home.jsp"><h1>SisBiblioteca</h1></a>
        </header>
        <aside class='vertical-nav'>
            <p class='title'>Navigation</p>
            <ul class='navigation'>
                <c:if test="${requestScope.firstLogin != true}">
                    <li><a href="alunos.jsp"><i class="fa fa-user" aria-hidden="true"></i>  Alunos</a></li>
                    <li><a href="livros.jsp"><i class="fa fa-book" aria-hidden="true"></i> Livros</a></li>
                    <li><a href="emprestimos.jsp"><i class="fa fa-calendar-o" aria-hidden="true"></i> Empréstimos</a></li>
                    <li><a href="realizarEmprestimo.jsp"><i class="fa fa-calendar-plus-o" aria-hidden="true"></i> Novo Empréstimo</a></li>
                    <li><a href="minhaconta.jsp"><i class="fa fa-cog" aria-hidden="true"></i> Sua Conta</a></li>
                </c:if>
            </ul>

            <div class="profile">
                <c:if test="${requestScope.firstLogin != true}">
                    <p class="welcome-msg">Bem vindo, <span class="blue-title">${sessionScope.loggedUser.getNome()}</span></p>
                </c:if>
                <a class="submit-button danger" href="Logout">
                    <i class="fa fa-sign-out" aria-hidden="true"></i> Sair
                </a>
            </div>

        </aside>
    </body>
</html>
