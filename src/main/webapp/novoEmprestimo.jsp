<%-- 
    Document   : novoEmprestimo
    Created on : 26/08/2016, 11:00:15
    Author     : kieckegard
--%>

<%@page import="br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.livro.Livro"%>
<%@page import="br.edu.ifpb.padroes.projeto.sisbiblioteca.model.LivroBo"%>
<%@page import="br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.aluno.Aluno"%>
<%@page import="br.edu.ifpb.padroes.projeto.sisbiblioteca.model.QueryAlunoBo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src='js/jquery-1.12.3.js'></script>
        <script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>
        <title>Novo Empréstimo</title>
    </head>
    <body>
        <div class='cad-container cad-aluno-container'>
            <div class='cad-header'>
                <h2>Selecione o <span class="blue-title">Aluno</span> desejado</h2>
            </div>
            <div class='cad-content'>
                <div class='panel panel-default'>
                    <table id="aluno-table" class='table table-hover'>
                        <thead>
                        <th>Matrícula</th>
                        <th>Nome</th>
                        <th>E-mail</th>
                        <th>Situação</th>
                        </thead>
                        <tbody>
                            <%
                                QueryAlunoBo queryAlunoBo = new QueryAlunoBo();
                                for (Aluno aluno : queryAlunoBo.listarAlunosHabilitados()) {
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
                                    out.print("</tr>");
                                }
                            %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <!-- END OF ALUNO VIEW -->

        <div class='cad-container' style='width: 80%;'>
            <div class='cad-header'>
                <h2>Selecione o <span class="blue-title">Livro</span> desejado</h2>
            </div>
            <div class='cad-content'>
                <div class='panel panel-default'>
                    <table id="livro-table" class='table table-hover'>
                        <thead>
                        <th>ISBN</th>
                        <th>Título</th>
                        <th>Autor</th>
                        <th>Estoque</th>
                        </thead>
                        <tbody>
                            <%
                                LivroBo bo = new LivroBo();
                                for (Livro item : bo.list()) {
                                    out.print("<tr>");
                                    out.print("<td>" + item.getIsbn() + "</td>");
                                    out.print("<td>" + item.getTitulo() + "</td>");
                                    out.print("<td>" + item.getAutor() + "</td>");
                                    out.print("<td>" + item.getEstoque() + "</td>");
                                    out.print("</tr>");
                                }
                            %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <div class="centered-container">
            <div id="alunoNotSelectedError" class="alert alert-danger" style='display: none;'>
                <strong>Error!</strong> Por favor selecione um <strong>aluno</strong> para realizar o empréstimo!
            </div>   

            <div id="livroNotSelectedError" class="alert alert-danger" style='margin-top: 10px; display: none;'> 
                <strong>Error!</strong> Por favor selecione um <strong>livro</strong> para realizar o empréstimo!
            </div>  
        </div>

        <!-- END OF LIVRO VIEW -->
        <div class="centered-container">
            <form action="FrontController?action=RealizarEmprestimo" method="POST">
                <input name="matricula" type="text" style="display: none">
                <input name="isbn" type="text" style="display: none">
                <ul style="list-style: none; padding: 0px;">
                    <li style="float: left; margin-right: 10px;">
                        <button type='submit' onclick='validate(event);' class='submit-button success'>
                            <i class="fa fa-plus" aria-hidden="true"></i> Realizar Empréstimo 
                        </button>
                    </li>
                    <li>
                        <button type="button" class='submit-button danger'>
                            <i class="fa fa-times" aria-hidden="true"></i> Cancelar 
                        </button>
                    </li>
                </ul>
            </form>
        </div>
    </body>
    <script>

        $('#aluno-table tbody tr').click(function () {
            $(this).addClass('selected').siblings().removeClass('selected');
            var value = $(this).find('td:first').html();
            $('input[name="matricula"').val(value);
        });

        $('#livro-table tbody tr').click(function () {
            $(this).addClass('selected').siblings().removeClass('selected');
            var value = $(this).find('td:first').html();
            $('input[name="isbn"').val(value);
        });

        function validate(event) {
            var isbn = $('input[name="isbn"').val();
            var matricula = $('input[name="matricula"').val();

            if (isbn === '') {
                $('#livroNotSelectedError').slideDown();

                event.preventDefault();
            } else {
                $('#livroNotSelectedError').slideUp();
            }

            if (matricula === '') {
                $('#alunoNotSelectedError').slideDown();
                event.preventDefault();
            } else {
                $('#alunoNotSelectedError').slideUp();

            }
        }


    </script>
</html>
