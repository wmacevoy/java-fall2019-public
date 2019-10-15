/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.wmacevoy.sqlite;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author wmacevoy
 */
import static com.github.wmacevoy.sqlite.DB.DEFAULT_URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public class DefaultDB implements DBI {

    private String url;

    public String getURL() {
        return url;
    }

    private int timeout = Integer.MAX_VALUE;

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setURL(String url) {
        if (!url.equals(this.url)) {
            synchronized (this) {
                if (connection != null) {
                    throw new UnsupportedOperationException("cannot set url after connecting");
                }
                this.url = url;
            }
        }
    }

    private Connection connection = null;

    @Override
    public Connection getConnection() throws SQLException {
        if (connection == null) {
            synchronized (this) {
                if (connection == null) {
                    connection = DriverManager.getConnection(url);
                }
            }
        }
        return connection;
    }

    @Override
    public PreparedStatement getPreparedStatement(String sql) throws SQLException {
        Connection connection = getConnection();
        int keyMode = sql.startsWith("insert")
                ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS;

        PreparedStatement preparedStatement
                = connection.prepareStatement(sql, keyMode);
        if (timeout < Integer.MAX_VALUE) {
            preparedStatement.setQueryTimeout(timeout);
        }
        return preparedStatement;
    }

    @Override
    public Statement getStatement() throws SQLException {
        Statement statement = getConnection().createStatement();
        if (timeout < Integer.MAX_VALUE) {
            statement.setQueryTimeout(timeout);
        }
        return statement;
    }

    @Override
    public void sql(String command) throws SQLException {
        Statement statement = getStatement();
        statement.executeUpdate(command);
    }
}
