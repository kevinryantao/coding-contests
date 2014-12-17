package hackerrank.schedule;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim());

        Test[] tests = new Test[n];

        for (int i = 0; i < n; i++) {
            tests[i] = new Test(scanner.nextLine().trim());
        }

        Solution solution = new Solution();
        System.out.println(solution.run(tests));

    }

    public double run(Test[] tests) {
        Arrays.sort(tests, strictTestComparator);

        Map<Integer, List<Test>> masterMap = new HashMap<Integer, List<Test>>();

        Test lastTest = null;
        int currentRank = 0;
        for (Test test : tests) {
            if (lastTest == null || strictTestComparator.compare(lastTest, test) < 0) {
                ArrayList<Test> list = new ArrayList<Test>();
                list.add(test);
                currentRank++;
                masterMap.put(currentRank, list);
            } else if (strictTestComparator.compare(lastTest, test) == 0) {
                masterMap.get(currentRank).add(test);
            }
            lastTest = test;
        }

        double runningTotal = 0;
        for (int i = currentRank; i > 0; i--) {
            runningTotal = findMinimumForSubset(masterMap.get(currentRank), runningTotal);
        }


        /*List<Test> testList = Arrays.asList(tests);
        double minimumForSubset = findMinimumForSubset(testList);*/

        return runningTotal;

    }

    public double findMinimumForSubset(List<Test> testCollection, double runningTotal) {
        // brute force it. n factorial.
        List<List<Test>> listOfLists = new ArrayList<List<Test>>();
        ArrayList<Test> initialList = new ArrayList<Test>();
        initialList.add(testCollection.get(0));
        listOfLists.add(initialList);
        // initialize
        for (int i = 1; i < testCollection.size(); i++) {
            List<List<Test>> newListOfLists = new ArrayList<List<Test>>();
            Test test = testCollection.get(i);
            for (List<Test> list : listOfLists) {
                for (int j = 0; j <= list.size(); j++) {
                    List<Test> cloneList = new ArrayList<Test>(list);
                    cloneList.add(j, test);
                    newListOfLists.add(cloneList);
                }
            }
            listOfLists = newListOfLists;
        }

        double runningMin = Double.MAX_VALUE;
        for (List<Test> list : listOfLists) {
            double score = evaluateScore(list, runningTotal);
            if (score < runningMin) {
                runningMin = score;
            }
        }
        return runningMin;
    }

    public double evaluateScore(Collection<Test> testCollection, double runningTotal) {
        double total = runningTotal;
        for (Test test : testCollection) {
            total = test.getTime() + test.getProbabilityOfSuccess() * total;
        }
        return total;
    }

    public static Comparator<Test> createTestComparatorForCurrentTotal(final double currentTotal) {
        return new Comparator<Test>() {
            @Override
            public int compare(Test o1, Test o2) {
                double firstTotal = o1.getTime() + o1.getProbabilityOfSuccess() * currentTotal;
                double secondTotal = o2.getTime() + o2.getProbabilityOfSuccess() * currentTotal;
                if (firstTotal < secondTotal) {
                    return -1;
                }
                if (secondTotal < firstTotal) {
                    return 1;
                }
                return 0;
            }
        };
    }

    public static Comparator<Test> strictTestComparator = new Comparator<Test>() {
        @Override
        public int compare(Test o1, Test o2) {
            if (o1.getTime() <= o2.getTime() && o1.getProbabilityOfSuccess() < o2.getProbabilityOfSuccess()) {
                return -1;
            }
            if (o1.getTime() < o2.getTime() && o1.getProbabilityOfSuccess() <= o2.getProbabilityOfSuccess()) {
                return -1;
            }
            if (o2.getTime() <= o1.getTime() && o2.getProbabilityOfSuccess() < o1.getProbabilityOfSuccess()) {
                return 1;
            }
            if (o2.getTime() < o1.getTime() && o2.getProbabilityOfSuccess() <= o1.getProbabilityOfSuccess()) {
                return 1;
            }
            return 0;
        }
    };

    public static class Test {

        int time;
        double probabilityOfSuccess;

        public Test(int time, double probabilityOfSuccess) {
            this.time = time;
            this.probabilityOfSuccess = probabilityOfSuccess;
        }

        public Test(String line) {
            String[] split = line.split(" ");
            time = Integer.parseInt(split[0]);
            probabilityOfSuccess = Double.parseDouble(split[1]);
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public double getProbabilityOfSuccess() {
            return probabilityOfSuccess;
        }

        public void setProbabilityOfSuccess(double probabilityOfSuccess) {
            this.probabilityOfSuccess = probabilityOfSuccess;
        }
    }
}