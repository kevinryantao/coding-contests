package topcoder.srm654;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class SquareScores
{
    public double calcexpectation(int[] p, String s)
    {


        return 0;
    }

    // BEGIN KAWIGIEDIT TESTING
    // Generated by KawigiEdit 2.1.4 (beta) modified by pivanof
    private static boolean KawigiEdit_RunTest(int testNum, int[] p0, String p1, boolean hasAnswer, double p2) {
        System.out.print("Test " + testNum + ": [" + "{");
        for (int i = 0; p0.length > i; ++i) {
            if (i > 0) {
                System.out.print(",");
            }
            System.out.print(p0[i]);
        }
        System.out.print("}" + "," + "\"" + p1 + "\"");
        System.out.println("]");
        SquareScores obj;
        double answer;
        obj = new SquareScores();
        long startTime = System.currentTimeMillis();
        answer = obj.calcexpectation(p0, p1);
        long endTime = System.currentTimeMillis();
        boolean res;
        res = true;
        System.out.println("Time: " + (endTime - startTime) / 1000.0 + " seconds");
        if (hasAnswer) {
            System.out.println("Desired answer:");
            System.out.println("\t" + p2);
        }
        System.out.println("Your answer:");
        System.out.println("\t" + answer);
        if (hasAnswer) {
            res = Math.abs(p2 - answer) <= 1e-9 * Math.max(1.0, Math.abs(p2));
        }
        if (!res) {
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

        int[] p0;
        String p1;
        double p2;

        // ----- test 0 -----
        p0 = new int[]{1,99};
        p1 = "aaaba";
        p2 = 8.0D;
        all_right = KawigiEdit_RunTest(0, p0, p1, true, p2) && all_right;
        // ------------------

        // ----- test 1 -----
        p0 = new int[]{10,20,70};
        p1 = "aa?bbbb";
        p2 = 15.0D;
        all_right = KawigiEdit_RunTest(1, p0, p1, true, p2) && all_right;
        // ------------------

        // ----- test 2 -----
        p0 = new int[]{10,20,30,40};
        p1 = "a??c?dc?b";
        p2 = 11.117D;
        all_right = KawigiEdit_RunTest(2, p0, p1, true, p2) && all_right;
        // ------------------

        // ----- test 3 -----
        p0 = new int[]{25,25,21,2,2,25};
        p1 = "a??b???????ff??e";
        p2 = 21.68512690712425D;
        all_right = KawigiEdit_RunTest(3, p0, p1, true, p2) && all_right;
        // ------------------

        // ----- test 4 -----
        p0 = new int[]{4,4,4,3,4,4,4,4,4,4,3,4,4,4,3,4,4,4,4,4,4,4,3,4,4,4};
        p1 = "??????????????????????????????";
        p2 = 31.16931963781721D;
        all_right = KawigiEdit_RunTest(4, p0, p1, true, p2) && all_right;
        // ------------------

        // ----- test 5 -----
        p0 = new int[]{4,3,4,3,8,2,4,3,4,4,3,2,4,4,3,4,2,4,7,6,4,4,3,4,4,3};
        p1 = "makigotapresentfromniko";
        p2 = 23.0D;
        all_right = KawigiEdit_RunTest(5, p0, p1, true, p2) && all_right;
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