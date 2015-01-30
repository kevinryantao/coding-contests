package codechef.piececake;

import java.util.Scanner;

/**
 * Created by ktao on 1/24/15.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numTestCases = Integer.parseInt(scanner.nextLine());

        for(int i = 0; i < numTestCases; i++){
            String line = scanner.nextLine();
            System.out.println(solve(line));


        }
    }

    public static String solve(String line){

        if(line.length() % 2 == 1){
            return "NO";
        }

        int[] letterCounts = new int[26];
        for(int i = 0; i < line.length(); i++){
            char character = line.charAt(i);
            int charIndex = character - 'a';
            letterCounts[charIndex]++;
        }


        for(int i = 0; i < letterCounts.length; i++){
            if(letterCounts[i] == line.length() / 2){
                return "YES";
            }
        }
        return "NO";

    }
}
