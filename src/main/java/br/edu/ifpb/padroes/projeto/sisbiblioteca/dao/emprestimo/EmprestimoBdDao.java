/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.emprestimo;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.FactoryConnection;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.FactoryProvider;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.aluno.AlunoBdDao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.aluno.AlunoDao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.livro.LivroDao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Emprestimo;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.aluno.Aluno;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.livro.Livro;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.enums.EstadoEmprestimoEnum;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kieckegard
 */
public class EmprestimoBdDao implements EmprestimoDao {

    private final AlunoDao alunoDao;
    private final LivroDao livroDao;
    
    public EmprestimoBdDao() {
        alunoDao = FactoryProvider.createFactory(FactoryProvider.jdbc).getAlunoDao();
        livroDao = FactoryProvider.createFactory(FactoryProvider.jdbc).getLivroDao();
    }
    
    @Override
    public void finalizarEmprestimo(Emprestimo emprestimo) {
        String sql = "UPDATE emprestimo SET idEstado = ?, dataEntrega = ? WHERE id = ?";
        try {
            Connection conn = FactoryConnection.getInstance();
            PreparedStatement pstm = conn.prepareStatement(sql);
            
            int i = 1;
            
            pstm.setInt(i++, emprestimo.getEstadoValue());
            pstm.setDate(i++, java.sql.Date.valueOf(emprestimo.getDataEntregue()));
            pstm.setInt(i++, emprestimo.getId());
            
            pstm.executeUpdate();
            
            pstm.close();
            conn.close();
        }
        catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void adicionarEmprestimo(Emprestimo obj) {
        String sql = "INSERT INTO emprestimo(alunoMatricula, livroIsbn, dataInicio, dataFim, idEstado)"
                + " VALUES(?,?,?,?,?) RETURNING id";
        
        try {
            Connection conn = FactoryConnection.getInstance();
            PreparedStatement pstm = conn.prepareStatement(sql);
            
            int i=1;
            
            pstm.setString(i++, obj.getAluno().getMatricula());
            pstm.setLong(i++, obj.getLivro().getIsbn());
            pstm.setDate(i++, java.sql.Date.valueOf(obj.getStartDate()));
            pstm.setDate(i++, java.sql.Date.valueOf(obj.getEndDate()));
            pstm.setInt(i++, obj.getEstadoValue());
            
            ResultSet rs = pstm.executeQuery();
            
            if(rs.next())
                obj.setId(rs.getInt("id"));
            
            pstm.close();
            conn.close();
        }
        catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Emprestimo recuperarEmprestimoPorId(Integer obj) {
        try {
            String sql = "SELECT * FROM emprestimo WHERE id = ?";
            
            Connection conn = FactoryConnection.getInstance();
            PreparedStatement pstm = conn.prepareStatement(sql);
            
            int i=1;
            
            pstm.setInt(i++, obj);
            
            ResultSet rs = pstm.executeQuery();
            
            if(rs.next())
                return formaEmprestimo(rs);
            
            pstm.close();
            conn.close();
        }
        catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        
        return null;
    }

    @Override
    public List<Emprestimo> listarEmprestimos() {
        String sql = "SELECT * FROM emprestimo";
        List<Emprestimo> emprestimos = new LinkedList<>();
        
        try {
            Connection conn = FactoryConnection.getInstance();
            PreparedStatement pstm = conn.prepareStatement(sql);
            
            ResultSet rs = pstm.executeQuery();
            
            while(rs.next()) {
                emprestimos.add(formaEmprestimo(rs));
            }
            
            pstm.close();
            conn.close();
            
            return emprestimos;
        }
        catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        
        return emprestimos;
    }
    
    private Livro getLivroFromIsbn(Long isbn) {
        return livroDao.recuperarLivroPorIsbn(isbn);
    }
    
    private Aluno getAlunoFromMatricula(String matricula) {
        return alunoDao.recuperarAlunoPorMatricula(matricula);
    }

    @Override
    public List<Emprestimo> listByAttributes(Map<String, String> attributes) {

        StringBuilder sql = new StringBuilder("SELECT * FROM emprestimo WHERE ");

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

        List<Emprestimo> emprestimos = new LinkedList<>();

        try {
            Connection conn = FactoryConnection.getInstance();
            PreparedStatement pstm = conn.prepareStatement(sql.toString());

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                emprestimos.add(formaEmprestimo(rs));
            }
            
            pstm.close();
            conn.close();

            return emprestimos;
        }
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlunoBdDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return emprestimos;
    }
    
    @Override
    public List<Emprestimo> listEmprestimosToEndByDaysQuantity(Integer daysQuantity) {
        
        List<Emprestimo> emprestimos = new LinkedList<>();
        
        String sql = "SELECT * FROM emprestimo WHERE idEstado = 0"
                + " AND date_part('day',age(datafim,current_date)) = ?"
                + " AND id NOT IN(SELECT emprestimoId FROM emprestimos_notificados)";
        
        try {
            
            Connection conn = FactoryConnection.getInstance();
            PreparedStatement pstm = conn.prepareStatement(sql);
            
            pstm.setInt(1, daysQuantity);
            
            ResultSet rs = pstm.executeQuery();
            
            System.out.println("Executing Query... ");
            System.out.println(pstm.toString());
            
            while(rs.next()) {
                emprestimos.add(formaEmprestimo(rs));
            }
            
            pstm.close();
            conn.close();
            
            return emprestimos;
        }
        catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        
        return emprestimos;
    }
    
    private Emprestimo formaEmprestimo(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        String alunoMatricula = rs.getString("alunoMatricula");
        Long livroIsbn = rs.getLong("livroIsbn");
        LocalDate dataInicio = rs.getDate("dataInicio").toLocalDate();
        LocalDate dataFim = rs.getDate("dataFim").toLocalDate();
        Integer idEstado = rs.getInt("idEstado");
        
        EstadoEmprestimoEnum estadoEmprestimo = EstadoEmprestimoEnum.values()[idEstado];
        
        Date date = rs.getDate("dataEntrega");
        LocalDate dataEntrega = null;
        if(date != null) dataEntrega = date.toLocalDate();
        
        Livro livro = getLivroFromIsbn(livroIsbn);
        Aluno aluno = getAlunoFromMatricula(alunoMatricula);
        
        Emprestimo emprestimo = new Emprestimo(aluno,livro,dataInicio,dataFim,estadoEmprestimo);
        emprestimo.setDataEntrega(dataEntrega);
        emprestimo.setId(id);
        
        return emprestimo;
    }

    @Override
    public void registraNotificacao(Integer emprestimoId, LocalDateTime dateTime) {
        String sql = "INSERT INTO emprestimos_notificados VALUES(?,?)";
        
        try {
            Connection conn = FactoryConnection.getInstance();
            PreparedStatement pstm = conn.prepareStatement(sql);
            
            Integer i = 1;
            
            pstm.setInt(i++, emprestimoId);
            pstm.setTimestamp(i++, java.sql.Timestamp.valueOf(dateTime));
            
            pstm.executeUpdate();
            
            pstm.close();
            conn.close();
        }
        catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
