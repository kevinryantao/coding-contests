package codeforces;

import java.util.Scanner;

public class OldPeykan {


    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        String[] myFirstLine = myScanner.nextLine().split(" ");
        int m = Integer.parseInt(myFirstLine[0]);
        int k = Integer.parseInt(myFirstLine[1]);

        String[] myDistanceString = myScanner.nextLine().split(" ");
        int[] myDistances = new int[m];
        String[] myFuelString = myScanner.nextLine().split(" ");
        int[] myFuels = new int[m];

        for (int i = 0; i < m; i++) {
            myDistances[i] = Integer.parseInt(myDistanceString[i]);
            myFuels[i] = Integer.parseInt(myFuelString[i]);
        }
        System.out.println(getMinimumTime(m,
                                          k,
                                          myDistances,
                                          myFuels));
    }

    /*
    private static int getMinimumTime(int m, int k, int[] myDistances, int[] myFuels, int[][] fuelRemainingDP) {
        for (int numRests = 0; numRests < 100000; numRests++) {
            int currentTime = numRests * k;
            fuelRemainingDP[0][numRests] = myFuels[0] * (numRests + 1);
            for (int i = 1; i < m + 1; i++) {
                currentTime += myDistances[i - 1];
                int fuelRemaining;
                if(numRests > 0){
                    fuelRemaining = Math.max(fuelRemainingDP[i][numRests - 1],
                                             fuelRemainingDP[i - 1][numRests]
                                                     - myDistances[i - 1]);
                }else{
                    fuelRemaining = fuelRemainingDP[i - 1][numRests]
                            - myDistances[i - 1];
                }
                if (fuelRemaining >= 0){
                    if(i == m){
                        return currentTime;
                    }
                    fuelRemaining += myFuels[i];
                }
                fuelRemainingDP[i][numRests] = fuelRemaining;
            }
        }
        return -1;
    }             */

    /*

    private static int getMinimumTime(int m, int k, int[] myDistances, int[] myFuels, int[][] fuelRemainingDP) {
        Stack<Status> myStack = new Stack<Status>();
        myStack.push(new Status(0,
                                myFuels[0],
                                0));

        int myTotalDistance = 0;
        for (int i = 0; i < m; i++) {
            myTotalDistance += myDistances[i];
        }

        while (! myStack.empty()) {

            Status myStatus = myStack.pop();
            int location = myStatus.location;
            // end case
            if (location == m) {
                return myTotalDistance + myStatus.numberOfRests * k;
            }
            myStatus.fuelRemaining += myFuels[location];
            // if possible, push resting for 1, then push go.
            if (myStatus.fuelRemaining >= myDistances[location]) {
                myStack.push(new Status(myStatus.numberOfRests + 1,
                                        myStatus.fuelRemaining,
                                        location));
                myStack.push(new Status(myStatus.numberOfRests,
                                        myStatus.fuelRemaining - myDistances[location],
                                        location));
            } else { //rest enough times to be able to go
                int numberOfRests = (myDistances[location] - myStatus.fuelRemaining) / myFuels[location];
                myStack.push(new Status(myStatus.numberOfRests + numberOfRests,
                                        myStatus.fuelRemaining + numberOfRests * myFuels[location],
                                        location));
            }
        }


        for (int numRests = 0; numRests < 100000; numRests++) {
            int currentTime = numRests * k;
            fuelRemainingDP[0][numRests] = myFuels[0] * (numRests + 1);
            for (int i = 1; i < m + 1; i++) {
                currentTime += myDistances[i - 1];
                int fuelRemaining;
                if (numRests > 0) {
                    fuelRemaining = Math.max(fuelRemainingDP[i][numRests - 1],
                                             fuelRemainingDP[i - 1][numRests] - myDistances[i - 1]);
                } else {
                    fuelRemaining = fuelRemainingDP[i - 1][numRests] - myDistances[i - 1];
                }
                if (fuelRemaining >= 0) {
                    if (i == m) {
                        return currentTime;
                    }
                    fuelRemaining += myFuels[i];
                }
                fuelRemainingDP[i][numRests] = fuelRemaining;
            }
        }
        return - 1;
    }                                     */

    private static int getMinimumTime(int m, int k, int[] myDistances, int[] myFuels) {
        int myTotalDistance = 0;
        for (int i = 0; i < m; i++) {
            myTotalDistance += myDistances[i];
        }
        Status myStatus = getNumberOfRestsAndFuelLeft(m, myDistances, myFuels);
        return myTotalDistance + myStatus.numberOfRests * k;
    }

    private static Status getNumberOfRestsAndFuelLeft(int goal, int[] myDistances, int[] myFuels) {
        int maxFuelIndex = - 1;
        int maxFuelAmount = 0;
        for (int i = 0; i < goal; i++) {
            if (myFuels[i] > maxFuelAmount) {
                maxFuelIndex = i;
                maxFuelAmount = myFuels[i];
            }
        }
        int myStartingFuelRequired = getMinStartingFuelRequired(0,
                                                                maxFuelIndex,
                                                                myDistances,
                                                                myFuels);
        int fuelLeftBeforeArrival;
        int numRests = 0;
        if (myFuels[0] >= myStartingFuelRequired || maxFuelIndex == 0) {
            //go to max fuel index, refuel enough to make it to goal
            fuelLeftBeforeArrival = traverse(0,
                                                 goal,
                                                 myDistances,
                                                 myFuels);

        } else {
            //max fuel index is the new goal. get the number of rests it takes to get there.
            Status myStatus = getNumberOfRestsAndFuelLeft(maxFuelIndex, myDistances, myFuels);
            fuelLeftBeforeArrival = myStatus.fuelRemaining;
            numRests += myStatus.numberOfRests;
        }
        int fuel = fuelLeftBeforeArrival + myFuels[maxFuelIndex];

        int myFuelRequired = getMinStartingFuelRequired(maxFuelIndex,
                                                        goal,
                                                        myDistances,
                                                        myFuels);
        int myNumRefills = Math.max(0,
                                    (int) Math.ceil(1.0 * (myFuelRequired - fuel) / maxFuelAmount));
        return new Status(numRests + myNumRefills,
                          fuelLeftBeforeArrival + myNumRefills * maxFuelAmount + traverse(maxFuelIndex,
                                                                                          goal,
                                                                                          myDistances,
                                                                                          myFuels),
                          goal);
    }

    private static int traverse(int start, int goal, int[] myDistances, int[] myFuels) {
        int myFuel = 0;
        for (int i = start; i < goal; i++) {
            myFuel += myFuels[i];
            myFuel -= myDistances[i];
        }
        return myFuel;
    }

    private static int getMinStartingFuelRequired(int start, int goal, int[] myDistances, int[] myFuels) {
        int myMinStartingFuel = myDistances[start];
        int myCurrentFuel = myMinStartingFuel;
        for (int i = start; i < goal; i++) {
            if (myCurrentFuel < myDistances[i]) {
                myMinStartingFuel += myDistances[i] - myCurrentFuel;
                myCurrentFuel = myDistances[i];
            }
            myCurrentFuel -= myDistances[i];
            if (i != goal - 1) {
                myCurrentFuel += myFuels[i + 1];
            }
        }
        return myMinStartingFuel;
    }

    public static class Status {
        int numberOfRests;
        int fuelRemaining;
        int location;

        public Status(int numberOfRests, int fuelRemaining, int location) {
            this.numberOfRests = numberOfRests;
            this.fuelRemaining = fuelRemaining;
            this.location = location;
        }
    }
}
