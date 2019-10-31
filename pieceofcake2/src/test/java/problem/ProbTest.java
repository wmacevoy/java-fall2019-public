package problem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.*;
import java.io.*;
import java.time.*;
import java.nio.charset.StandardCharsets;

public class ProbTest {

    final String EOL = System.lineSeparator();
    final double TIME_LIMIT = 1.0;

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
            return n == to.n && h == to.h && v == to.v;
        }

        boolean outputEqual(Prob to) {
            return volume == to.volume;
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
        String input = "10 4 7\n";
        String answer = "168\n";
        Target prob = new Target(input, answer);
        prob.n = 10;
        prob.h = 4;
        prob.v = 7;
        prob.volume = 168;
        return prob;
    }

    Target ex2() {
        String input = "5 2 2\n";
        String answer = "36\n";
        Target prob = new Target(input, answer);
        prob.n = 5;
        prob.h = 2;
        prob.v = 2;
        prob.volume = 36;
        return prob;
    }

    Target ex3() {
        String input = "4 2 1\n";
        String answer = "24\n";
        Target prob = new Target(input, answer);
        prob.n = 4;
        prob.h = 2;
        prob.v = 1;
        prob.volume = 24;
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
        var prob = ex(k);
        var result = new Prob();
        result.in = prob.in;
        result.read();
        assertTrue(prob.inputEqual(result), () -> "k=" + k);
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
        var prob0 = ex(k);
        var prob1 = ex(k);
        Instant t0 = Instant.now();
        prob1.solve();
        Instant t1 = Instant.now();
        long nanos = Duration.between(t0, t1).toNanos();
        double duration = nanos * 1e-9;
        assertTrue(prob0.outputEqual(prob1), () -> "k=" + k);
        assertTrue(duration <= TIME_LIMIT);
    }

    @Test
    public void testSolve() {
        for (int k = 1; k <= N; ++k) {
            testSolve(k);
        }
    }

    void testRun(int k) {
        var prob = ex(k);
        Instant t0 = Instant.now();
        prob.run();
        Instant t1 = Instant.now();
        long nanos = Duration.between(t0, t1).toNanos();
        double duration = nanos * 1e-9;
        assertEquals(prob.answer, prob.output(), () -> "k=" + k);
        assertTrue(duration <= TIME_LIMIT);
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
