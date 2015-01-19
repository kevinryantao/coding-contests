package facebookhackercup.autocomplete;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by ktao on 1/17/15.
 */
public class CreateHarderInput {


    public static String alphabet = "abcdefghijklmnopqrstuvwxyz";
    public static Random random = new Random();


    public static void main(String[] args) throws FileNotFoundException {

        PrintWriter writer  = new PrintWriter("autocomplete_hardest_input.txt");



        writer.println(1);

        for(int caseNum = 1; caseNum <= 1; caseNum++){
            writer.println(10);
            HashSet<String> alreadyAdded = new HashSet<String>();
            for(int i = 0; i < 10; i++){
                String randomString = getRandomString(100000);
                while(alreadyAdded.contains(randomString)){
                    randomString = getRandomString(100000);
                }
                alreadyAdded.add(randomString);
                writer.println(randomString);
            }
        }
        writer.close();

    }

    public static String getRandomString(int length){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < length; i++){
            stringBuilder.append(getRandomCharacter());
        }
        return stringBuilder.toString();
    }

    public static char getRandomCharacter(){
        return alphabet.charAt(random.nextInt(alphabet.length()));
    }

}
