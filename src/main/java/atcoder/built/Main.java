package atcoder.built;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by ktao on 6/24/17.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int[][] coordinates = new int[n][2];
        for (int i = 0; i < n; i++){
            coordinates[i] = parseIntsFromStringArray(scanner.nextLine().split(" "));
        }
        long result = run(n, coordinates);
        System.out.println(result);
    }

    private static long run(int n, int[][] coordinates) {
        long[][] costs = new long[n][n];
        for(int i = 0; i < n - 1; i++){
            for(int j = i + 1; j < n; j++){
                costs[i][j] = cost(coordinates[i], coordinates[j]);
                costs[j][i] = costs[i][j];
            }
        }

        Set<Integer> collection = new HashSet<Integer>();





        return 0;
    }

    private static long cost(int[] coordinate1, int[] coordinate2){
        return Math.min(Math.abs(coordinate1[0] - coordinate2[0]), Math.abs(coordinate1[1] - coordinate2[1]));
    }

    private static int[] parseIntsFromStringArray(String[] strings) {
        int[] result = new int[strings.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(strings[i]);
        }
        return result;
    }

}
