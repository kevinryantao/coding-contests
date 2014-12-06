package interviewstreet.Balances;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        int myNumBalances = Integer.valueOf(myScanner.nextLine());
        Map<Integer, Balance> myBalanceMap = new HashMap<Integer, Balance>();
        for (int i = 0; i < myNumBalances; i++) {
            Balance myBalance = getOrCreate(myBalanceMap,
                                            i);
            String[] myLeftLine = myScanner.nextLine().split(" ");
            myBalance.setLeftWeight(Integer.valueOf(myLeftLine[0]));
            for (int j = 1; j < myLeftLine.length; j++) {
                Integer integer = Integer.valueOf(myLeftLine[j]);
                myBalance.addLeftBalance(getOrCreate(myBalanceMap,
                                                     integer));
            }
            String[] myRightLine = myScanner.nextLine().split(" ");
            myBalance.setRightWeight(Integer.valueOf(myRightLine[0]));
            for (int j = 1; j < myRightLine.length; j++) {
                Integer integer = Integer.valueOf(myRightLine[j]);
                myBalance.addRightBalance(getOrCreate(myBalanceMap,
                                                      integer));
            }
        }
        balanceTheBalances(myBalanceMap,
                           myNumBalances);
    }

    private static void balanceTheBalances(Map<Integer, Balance> myBalanceMap, int aNumBalances) {
        for (int i = 0; i < aNumBalances; i++) {
            int[] myExtraWeights = myBalanceMap.get(i).getWeightToBalance();
            System.out.println(i + ": " + myExtraWeights[0] + " " + myExtraWeights[1]);
        }
    }

    private static Balance getOrCreate(Map<Integer, Balance> myBalanceMap, int i) {
        Balance myBalance = myBalanceMap.get(i);
        if (myBalance == null) {
            myBalance = new Balance();
            myBalanceMap.put(i,
                             myBalance);
        }
        return myBalance;
    }

    public static class Balance {
        int theLeftWeight = 0;
        int theRightWeight = 0;
        List<Balance> theLeftBalances = new ArrayList<Balance>();
        List<Balance> theRightBalances = new ArrayList<Balance>();

        public Balance() {
        }

        public int[] getWeightToBalance() {
            int totalLeftWeight = theLeftWeight;
            for (Balance myBalance : theLeftBalances) {
                totalLeftWeight += myBalance.getBalancedWeight();
            }
            int totalRightWeight = theRightWeight;
            for (Balance myBalance : theRightBalances) {
                totalRightWeight += myBalance.getBalancedWeight();
            }
            return new int[]{Math.max(0,
                                      totalRightWeight - totalLeftWeight), Math.max(0,
                                                                                    totalLeftWeight -
                                                                                            totalRightWeight)};
        }

        public int getBalancedWeight() {
            int totalLeftWeight = theLeftWeight;
            for (Balance myBalance : theLeftBalances) {
                totalLeftWeight += myBalance.getBalancedWeight();
            }
            int totalRightWeight = theRightWeight;
            for (Balance myBalance : theRightBalances) {
                totalRightWeight += myBalance.getBalancedWeight();
            }
            return 10 + 2 * Math.max(totalLeftWeight,
                                     totalRightWeight);
        }

        public int getLeftWeight() {
            return theLeftWeight;
        }

        public void setLeftWeight(int theLeftWeight) {
            this.theLeftWeight = theLeftWeight;
        }

        public int getRightWeight() {
            return theRightWeight;
        }

        public void setRightWeight(int theRightWeight) {
            this.theRightWeight = theRightWeight;
        }

        public List<Balance> getLeftBalances() {
            return theLeftBalances;
        }

        public void addLeftBalance(Balance aBalance) {
            this.theLeftBalances.add(aBalance);
        }

        public List<Balance> getRightBalances() {
            return theRightBalances;
        }

        public void addRightBalance(Balance aBalance) {
            this.theRightBalances.add(aBalance);
        }
    }
}
