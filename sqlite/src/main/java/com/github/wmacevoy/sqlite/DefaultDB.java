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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DefaultDB implements DBI {

    private String url;

    public String getURL() {
        return url;
    }

    private int sqlStatementTimeoutSeconds = Integer.MAX_VALUE;

    public int getSqlStatementTimeoutSeconds() {
        return sqlStatementTimeoutSeconds;
    }

    public void setSqlStatementTimeoutSeconds(int value) {
        this.sqlStatementTimeoutSeconds = value;
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
}
