/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.dao;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Endereco;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Usuario;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.enums.TipoUsuario;
import java.time.LocalDate;

/**
 *
 * @author kieckegard
 */
public class TesteUsuarioBdDao {

    public static void main(String[] args) {
        Dao<Usuario, String> usuarioDao = FactoryProvider.createFactory(FactoryProvider.jdbc).getUsuarioDao();

        Usuario usuario = new Usuario("111.111.111-11", "Pedro", LocalDate.now(), "111222333", "123456", TipoUsuario.BIBLIOTECARIO);

        Endereco e = new Endereco("brasil", "rio grande do norte", "caicó", "vila do príncipe", "rua serafim bernardo", 352);

        usuario.setEndereco(e);

        usuarioDao.add(usuario);

        for (Usuario u : usuarioDao.list()) {
            System.out.println(u);
        }
    }
}
