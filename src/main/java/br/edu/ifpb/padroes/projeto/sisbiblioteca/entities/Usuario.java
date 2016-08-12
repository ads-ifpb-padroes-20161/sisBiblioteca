/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.entities;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.enums.TipoUsuario;
import java.time.LocalDate;

/**
 *
 * @author kieckegard
 */
public class Usuario extends Pessoa {

    private String matricula;
    private String senha;
    private TipoUsuario tipoUsuario;

    public Usuario(String cpf, String nome, LocalDate dataNascimento, String matricula, String senha, TipoUsuario tipoUsuario) {
        super(cpf, nome, dataNascimento);
        this.matricula = matricula;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }

    public String getMatricula() {
        return matricula;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario{" + "matricula=" + matricula + ", senha=" + senha + ", tipoUsuario=" + tipoUsuario + '}';
    }
}
