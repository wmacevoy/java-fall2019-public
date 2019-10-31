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
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;


public class ProbTest {

    final String EOL = System.lineSeparator();
    final double TIME_LIMIT = 3.0;

    class Target extends Prob {

        String input;
        String answer;
        ByteArrayOutputStream baos;

        String output() {
            return baos.toString(StandardCharsets.UTF_8);
        }

        Target(String input, String answer) {
            this.input = input.replace("\n", EOL);
            this.in = new Scanner(input);
            this.answer = answer.replace("\n", EOL);
            this.baos = new ByteArrayOutputStream();
            this.out = new PrintStream(baos, true, StandardCharsets.UTF_8);
        }

        boolean inputEqual(Prob to) {
            if (lines.size() != to.lines.size()) {
                return false;
            }
            for (int i = 0; i < lines.size(); ++i) {
                if (lines.get(i).intersect(to.lines.get(i)) != lines.get(i)) {
                    return false;
                }
            }
            return true;
        }

        boolean outputEqual(Prob to) {
            return Math.abs(maxPerimeter - to.maxPerimeter) <= TOLERANCE;
        }

        double runTime = 0;

        void run() {
            Instant t0 = Instant.now();
            super.run();
            Instant t1 = Instant.now();
            long nanos = Duration.between(t0, t1).toNanos();
            runTime = nanos * 1e-9;
            assertTrue(runTime <= TIME_LIMIT);
        }

        double solveTime = 0;

        void solve() {
            Instant t0 = Instant.now();
            super.solve();
            Instant t1 = Instant.now();
            long nanos = Duration.between(t0, t1).toNanos();
            solveTime = nanos * 1e-9;
            assertTrue(solveTime <= TIME_LIMIT);
        }
    }

    Target ex1() {
        String input = "3\n"
                + "0 0 0 1\n"
                + "0 0 1 0\n"
                + "0 1 1 0\n";
        String answer = "3.414214\n";
        var prob = new Target(input, answer);
        prob.lines.add(new Line(new Point(0, 0), new Point(0, 1)));
        prob.lines.add(new Line(new Point(0, 0), new Point(1, 0)));
        prob.lines.add(new Line(new Point(0, 1), new Point(1, 0)));
        prob.maxPerimeter = 3.4142135624;
        return prob;
    }

    Target ex2() {
        String input = "3\n"
                + "0 0 0 1\n"
                + "0 0 1 0\n"
                + "0 0 1 1\n";
        String answer = "no triangle\n";
        var prob = new Target(input, answer);
        prob.lines.add(new Line(new Point(0, 0), new Point(0, 1)));
        prob.lines.add(new Line(new Point(0, 0), new Point(1, 0)));
        prob.lines.add(new Line(new Point(0, 0), new Point(1, 1)));
        prob.maxPerimeter = 0;
        return prob;
    }

    Target ex3() {
        String input = "4\n"
                + "0 0 0 1\n"
                + "0 4 3 0\n"
                + "0 0 1 0\n"
                + "-1 -1 1 1\n";
        String answer = "12.000000\n";
        var prob = new Target(input, answer);
        prob.lines.add(new Line(new Point(0, 0), new Point(0, 1)));
        prob.lines.add(new Line(new Point(0, 4), new Point(3, 0)));
        prob.lines.add(new Line(new Point(0, 0), new Point(1, 0)));
        prob.lines.add(new Line(new Point(-1, -1), new Point(1, 1)));
        prob.maxPerimeter = 12;
        return prob;
    }

    final int N = 3;

    Target ex(int k) {
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
        var expect = ex(k);
        var result = new Prob();
        result.in = expect.in;
        result.read();
        assertTrue(expect.inputEqual(result), () -> "k=" + k);
    }

    @Test
    public void testRead() {
        for (int k = 1; k <= N; ++k) {
            testRead(k);
        }
    }

    void testWrite(int k) {
        var prob = ex(k);
        prob.write();
        assertEquals(prob.answer, prob.output(), () -> "k=" + k);
    }

    @Test
    public void testWrite() {
        for (int k = 1; k <= N; ++k) {
            testWrite(k);
        }

    }

    void testSolve(int k) {
        var expect = ex(k);
        var result = ex(k);
        result.solve();
        assertTrue(expect.outputEqual(result), () -> "k=" + k);
    }

    @Test
    public void testSolve() {
        for (int k = 1; k <= N; ++k) {
            testSolve(k);
        }
    }

    void testRun(int k) {
        var prob = ex(k);
        prob.run();
        assertEquals(prob.answer, prob.output(), () -> "k=" + k);
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
