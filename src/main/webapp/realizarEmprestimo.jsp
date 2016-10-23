<%-- 
    Document   : realizarEmprestimo
    Created on : 25/08/2016, 11:29:34
    Author     : kieckegard
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="br.edu.ifpb.bdnc.maisdenuncia.utils.DateUtils"%>
<%@page import="br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Emprestimo"%>
<%@page import="br.edu.ifpb.padroes.projeto.sisbiblioteca.model.EmprestimoBo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%!
    EmprestimoBo queryBo = new EmprestimoBo();
    List<Emprestimo> emprestimos = queryBo.listarEmprestimos();
%>

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


            <c:if test="${requestScope.success == true}">
                <div class="centered-container">
                    <div id="successMsg" class="alert alert-success" style="margin: 10px 10px 5px 10px;">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <strong>Success!</strong> Emprestimo realizado com sucesso, o aluno deverá devolver o livro na data ${requestScope.emprestimoEndDate}
                    </div>
                </div>
            </c:if>
            <c:if test="${requestScope.success == false}">
                <div class="centered-container">
                    <div id="errorMsg" class="alert alert-danger" style="margin: 10px 10px 5px 10px;">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <strong>Error!</strong> ${requestScope.errorMsg}
                    </div>
                </div>
            </c:if>


            <%@include file="novoEmprestimo.jsp" %>

        </div>
    </body>
</html>
