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
        
        
        <script type="text/javascript" src='js/jquery-1.12.3.js'></script>
        <script type="text/javascript" src='js/jquery-mask.js'></script>    
        <script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>

        <script type="text/javascript">
            $(document).ready(function () {

                $('#toggleReport').click(function () {
                    
                    $('#reportModal').modal("show");
                });

            });
        </script>
        
        <style>
            
            .modal-content {
                overflow: hidden;
            }
            
            .modal-content .modal-header{
                background-color: rgb(8,102,198);
            }
            
            .modal-content .modal-header h4 {
                color: #fff;
            }
            
        </style>
    </head>
    <body>
        <header>
            <a href="home.jsp"><h1>SisBiblioteca</h1></a>
        </header>
        <aside class='vertical-nav'>
            <p class='title'>Navegação</p>
            <ul class='navigation'>
                <c:if test="${requestScope.firstLogin != true}">
                    <li><a href="alunos.jsp"><i class="fa fa-user" aria-hidden="true"></i>  Alunos</a></li>
                    <li><a href="livros.jsp"><i class="fa fa-book" aria-hidden="true"></i> Livros</a></li>
                    <li><a href="emprestimos.jsp"><i class="fa fa-calendar-o" aria-hidden="true"></i> Empréstimos</a></li>
                    <li><a href="realizarEmprestimo.jsp"><i class="fa fa-calendar-plus-o" aria-hidden="true"></i> Novo Empréstimo</a></li>
                    <li><a href="minhaconta.jsp"><i class="fa fa-cog" aria-hidden="true"></i> Sua Conta</a></li>
                    <li><a id="toggleReport" href="#"><i class="fa fa-file-pdf-o" aria-hidden="true"></i> Gerar Relatórios</a></li>
                    </c:if>
            </ul>

            <div class="modal fade" id="reportModal" tabindex="-1" role="dialog">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title">Gerar Relatórios</h4>
                        </div>
                        <form action="FrontController?action=GerarRelatorio" method="POST" target="_blank">
                            <div class="modal-body">
                                <p>Selecione o Tipo de relatório que desejas gerar!</p>
                                <div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <select style="margin-bottom: 15px;" class="form-control" id = "selectSearchCriteria" name="tipoRelatorio" required>
                                                    <option value="0">Empréstimos em Andamento</option>
                                                    <option value="1">Empréstimos Finalizados</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                <button type="submit" class="btn btn-primary"> 
                                    <i class="fa fa-print" aria-hidden="true"></i>
                                    Gerar Relatório
                                </button>
                            </div>
                        </form>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->

            <div class="profile">
                <c:if test="${requestScope.firstLogin != true}">
                    <p class="welcome-msg">Bem vindo, <span class="blue-title">${sessionScope.loggedUser.getNome()}</span></p>
                </c:if>
                <a class="submit-button danger" href="FrontController?action=Logout">
                    <i class="fa fa-sign-out" aria-hidden="true"></i> Sair
                </a>
            </div>

        </aside>
    </body>
</html>
