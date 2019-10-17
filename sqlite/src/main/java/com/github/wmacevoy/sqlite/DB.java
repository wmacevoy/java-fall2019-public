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
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB extends DefaultDB {

    public static String DEFAULT_DB = "tests.db";
    public static String DEFAULT_URL = "jdbc:sqlite:" + DEFAULT_DB;

    public final String url;

    DB() {
        this(DEFAULT_URL);
    }

    DB(String url) {
        this.url = url;
    }

    void reset() throws SQLException {
        sql("drop table if exists person");
        sql("create table person (id integer primary key, name string)");
    }

    long insertPerson(String name) throws SQLException {
        return longResult(sql("insert into person (name) values (?)", name));
    }

    String getPerson(long id) throws SQLException {
        return stringResult(sql("select name from person where id=?", id));
    }

    void run() throws SQLException {
        reset();
        long aliceId = insertPerson("alice");
        System.out.println("aliceId=" + aliceId);
        long bobId = insertPerson("bob");
        System.out.println("alice name=" + getPerson(aliceId));
    }

}
