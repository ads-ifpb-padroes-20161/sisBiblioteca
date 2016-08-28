/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.livro;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.FactoryConnection;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.livro.Livro;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.livro.LivroNulo;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.livro.LivroPadrao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kieckegard
 */
public class LivroBdDao implements LivroDao {

    @Override
    public void cadastrarLivro(Livro obj) {
        String sql = "INSERT INTO livro VALUES(?,?,?,?,?)";

        try {
            PreparedStatement pstm = FactoryConnection.createConnection().prepareStatement(sql);

            int i = 1;

            pstm.setLong(i++, obj.getIsbn());
            pstm.setString(i++, obj.getTitulo());
            pstm.setString(i++, obj.getAutor());
            pstm.setString(i++, obj.getDescricao());
            pstm.setInt(i++, obj.getEstoque());

            pstm.executeUpdate();
        }
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LivroBdDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Livro recuperarLivroPorIsbn(Long obj) {
        String sql = "SELECT * FROM livro WHERE isbn = ?";
        try {
            PreparedStatement pstm = FactoryConnection.createConnection().prepareStatement(sql);
            pstm.setLong(1, obj);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                return formaLivro(rs);
            }
        }
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LivroBdDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new LivroNulo();
    }
    
    private List<Livro> selectLivro(String sql) {
        List<Livro> livros = new ArrayList<>();
        try {
            PreparedStatement pstm = FactoryConnection.createConnection().prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                livros.add(formaLivro(rs));
            }

            return livros;
        }
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LivroBdDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return livros;
    }

    @Override
    public List<Livro> listarLivros() {
        String sql = "SELECT * FROM livro";
        return selectLivro(sql);
    }

    private Livro formaLivro(ResultSet rs) throws SQLException {
        long isbn = rs.getLong("isbn");
        String titulo = rs.getString("titulo");
        String autor = rs.getString("autor");
        String descricao = rs.getString("descricao");
        int estoque = rs.getInt("estoque");

        Livro l = new LivroPadrao(isbn, titulo, autor, descricao, estoque);

        return l;
    }

    @Override
    public void atualizarEstoque(Livro livro) {
        
        String sql = "UPDATE livro SET estoque = ? WHERE isbn = ?";
        
        try {
            PreparedStatement pstm = FactoryConnection.createConnection().prepareStatement(sql);
            
            int i = 1;
            
            pstm.setInt(i++, livro.getEstoque());
            pstm.setLong(i++, livro.getIsbn());
            
            pstm.executeUpdate();
        }
        catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Livro> listarLivrosDisponiveis() {
        String sql = "SELECT * FROM livro WHERE estoque > 0";
        return selectLivro(sql);
    }

    @Override
    public void removerLivro(Long isbn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}