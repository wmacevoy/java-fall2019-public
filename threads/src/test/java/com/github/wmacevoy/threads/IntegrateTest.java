/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.wmacevoy.threads;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author wmacevoy
 */
public class IntegrateTest {
    
    static double exact(double a, double b) {
        return Math.cos(a) - Math.cos(b);
    }
    
    
    
    public IntegrateTest() {
    }
    

    /**
     * Test of slice method, of class Integrate.
     */
    @Test
    public void testSlice() {
        System.out.println("slice");
        int i = 23;
        Integrate I = new Integrate();
        I.a = 0;
        I.b = Math.PI/2;
        I.n = 100;
        double x0=(I.a+(I.b-I.a)*i)/((double) I.n);
        double x1=(I.a+(I.b-I.a)*(i+1))/((double) I.n);
        double bit = I.slice(i);
        double exactBit = exact(x0,x1);
        assertEquals(exactBit, bit, 0.001);
    }

    /**
     * Test of eval method, of class Integrate.
     */
    @Test
    public void testEval_0args() {
        System.out.println("eval");
        Integrate I = new Integrate();
        I.a = 0;
        I.b = Math.PI;
        I.n = 100;
        double expResult = 2.0;
        double result = I.eval();
        assertEquals(expResult, result, 0.001);
    }

    /**
     * Test of eval method, of class Integrate.
     */
    @Test
    public void testEval_int_int() {
        System.out.println("eval");
        int i0 = 0;
        int i1 = 0;
        Integrate instance = new Integrate();
        double expResult = 0.0;
        double result = instance.eval(i0, i1);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parallelEval method, of class Integrate.
     */
    @Test
    public void testParallelEval_int() {
        System.out.println("parallelEval");
        int threads = 8;
        Integrate I = new Integrate();
        I.a = 0;
        I.b = Math.PI;
        I.n = 100;
        double expResult = 2.0;

       
        double result = I.parallelEval(threads);
        assertEquals(expResult, result, 0.001);
    }

    /**
     * Test of parallelEval method, of class Integrate.
     */
    @Test
    public void testParallelEval_0args() {
        System.out.println("parallelEval");
        Integrate instance = new Integrate();
        double expResult = 0.0;
        double result = instance.parallelEval();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
