/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.nsinova.teste.conexao;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 *
 * @author miqueias.nadaluti
 */
public class Conexao {

    private Connection connection;

    public Conexao() throws Exception {
        Class.forName("org.postgresql.Driver").newInstance();
        connection = DriverManager.getConnection(
                "jdbc:postgresql://LocalHost/Banco?"
                + "user=User&password=Senha");
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void rollback() throws Exception {
        if (connection != null && !connection.isClosed() && !connection.getAutoCommit()) {
            connection.rollback();
            connection.setAutoCommit(true);
        }
    }

    public void autoCommit() throws Exception {
        connection.setAutoCommit(false);
    }

    public void commit() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.commit();
            connection.setAutoCommit(true);
        }
    }

    public Boolean isClosed() throws Exception {
        if (connection != null) {
            return connection.isClosed();
        }
        return null;
    }

    public void close() throws Exception {
        connection.close();
    }
}
