/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.wmacevoy.strings;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author wmacevoy
 */
public class AppTest {
    

    @Test
    public void testAddSuffix() {
        System.out.println("addSuffix");
        App instance = new App();
        instance.addSuffix("alice");
        instance.addSuffix("bob");
        assertEquals("alicebob",instance.getResult());
    }

    /**
     * Test of addPrefix method, of class App.
     */
    @Test
    public void testAddPrefix() {
        System.out.println("addPrefix");
        App instance = new App();
        instance.addPrefix("alice");
        instance.addPrefix("bob");
        assertEquals("bobalice",instance.getResult());
    }

    
}
