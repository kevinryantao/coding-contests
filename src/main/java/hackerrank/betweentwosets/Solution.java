package hackerrank.betweentwosets;

/**
 * Created by ktao on 11/6/16.
 */
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
        int[] b = new int[m];
        for(int b_i=0; b_i < m; b_i++){
            b[b_i] = in.nextInt();
        }

        Arrays.sort(a);
        Arrays.sort(b);

        int max_a = a[a.length -1];
        int min_b = b[0];

        int count = 0;
        for (int i = max_a; i <= min_b; i++){
            boolean allAAreFactors = true;
            for(int j = 0; j < a.length; j++){
                if(i % a[j] != 0){
                    allAAreFactors = false;
                    break;
                }
            }
            if (!allAAreFactors){
                continue;
            }
            boolean factorOfAllB = true;
            for(int k = 0; k < b.length; k++){
                if(b[k] % i != 0){
                    factorOfAllB = false;
                    break;
                }
            }
            if(factorOfAllB){
                count ++;
            }
        }

        System.out.println(count);
    }
}
