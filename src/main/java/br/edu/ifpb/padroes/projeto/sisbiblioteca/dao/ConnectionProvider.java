/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.dao;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author kieckegard
 */
public final class ConnectionProvider {

    private static final String url = "jdbc:postgresql://localhost:5432/sisBiblioteca";
    private static final String username = "postgres";
    private static final String password = "123456";
    private static final String driver = "org.postgresql.Driver";
    
    private static ConnectionProvider provider;
    private BasicDataSource connectionPool;
    
    private ConnectionProvider() {

    }

    public static synchronized ConnectionProvider getInstance() {
        if (provider == null) 
            provider = new ConnectionProvider();
        return provider;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        
        if ( connectionPool == null || connectionPool.isClosed() ){

            try{

                connectionPool = new BasicDataSource();
                connectionPool.setUsername(username);
                connectionPool.setPassword(password);
                connectionPool.setDriverClassName(driver);
                connectionPool.setUrl(url);

            } catch ( IndexOutOfBoundsException ex ){
                connectionPool.close();
                return null;
            }

        }

        return connectionPool.getConnection();
    }
}
