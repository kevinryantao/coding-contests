package hackerrank.pointsonarectangle;

/**
 * Created by ktao on 10/18/16.
 */
import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        int numQueries = Integer.parseInt(scanner.nextLine());

        for(int i = 0; i < numQueries; i++){
            int numPoints = Integer.parseInt(scanner.nextLine());
            List<int[]> points = new ArrayList<int[]>();
            for(int j = 0; j < numPoints; j++){
                int[] point = parseIntsFromStringArray(scanner.nextLine().split(" "));
                points.add(point);
            }
            System.out.println(assessRectangularity(points));
        }
    }

    private static int[] parseIntsFromStringArray(String[] string) {
        int[] result = new int[string.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(string[i]);
        }
        return result;
    }

    private static String assessRectangularity(List<int[]> points){
        Map<Integer, List<int[]>> xMap = new TreeMap<Integer, List<int[]>>();
        Map<Integer, List<int[]>> yMap = new TreeMap<Integer, List<int[]>>();

        for(int[] point: points){
            if(xMap.get(point[0]) == null){
                xMap.put(point[0], new ArrayList<int[]>());
            }
            if(yMap.get(point[1]) == null){
                yMap.put(point[1], new ArrayList<int[]>());
            }
            xMap.get(point[0]).add(point);
            yMap.get(point[1]).add(point);
        }

        if(xMap.size() <= 2){
            return "YES";
        }
        if(yMap.size() <= 2){
            return "YES";
        }

        Set<int[]> pointsCopy = new HashSet<int[]>(points);

        Integer[] xKeys = xMap.keySet().toArray(new Integer[xMap.size()]);
        Integer[] yKeys = yMap.keySet().toArray(new Integer[yMap.size()]);

        List<int[]> rightMost = xMap.get(xKeys[xKeys.length - 1]);
        List<int[]> leftMost = xMap.get(xKeys[0]);

        List<int[]> topMost = yMap.get(yKeys[yKeys.length - 1]);
        List<int[]> bottomMost = yMap.get(yKeys[0]);

        deleteFromSet(pointsCopy, rightMost);
        deleteFromSet(pointsCopy, leftMost);
        deleteFromSet(pointsCopy, topMost);
        deleteFromSet(pointsCopy, bottomMost);

        if(pointsCopy.size() > 0){
            return "NO";
        }

        return "YES";
    }

    private static void deleteFromSet(Set<int[]> pointsCopy, List<int[]> pointsInLine) {
        for(int[] point : pointsInLine){
            pointsCopy.remove(point);
        }
    }
}