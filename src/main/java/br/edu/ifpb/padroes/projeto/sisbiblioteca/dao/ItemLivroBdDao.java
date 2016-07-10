/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.dao;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.ItemLivro;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Livro;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.enums.EstadoLivroEnum;
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
public class ItemLivroBdDao implements Dao<ItemLivro, Long>
{
    
    private Dao<Livro, Long> livroBdDao;
    
    public ItemLivroBdDao(){
        livroBdDao = new LivroBdDao();
    }

    @Override
    public void add(ItemLivro obj)
    {   
        String sql = "INSERT INTO itemLivro VALUES(?,?,?)";
        
        try
        {
            PreparedStatement pstm = FactoryConnection.createConnection().prepareStatement(sql);
            
            livroBdDao.add(obj.getItemLivro());
            
            int i=1;
            
            pstm.setLong(i++, obj.getItemLivro().getIsbn());
            pstm.setInt(i++, obj.getEstoque());
            pstm.setInt(i++, obj.getEstadoIndex());
            
            pstm.executeUpdate();
        }
        catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(ItemLivroBdDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @Override
    public void rem(ItemLivro obj)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(ItemLivro obj)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItemLivro get(Long obj)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ItemLivro> list()
    {
        String sql = "SELECT * FROM itemLivro";
        List<ItemLivro> itensLivros = new ArrayList<>();
        
        try
        {
            PreparedStatement pstm = FactoryConnection.createConnection().prepareStatement(sql);
            
            ResultSet rs = pstm.executeQuery();
            
            while(rs.next())
                itensLivros.add(formaItemLivro(rs));
            
            return itensLivros;
        }
        catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(ItemLivroBdDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return itensLivros;
    }
    
    private ItemLivro formaItemLivro(ResultSet rs) throws SQLException{
        long isbn = rs.getLong("isbnLivro");
        int estoque = rs.getInt("estoque");
        int estado = rs.getInt("idEstado");
        EstadoLivroEnum estadoEnum = null;
        
        for(EstadoLivroEnum estadoEnumAux : EstadoLivroEnum.values()){
            if(estado == estadoEnumAux.getIndex())
                estadoEnum = estadoEnumAux;
        }
        
        Livro livro = livroBdDao.get(isbn);
        
        ItemLivro item = new ItemLivro(livro, estoque, estadoEnum);
        
        return item;
    }
    
}
