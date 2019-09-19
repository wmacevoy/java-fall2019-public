/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.wmacevoy.billiard;

import java.util.Date;

/**
 *
 * @author wmacevoy
 */

class Alice {
    Date time = new Date();
    int size = SMALL;
    public static final int SMALL = 0;
}
public class Sample {
    public static final boolean DEBUG = false;
    Alice getAlice() {
        int tmp = 3;
        
        Alice alice = new Alice();
        alice.time = new Date();
        alice.size = Alice.SMALL;
        return alice;
    }
    
    void grow(Alice alice) {
        // alice = new Alice(); // lost in wonderland
        alice.size = alice.size + 1;
    }
    int div(int a, int b) {
        if (b == 0) {
            throw new UnsupportedOperationException("denominator cannot be zero");
        }
        if (DEBUG) {
            System.out.println("a="+ a);
        }
        a = 3;
        return a / b;
    }

    int mod(int a, int b) {
        return a % b;
    }

    int[] divmod(int a, int b) {
        return new int[]{a / b, a % b};
    }

    
}
