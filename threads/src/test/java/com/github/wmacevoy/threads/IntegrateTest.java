/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.wmacevoy.threads;

import org.junit.*;
import java.time.Instant;
import java.time.Duration;
import static org.junit.Assert.*;

/**
 *
 * @author wmacevoy
 */
public class IntegrateTest {

    static Integrate getI() {
        var I = new Integrate();
        I.a = 0;
        I.b = Math.PI / 2;
        I.n = 100;
        return I;
    }

    static double exact(double a, double b) {
        return Math.cos(a) - Math.cos(b);
    }

    /**
     * Test of slice method, of class Integrate.
     */
    @Test
    public void testSlice() {
        System.out.println("slice(i)");
        var I = getI();
        int i = 23;
        double x0 = I.x(i);
        double x1 = I.x(i + 1);
        double bit = I.slice(i);
        double exactBit = exact(x0, x1);
        assertEquals(exactBit, bit, 0.001);
    }

    /**
     * Test of eval method, of class Integrate.
     */
    @Test
    public void testEval_0args() {
        System.out.println("eval()");
        var I = getI();
        double expResult = exact(I.a, I.b);
        double result = I.eval();
        assertEquals(expResult, result, 0.001);
    }

    /**
     * Test of eval method, of class Integrate.
     */
    @Test
    public void testEval_int_int() {
        System.out.println("eval(i0,i1)");
        int i0 = 23;
        int i1 = 87;
        var I = getI();
        double x0 = I.x(i0);
        double x1 = I.x(i1);
        double expResult = exact(x0, x1);
        double result = I.eval(i0, i1);
        assertEquals(expResult, result, 0.001);
    }

    /**
     * Test of parallelEval method, of class Integrate.
     */
    @Test
    public void testParallelEval_int() {
        System.out.println("parallelEval(threads)");
        int threads = 8;
        var I = getI();
        double expResult = exact(I.a, I.b);
        double result = I.parallelEval(threads);
        assertEquals(expResult, result, 0.001);
    }

    /**
     * Test of parallelEval method, of class Integrate.
     */
    @Test
    public void testParallelEval_0args() {
        System.out.println("parallelEval()");
        var I = getI();
        double expResult = exact(I.a, I.b);
        double result = I.parallelEval();
        assertEquals(expResult, result, 0.001);
    }

    double timed(Integrate I, boolean parallel) {
        Instant t0 = Instant.now();
        double result = parallel ? I.parallelEval() : I.eval();
        Instant t1 = Instant.now();
        double exact = exact(I.a, I.b);
        assertEquals(exact, result, 0.001);
        double duration = Duration.between(t0, t1).toMillis() * 0.001;
        return duration;
    }

    @Test
    public void testSpeedup() {
        Integrate I = new Integrate();
        I.a = 0;
        I.b = Math.PI / 2;
        I.n = 100_000_000;

        double duration = timed(I, false);
        double parallelDuration = timed(I, true);
        double ratio = parallelDuration / duration;
        double perfect = 1.0/Runtime.getRuntime().availableProcessors();
        assertTrue(ratio < 0.66);
        System.out.println("duration = " + duration + " parallelDuration = " + parallelDuration + " ratio = " + ratio + " perfect = " + perfect);
    }

}
