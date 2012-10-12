package topcoder.srm557;

import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class Incubator {
    public int maxMagicalGirls(String[] love) {
        boolean[][] myLoveGraph = new boolean[love.length][love.length];
        for (int i = 0; i < love.length; i++) {
            for (int j = 0; j < love[i].length(); j++) {
                myLoveGraph[i][j] = love[i].charAt(j) == 'Y';
            }
        }
        for (String aLove : love) {
            for (int i = 0; i < love.length; i++) {
                for (int j = 0; j < love[i].length(); j++) {
                    if (myLoveGraph[i][j]) {
                        allLoversOfILoveJ(i,
                                          j,
                                          myLoveGraph);
                    }
                }
            }
        }



        /*
        Girl[] myGirls = new Girl[love.length];
        for (int i = 0; i < love.length; i++) {
            myGirls[i] = new Girl();
        }

        for (int i = 0; i < love.length; i++) {
            for (int j = 0; j < myLoveGraph[i].length; j++) {
                List<Girl> myLovedGirls = new ArrayList<Girl>();
                if (myLoveGraph[i][j]) {
                    myLovedGirls.add(myGirls[j]);
                }
            }
        }
                          */

        return 0;

    }

    private void allLoversOfILoveJ(int i, int j, boolean[][] myLoveGraph) {
        for (int row = 0; row < myLoveGraph.length; row++) {
            if (myLoveGraph[row][i]) {
                myLoveGraph[row][j] = true;
            }
        }
    }

    public static class Girl {
        List<Girl> theGirlsLoved = new ArrayList<Girl>();
        boolean isMagical = false;
        boolean isProtected = false;

        public Girl() {
        }

        public void addGirls(List<Girl> aGirls) {
            theGirlsLoved.addAll(aGirls);
        }
    }

    // BEGIN KAWIGIEDIT TESTING
    // Generated by KawigiEdit 2.1.4 (beta) modified by pivanof
    private static boolean KawigiEdit_RunTest(int testNum, String[] p0, boolean hasAnswer, int p1) {
        System.out.print("Test " + testNum + ": [" + "{");
        for (int i = 0; p0.length > i; ++ i) {
            if (i > 0) {
                System.out.print(",");
            }
            System.out.print("\"" + p0[i] + "\"");
        }
        System.out.print("}");
        System.out.println("]");
        Incubator obj;
        int answer;
        obj = new Incubator();
        long startTime = System.currentTimeMillis();
        answer = obj.maxMagicalGirls(p0);
        long endTime = System.currentTimeMillis();
        boolean res;
        res = true;
        System.out.println("Time: " + (endTime - startTime) / 1000.0 + " seconds");
        if (hasAnswer) {
            System.out.println("Desired answer:");
            System.out.println("\t" + p1);
        }
        System.out.println("Your answer:");
        System.out.println("\t" + answer);
        if (hasAnswer) {
            res = answer == p1;
        }
        if (! res) {
            System.out.println("DOESN'T MATCH!!!!");
        } else if ((endTime - startTime) / 1000.0 >= 2) {
            System.out.println("FAIL the timeout");
            res = false;
        } else if (hasAnswer) {
            System.out.println("Match :-)");
        } else {
            System.out.println("OK, but is it right?");
        }
        System.out.println("");
        return res;
    }

    public static void main(String[] args) {
        boolean all_right;
        all_right = true;

        String[] p0;
        int p1;

        // ----- test 0 -----
        p0 = new String[]{"NY", "NN"};
        p1 = 1;
        all_right = KawigiEdit_RunTest(0,
                                       p0,
                                       true,
                                       p1) && all_right;
        // ------------------

        // ----- test 1 -----
        p0 = new String[]{"NYN", "NNY", "NNN"};
        p1 = 1;
        all_right = KawigiEdit_RunTest(1,
                                       p0,
                                       true,
                                       p1) && all_right;
        // ------------------

        // ----- test 2 -----
        p0 = new String[]{"NNYNN", "NNYNN", "NNNYY", "NNNNN", "NNNNN"};
        p1 = 2;
        all_right = KawigiEdit_RunTest(2,
                                       p0,
                                       true,
                                       p1) && all_right;
        // ------------------

        // ----- test 3 -----
        p0 = new String[]{"NNNNN", "NYNNN", "NYNYN", "YNYNN", "NNNNN"};
        p1 = 2;
        all_right = KawigiEdit_RunTest(3,
                                       p0,
                                       true,
                                       p1) && all_right;
        // ------------------

        // ----- test 4 -----
        p0 = new String[]{"NNNNN", "NNNNN", "NNNNN", "NNNNN", "NNNNN"};
        p1 = 5;
        all_right = KawigiEdit_RunTest(4,
                                       p0,
                                       true,
                                       p1) && all_right;
        // ------------------

        // ----- test 5 -----
        p0 = new String[]{"NNYNNNNN", "NNNYNNNN", "NNNNYNNN", "NNYNNNNN", "NNNNNYYN", "NNNYNNNY", "NNNNNNNN",
                "NNNNNNNN"};
        p1 = 2;
        all_right = KawigiEdit_RunTest(5,
                                       p0,
                                       true,
                                       p1) && all_right;
        // ------------------

        // ----- test 6 -----
        p0 = new String[]{"Y"};
        p1 = 0;
        all_right = KawigiEdit_RunTest(6,
                                       p0,
                                       true,
                                       p1) && all_right;
        // ------------------

        if (all_right) {
            System.out.println("You're a stud (at least on the example cases)!");
        } else {
            System.out.println("Some of the test cases had errors.");
        }
    }
    // END KAWIGIEDIT TESTING
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!