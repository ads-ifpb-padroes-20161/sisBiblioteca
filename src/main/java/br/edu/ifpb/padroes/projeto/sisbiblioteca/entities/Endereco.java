/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.entities;

/**
 *
 * @author kieckegard
 */
public class Endereco {

    private int id;
    private String pais;
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private int numero;

    public Endereco(int id, String pais, String estado, String cidade, String bairro, String rua, int numero) {
        this.pais = pais;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.id = id;
    }

    public Endereco(String pais, String estado, String cidade, String bairro, String rua, int numero) {
        this.pais = pais;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
    }
    
    public Endereco() {
        
    }

    public String getPais() {
        return pais;
    }

    public String getEstado() {
        return estado;
    }

    public String getCidade() {
        return cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public String getRua() {
        return rua;
    }

    public int getNumero() {
        return numero;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Endereco{" + "id=" + id + ", pais=" + pais + ", estado=" + estado + ", cidade=" + cidade + ", bairro=" + bairro + ", rua=" + rua + ", numero=" + numero + '}';
    }

}
