/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.wmacevoy.biggesttriangle;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author wmacevoy
 */
public class TriangleTest {
    /**
     * Test of perimeter method, of class Triangle.
     */
    @Test
    public void testPerimeter() {
        Line alpha = new Line(new Point(0,0),new Point(1,0));
        Line beta = new Line(new Point(0,0),new Point(0,1));
        Line gamma = new Line(new Point(0,4), new Point(3,0));
        Triangle triangle = new Triangle(alpha,beta,gamma);
        double expResult = 3+4+5;
        double result = triangle.perimeter();
        assertEquals(expResult, result, 1e-9);
    }
    
}
