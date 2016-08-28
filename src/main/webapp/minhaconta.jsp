<%-- 
    Document   : minhaconta
    Created on : 27/08/2016, 01:55:49
    Author     : kieckegard
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="br.edu.ifpb.padroes.projeto.sisbiblioteca.utils.DateUtils"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="css/estilo.css">
        <link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">
        <script type="text/javascript" src='js/jquery-1.12.3.js'></script>
        <script type="text/javascript" src='js/jquery-mask.js'></script>
        <script type="text/javascript" src='js/script.js'></script>
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
                    <div id="successMsg" class="alert alert-success">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <strong>Error!</strong> Dados atualizados com sucesso!
                    </div>
                </div>
            </c:if>
            <c:if test="${requestScope.success == false}">
                <div class="centered-container">
                    <div id="successMsg" class="alert alert-danger">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <strong>Error!</strong> ${requestScope.errorMsg}
                    </div>
                </div>
            </c:if>

            <c:if test="${requestScope.firstLogin == true}">
                <%@include file="cadastroNovoUsuario.jsp" %>
            </c:if>
            <c:if test="${requestScope.firstLogin == null}">
                <!-- My Account view -->
                <div class='cad-container cad-aluno-container'>
                    <div class='cad-header'>
                        <h2>Sua Conta de <span>Usuário</span></h2>
                    </div>
                    <div class='cad-content'>
                        <form id="formUserAccount" method="POST" action="AlterarConta">
                            <div class='inputs'>
                                <div class='group-form'>
                                    <p class='form-title'>Pessoa</p>
                                    <div class='group-input'>
                                        <input type="text" name="cpf" placeholder='CPF' value="${sessionScope.loggedUser.getCpf()}" required readonly>
                                        <i class="fa fa-user" aria-hidden="true"></i>
                                    </div>
                                    <div class='group-input'>
                                        <input type="text" name="nome" placeholder='Nome' value="${sessionScope.loggedUser.getNome()}" required readonly>
                                        <i class="fa fa-font" aria-hidden="true"></i>
                                    </div>

                                    <div class='group-input'>
                                        <input type="text" name="dataNascimento" placeholder='Data de Nascimento' value="${DateUtils.formatToBrazilPattern(loggedUser.getDataNascimento())}" required readonly>
                                        <i class="fa fa-calendar" aria-hidden="true"></i>
                                    </div>
                                </div>
                                <div class='group-form'>
                                    <p class='form-title'>Bibliotecário</p>
                                    <div class='group-input'>
                                        <input type="text" name="matricula" value="${sessionScope.loggedUser.getMatricula()}" placeholder='Matrícula' required readonly>
                                        <i class="fa fa-font" aria-hidden="true"></i>
                                    </div>
                                    <div class='group-input'>
                                        <input type="password" name="senha" placeholder='Senha' value="${sessionScope.loggedUser.getSenha()}" required readonly>
                                        <i class="fa fa-envelope" aria-hidden="true"></i>
                                    </div>
                                </div>
                                <div class='group-form'>
                                    <p class='form-title'>Endereço</p>
                                    <input type="text" name="idEndereco" value="${sessionScope.loggedUser.getEndereco().getId()}" style="display: none;">
                                    <div class='group-input'>
                                        <input type="text" name="pais" placeholder='País' value="${sessionScope.loggedUser.getEndereco().getPais()}" required readonly>
                                        <i class="fa fa-map-marker" aria-hidden="true"></i>
                                    </div>
                                    <div class='group-input'>
                                        <input type="text" name="estado" placeholder='Estado' value="${sessionScope.loggedUser.getEndereco().getEstado()}" required readonly>
                                        <i class="fa fa-map-marker" aria-hidden="true"></i>
                                    </div>
                                    <div class='group-input'>
                                        <input type="text" name="cidade" placeholder='Cidade' value="${sessionScope.loggedUser.getEndereco().getCidade()}" readonly>
                                        <i class="fa fa-map-marker" aria-hidden="true"></i>
                                    </div>
                                    <div class='group-input'>
                                        <input type="text" name="bairro" placeholder='Bairro' value="${sessionScope.loggedUser.getEndereco().getBairro()}" required readonly>
                                        <i class="fa fa-map-marker" aria-hidden="true"></i>
                                    </div>
                                    <div class='group-input'>
                                        <input type="text" name="rua" placeholder='Rua' value="${sessionScope.loggedUser.getEndereco().getRua()}" required readonly> 
                                        <i class="fa fa-map-marker" aria-hidden="true"></i>
                                    </div>
                                    <div class='group-input'>
                                        <input type="number" name="numero" placeholder='Número' value="${sessionScope.loggedUser.getEndereco().getNumero()}" required readonly>
                                        <i class="fa fa-map-marker" aria-hidden="true"></i>
                                    </div>
                                </div>
                            </div>

                            <ul class='list-buttons'>
                                <li>
                                    <button type='button' onclick='changeInfo();' class='submit-button success'><i class="fa fa-pencil-square-o" aria-hidden="true"></i> Alterar informações </button>
                                </li>
                                <li>
                                    <button id='btnUpdate' type='submit' class='submit-button success' disabled><i class="fa fa-floppy-o" aria-hidden="true"></i> Salvar Alterações </button>
                                </li>
                                <li>
                                    <a id="btnRemove" href="DeletarConta?matricula=${sessionScope.loggedUser.getMatricula()}" class='submit-button danger'><i class="fa fa-trash" aria-hidden="true"></i> Excluir Conta </a>
                                </li>
                            </ul>
                        </form>             
                    </div>
                </div>    
            </c:if>
        </div>
    </body>
    <script>
        function changeInfo() {
            $('#formUserAccount input').each(function () {
                if ($(this).attr("name") !== "matricula" && $(this).attr("name") !== "cpf")
                    $(this).attr("readonly", false);

            });
            $('#btnUpdate').attr("disabled", false);
            $('#btnRemove').attr("disabled", true);
        }
    </script>
</html>
