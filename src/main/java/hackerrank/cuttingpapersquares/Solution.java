package hackerrank.cuttingpapersquares;

/**
 * Created by ktao on 10/18/16.
 */
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        long length = scanner.nextInt();
        long width = scanner.nextInt();

        long numberLengthCuts = length - 1;
        long numberWidthCuts = width - 1;

        System.out.println(numberLengthCuts * width + numberWidthCuts);


    }
}