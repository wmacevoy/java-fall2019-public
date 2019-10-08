/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.wmacevoy.threads;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author wmacevoy
 */
public class IntegrateJobTest {
    static Integrate getI() { return IntegrateTest.getI(); }
    static IntegrateJob getJob() {
        var I = getI();
        var job = new IntegrateJob(I,23,87);
        return job;
    }
    static double exact(IntegrateJob job) {
        double x0 = job.problem.x(job.i0);
        double x1 = job.problem.x(job.i1);
        double area = IntegrateTest.exact(x0,x1);
        return area;
    }
    /**
     * Test of run method, of class IntegrateJob.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        var job = getJob();
        job.run();
        double expect = exact(job);
        assertEquals(expect,job.partialSum,0.001);
    }
    
}
