package problem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.*;
import java.io.*;
import java.util.function.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author wmacevoy
 */
public class Assertions {

    public static final double TOLERANCE = 1e-9;
    public static final Supplier<String> NO_MESSAGE = ()->"";

    static void assertEquals(Point a, Point b, Supplier<String> msg) {
        if (a == null || b == null) {
            assertTrue(a == null && b == null,msg);
        } else {
            org.junit.jupiter.api.Assertions.assertEquals(a.x, b.x,TOLERANCE,msg);
            org.junit.jupiter.api.Assertions.assertEquals(a.y, b.y,TOLERANCE,msg);
        }
    }

    static void assertEquals(Line alpha, Line beta, Supplier<String> msg) {
        assertTrue(alpha.intersect(beta) == alpha, msg);
    }
}
