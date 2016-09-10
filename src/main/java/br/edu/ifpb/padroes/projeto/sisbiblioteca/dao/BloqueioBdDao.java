/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.dao;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Bloqueio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author kieckegard
 */
public class BloqueioBdDao implements Dao<Bloqueio, Integer> {

    @Override
    public void add(Bloqueio obj) {
        String sql = "INSERT INTO bloqueio(alunoMatricula,dataInicio,dataFim)"
                + " VALUES(?,?,?) RETURNING id";
        
        try {
            PreparedStatement pstm = FactoryConnection.createConnection().prepareStatement(sql);
            
            int i = 1;
            
            pstm.setString(i++, obj.getAluno().getMatricula());
            pstm.setDate(i++, java.sql.Date.valueOf(obj.getDataInicio()));
            pstm.setDate(i++, java.sql.Date.valueOf(obj.getDataFim()));
            
            ResultSet rs = pstm.executeQuery();
            
            if(rs.next())
                obj.setId(rs.getInt("id"));
        }
        catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void rem(Bloqueio obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Bloqueio obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Bloqueio get(Integer obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Bloqueio> list() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
