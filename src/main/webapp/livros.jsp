<%-- 
    Document   : livros
    Created on : 10/07/2016, 03:00:56
    Author     : kieckegard
--%>
<%@page import="br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.livro.Livro"%>
<%@page import="br.edu.ifpb.padroes.projeto.sisbiblioteca.model.LivroBo"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>sisBiblioteca</title>
        <link rel="stylesheet" type="text/css" href="css/estilo.css">
        <script type="text/javascript" src='js/jquery-1.12.3.js'></script>
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
            <!-- LIVRO VIEW -->
            <div class='cad-container'>
                <div class='cad-header'>
                    <h2>Cadastro de <span>Livros</span></h2>
                </div>
                <div class='cad-content'>
                    <c:if test="${isEdit == false || isEdit == null}">
                        <form id="livroForm" method="POST" action="FrontController?action=CadastrarLivro">
                            <div class='inputs'>
                                <div class='group-form'>
                                    <p class='form-title'>Livro</p>
                                    <div class='group-input'>
                                        <input type="text" name="isbn" placeholder='ISBN' required>
                                        <i class="fa fa-book" aria-hidden="true"></i>
                                    </div>
                                    <div class='group-input'>
                                        <input type="text" name="titulo" placeholder='Título' required>
                                        <i class="fa fa-book" aria-hidden="true"></i>
                                    </div>
                                    <div class='group-input'>
                                        <input type="text" name="descricao" placeholder='Descrição' required>
                                        <i class="fa fa-book" aria-hidden="true"></i>
                                    </div>
                                    <div class='group-input'>
                                        <input type="text" name="autor" placeholder='Autor do Livro' required>
                                        <i class="fa fa-user" aria-hidden="true"></i>
                                    </div>
                                </div>
                                <div class='group-form'>
                                    <p class='form-title'>Item Livro</p>
                                    <div class='group-input'>
                                        <input type="number" name="estoque" placeholder='Estoque' required>
                                        <i class="fa fa-sort-numeric-asc" aria-hidden="true"></i>
                                    </div>
                                </div>

                            </div>
                            <button type='submit' class='submit-button success' onclick="valid(${requestScope.success});"><i class="fa fa-plus" aria-hidden="true"></i> Cadastrar Livro </button>  
                        </form>
                    </c:if>
                    <c:if test="${isEdit == true}">
                        <form id="livroForm" method="POST" action="FrontController?action=EditarLivro">
                            <div class='inputs'>
                                <div class='group-form'>
                                    <p class='form-title'>Livro</p>
                                    <div class='group-input'>
                                        <input type="text" name="isbn" placeholder='ISBN' value="${livro.isbn}" required readonly="readonly">
                                        <i class="fa fa-book" aria-hidden="true"></i>
                                    </div>
                                    <div class='group-input'>
                                        <input type="text" name="titulo" placeholder='Título' value="${livro.titulo}" required>
                                        <i class="fa fa-book" aria-hidden="true"></i>
                                    </div>
                                    <div class='group-input'>
                                        <input type="text" name="descricao" placeholder='Descrição' value="${livro.descricao}" required>
                                        <i class="fa fa-book" aria-hidden="true"></i>
                                    </div>
                                    <div class='group-input'>
                                        <input type="text" name="autor" placeholder='Autor do Livro' value="${livro.autor}" required>
                                        <i class="fa fa-user" aria-hidden="true"></i>
                                    </div>
                                </div>
                                <div class='group-form'>
                                    <p class='form-title'>Item Livro</p>
                                    <div class='group-input'>
                                        <input type="number" name="estoque" placeholder='Estoque' value="${livro.estoque}" required>
                                        <i class="fa fa-sort-numeric-asc" aria-hidden="true"></i>
                                    </div>
                                </div>

                            </div>

                            <ul class='list-buttons'>
                                <li>
                                    <button type='submit' class='submit-button success' onclick="valid(${requestScope.success});" disabled><i class="fa fa-plus" aria-hidden="true"></i> Cadastrar Livro </button> 
                                </li>
                                <li>
                                    <button type='submit' class='submit-button success'><i class="fa fa-floppy-o" aria-hidden="true"></i> Salvar Alterações </button>
                                </li>
                                <li>
                                    <a href="livros.jsp" class='submit-button danger'> Cancelar </a>
                                </li>
                            </ul>
                        </form>
                    </c:if>
                    <c:if test="${requestScope.success == true}">
                        <div id="successMsg" class="alert alert-success" style="margin: 10px 10px 5px 10px;">
                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                            <strong>Success!</strong> Livro cadastrado com sucesso!
                        </div>
                    </c:if>
                    <c:if test="${requestScope.success == false}">
                        <div id="errorMsg" class="alert alert-danger" style="margin: 10px 10px 5px 10px;">
                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                            <strong>Error!</strong> Já existe um livro com esse ISBN!
                        </div>
                    </c:if>
                </div>
            </div> 

            <div class='cad-container'>
                <div class='cad-header'>
                    <h2>Livros cadastrados</h2>
                </div>
                <div class='cad-content'>
                    <div class='panel panel-default'>
                        <table class='table table-hover'>
                            <thead>
                            <th>ISBN</th>
                            <th>Título</th>
                            <th>Autor</th>
                            <th>Estoque</th>
                            <th>Ações</th>
                            </thead>
                            <tbody>
                                <%
                                    LivroBo livroBo = new LivroBo();
                                    for (Livro item : livroBo.list()) {
                                        out.print("<tr>");
                                        out.print("<td>" + item.getIsbn() + "</td>");
                                        out.print("<td>" + item.getTitulo() + "</td>");
                                        out.print("<td>" + item.getAutor() + "</td>");
                                        out.print("<td>" + item.getEstoque() + "</td>");
                                        out.print("<td><a href='FrontController?action=ExibirLivro&isbn=" + item.getIsbn() + "'>Editar</a></td>");
                                        out.print("</tr>");
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <!-- END OF LIVRO VIEW -->


        </div>
    </body>
</html>
