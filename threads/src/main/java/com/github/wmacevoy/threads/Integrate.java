/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.wmacevoy.threads;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wmacevoy
 */

class IntegrateJob extends Thread {
    Integrate problem;
    int i0,i1;
    double partialSum;
    IntegrateJob(Integrate problem, int i0, int i1) {
        this.problem = problem;
        this.i0 = i0;
        this.i1 = i1;
    }
    @Override
    public void run() {
        partialSum = problem.eval(i0,i1);
    }   
}
public class Integrate {
    double a,b;
    int n;
    double f(double x) {
        return Math.sin(x);
    }
    double slice(int i) {
        double x0 = a + (b-a)*(i)/((double) n);
        double x1 = a + (b-a)*(i+1)/((double) n);
        double y0 = f(x0);
        double y1 = f(x1);
        double area = (x1-x0)*(y0+y1)/2.0;
        return area;
    }
    double eval() {
        double sum = 0;
        for (int i=0; i<n; ++i) {
            sum += slice(i);
        }
        return sum;
    }
    
    double eval(int i0, int i1) {
        double sum = 0;
        for (int i=i0; i<i1; ++i) {
            sum += slice(i);
        }
        return sum;
    }
    
    double parallelEval(int threads) {
        ArrayList<IntegrateJob> jobs = new ArrayList<IntegrateJob>();
        for (int k=0; ; ++k) {
            int i0 = (k*n)/threads;
            int i1 = ((k+1)*n)/threads;
            if (i0 >= n) break;
            if (i1 > n) i1=n;
            jobs.add(new IntegrateJob(this, i0,i1));
        }
        for (var job : jobs) {
            job.start();
        }
        for (var job : jobs) {
            try {
                job.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Integrate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        double sum = 0;
        for (var job : jobs) {
            sum += job.partialSum;
        }
        return sum;
    }
    
    double parallelEval() {
        int cores = Runtime.getRuntime().availableProcessors();
        int threads = 2*cores;
        double ans = parallelEval(threads);
        return ans;
    }
}
