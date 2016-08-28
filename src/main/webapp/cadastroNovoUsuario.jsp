<%-- 
    Document   : cadastroNovoUsuario
    Created on : 28/08/2016, 17:38:40
    Author     : kieckegard
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastrar Novo Usuário</title>
    </head>
    <body>

        <div class="centered-container">
            <div id="successMsg" class="alert alert-info">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                <strong>Olá, Usuário!</strong> Percebemos que você está usando o sistema pela primeira vez.
                Por favor, preencha os campos abaixo para criar sua conta de usuário. Uma vez criada sua conta, 
                você terá acesso as funcionalidades do sistema ao efetuar o procedimento de login. 
            </div>
        </div>

        <!-- My Account view -->
        <div class='cad-container cad-aluno-container'>
            <div class='cad-header'>
                <h2>Por favor, preencha todos os campos para cadastrar sua conta de <span>Usuário</span></h2>
            </div>
            <div class='cad-content'>
                <form id="formUserAccount" method="POST" action="CadastrarUsuario">
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
                            <p class='form-title'>Bibliotecário</p>
                            <div class='group-input'>
                                <input type="text" name="matricula" placeholder='Matrícula' required>
                                <i class="fa fa-font" aria-hidden="true"></i>
                            </div>
                            <div class='group-input'>
                                <input type="password" name="senha" placeholder='Senha' required>
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
                                <input type="text" name="estado" placeholder='Estado'required>
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

                    <ul class='list-buttons'>
                        <li>
                            <button type='submit' class='submit-button success'><i class="fa fa-pencil-square-o" aria-hidden="true"></i> Cadastrar Usuário </button>
                        </li>
                    </ul>
                </form>

            </div>
        </div>      
    </body>
</html>
