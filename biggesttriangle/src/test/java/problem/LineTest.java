package problem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static problem.Assertions.*;

/**
 *
 * @author wmacevoy
 */
public class LineTest {

    Point intersect(Line a, Line b) {
        Point ab = (Point) a.intersect(b);
        Point ba = (Point) b.intersect(a);
        assertEquals(ab, ba, NO_MESSAGE);
        return ab;
    }

    @Test
    public void testIntersect() {
        Line diag = new Line(new Point(0, 0), new Point(1, 2));
        Line vert = new Line(new Point(10, 0), new Point(10, 100));
        Line horz = new Line(new Point(0, 7), new Point(-3, 7));

        assertEquals(new Point(10, 20), intersect(diag, vert), NO_MESSAGE);
        assertEquals(new Point(3.5, 7), intersect(diag, horz), NO_MESSAGE);
        assertEquals(new Point(10, 7), intersect(vert, horz), NO_MESSAGE);
    }

    @Test
    void testIntersectEmpty() {
        Line diag0 = new Line(new Point(0, 0), new Point(1, 2));
        Line diag1 = new Line(new Point(0, 1), new Point(1, 3));
        assertTrue(diag0.intersect(diag1) == null);
        assertTrue(diag1.intersect(diag0) == null);
    }

    @Test
    void testIntersectLine() {
        Line diag0 = new Line(new Point(0, 0), new Point(1, 2));
        Line diag1 = new Line(new Point(0.5, 1), new Point(3, 6));
        assertTrue(diag0.intersect(diag1) == diag0);
        assertTrue(diag1.intersect(diag0) == diag1);
    }

}
