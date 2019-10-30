package problem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static org.junit.jupiter.api.Assertions.*;
import static problem.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.*;
import java.io.*;

/**
 *
 * @author wmacevoy
 */
class TestStream extends PrintStream {

    static TestStream create() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        return new TestStream(bos);
    }
    final ByteArrayOutputStream bos;

    TestStream(ByteArrayOutputStream bos) {
        super(bos);
        this.bos = bos;
    }

    @Override
    public void close() {
        super.close();
        try {
            bos.close();
        } catch (IOException ex) {
            throw new AssertionError(ex);
        }
    }

    @Override
    public String toString() {
        return bos.toString();
    }
}

class PIO {

    Prob prob;
    String input, output;

    PIO(Prob prob, String input, String output) {
        this.prob = prob;
        this.input = input;
        this.output = output;
        prob.in = new Scanner(input);
        prob.out = TestStream.create();
    }
}

public class ProbTest {

    PIO ex1() {
        Prob prob = new Prob();
        prob.lines.add(new Line(new Point(0, 0), new Point(0, 1)));
        prob.lines.add(new Line(new Point(0, 0), new Point(1, 0)));
        prob.lines.add(new Line(new Point(0, 1), new Point(1, 0)));
        prob.maxPerimeter = 3.4142135624;
        String EOL = System.lineSeparator();
        String input = "3" + EOL
                + "0 0 0 1" + EOL
                + "0 0 1 0" + EOL
                + "0 1 1 0" + EOL;
        String output = "3.414214" + EOL;
        return new PIO(prob, input, output);
    }

    PIO ex2() {
        Prob prob = new Prob();
        prob.lines.add(new Line(new Point(0, 0), new Point(0, 1)));
        prob.lines.add(new Line(new Point(0, 0), new Point(1, 0)));
        prob.lines.add(new Line(new Point(0, 0), new Point(1, 1)));
        prob.maxPerimeter = 0;
        String EOL = System.lineSeparator();
        String input = "3" + EOL
                + "0 0 0 1" + EOL
                + "0 0 1 0" + EOL
                + "0 0 1 1" + EOL;
        String output = "no triangle" + EOL;
        return new PIO(prob, input, output);
    }

    PIO ex3() {
        Prob prob = new Prob();
        prob.lines.add(new Line(new Point(0, 0), new Point(0, 1)));
        prob.lines.add(new Line(new Point(0, 4), new Point(3, 0)));
        prob.lines.add(new Line(new Point(0, 0), new Point(1, 0)));
        prob.lines.add(new Line(new Point(-1, -1), new Point(1, 1)));
        prob.maxPerimeter = 12;
        String EOL = System.lineSeparator();
        String input = "4" + EOL
                + "0 0 0 1" + EOL
                + "0 4 3 0" + EOL
                + "0 0 1 0" + EOL
                + "-1 -1 1 1" + EOL;
        String output = "12.000000" + EOL;
        return new PIO(prob, input, output);
    }

    final int N = 3;

    PIO ex(int k) {
        switch (k) {
            case 1:
                return ex1();
            case 2:
                return ex2();
            case 3:
                return ex3();
        }
        throw new IndexOutOfBoundsException("k=" + k);
    }

    void testRead(int k) {
        PIO expect = ex(k);
        Prob result = new Prob();
        result.in = expect.prob.in;
        result.read();
        assertEquals(expect.prob.lines, result.lines, () -> "k=" + k);
    }

    @Test
    public void testRead() {
        for (int k = 1; k <= N; ++k) {
            testRead(k);
        }
    }

    void testWrite(int k) {
        PIO pio = ex(k);
        pio.prob.write();
        pio.prob.out.close();
        assertEquals(pio.output, pio.prob.out.toString(), () -> "k=" + k);
    }

    @Test
    public void testWrite() {
        for (int k = 1; k <= N; ++k) {
            testWrite(k);
        }

    }

    void testSolve(int k) {
        PIO expect = ex(k);
        PIO result = ex(k);
        result.prob.maxPerimeter = Double.NaN;
        result.prob.solve();
        assertEquals(expect.prob.maxPerimeter, result.prob.maxPerimeter, TOLERANCE, () -> "k=" + k);
    }

    @Test
    public void testSolve() {
        for (int k = 1; k <= N; ++k) {
            testSolve(k);
        }
    }

    void testRun(int k) {
        var pio = ex(k);
        Prob prob = new Prob();
        prob.in = pio.prob.in;
        prob.out = pio.prob.out;
        prob.run();
        prob.out.close();
        assertEquals(pio.output, pio.prob.out.toString(), () -> "k=" + k);
    }

    /**
     * Test of run method, of class Prob.
     */
    @Test
    public void testRun() {
        for (int k = 1; k <= N; ++k) {
            testRun(k);
        }
    }

}
