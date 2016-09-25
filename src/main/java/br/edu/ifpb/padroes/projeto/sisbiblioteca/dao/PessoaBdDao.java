/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.dao;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Endereco;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.IPessoa;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kieckegard
 */
public class PessoaBdDao implements PessoaDao {

    private final EnderecoDao enderecoBdDao;

    public PessoaBdDao() {
        enderecoBdDao = FactoryProvider.createFactory(FactoryProvider.jdbc).getEnderecoDao();
    }

    @Override
    public void adicionarPessoa(IPessoa obj) {
        String sql = "INSERT INTO pessoa VALUES(?,?,?,?)";

        try {
            PreparedStatement pstm = FactoryConnection.getInstance().prepareStatement(sql);

            int i = 1;

            pstm.setString(i++, obj.getCpf());
            pstm.setString(i++, obj.getNome());
            pstm.setDate(i++, java.sql.Date.valueOf(obj.getDataNascimento()));

            enderecoBdDao.adicionarEndereco(obj.getEndereco());

            pstm.setInt(i++, obj.getEndereco().getId());

            pstm.executeUpdate();

        }
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PessoaBdDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void removerPessoa(String cpf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizarPessoa(IPessoa obj) {
        String sql = "UPDATE pessoa SET nome = ?, datanascimento = ? WHERE cpf = ?";
        
        try {
            PreparedStatement pstm = FactoryConnection.getInstance().prepareStatement(sql);
            
            int i = 1;
            
            pstm.setString(i++, obj.getNome());
            pstm.setDate(i++, java.sql.Date.valueOf(obj.getDataNascimento()));
            pstm.setString(i++, obj.getCpf());
            
            pstm.executeUpdate();
            enderecoBdDao.atualizarEndereco(obj.getEndereco());
        }
        catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

}
