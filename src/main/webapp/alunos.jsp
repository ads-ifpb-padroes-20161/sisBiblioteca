<%-- 
    Document   : aluno.jsp
    Created on : 09/07/2016, 23:25:30
    Author     : kieckegard
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Aluno"%>
<%@page import="br.edu.ifpb.padroes.projeto.sisbiblioteca.model.QueryAlunoBo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>sisBiblioteca</title>
        <link rel="stylesheet" type="text/css" href="css/estilo.css">
        <script type="text/javascript" src='js/jquery-1.12.3.js'></script>
        <script type="text/javascript" src='js/jquery-mask.js'></script>
        <script type="text/javascript" src='js/script.js'></script>
        <link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">

        <!-- BOOTSTRAP-->

        <link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap.min.css">
        <script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>

        <!-- END OF BOOTSTRAP -->
    </head>
    <body>

        <%@include file="menus.jsp" %>
        <div class='content-container'>
            <!-- Aluno view -->
            <div class='cad-container cad-aluno-container'>
                <div class='cad-header'>
                    <h2>Cadastro de <span>Alunos</span></h2>
                </div>
                <div class='cad-content'>
                    <form id="formCadastroAluno" method="POST" action="CadastrarAluno">
                        <div class='inputs'>
                            <div class='group-form'>
                                <p class='form-title'>Pessoa</p>
                                <div class='group-input'>
                                    <input type="text" name="cpf" placeholder='CPF' required>
                                    <i class="fa fa-user" aria-hidden="true"></i>
                                </div>
                                <div class='group-input'>
                                    <input type="text" name="nome" placeholder='Nome' required>
                                    <i class="fa fa-font" aria-hidden="true"></i>
                                </div>

                                <div class='group-input'>
                                    <input type="text" name="dataNascimento" placeholder='Data de Nascimento' required>
                                    <i class="fa fa-calendar" aria-hidden="true"></i>
                                </div>
                            </div>
                            <div class='group-form'>
                                <p class='form-title'>Aluno</p>
                                <div class='group-input'>
                                    <input type="text" name="matricula" placeholder='Matrícula' required>
                                    <i class="fa fa-font" aria-hidden="true"></i>
                                </div>
                                <div class='group-input'>
                                    <input type="email" name="email" placeholder='E-mail' required>
                                    <i class="fa fa-envelope" aria-hidden="true"></i>
                                </div>
                            </div>
                            <div class='group-form'>
                                <p class='form-title'>Endereço</p>
                                <div class='group-input'>
                                    <input type="text" name="pais" placeholder='País' required>
                                    <i class="fa fa-map-marker" aria-hidden="true"></i>
                                </div>
                                <div class='group-input'>
                                    <input type="text" name="estado" placeholder='Estado' required>
                                    <i class="fa fa-map-marker" aria-hidden="true"></i>
                                </div>
                                <div class='group-input'>
                                    <input type="text" name="cidade" placeholder='Cidade'>
                                    <i class="fa fa-map-marker" aria-hidden="true"></i>
                                </div>
                                <div class='group-input'>
                                    <input type="text" name="bairro" placeholder='Bairro' required>
                                    <i class="fa fa-map-marker" aria-hidden="true"></i>
                                </div>
                                <div class='group-input'>
                                    <input type="text" name="rua" placeholder='Rua' required>
                                    <i class="fa fa-map-marker" aria-hidden="true"></i>
                                </div>
                                <div class='group-input'>
                                    <input type="number" name="numero" placeholder='Número' required>
                                    <i class="fa fa-map-marker" aria-hidden="true"></i>
                                </div>
                            </div>
                        </div>

                        <button type='submit' class='submit-button success'><i class="fa fa-user-plus" aria-hidden="true"></i> Cadastrar Aluno </button>
                    </form>

                    <c:if test="${requestScope.success == true}">
                        <div id="successMsg" class="alert alert-success" style="margin: 10px 10px 5px 10px;">
                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                            <strong>Success!</strong> Aluno cadastrado com sucesso!
                        </div>
                    </c:if>
                    <c:if test="${requestScope.success == false}">
                        <div id="errorMsg" class="alert alert-danger" style="margin: 10px 10px 5px 10px;">
                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                            <strong>Error!</strong> ${requestScope.errorMsg}
                        </div>
                    </c:if>
                </div>
            </div>
            <div class='cad-container cad-aluno-container'>
                <div class='cad-header'>
                    <h2>Alunos cadastrados</h2>
                </div>
                <div class='cad-content'>
                    <div class='panel panel-default'>
                        <table class='table table-hover'>
                            <thead>
                            <th>Matrícula</th>
                            <th>Nome</th>
                            <th>E-mail</th>
                            <th>Situação</th>
                            <th>Ações</th>
                            </thead>
                            <tbody>
                                <%
                                    QueryAlunoBo queryAlunoBo = new QueryAlunoBo();
                                    for (Aluno aluno : queryAlunoBo.listar()) {
                                        out.print("<tr>");
                                        out.print("<td>" + aluno.getMatricula() + "</td>");
                                        out.print("<td>" + aluno.getNome() + "</td>");
                                        out.print("<td>" + aluno.getEmail() + "</td>");
                                        if (aluno.getValorEstado() == 1) {
                                            out.print("<td class='light-green'>" + aluno.getDescricaoEstado() + "</td>");
                                        }
                                        else {
                                            out.print("<td class='light-red'>" + aluno.getDescricaoEstado() + "</td>");
                                        }
                                        out.print("<td><a href='RemoverAluno?matricula=" + aluno.getMatricula() + "'>Remover</a></td>");
                                        out.print("</tr>");
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <!-- END OF ALUNO VIEW -->

        </div>
    </body>
</html>
