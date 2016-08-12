/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.dao;

/**
 *
 * @author kieckegard
 */
public class FactoryProvider {

    public static final int jdbc = 1;

    public static FactoryDaoIF createFactory(int tipo) {
        if (tipo == jdbc) {
            return new FactoryBdDao();
        }
        return null;
    }
}
