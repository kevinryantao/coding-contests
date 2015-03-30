package codeforces.round297;

import java.util.Scanner;

/**
 * http://codeforces.com/contest/525/problem/B
 */
public class PashaAndString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String beautifulString = scanner.nextLine();
        int numReverses = Integer.parseInt(scanner.nextLine());
        int[] reverses = parseIntsFromStringArray(scanner.nextLine().split(" "));

        System.out.println(solve(beautifulString, numReverses, reverses));

    }

    private static String solve(String beautifulString, int numReverses, int[] reverses) {
        int length = beautifulString.length();
        CharacterDuo[] characterDuos = new CharacterDuo[(length + 1) / 2];
        for(int i = 0 ; i < characterDuos.length; i++){
            characterDuos[i] = new CharacterDuo(beautifulString.charAt(i), beautifulString.charAt(length - i - 1));
        }

        int[] reversesHistogram = new int[(length + 2) / 2];
        for(int i = 0; i < reverses.length; i++){
            reversesHistogram[reverses[i] - 1]++;
        }


        int numReversesThusFar = 0;

        for(int i = 0; i < characterDuos.length; i++){
            numReversesThusFar += reversesHistogram[i];
            if(numReversesThusFar % 2 == 1){
                characterDuos[i].reverse();
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < length; i++){
            if(i < characterDuos.length){
                // character duos first char
                stringBuilder.append(characterDuos[i].firstChar);
            } else {
                int distanceFromEnd = length - 1 - i;
                // character duos second char
                stringBuilder.append(characterDuos[distanceFromEnd].secondChar);
            }
        }


        return stringBuilder.toString();
    }

    public static class CharacterDuo{
        char firstChar;
        char secondChar;

        public CharacterDuo(char firstChar, char secondChar) {
            this.firstChar = firstChar;
            this.secondChar = secondChar;
        }

        public void reverse(){
            char tempChar = firstChar;
            firstChar = secondChar;
            secondChar = tempChar;
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
