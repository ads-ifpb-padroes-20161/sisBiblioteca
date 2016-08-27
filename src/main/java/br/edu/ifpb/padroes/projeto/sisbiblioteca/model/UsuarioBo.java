/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.model;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.Dao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.FactoryProvider;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Usuario;

/**
 *
 * @author kieckegard
 */
public class UsuarioBo {
    
    private final Dao<Usuario, String> usuarioDao;
    
    public UsuarioBo() {
        usuarioDao = FactoryProvider.createFactory(FactoryProvider.jdbc).getUsuarioDao();
    }
    
    public void atualizarConta(Usuario usuario) throws InvalidStateException {
        validaUsuario(usuario);
        usuarioDao.update(usuario);
    }
    
    public void removerConta(Usuario usuario) {
        usuarioDao.rem(usuario);
    }
    
    private void validaUsuario(Usuario usuario) throws InvalidStateException {
        new CPFValidator().assertValid(usuario.getCpf());
    }
    
}
