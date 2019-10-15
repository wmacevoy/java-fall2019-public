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
import java.util.ArrayList;
import java.util.HashMap;

public interface DBI {

    Connection getConnection() throws SQLException;

    PreparedStatement getPreparedStatement(String sql) throws SQLException;

    Statement getStatement() throws SQLException;

    default ResultSet sql(String sql, Object... objects) throws SQLException {
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
                preparedStatement.setBlob(index, (Blob) object);
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
    }

    default void sql(String command) throws SQLException {
        Statement statement = getStatement();
        statement.executeUpdate(command);
    }

    default boolean booleanResult(ResultSet resultSet) throws SQLException {
        if (resultSet != null && resultSet.next()) {
            return resultSet.getBoolean(1);
        }
        throw new SQLException("missing boolean result");
    }

    default long longResult(ResultSet resultSet) throws SQLException {
        if (resultSet != null && resultSet.next()) {
            return resultSet.getLong(1);
        }
        throw new SQLException("missing long result");
    }

    default double doubleResult(ResultSet resultSet) throws SQLException {
        if (resultSet != null && resultSet.next()) {
            return resultSet.getDouble(1);
        }
        throw new SQLException("missing double result");
    }

    default String stringResult(ResultSet resultSet) throws SQLException {
        if (resultSet != null && resultSet.next()) {
            return resultSet.getString(1);
        }
        throw new SQLException("missing string result");
    }

    default Blob blobResult(ResultSet resultSet) throws SQLException {
        if (resultSet != null && resultSet.next()) {
            return resultSet.getBlob(1);
        }
        throw new SQLException("missing blob result");
    }

    default Object[] results(ResultSet resultSet, Object... args) throws SQLException {
        if (resultSet != null && resultSet.next()) {
            Object index = Integer.valueOf(0);
            ArrayList<Object> ans = new ArrayList<Object>();
            int i = 0;
            while (i < args.length) {
                if (args[i] instanceof Integer) {
                    index = args[i];
                    ans.add(resultSet.getObject((Integer) index, (Class) args[i + 1]));
                    ++i;
                } else if (args[i] instanceof String) {
                    index = args[i];
                    ans.add(resultSet.getObject((String) index, (Class) args[i + 1]));
                    ++i;
                } else {
                    index = 1 + (Integer) index;
                    ans.add(resultSet.getObject((Integer) index, (Class) args[i]));
                }
                ++i;
            }
            return ans.toArray();
        } else {
            throw new SQLException("missing result");
        }
    }
}
