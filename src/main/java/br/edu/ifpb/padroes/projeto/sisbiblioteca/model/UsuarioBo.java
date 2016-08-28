/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.model;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.FactoryProvider;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.UsuarioDao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Usuario;

/**
 *
 * @author kieckegard
 */
public class UsuarioBo {
    
    private final UsuarioDao usuarioDao;
    
    public UsuarioBo() {
        usuarioDao = FactoryProvider.createFactory(FactoryProvider.jdbc).getUsuarioDao();
    }
    
    public void cadastrarUsuario(Usuario usuario) throws InvalidStateException {
        validaUsuario(usuario);
        usuarioDao.cadastrarUsuario(usuario);
    }
    
    public void atualizarConta(Usuario usuario) throws InvalidStateException {
        validaUsuario(usuario);
        usuarioDao.atualizarUsuario(usuario);
    }
    
    public void removerConta(String matricula) {
        usuarioDao.removerUsuario(matricula);
    }
    
    private void validaUsuario(Usuario usuario) throws InvalidStateException {
        new CPFValidator().assertValid(usuario.getCpf());
    }
    
}
