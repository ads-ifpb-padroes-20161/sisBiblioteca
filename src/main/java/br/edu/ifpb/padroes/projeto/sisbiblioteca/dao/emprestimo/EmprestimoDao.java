/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.emprestimo;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Emprestimo;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kieckegard
 */
public interface EmprestimoDao {
    
    void adicionarEmprestimo(Emprestimo emprestimo);
    Emprestimo recuperarEmprestimoPorId(Integer id);
    void finalizarEmprestimo(Emprestimo emprestimo);
    List<Emprestimo> listarEmprestimos();
    List<Emprestimo> listByAttributes(Map<String, String> attributes);
    List<Emprestimo> listEmprestimosToEndByDaysQuantity(Integer daysQuantity);
    void registraNotificacao(Integer emprestimoId, LocalDateTime dateTime);
}
