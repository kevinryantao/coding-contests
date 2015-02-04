package codeforces.round290;

import java.util.Scanner;

/**
 * Created by ktao on 2/2/15.
 */
public class FoxAndSnake {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nAndM = parseIntsFromStringArray(scanner.nextLine().split(" "));
        int rows = nAndM[0];
        int cols = nAndM[1];

        for(int r = 0; r < rows; r++){
            StringBuilder stringBuilder = new StringBuilder();
            for(int c = 0; c < cols; c++){
                if(r % 2 == 0){
                    stringBuilder.append('#');
                } else {
                    if(r % 4 == 1){
                        if(c == cols - 1){
                            stringBuilder.append('#');
                        } else {
                            stringBuilder.append('.');
                        }
                    } else if(r % 4 ==3){
                        if(c==0){
                            stringBuilder.append('#');
                        } else {
                            stringBuilder.append('.');
                        }
                    }
                }
            }
            System.out.println(stringBuilder);
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
