/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.commands;

import br.com.caelum.stella.validation.InvalidStateException;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Endereco;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.aluno.Aluno;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.aluno.AlunoPadrao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.enums.EstadoAlunoEnum;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.CPFJaExisteException;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.EmailJaExisteException;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.MatriculaJaExisteException;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.model.AlunoBo;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.utils.DateUtils;
import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kieckegard
 */
public class CadastrarAluno implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Pessoa
        String cpf = request.getParameter("cpf");
        String nome = request.getParameter("nome");
        LocalDate dataNascimento = DateUtils.fromBrazilPattern(request.getParameter("dataNascimento"));

        //Aluno
        String matricula = request.getParameter("matricula");
        String email = request.getParameter("email");

        //Endereço
        String pais = request.getParameter("pais");
        String estado = request.getParameter("estado");
        String cidade = request.getParameter("cidade");
        String bairro = request.getParameter("bairro");
        String rua = request.getParameter("rua");
        
        int numero = Integer.valueOf(request.getParameter("numero"));
        Endereco endereco = new Endereco(pais, estado, cidade, bairro, rua, numero);

        Aluno aluno = new AlunoPadrao();
        
        aluno.setCpf(cpf);
        aluno.setNome(nome);
        aluno.setDataNascimento(dataNascimento);
        aluno.setMatricula(matricula);
        aluno.setEmail(email);
        aluno.setEstado(EstadoAlunoEnum.HABILITADO);
        aluno.setEndereco(endereco);

        AlunoBo bo = new AlunoBo();

        RequestDispatcher dispatcher = request.getRequestDispatcher("alunos.jsp");

        try {
            bo.cadastrarAluno(aluno);

            request.setAttribute("success", true);
        }
        catch (InvalidStateException ex) {
            request.setAttribute("success", false);
            request.setAttribute("errorMsg", "O CPF digitado é inválido!");
        }
        catch (CPFJaExisteException | MatriculaJaExisteException | EmailJaExisteException ex) {
            request.setAttribute("success", false);
            request.setAttribute("errorMsg", ex.getMessage());
        }
        finally {
            dispatcher.forward(request, response);
        }
    }
    
}
