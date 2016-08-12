/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.dao;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Aluno;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Endereco;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Pessoa;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.enums.EstadoAlunoEnum;
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
public class AlunoBdDao implements Dao<Aluno, String> {

    private final Dao<Endereco, Pessoa> enderecoBdDao;
    private final SimpleDao<Pessoa, String> pessoaBdDao;

    public AlunoBdDao() {
        enderecoBdDao = new EnderecoBdDao();
        pessoaBdDao = new PessoaBdDao();
    }

    @Override
    public void add(Aluno obj) {
        String sql = "INSERT INTO aluno VALUES(?,?,?,?)";
        pessoaBdDao.add(obj);
        try {
            PreparedStatement pstm = FactoryConnection.createConnection().prepareStatement(sql);

            int i = 1;

            pstm.setString(i++, obj.getMatricula());
            pstm.setString(i++, obj.getEmail());
            pstm.setInt(i++, obj.getEstado().getIndex());
            pstm.setString(i++, obj.getCpf());

            pstm.executeUpdate();
        }
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlunoBdDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void rem(Aluno obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Aluno obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Aluno get(String obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            PreparedStatement pstm = FactoryConnection.createConnection().prepareStatement(sql.toString());

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                alunos.add(formaAluno(rs));
            }

            return alunos;
        }
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlunoBdDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alunos;
    }

    @Override
    public List<Aluno> list() {
        String sql = "SELECT * FROM Aluno a JOIN Pessoa p ON a.cpfPessoa = p.cpf";
        List<Aluno> alunos = new ArrayList<>();
        try {
            PreparedStatement pstm = FactoryConnection.createConnection().prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                alunos.add(formaAluno(rs));
            }

            return alunos;
        }
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlunoBdDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return alunos;
    }

    private Aluno formaAluno(ResultSet rs) throws SQLException {
        //aluno
        String email = rs.getString("email");
        String matricula = rs.getString("matricula");
        int estado = rs.getInt("idEstado");
        EstadoAlunoEnum estadoAluno = EstadoAlunoEnum.get(estado);
        //pessoa
        String cpf = rs.getString("cpf");
        String nome = rs.getString("nome");
        LocalDate dataNascimento = rs.getDate("dataNascimento").toLocalDate();
        //endereco
        Aluno aluno = new Aluno(cpf, nome, dataNascimento, matricula, email);
        Endereco endereco = enderecoBdDao.get(aluno);
        aluno.setEndereco(endereco);
        aluno.setEstado(estadoAluno);
        return aluno;
    }

}
