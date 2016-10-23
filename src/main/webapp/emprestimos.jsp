<%-- 
    Document   : emprestimos
    Created on : 26/08/2016, 12:08:56
    Author     : kieckegard
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="br.edu.ifpb.bdnc.maisdenuncia.utils.DateUtils"%>
<%@page import="br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Emprestimo"%>
<%@page import="br.edu.ifpb.padroes.projeto.sisbiblioteca.model.EmprestimoBo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Empréstimos</title>
        <link rel="stylesheet" type="text/css" href="css/estilo.css">
        <link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">

        <!-- BOOTSTRAP-->

        <link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap.min.css">
        <script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>

        <!-- END OF BOOTSTRAP -->

    </head>
    <body>
        <%@include file="menus.jsp" %>
        <div class='content-container'>
            
            <%@include file="viewEmprestimos.jsp" %>

        </div>
    </body>
</html>

