/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.wmacevoy.billiard;

import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author wmacevoy
 */
public class App {
    Scanner in;
    PrintStream out;
    int a,b,s,n,m;
    double A,velocity;
    
    public static void main(String[] args) throws Exception {
        App app = new App();
        app.run();
    }

    private void run() {
        in = new Scanner(System.in);
        out = System.out;
        while (true) {
            read();
            if (finished()) break;
            solve();
            write();
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void read() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean finished() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void solve() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void write() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
