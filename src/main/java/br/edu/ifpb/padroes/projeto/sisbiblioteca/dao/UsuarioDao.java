/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.dao;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Usuario;
import java.util.List;

/**
 *
 * @author kieckegard
 */
public interface UsuarioDao {
    void cadastrarUsuario(Usuario usuario);
    void removerUsuario(String matricula);
    void atualizarUsuario(Usuario usuario);
    List<Usuario> listarUsuarios();
}
