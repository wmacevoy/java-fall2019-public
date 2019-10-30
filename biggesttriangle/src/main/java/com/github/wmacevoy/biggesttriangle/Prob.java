package com.github.wmacevoy.biggesttriangle;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author wmacevoy
 */
// https://rmc19.kattis.com/problems/biggesttriangle

class Point {

    final double x, y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    double distance(Point with) {
        return Math.sqrt(Math.pow(x - with.x, 2) + Math.pow(y - with.y, 2));
    }
}

class Line {

    final Point a;
    final Point b;

    Line(Point a, Point b) {
        this.a = a;
        this.b = b;
    }

    Point gamma(double t) {
        return new Point(a.x + (b.x - a.x) * t, a.y + (b.y - a.y) * t);
    }

    Object intersect(Line with) {
        double x1 = a.x;
        double y1 = a.y;
        double x2 = b.x;
        double y2 = b.y;

        double xbar1 = with.a.x;
        double ybar1 = with.a.y;
        double xbar2 = with.b.x;
        double ybar2 = with.b.y;

        double deltax = x2 - x1;
        double deltabarx = xbar2 - xbar1;
        double deltay = y2 - y1;
        double deltabary = ybar2 - ybar1;
        double dx = xbar1 - x1;
        double dy = ybar1 - y1;

        double d = deltax * deltabary - deltabarx * deltay;
        double td = (deltabary * dx - deltabarx * dy);

        if (d == 0) {
            if (td == 0) {
                return this;
            } else {
                return null;
            }
        } else {
            return gamma(td / d);
        }
    }
}

class Triangle {

    final Line alpha;
    final Line beta;
    final Line gamma;

    Triangle(Line alpha, Line beta, Line gamma) {
        this.alpha = alpha;
        this.beta = beta;
        this.gamma = gamma;
    }

    double perimeter() {
        Object a = alpha.intersect(beta);
        if (!(a != null && a instanceof Point)) {
            return 0;
        }
        Object b = alpha.intersect(gamma);
        if (!(b != null && b instanceof Point)) {
            return 0;
        }
        Object c = beta.intersect(gamma);
        if (!(c != null && c instanceof Point)) {
            return 0;
        }
        Point pa = (Point) a;
        Point pb = (Point) b;
        Point pc = (Point) c;
        return pa.distance(pb) + pb.distance(pc) + pc.distance(pa);
    }

}

public class Prob {

    PrintStream out = System.out;
    Scanner in = new Scanner(System.in);
    ArrayList<Line> lines = new ArrayList<Line>();
    double maxPerimeter;

    void solve() {
        maxPerimeter = 0;
        for (int i = 0; i<lines.size(); ++i) {
            for (int j=i+1; j<lines.size(); ++j) {
                for (int k=j+1; k<lines.size(); ++k) {
                    Line alpha=lines.get(i);
                    Line beta=lines.get(j);
                    Line gamma=lines.get(k);
                    Triangle triangle = new Triangle(alpha, beta, gamma);
                    maxPerimeter = Math.max(maxPerimeter, triangle.perimeter());
                }
            }
        }
    }

    void read() {
        lines.clear();
        int n = in.nextInt();
        for (int i=0; i<n; ++i) {
            int x1, y1, x2, y2;
            x1 = in.nextInt();
            y1 = in.nextInt();
            x2 = in.nextInt();
            y2 = in.nextInt();
            lines.add(new Line(new Point(x1, y1), new Point(x2, y2)));
        }
    }

    void write() {
        if (maxPerimeter == 0) {
            out.println("no triangle");
        } else {
            out.printf("%1.6f%n",maxPerimeter);
        }
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
