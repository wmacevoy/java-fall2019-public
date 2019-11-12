/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.wmacevoy.collections;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

/**
 *
 * @author wmacevoy
 */
public class ListEx {

    PrintStream out = System.out;
    int direction = -1;
    LinkedList<String> lls = new LinkedList<String>();

    class CompareLowerCaseStrings implements Comparator<String> {

        @Override
        public int compare(String a, String b) {
            return direction * a.toLowerCase().compareTo(b.toLowerCase());
        }

    }

    void listOfStrings() {
        var lls = new ArrayList<String>();
        lls.add("alice");
        lls.add("bob");

        for (var name : lls) {
            out.println(name);
        }

        lls.forEach((name) -> {
            out.println(name);
        });
        lls.sort((a,b)->direction * a.toLowerCase().compareTo(b.toLowerCase()));
        lls.sort(new CompareLowerCaseStrings());
        lls.sort(new Comparator<String>() {

        @Override
        public int compare(String a, String b) {
            return direction * a.toLowerCase().compareTo(b.toLowerCase());
        }

    });

    }

    public static void main(String[] args) {
        ListEx ex = new ListEx();
        ex.listOfStrings();
    }

}
