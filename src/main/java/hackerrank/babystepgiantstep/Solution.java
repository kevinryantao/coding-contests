package hackerrank.babystepgiantstep;

/**
 * Created by ktao on 11/7/16.
 *
 * https://www.hackerrank.com/contests/w25/challenges/baby-step-giant-step
 */
import java.util.*;

public class Solution {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int numQueries = scanner.nextInt();

        for(int i = 0 ; i < numQueries; i++){
            int aStep = scanner.nextInt();
            int bStep = scanner.nextInt();
            int goalStep = scanner.nextInt();
            System.out.println(numSteps(aStep, bStep, goalStep));
        }

        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }

    /*
    Example: 3, 5 => 0 - 0 steps, 1 - 2 steps, 2 - 2 steps, 3 - 1 step, 4 - 2 steps, 5 - 1 step, 6 - 2 steps, 7 - 2 steps. 8 - 2 steps


     */

    private static int numSteps(int aStep, int bStep, int goalStep) {
        if(goalStep == 0){
            return 0;
        }

        int bigStep = Math.max(aStep, bStep);
        int littleStep = Math.min(aStep, bStep);
        if(goalStep < bigStep && goalStep != littleStep){
            return 2;
        }

        int numSteps = goalStep / bigStep;
        int remainder = goalStep % bigStep;
        if(remainder > 0){
            numSteps++;
        }
        return numSteps;
    }
}