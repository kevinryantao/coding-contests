package codechef.chefch;
import java.util.Scanner;

/**
 * Created by ktao on 2/10/15.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCases = Integer.parseInt(scanner.nextLine());
        for(int i = 0; i < numCases; i++){
            System.out.println(process(scanner.nextLine()));
        }


    }

    private static int process(String line) {
        int plusFirstCount = 0;
        int minusFirstCount = 0;
        for(int i = 0; i < line.length(); i++){
            if(i % 2 ==0){
                if(line.charAt(i) == '+'){
                    minusFirstCount++;
                } else {
                    plusFirstCount++;
                }
                //even index
            } else{
                if(line.charAt(i) == '+'){
                    plusFirstCount++;
                } else {
                    minusFirstCount++;
                }
                //odd index
            }
        }

        return Math.min(plusFirstCount, minusFirstCount);
    }


    private static int[] parseIntsFromStringArray(String[] strings) {
        int[] result = new int[strings.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(strings[i]);
        }
        return result;
    }

}
