<%-- 
    Document   : viewEmprestimos
    Created on : 26/08/2016, 11:08:55
    Author     : kieckegard
--%>

<%@page import="br.edu.ifpb.bdnc.maisdenuncia.utils.DateUtils"%>
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

    <div class='cad-container cad-aluno-container'>
        <div class='cad-header'>
            <h2>Empréstimos Realizados</h2>
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
                            EmprestimoBo emprestimoBo = new EmprestimoBo();
                            for (Emprestimo emprestimo : emprestimoBo.listarEmprestimos()) {
                                System.out.println("descrição livro: " + emprestimo.getLivro().getDescricao());
                                System.out.println("aluno: " + emprestimo.getAluno());
                                out.print("<tr>");
                                out.print("<td>" + emprestimo.getId() + "</td>");
                                out.print("<td>" + emprestimo.getLivro().getTitulo() + "</td>");
                                out.print("<td>" + emprestimo.getAluno().getNome() + "</td>");
                                out.print("<td>" + DateUtils.formatToBrazilPattern(emprestimo.getStartDate()) + "</td>");
                                out.print("<td>" + DateUtils.formatToBrazilPattern(emprestimo.getEndDate()) + "</td>");

                                if (emprestimo.getEstadoValue() == 0) {
                                    out.print("<td class='light-green'>" + emprestimo.getEstadoDescricao() + "</td>");
                                    out.print("<td><a href='FinalizarEmprestimo?id=" + emprestimo.getId() + "'>Finalizar</a></td>");
                                }
                                else {
                                    out.print("<td style='color: lightblue;'>" + DateUtils.formatToBrazilPattern(emprestimo.getDataEntregue()) + "</td>");
                                    out.print("<td></td>");
                                }

                                out.print("</tr>");
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
