/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.aluno;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.EnderecoBdDao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.EnderecoDao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.FactoryConnection;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.PessoaBdDao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.PessoaDao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Endereco;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.aluno.Aluno;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.aluno.AlunoPadrao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.enums.EstadoAlunoEnum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kieckegard
 */
public class AlunoBdDao implements AlunoDao {

    private final EnderecoDao enderecoBdDao;
    private final PessoaDao pessoaBdDao;

    public AlunoBdDao() {
        enderecoBdDao = new EnderecoBdDao();
        pessoaBdDao = new PessoaBdDao();
    }

    @Override
    public void cadastrarAluno(Aluno obj) {
        String sql = "INSERT INTO aluno VALUES(?,?,?,?)";
        pessoaBdDao.adicionarPessoa(obj);
        try {
            Connection conn = FactoryConnection.getInstance();
            PreparedStatement pstm = conn.prepareStatement(sql);

            int i = 1;

            pstm.setString(i++, obj.getMatricula());
            pstm.setString(i++, obj.getEmail());
            pstm.setInt(i++, obj.getValorEstado());
            pstm.setString(i++, obj.getCpf());

            pstm.executeUpdate();
            
            pstm.close();
            conn.close();
        }
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlunoBdDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Aluno recuperarAlunoPorMatricula(String obj) {

        String sql = "SELECT * FROM Aluno a JOIN Pessoa p ON a.cpfPessoa = p.cpf WHERE a.matricula = ?";
        try {
            Connection conn = FactoryConnection.getInstance();
            PreparedStatement pstm = conn.prepareStatement(sql);

            int i = 1;

            pstm.setString(i++, obj);

            ResultSet rs = pstm.executeQuery();
            Aluno aluno = null;
            if (rs.next()) {
                aluno = formaAluno(rs);
            }
            
            pstm.close();
            conn.close();

            return aluno;

        }
        catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Aluno> getByAttributes(Map<String, String> attributes) {

        StringBuilder sql = new StringBuilder("SELECT * FROM aluno WHERE ");

        Set<String> keys = attributes.keySet();
        Iterator<String> it = keys.iterator();

        String key;
        while (it.hasNext()) {
            key = it.next();
            sql.append(key);
            sql.append(" = ");
            sql.append("'").append(attributes.get(key)).append("'");
            if (it.hasNext()) {
                sql.append(" AND ");
            }
        }

        List<Aluno> alunos = new ArrayList<>();

        try {
            Connection conn = FactoryConnection.getInstance();
            PreparedStatement pstm = conn.prepareStatement(sql.toString());

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                alunos.add(formaAluno(rs));
            }
            
            pstm.close();
            conn.close();

            return alunos;
        }
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlunoBdDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alunos;
    }

    @Override
    public List<Aluno> listarAlunos() {

        String sql = "SELECT * FROM Aluno a JOIN Pessoa p ON a.cpfPessoa = p.cpf";
        return selectAlunos(sql);
    }

    private Aluno formaAluno(ResultSet rs) throws SQLException {
        //aluno
        String email = rs.getString("email");
        String matricula = rs.getString("matricula");
        int estado = rs.getInt("idEstado");
        EstadoAlunoEnum estadoAlunoEnum = EstadoAlunoEnum.values()[estado - 1];

        //pessoa
        String cpf = rs.getString("cpf");
        String nome = rs.getString("nome");
        LocalDate dataNascimento = rs.getDate("dataNascimento").toLocalDate();

        Endereco endereco = enderecoBdDao.recuperarEnderecoPorPessoaCpf(cpf);

        //endereco
        Aluno aluno = new AlunoPadrao();
        aluno.setCpf(cpf);
        aluno.setNome(nome);
        aluno.setDataNascimento(dataNascimento);
        aluno.setMatricula(matricula);
        aluno.setEmail(email);
        aluno.setEstado(estadoAlunoEnum);
        aluno.setEndereco(endereco);

        return aluno;
    }

    @Override
    public void atualizarEstadoAluno(Aluno aluno) {

        String sql = "UPDATE aluno SET idEstado = ? WHERE matricula = ?";

        try {
            PreparedStatement pstm = FactoryConnection.getInstance().prepareStatement(sql);

            int i = 1;

            pstm.setInt(i++, aluno.getValorEstado());
            pstm.setString(i++, aluno.getMatricula());

            pstm.executeUpdate();
        }
        catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    private List<Aluno> selectAlunos(String sql) {
        List<Aluno> alunos = new ArrayList<>();
        try {
            Connection conn = FactoryConnection.getInstance();
            PreparedStatement pstm = conn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                alunos.add(formaAluno(rs));
            }
            
            pstm.close();
            conn.close();

            return alunos;
        }
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlunoBdDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return alunos;
    }

    @Override
    public List<Aluno> listarAlunosHabilitados() {

        String sql = "SELECT * FROM Aluno a JOIN Pessoa p ON a.cpfPessoa = p.cpf WHERE a.idEstado = 1";
        return selectAlunos(sql);
    }

}
