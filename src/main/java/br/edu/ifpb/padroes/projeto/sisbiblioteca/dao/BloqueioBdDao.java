/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.dao;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Bloqueio;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.enums.EstadoBloqueioEnum;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author kieckegard
 */
public class BloqueioBdDao implements BloqueioDao {

    @Override
    public void inserir(Bloqueio obj) {
        String sql = "INSERT INTO bloqueio(alunoMatricula,dataInicio,dataFim,status)"
                + " VALUES(?,?,?,?) RETURNING id";
        
        try {
            PreparedStatement pstm = FactoryConnection.createConnection().prepareStatement(sql);
            
            int i = 1;
            
            pstm.setString(i++, obj.getAluno().getMatricula());
            pstm.setDate(i++, java.sql.Date.valueOf(obj.getDataInicio()));
            pstm.setDate(i++, java.sql.Date.valueOf(obj.getDataFim()));
            pstm.setInt(i++, obj.getEstado().getId());
            
            ResultSet rs = pstm.executeQuery();
            
            if(rs.next())
                obj.setId(rs.getInt("id"));
        }
        catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void alterarEstado(Integer bloqueioId, EstadoBloqueioEnum estado) {
        String sql = "UPDATE bloqueio SET status = ? WHERE id = ?";
        
        try {
            PreparedStatement pstm = FactoryConnection.createConnection().prepareStatement(sql);
            
            int i = 1;
            
            pstm.setInt(i++, estado.getId());
            pstm.setInt(i++, bloqueioId);
            
            pstm.executeUpdate();
        }
        catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Bloqueio> lista() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Bloqueio formaBloqueio(ResultSet rs) throws SQLException{
        return null;
    }

    @Override
    public void verificaEDesbloqueiaAlunos() {
        String sql = "UPDATE aluno SET idEstado = 1 FROM Bloqueio b"
                + " WHERE matricula = b.alunoMatricula AND b.datafim <= current_date AND b.status = 0 RETURNING b.id";
        
        try {
            PreparedStatement pstm = FactoryConnection.createConnection().prepareStatement(sql);
            
            ResultSet rs = pstm.executeQuery();
            
            if(rs.next()) {
                alterarEstado(rs.getInt("id"), EstadoBloqueioEnum.FINALIZADO);
            }
        }
        catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    
}
