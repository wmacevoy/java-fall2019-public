package problem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import static org.junit.jupiter.api.Assertions.*;
import static problem.Assertions.*;
import org.junit.jupiter.api.*;


/**
 *
 * @author wmacevoy
 */
public class PointTest {
    @Test
    public void testDistance() {
        assertEquals(5.0,new Point(0,0).distance(new Point(3,4)),TOLERANCE);
    }
    
}
