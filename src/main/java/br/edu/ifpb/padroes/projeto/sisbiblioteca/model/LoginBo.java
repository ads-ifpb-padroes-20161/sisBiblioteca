/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.model;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.Dao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.FactoryProvider;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Usuario;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.PrimeiroLoginException;
import javax.security.auth.login.LoginException;

/**
 *
 * @author kieckegard
 */
public class LoginBo {

    private final Dao<Usuario, String> usuarioDao;

    public LoginBo() {
        usuarioDao = FactoryProvider.createFactory(FactoryProvider.jdbc).getUsuarioDao();
    }

    private void verifyFirstLogin() throws PrimeiroLoginException {
        if (usuarioDao.list().isEmpty()) {
            throw new PrimeiroLoginException("Ainda não possui contas de usuário no Sistema.");
        }
    }

    public Usuario login(String matricula, String senha) throws PrimeiroLoginException, LoginException {
        verifyFirstLogin();
        for (Usuario u : usuarioDao.list()) {
            if (u.getMatricula().equals(matricula) && u.getSenha().equals(senha)) {
                return u;
            }
        }
        throw new LoginException("Usuário e(ou) Senha inválido(os)!");
    }
}
