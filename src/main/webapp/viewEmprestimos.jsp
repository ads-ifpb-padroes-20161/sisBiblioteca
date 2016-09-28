<%-- 
    Document   : viewEmprestimos
    Created on : 26/08/2016, 11:08:55
    Author     : kieckegard
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="br.edu.ifpb.padroes.projeto.sisbiblioteca.utils.DateUtils"%>
<%@page import="br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Emprestimo"%>
<%@page import="br.edu.ifpb.padroes.projeto.sisbiblioteca.model.EmprestimoBo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Visualizar Empréstimos</title>
    </head>
    <body>


        <div class="centered-container">
            <div id="errorMsg" class="alert alert-danger" style="margin: 10px 10px 5px 10px; display: none;">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                <strong>Error!</strong>
            </div>
        </div>

        <c:if test="${requestScope.success == true}">
            <div class="centered-container">
                <div id="successMsg" class="alert alert-success" style="margin: 10px 10px 5px 10px;">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <strong>Success!</strong> Empréstimo finalizado com sucesso!!
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

        <div class="cad-container cad-aluno-container">
            <div class='cad-header'>
                <h2>Buscar por <span class="blue-title">Empréstimos</span></h2>
            </div>
            <div class="cad-content" style='overflow: auto;'>
                <form style="display: block;" action="FrontController?action=BuscaEmprestimo" method="POST">
                    <div class='row'>
                        <div class='col-md-9'>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-info" aria-hidden="true"></i></span>
                                    <input class="form-control" type="text" name="searchText" placeholder="Search" required>
                                </div>
                            </div>
                        </div>
                        <div class='col-md-3'>
                            <div class="form-group">
                                <select style="margin-bottom: 15px;" class="form-control" id = "selectSearchCriteria" name="searchCriteria" onchange="enableAgency();" required>
                                    <option value="placeholder" disabled selected>Critério da Busca</option>s
                                    <option value="livroIsbn">ISBN</option>
                                    <option value="alunoMatricula">Matrícula</option>
                                    <option value="dataInicio">Data de Início</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class='row'>
                        <button class='submit-button success' onclick='validate(event);' style="margin-left: 15px; ">
                            Realizar Busca <i class="fa fa-search" aria-hidden="true" ></i> 
                        </button>
                    </div>
                </form>
            </div>
        </div>

        <div class='cad-container cad-aluno-container'>
            <div class='cad-header'>
                <h2>
                    Empréstimos Realizados 
                </h2>
            </div>
            <div class='cad-content'>
                <div class='panel panel-default'>
                    <table class='table table-hover'>
                        <thead>
                        <th>#</th>
                        <th>Livro</th>
                        <th>Aluno</th>
                        <th>Início</th>
                        <th>Término</th>
                        <th>Entrega</th>
                        <th>Ações</th>
                        </thead>
                        <tbody>

                            <%
                                if (request.getAttribute("emprestimos") == null) {
                                    List<Emprestimo> emprestimos = new EmprestimoBo().listarEmprestimos();
                                    request.setAttribute("emprestimos", emprestimos);
                                    session.setAttribute("emprestimosCached", emprestimos);
                                }
                            %>
                            <c:forEach var="emprestimo" items="${requestScope.emprestimos}">
                                <tr>
                                    <td>${emprestimo.id}</td>
                                    <td>${emprestimo.livro.titulo}</td>
                                    <td>${emprestimo.aluno.nome}</td>
                                    <td>${DateUtils.formatToBrazilPattern(emprestimo.startDate)}</td>
                                    <td>${DateUtils.formatToBrazilPattern(emprestimo.endDate)}</td>
                                    <c:if test="${emprestimo.estadoValue == 0}">
                                        <td class='light-green'>${emprestimo.estadoDescricao}</td>
                                        <td><a href='FrontController?action=FinalizarEmprestimo&id=${emprestimo.id}'>Finalizar</a></td>
                                    </c:if>
                                    <c:if test="${emprestimo.estadoValue == 1}">
                                        <td style='color: lightblue;'>${DateUtils.formatToBrazilPattern(emprestimo.dataEntregue)}</td>
                                        <td></td>
                                    </c:if>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
    <script>
        function validate(e) {
            if ($('#selectSearchCriteria option:selected').val() === "placeholder") {
                var text = "Por favor, selecione o critério da busca!";
                $('#errorMsg').html(text);
                $('#errorMsg').slideDown();
                e.preventDefault();
            }
        }
    </script>
</html>
