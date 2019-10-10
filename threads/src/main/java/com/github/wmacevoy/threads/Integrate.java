/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.wmacevoy.threads;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

/**
 *
 * @author wmacevoy
 */
class IntegrateJob {

    Integrate problem;
    int i0, i1;
    double partialSum;

    IntegrateJob(Integrate problem, int i0, int i1) {
        this.problem = problem;
        this.i0 = i0;
        this.i1 = i1;
    }

    public void run() {
        partialSum = problem.eval(i0, i1);
        problem.addArea(partialSum);
        //problem.setArea(problem.getArea()+partialSum); // not a transaction! - wrong
    }
}

public class Integrate {

    double a, b;
    int n;
    private Object areaLock = new Object();
    private double area = 0;

    double getArea() {
        synchronized (areaLock) {
            return area;
        }
    }

    void setArea(double value) {
        synchronized (areaLock) {
            area = value;
        }
    }

    double f(double x) {
        return Math.sin(x);
    }

    void addArea(double delta) {
        synchronized (areaLock) {
            area += delta;
        }
    }

    double x(double i) {
        double xi = a + (b - a) * (i) / ((double) n);
        return xi;
    }

    double slice(int i) {
        double x0 = x(i);
        double x1 = x(i + 1);
        double y0 = f(x0);
        double y1 = f(x1);
        // approximate via trapezoid approximation
        double area = (x1 - x0) * (y0 + y1) / 2.0;
        return area;
    }

    double eval(int i0, int i1) {
        double sum = 0;
        for (int i = i0; i < i1; ++i) {
            sum += slice(i);
        }
        return sum;
    }

    double eval() {
        return eval(0, n);
    }

    double parallelEval(int threads) {
        ArrayList<IntegrateJob> jobs = new ArrayList<IntegrateJob>(threads);
        for (int thread = 0; thread < threads; ++thread) {
            int i0 = (thread * n) / threads;
            int i1 = ((thread + 1) * n) / threads;
            jobs.add(new IntegrateJob(this, i0, i1));
        }
        jobs.parallelStream().forEach(job->job.run());
        return getArea();
    }

    double parallelEval() {
        int cores = Runtime.getRuntime().availableProcessors();
        int threads = 2 * cores;
        double ans = parallelEval(threads);
        return ans;
    }
    
    void streamExample() {
        IntStream range = IntStream.rangeClosed(1, 10);
        range.forEach(i -> System.out.println(i));

    }
}
