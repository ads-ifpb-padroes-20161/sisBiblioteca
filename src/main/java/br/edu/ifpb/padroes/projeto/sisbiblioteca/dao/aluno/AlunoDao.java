/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.aluno;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.aluno.Aluno;
import java.util.List;

/**
 *
 * @author kieckegard
 */
public interface AlunoDao {
    
    void atualizarEstadoAluno(Aluno aluno);
    List<Aluno> listarAlunosHabilitados();
    Aluno recuperarAlunoPorMatricula(String matricula);
    void cadastrarAluno(Aluno aluno);
    List<Aluno> listarAlunos();
    
}
