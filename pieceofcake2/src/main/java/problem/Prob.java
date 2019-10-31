package problem;

import java.io.*;
import java.util.*;

/**
 *
 * @author wmacevoy
 */
// https://rmc19.kattis.com/problems/pieceofcake2


public class Prob {
    Scanner in = new Scanner(System.in);
    PrintStream out = System.out;
    int n,h,v;
    int volume;

    void read() {
        n = in.nextInt();
        h = in.nextInt();
        v = in.nextInt();
    }

    void solve() {
        volume = 4*Math.max(h,n-h)*Math.max(v,n-v);
    }

    void write() {
        out.println(volume);
    }

    void run() {
        read();
        solve();
        write();
    }

    public static void main(String[] args) {
        var prob = new Prob();
        prob.run();
    }
}
