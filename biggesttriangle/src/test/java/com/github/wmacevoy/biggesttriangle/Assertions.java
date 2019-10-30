/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.wmacevoy.biggesttriangle;

import java.util.function.Supplier;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author wmacevoy
 */
public class Assertions {

    public static final double TOLERANCE = 1e-9;

    static void assertEquals(double expect, double result, Supplier<String> msg) {
        double error = Math.abs(result - expect);
        if (error <= TOLERANCE) {
            return;
        }
        fail("expect=" + expect + " result=" + result + " error=" + error + " tolerance=" + TOLERANCE + " msg=" + msg.get());
    }

    static void assertEquals(Point a, Point b, Supplier<String> msg) {
        if (a == null || b == null) {
            assertTrue(a == null && b == null,msg);
        } else {
            assertEquals(a.x, b.x,msg);
            assertEquals(a.y, b.y,msg);
        }
    }

    static void assertEquals(Line alpha, Line beta, Supplier<String> msg) {
        assertTrue(alpha.intersect(beta) == alpha, msg);
    }
    
    static void assertEquals(Iterable<Line> a, Iterable<Line> b, Supplier<String> msg) {
        var ai = a.iterator();
        var bi = b.iterator();
        for (;;) {
            boolean an = ai.hasNext();
            boolean bn = bi.hasNext();
            assertTrue(an == bn,msg);
            if (an == false) break;
            var al = ai.next();
            var bl = bi.next();
            assertEquals(al,bl,msg);
        }
    }
    
}
