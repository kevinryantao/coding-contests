package hackerrank.hackapril.conteststrategy;
import java.util.*;

/**
 * https://www.hackerrank.com/contests/101hack24/challenges/contest-strategy
 */

public class Solution {

    private int timeToTeach;

    public Solution(int n, int d, int[] timesToSolveProblem) {
        timeToTeach = d;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int testCases = Integer.parseInt(scanner.nextLine());

        for(int i = 0; i < testCases; i++){

            int[] nAndD = parseIntsFromStringArray(scanner.nextLine().split(" "));

            int n = nAndD[0];

            int d = nAndD[1];

            int[] timesToSolveProblem = parseIntsFromStringArray(scanner.nextLine().split(" "));


            List<Integer> problemsToDo = new ArrayList<Integer>();

            Arrays.sort(timesToSolveProblem);
            for(int index = n - 1; index >=0; index--){
                problemsToDo.add(timesToSolveProblem[index]);
            }

            Solution solution = new Solution(n, d, timesToSolveProblem);

            System.out.println(solution.getMinTime(new Input(1, n-1, problemsToDo)));
        }
    }


    private static class Input{
        int know;
        int dontKnow;
        List<Integer> problems;

        public Input(int know, int dontKnow, List<Integer> problems) {
            this.know = know;
            this.dontKnow = dontKnow;
            this.problems = problems;
        }
    }

    private long getMinTime(Input input){
        Collections.sort(input.problems);
        if(input.dontKnow == 0){
            return input.problems.get(input.problems.size() - 1);
        }

        int maxTeachers = Math.min(input.dontKnow, input.know);
        long minTimeSoFar = Long.MAX_VALUE;

        for(int teachers = 1; teachers <= maxTeachers; teachers++){
            int numWorkers = input.know - teachers;
            long timeForWorkersToFinish = Collections.max(input.problems);

            List<Integer> tasksToDo = input.problems.subList(0, input.problems.size() - numWorkers);

            int know = teachers * 2;
            int dontKnow = input.dontKnow - teachers;

            long timeForOthersToFinish = timeToTeach + getMinTime(new Input(know, dontKnow, new ArrayList<Integer>(tasksToDo)));

            long timeToFinish = Math.max(timeForWorkersToFinish, timeForOthersToFinish);

            if(timeToFinish < minTimeSoFar){
                minTimeSoFar = timeToFinish;
            }
        }

        return minTimeSoFar;
    }


    private static int[] parseIntsFromStringArray(String[] goalsString) {
        int[] result = new int[goalsString.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(goalsString[i]);
        }
        return result;
    }
}