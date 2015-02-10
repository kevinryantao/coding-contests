package topcoder.srm649;

import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class Decipherability
{
    public String check(String s, int K)
    {
        Map<Character, Integer> lastIndexOfChar = new HashMap<Character, Integer>();
        int minDistanceBetweenSameChar = s.length();
        if(K == s.length()){
            return "Certain";
        }
        for(int i = 0; i < s.length(); i++){
            char character = s.charAt(i);
            if(lastIndexOfChar.containsKey(character)){
                int distance = i - lastIndexOfChar.get(character);
                if(distance < minDistanceBetweenSameChar){
                    minDistanceBetweenSameChar = distance;
                }

            }
            lastIndexOfChar.put(character, i);
        }
        if(K < minDistanceBetweenSameChar){
            return "Certain";
        } else {
            return "Uncertain";
        }
    }

    // BEGIN KAWIGIEDIT TESTING
    // Generated by KawigiEdit 2.1.4 (beta) modified by pivanof
    private static boolean KawigiEdit_RunTest(int testNum, String p0, int p1, boolean hasAnswer, String p2) {
        System.out.print("Test " + testNum + ": [" + "\"" + p0 + "\"" + "," + p1);
        System.out.println("]");
        Decipherability obj;
        String answer;
        obj = new Decipherability();
        long startTime = System.currentTimeMillis();
        answer = obj.check(p0, p1);
        long endTime = System.currentTimeMillis();
        boolean res;
        res = true;
        System.out.println("Time: " + (endTime - startTime) / 1000.0 + " seconds");
        if (hasAnswer) {
            System.out.println("Desired answer:");
            System.out.println("\t" + "\"" + p2 + "\"");
        }
        System.out.println("Your answer:");
        System.out.println("\t" + "\"" + answer + "\"");
        if (hasAnswer) {
            res = answer.equals(p2);
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

        String p0;
        int p1;
        String p2;

        // ----- test 0 -----
        p0 = "snuke";
        p1 = 2;
        p2 = "Certain";
        all_right = KawigiEdit_RunTest(0, p0, p1, true, p2) && all_right;
        // ------------------

        // ----- test 1 -----
        p0 = "aba";
        p1 = 1;
        p2 = "Certain";
        all_right = KawigiEdit_RunTest(1, p0, p1, true, p2) && all_right;
        // ------------------

        // ----- test 2 -----
        p0 = "aba";
        p1 = 2;
        p2 = "Uncertain";
        all_right = KawigiEdit_RunTest(2, p0, p1, true, p2) && all_right;
        // ------------------

        // ----- test 3 -----
        p0 = "abcdabcd";
        p1 = 3;
        p2 = "Certain";
        all_right = KawigiEdit_RunTest(3, p0, p1, true, p2) && all_right;
        // ------------------

        // ----- test 4 -----
        p0 = "koukyoukoukokukikou";
        p1 = 2;
        p2 = "Uncertain";
        all_right = KawigiEdit_RunTest(4, p0, p1, true, p2) && all_right;
        // ------------------

        // ----- test 5 -----
        p0 = "wolfsothe";
        p1 = 8;
        p2 = "Uncertain";
        all_right = KawigiEdit_RunTest(5, p0, p1, true, p2) && all_right;
        // ------------------

        // ----- test 6 -----
        p0 = "aa";
        p1 = 2;
        p2 = "Certain";
        all_right = KawigiEdit_RunTest(6, p0, p1, true, p2) && all_right;
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