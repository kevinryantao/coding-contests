package facebookhackercup;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by ktao on 1/9/15.
 */
public class NewYearsResolution {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("new_years_resolution.txt"));
        int numTestCases = Integer.parseInt(scanner.nextLine());
        PrintWriter writer = new PrintWriter("output.txt");

        for (int testCase = 1; testCase <= numTestCases; testCase++) {
            String[] goalsString = scanner.nextLine().split(" ");

            int goals[] = parseIntsFromStringArray(goalsString);

            int numFoodItems = Integer.parseInt(scanner.nextLine());
            List<int[]> allFoodItems = new ArrayList<int[]>();
            for (int i = 0; i < numFoodItems; i++) {
                int[] foodNutrition = parseIntsFromStringArray(scanner.nextLine().split(" "));
                allFoodItems.add(foodNutrition);
            }

            String yesOrNo = canEatExactAmount(goals, allFoodItems);
            writer.println("Case #" + testCase + ": " + yesOrNo);
        }
        writer.close();
    }

    private static String canEatExactAmount(int[] goals, List<int[]> allFoodItems) {
        Set<ThreeDimensionalCoordinate> allPossibleCoordinates = new HashSet<ThreeDimensionalCoordinate>();
        for (int[] foodNutrition : allFoodItems) {
            // iterate over entire menu.
            Set<ThreeDimensionalCoordinate> potentialNewCoordinates = new HashSet<ThreeDimensionalCoordinate>();
            potentialNewCoordinates.add(new ThreeDimensionalCoordinate(foodNutrition));
            for (ThreeDimensionalCoordinate existingCoordinate : allPossibleCoordinates) {
                potentialNewCoordinates.add(existingCoordinate.createNewCoordinate(foodNutrition));
            }

            Collection<ThreeDimensionalCoordinate> newCoordinates = filterNewCoordinatesOutsideOfBounds(potentialNewCoordinates, goals);


            if (checkGoalMet(newCoordinates, goals)) {
                return "yes";
            }
            allPossibleCoordinates.addAll(newCoordinates);


        }

        return "no";
    }

    private static Collection<ThreeDimensionalCoordinate> filterNewCoordinatesOutsideOfBounds(Collection<ThreeDimensionalCoordinate> coordinateCollection, int[] goals) {
        List<ThreeDimensionalCoordinate> coordinateList = new ArrayList<ThreeDimensionalCoordinate>();
        for (ThreeDimensionalCoordinate coordinate : coordinateCollection) {
            if (!(coordinate.protein > goals[0] || coordinate.carbs > goals[1] || coordinate.fat > goals[2])) {
                coordinateList.add(coordinate);
            }
        }
        return coordinateList;
    }

    private static boolean checkGoalMet(Collection<ThreeDimensionalCoordinate> coordinateCollection, int[] goals) {
        for (ThreeDimensionalCoordinate coordinate : coordinateCollection) {
            if (coordinate.protein == goals[0] && coordinate.carbs == goals[1] && coordinate.fat == goals[2]) {
                return true;
            }
        }
        return false;
    }


    private static int[] parseIntsFromStringArray(String[] goalsString) {
        int[] result = new int[goalsString.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(goalsString[i]);
        }
        return result;
    }

    private static class ThreeDimensionalCoordinate {
        private int protein;
        private int carbs;
        private int fat;

        private ThreeDimensionalCoordinate(int protein, int carbs, int fat) {
            this.protein = protein;
            this.carbs = carbs;
            this.fat = fat;
        }

        private ThreeDimensionalCoordinate(int[] nutrition) {
            this.protein = nutrition[0];
            this.carbs = nutrition[1];
            this.fat = nutrition[2];
        }

        private ThreeDimensionalCoordinate createNewCoordinate(int[] nutrition) {
            return new ThreeDimensionalCoordinate(this.protein + nutrition[0], this.carbs + nutrition[1], this.fat + nutrition[2]);
        }

        public boolean equals(Object o) {
            if (o instanceof ThreeDimensionalCoordinate) {
                ThreeDimensionalCoordinate other = (ThreeDimensionalCoordinate) o;

                return new EqualsBuilder().append(protein, other.protein).append(carbs, other.carbs).append(fat, other.fat).isEquals();


            }
            return false;
        }

        public int hashCode() {
            return new HashCodeBuilder().append(protein).append(carbs).append(fat).toHashCode();
        }
    }
}
