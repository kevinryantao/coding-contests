package hackerrank.archery;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {


        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        int nCircles;
        nCircles = Integer.parseInt(scanner.nextLine().trim());

        int[] radii = new int[nCircles];
        String[] split = scanner.nextLine().trim().split(" ");

        for (int i = 0; i < radii.length; i++) {
            radii[i] = Integer.parseInt(split[i]);
        }

        int nArrows = Integer.parseInt(scanner.nextLine().trim());

        Arrow[] arrows = new Arrow[nArrows];

        for (int i = 0; i < arrows.length; i++) {
            arrows[i] = new Arrow(scanner.nextLine().trim());
        }

        Solution solution = new Solution();
        System.out.println(solution.run(radii, arrows));

        /*
        int[] radii = new int[10000];
        for (int i = 0; i < 10000; i++) {
            radii[i] = (i + 1) * 10;
        }
        Arrow[] arrows = new Arrow[10000];
        for (int i = 0; i < 10000; i++) {
            arrows[i] = new Arrow((int) (Math.random() * 100000), (int) (Math.random() * 100000), (int) (Math.random() * 100000), (int) (Math.random() * 100000));
        }

        Solution solution = new Solution();
        System.out.println(solution.run(radii, arrows));
        */

    }

    public long run(int[] radii, Arrow[] arrows) {
        long count = 0;
        Set<Integer> radiiSet = new HashSet<Integer>(radii.length);

        for (int radius : radii) {
            radiiSet.add(radius);
        }
        int radiusMap[] = new int[1414214];
        int currentRadiusCount = 0;
        for (int r = 0; r < 1414214; r++) {
            if (radiiSet.contains(r)) {
                currentRadiusCount++;
            }
            radiusMap[r] = currentRadiusCount;
        }

        for (int i = 0; i < arrows.length; i++) {
            double r1 = getR(arrows[i].getX1(), arrows[i].getY1());
            double r2 = getR(arrows[i].getX2(), arrows[i].getY2());

            double minR = Math.min(r1, r2);
            double maxR = Math.max(r1, r2);

            int floorMinR = (int) Math.floor(minR);
            int floorMaxR = (int) Math.floor(maxR);

            int floorNumCircles = radiusMap[floorMaxR] - radiusMap[floorMinR];

            count += floorNumCircles;

        }
        return count;
    }

    public static double getR(long x, long y) {
        return Math.sqrt(x * x + y * y);
    }

    public static class Arrow {
        int x1;
        int y1;
        int x2;
        int y2;

        public Arrow(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        public Arrow(String line) {
            String[] split = line.split(" ");
            x1 = Integer.parseInt(split[0]);
            y1 = Integer.parseInt(split[1]);
            x2 = Integer.parseInt(split[2]);
            y2 = Integer.parseInt(split[3]);
        }

        public int getX1() {
            return x1;
        }

        public void setX1(int x1) {
            this.x1 = x1;
        }

        public int getY1() {
            return y1;
        }

        public void setY1(int y1) {
            this.y1 = y1;
        }

        public int getX2() {
            return x2;
        }

        public void setX2(int x2) {
            this.x2 = x2;
        }

        public int getY2() {
            return y2;
        }

        public void setY2(int y2) {
            this.y2 = y2;
        }
    }
}