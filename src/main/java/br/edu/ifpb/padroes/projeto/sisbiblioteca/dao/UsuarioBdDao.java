/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.dao;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Endereco;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.IPessoa;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Usuario;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.enums.TipoUsuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kieckegard
 */
public class UsuarioBdDao implements UsuarioDao {

    private EnderecoDao enderecoDao;
    private PessoaDao pessoaDao;

    public UsuarioBdDao() {
        enderecoDao = FactoryProvider.createFactory(1).getEnderecoDao();
        pessoaDao = FactoryProvider.createFactory(1).getPessoaDao();
    }

    @Override
    public void cadastrarUsuario(Usuario obj) {
        String sql = "INSERT INTO usuario VALUES(?,?,?,?)";
        pessoaDao.adicionarPessoa(obj);
        try {
            PreparedStatement pstm = FactoryConnection.createConnection().prepareStatement(sql);

            int i = 1;

            pstm.setString(i++, obj.getMatricula());
            pstm.setString(i++, obj.getSenha());
            pstm.setInt(i++, obj.getTipoUsuario().getValue());
            pstm.setString(i++, obj.getCpf());

            pstm.executeUpdate();
        }
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsuarioBdDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void removerUsuario(String matricula) {
        String sql = "DELETE FROM usuario WHERE matricula = ?";
        
        try {
            PreparedStatement pstm = FactoryConnection.createConnection().prepareStatement(sql);
            
            pstm.setString(1, matricula);
            
            pstm.executeUpdate();
        }
        catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void atualizarUsuario(Usuario obj) {
        String sql = "UPDATE usuario SET senha = ? WHERE matricula = ?";
        
        try {
            PreparedStatement pstm = FactoryConnection.createConnection().prepareStatement(sql);
            
            int i = 1;
            
            pstm.setString(i++, obj.getSenha());
            pstm.setString(i++, obj.getMatricula());
            
            pstm.executeUpdate();
            pessoaDao.atualizarPessoa(obj);
        }
        catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Usuario> listarUsuarios() {
        String sql = "SELECT * FROM usuario u JOIN Pessoa p ON u.cpfPessoa = p.cpf";
        List<Usuario> usuarios = new ArrayList<>();

        try {
            PreparedStatement pstm = FactoryConnection.createConnection().prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                usuarios.add(formaUsuario(rs));
            }

            return usuarios;
        }
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsuarioBdDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return usuarios;
    }

    private Usuario formaUsuario(ResultSet rs) throws SQLException {
        //Usuário
        String matricula = rs.getString("matricula");
        String senha = rs.getString("senha");
        TipoUsuario tipoUsuario = TipoUsuario.getEnum(rs.getInt("idTipoUsuario"));

        //Pessoa
        String cpf = rs.getString("cpf");
        String nome = rs.getString("nome");
        LocalDate dataNascimento = rs.getDate("dataNascimento").toLocalDate();

        Usuario usuario = new Usuario(cpf, nome, dataNascimento, matricula, senha, tipoUsuario);

        Endereco endereco = enderecoDao.recuperarEnderecoPorPessoaCpf(usuario.getCpf());

        usuario.setEndereco(endereco);

        return usuario;
    }
}
