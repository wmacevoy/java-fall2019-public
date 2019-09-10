/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.wmacevoy.billiard;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author wmacevoy
 */
public class AppTest {
    
    public AppTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }


    /**
     * Test of main method, of class App.
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        App.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of read method, of class App.
     */
    @Test
    public void testRead() {
        System.out.println("read");
        App instance = new App();
        instance.read();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testSample1() {
        App app = new App();
        app.a=100;
        app.b=100;
        app.s=1;
        app.m=1;
        app.n=1;
        app.solve();
        assertEquals(app.A,45.00,0.01);
        assertEquals(app.velocity,141.42,0.01);
    }
 
    /**
     * Test of finished method, of class App.
     */
    @Test
    public void testFinished() {
        System.out.println("finished");
        App instance = new App();
        boolean expResult = false;
        boolean result = instance.finished();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of solve method, of class App.
     */
    @Test
    public void testSolve() {
        System.out.println("solve");
        App instance = new App();
        instance.solve();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of write method, of class App.
     */
    @Test
    public void testWrite() {
        System.out.println("write");
        App instance = new App();
        instance.write();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
