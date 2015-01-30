package codechef.candidatewalk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by ktao on 1/24/15.
 */
public class Main {

    static Map<nDimensionalCell, Long> dpArray = new HashMap<nDimensionalCell, Long>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numTestCases = Integer.parseInt(scanner.nextLine());

        for(int i = 0; i < numTestCases; i++){
            int[] inputs = parseIntsFromStringArray(scanner.nextLine().split(" "));

            int numDimensions = inputs[0];
            int edgeSize = inputs[1];

            Main main = new Main();

            long minimumUnfriendlyPeople = main.solve(numDimensions, edgeSize);

            System.out.println(minimumUnfriendlyPeople);

        }

    }

    private long solve(int numDimensions, int edgeSize) {

        // step one: initialize the N-dimensional DP array
        List<nDimensionalCell> arrayIterationOrder = createArrayIterationOrder(numDimensions, edgeSize);


        // step two: iterate over the entire array


        for(nDimensionalCell cell : arrayIterationOrder){

            if(dpArray.containsKey(cell)){
                continue;
            }

            // step three: during each step, iterate over each adjacent cell
            List<nDimensionalCell> earlierNeighbors = cell.getEarlierNeighbors();

            long minNeighborCost = Long.MAX_VALUE;
            // step 4: access the DP of each adjacent cell, find the min cost
            for(nDimensionalCell neighbor : earlierNeighbors){
                long cost = dpArray.get(neighbor);
                if(cost < minNeighborCost){
                    minNeighborCost = cost;
                }
            }
            if(earlierNeighbors.size() == 0){
                minNeighborCost = 0;
            }
            // step 5: calculate the min of adjacents + current cost. store this result in the DP array
            long totalCost = minNeighborCost + cell.getNumberOfUnfriendlyPeople();
            dpArray.put(cell, totalCost);

        }

        List<Integer> lastCoordinate = new ArrayList<Integer>();
        for(int i = 0; i < numDimensions; i++){
            lastCoordinate.add(edgeSize - 1);
        }
        nDimensionalCell lastCell = new nDimensionalCell(lastCoordinate);
        // at the very end, return DP of N-1... N-1


        return dpArray.get(lastCell);
    }

    public List<nDimensionalCell> createArrayIterationOrder(int numDimensions, int edgeSize) {
        int numberOfCells = (int) Math.pow(edgeSize, numDimensions);
        List<nDimensionalCell> allCells = new ArrayList<nDimensionalCell>();
        for(int i = 0; i < numberOfCells; i++){
            List<Integer> coordinates = new ArrayList<Integer>();
            int cellNumber = i;
            for(int d = 0; d < numDimensions; d++){
                coordinates.add(cellNumber % edgeSize);
                cellNumber = cellNumber / edgeSize;
            }
            allCells.add(new nDimensionalCell(coordinates));
        }

        return allCells;
    }

    private static class nDimensionalCell{

        private List<Integer> coordinates;

        public nDimensionalCell(List<Integer> coordinates){

            this.coordinates = coordinates;
        }

        public List<Integer> getCoordinates() {
            return coordinates;
        }

        public long getNumberOfUnfriendlyPeople(){
            long xorSum = 0;
            long sum = 0;
            for(int i : coordinates){
                xorSum = xorSum ^ i;
                sum += i;
            }
            return xorSum * sum;
        }

        public List<nDimensionalCell> getEarlierNeighbors(){
            List<nDimensionalCell> allEarlierNeighbors = new ArrayList<nDimensionalCell>();
            for(int i = 0; i < coordinates.size(); i++){
                if(coordinates.get(i) > 0){
                    List<Integer> newCoordinates = new ArrayList<Integer>(coordinates);
                    newCoordinates.remove(i);
                    newCoordinates.add(i, coordinates.get(i) - 1);
                    allEarlierNeighbors.add(new nDimensionalCell(newCoordinates));
                }
            }
            return allEarlierNeighbors;
        }

        public int hashCode(){
            return coordinates.hashCode();
        }

        public boolean equals(Object o){
            if(o instanceof nDimensionalCell){
                nDimensionalCell other = (nDimensionalCell) o;
                return coordinates.equals(other.coordinates);
            }
            return false;
        }
    }


    private static int[] parseIntsFromStringArray(String[] goalsString) {
        int[] result = new int[goalsString.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(goalsString[i]);
        }
        return result;
    }

}
