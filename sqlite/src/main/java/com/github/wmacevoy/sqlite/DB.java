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
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB {
    
    
    public static String DEFAULT_DB = "tests.db";
    public static String DEFAULT_URL = "jdbc:sqlite:" + DEFAULT_DB;

    public final String url;
    DB() { this(DEFAULT_URL); }
    DB(String url) { this.url = url; }


    private Connection connection = null;

    public Connection getConnection() {
        if (connection == null) {
            synchronized (this) {
                if (connection == null) {
                    try {
                        connection = DriverManager.getConnection(url);
                    } catch (SQLException ex) {
                        Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return connection;
    }

    private HashMap< String, PreparedStatement> preparedStatementCache = null;
    public static final int SQL_STATEMENT_TIMEOUT_SECONDS = 10;

    public PreparedStatement getPreparedStatement(String sql) {
        if (preparedStatementCache == null) {
            synchronized (this) {
                if (preparedStatementCache == null) {
                    preparedStatementCache = new HashMap< String, PreparedStatement>();
                }
            }
        }

        PreparedStatement preparedStatement = preparedStatementCache.get(sql);
        if (preparedStatement == null) {
            synchronized (this) {
                preparedStatement = preparedStatementCache.get(sql);
                if (preparedStatement == null) {
                    try {
                        Connection connection = getConnection();
                        int keyMode = sql.startsWith("insert")
                                ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS;

                        preparedStatement
                                = connection.prepareStatement(sql, keyMode);
                        preparedStatement.setQueryTimeout(SQL_STATEMENT_TIMEOUT_SECONDS);
                        preparedStatementCache.put(sql, preparedStatement);
                    } catch (SQLException ex) {
                        Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        }
        return preparedStatement;
    }

    ResultSet sql(String sql, Object... objects) {
        try {
            PreparedStatement preparedStatement = getPreparedStatement(sql);
            int index = 1;
            for (Object object : objects) {
                if (object instanceof Boolean) {
                    preparedStatement.setBoolean(index, (Boolean) object);
                } else if (object instanceof Integer) {
                    preparedStatement.setInt(index, (Integer) object);
                } else if (object instanceof Long) {
                    preparedStatement.setLong(index, (Long) object);
                } else if (object instanceof Double) {
                    preparedStatement.setDouble(index, (Double) object);
                } else if (object instanceof String) {
                    preparedStatement.setString(index, (String) object);
                } else if (object instanceof Blob) {
                    preparedStatement.setBlob(index,(Blob) object);
                } else {
                    throw new IllegalStateException("can't set type " + object.getClass().getName());
                }
                ++index;
            }
            if (sql.startsWith("insert")) {
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                return resultSet;
            } else {
                ResultSet resultSet = preparedStatement.executeQuery();
                return resultSet;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    Long longResult(ResultSet resultSet) {
        try {
            if (resultSet != null && resultSet.next()) return resultSet.getLong(1);
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    String stringResult(ResultSet resultSet) {
        try {
            if (resultSet != null && resultSet.next()) return resultSet.getString(1);
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private Statement statement = null;

    public Statement getStatement() {
        if (statement == null) {
            synchronized (this) {
                if (statement == null) {
                    try {
                        Connection connection = getConnection();
                        statement = connection.createStatement();
                        statement.setQueryTimeout(SQL_STATEMENT_TIMEOUT_SECONDS);
                    } catch (SQLException ex) {
                        Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return statement;
    }

    void sql(String command) {
        try {
            Statement statement = getStatement();
            statement.executeUpdate(command);
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void reset() {
        sql("drop table if exists person");
        sql("create table person (id integer primary key, name string)");
    }
    
    long insertPerson(String name) {
        return longResult(sql("insert into person (name) values (?)",name));
    }

    String getPerson(long id) {
        return stringResult(sql("select name from person where id=?",id));
    }

    void run() {
        reset();
        long aliceId = insertPerson("alice");
        System.out.println("aliceId=" + aliceId);
        long bobId = insertPerson("bob");
        System.out.println("alice name=" + getPerson(aliceId));
    }

}
