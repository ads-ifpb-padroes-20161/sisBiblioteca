/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.bloqueio;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Bloqueio;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.enums.EstadoBloqueioEnum;
import java.util.List;

/**
 *
 * @author kieckegard
 */
public interface BloqueioDao {
    
    void inserir(Bloqueio bloqueio);
    void alterarEstado(Integer bloqueioId, EstadoBloqueioEnum estado);
    List<Bloqueio> lista();
    void verificaEDesbloqueiaAlunos();
}
