package hackerrank.catandamouse;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

// https://www.hackerrank.com/contests/hourrank-15/challenges/cats-and-a-mouse

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            int x = in.nextInt();
            int y = in.nextInt();
            int z = in.nextInt();

            System.out.println(huntMouse(x,y,z));
        }

    }

    private static String huntMouse(int catAPosition, int catBPosition, int mousePosition) {
        int catADistance = Math.abs(catAPosition - mousePosition);
        int catBDistance = Math.abs(catBPosition - mousePosition);

        if(catADistance == catBDistance){
            return "Mouse C";
        }
        if(catADistance < catBDistance){
            return "Cat A";
        }
        if(catADistance > catBDistance){
            return "Cat B";
        }
        return null;
    }
}
