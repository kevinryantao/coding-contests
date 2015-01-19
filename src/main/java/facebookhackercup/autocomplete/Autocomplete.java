package facebookhackercup.autocomplete;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by ktao on 1/17/15.
 */
public class Autocomplete {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("autocomplete_example_input.txt"));
        int numCases = Integer.parseInt(scanner.nextLine());

        PrintWriter writer  = new PrintWriter("autocomplete_example_out.txt");

        for(int i = 1 ; i <= numCases; i++){
            int numWords = Integer.parseInt(scanner.nextLine());
            String[] words = new String[numWords];
            for(int j = 0 ; j < numWords; j++){
                words[j] = scanner.nextLine();
            }

            long result = run(words);
            System.out.println("Case #" + i + ": " + result);
            writer.println("Case #" + i + ": " + result);


        }
        writer.close();

    }

    private Map<String, String> dictionary;

    public Autocomplete(){
        dictionary = new HashMap<String, String>();
    }

    private static long run(String[] words) {
        Autocomplete autocomplete = new Autocomplete();
        long lettersTyped = 0;

        for(String word : words){
            autocomplete.addToDictionary(word);
            lettersTyped += autocomplete.minLettersTyped(word);
        }
        System.out.println("Size of map : " + autocomplete.dictionary.size());


        return lettersTyped;
    }

    private long minLettersTyped(String word) {
        for(int i = 1; i < word.length(); i++){
            if(dictionary.get(word.substring(0,i)) != null){
                return i;
            }
        }
        return word.length();
    }

    private void addToDictionary(String word) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < word.length(); i++){
            stringBuilder.append(word.charAt(i));
            String s = stringBuilder.toString();
            if(dictionary.containsKey(s)){
                String otherWord = dictionary.get(s);
                if(otherWord != null){
                    int indexOfFirstDiff = findIndexOfFirstDifference(word, otherWord);
                    if(indexOfFirstDiff < otherWord.length()){
                        String otherWordSub = otherWord.substring(0, indexOfFirstDiff);
                        dictionary.put(otherWordSub, otherWord);
                    }
                }
                dictionary.put(s, null);
            } else {
                dictionary.put(s, word);
                break;
            }
        }
    }

    private int findIndexOfFirstDifference(String word, String otherWord){
        for(int i = 0; i < Math.min(word.length(), otherWord.length()); i++){
            if(word.charAt(i) != otherWord.charAt(i)){
                return i;
            }
        }
        return Math.min(word.length(), otherWord.length());
    }

}
