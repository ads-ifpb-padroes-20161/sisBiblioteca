/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.dao;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Endereco;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Pessoa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kieckegard
 */
public class EnderecoBdDao implements Dao<Endereco, Pessoa> {

    private PreparedStatement pstm;

    public EnderecoBdDao() {
    }

    @Override
    public void add(Endereco e) {
        String sql = "INSERT INTO endereco(pais,cidade,estado,bairro,rua,numero) VALUES(?,?,?,?,?,?) RETURNING id";

        try {
            pstm = FactoryConnection.createConnection().prepareStatement(sql);

            int i = 1;
            pstm.setString(i++, e.getPais());
            pstm.setString(i++, e.getEstado());
            pstm.setString(i++, e.getCidade());
            pstm.setString(i++, e.getBairro());
            pstm.setString(i++, e.getRua());
            pstm.setInt(i++, e.getNumero());

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                e.setId(rs.getInt("id"));
            }

        }
        catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void rem(Endereco e) {
        String sql = "DELETE FROM endereco WHERE id = ?";

        try {
            pstm = FactoryConnection.createConnection().prepareStatement(sql);

            pstm.setInt(1, e.getId());

            pstm.executeUpdate();
        }
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EnderecoBdDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Endereco get(Pessoa p) {
        String sql = "SELECT * FROM Endereco e JOIN Pessoa p ON e.id = p.idEndereco WHERE p.cpf = ?";

        try {
            pstm = FactoryConnection.createConnection().prepareStatement(sql);

            pstm.setString(1, p.getCpf());

            ResultSet rs = pstm.executeQuery();

            Endereco endereco = null;

            if (rs.next()) {
                endereco = formaEndereco(rs);
            }

            return endereco;
        }
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EnderecoBdDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    private Endereco formaEndereco(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String pais = rs.getString("pais");
        String estado = rs.getString("estado");
        String cidade = rs.getString("cidade");
        String bairro = rs.getString("bairro");
        String rua = rs.getString("rua");
        int numero = rs.getInt("numero");

        Endereco endereco = new Endereco(pais, estado, cidade, bairro, rua, numero);
        endereco.setId(id);

        return endereco;
    }

    @Override
    public void update(Endereco obj) {
        String sql = "UPDATE endereco SET pais = ?, estado = ?, cidade = ?, bairro = ?, rua = ?, numero = ?"
                + " WHERE id = ? ";
        
        try {
            PreparedStatement pstm = FactoryConnection.createConnection().prepareStatement(sql);
            
            int i = 1;
            
            pstm.setString(i++, obj.getPais());
            pstm.setString(i++, obj.getEstado());
            pstm.setString(i++, obj.getCidade());
            pstm.setString(i++, obj.getBairro());
            pstm.setString(i++, obj.getRua());
            pstm.setInt(i++, obj.getNumero());
            pstm.setInt(i++, obj.getId());
            
            pstm.executeUpdate();
        }
        catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Endereco> list() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
