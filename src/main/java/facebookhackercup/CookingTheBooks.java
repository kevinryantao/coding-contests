package facebookhackercup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Created by ktao on 1/9/15.
 */
public class CookingTheBooks {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("cooking_the_books.txt"));
        int numLines = Integer.parseInt(scanner.nextLine());

        PrintWriter writer = new PrintWriter("output.txt");

        for(int i = 1; i <= numLines; i++){
            String line = scanner.nextLine();
            String result = findMinAndMax(line);

            writer.println("Case #" + i + ": " + result);

        }
        writer.close();
    }

    private static String findMinAndMax(String line) {
        TreeSet<String> orderedSet = new TreeSet<String>();
        orderedSet.add(line);
        for(int i = 0; i < line.length() - 1; i++){
            for(int j = i + 1; j < line.length(); j++){
                String swappedNumber = swap(line, i, j);
                if (! (swappedNumber.charAt(0) == '0')){
                    orderedSet.add(swappedNumber);
                }
            }
        }
        String smallest = orderedSet.first();
        String largest = orderedSet.last();

        return smallest + " " + largest;
    }

    private static String swap(String line, int index1, int index2) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < line.length(); i++){
            if(i == index1){
                stringBuilder.append(line.charAt(index2));
            } else if(i == index2){
                stringBuilder.append(line.charAt(index1));
            } else {
                stringBuilder.append(line.charAt(i));
            }
        }
        return stringBuilder.toString();
    }

}
