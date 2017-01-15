package facebookhackercup.qualification2017.fightingzombie;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Created by ktao on 1/8/17.
 */
public class FightingTheZombie {

    private static DecimalFormat df = new DecimalFormat("0.000000");

    public static void main(String[] args) throws FileNotFoundException {
        //Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(new File("fighting_the_zombie.txt"));
        PrintWriter writer = new PrintWriter("fighting_the_zombie_out.txt");


        int numZombies = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numZombies; i++){
            int[] zombieStats = parseIntsFromStringArray(scanner.nextLine().split(" "));
            int zombieHealth = zombieStats[0];
            String[] spells = scanner.nextLine().split(" ");
            double highestProbabilityThusFar = 0;
            for (int j = 0; j < spells.length; j++){
                double probability = getProbability(zombieHealth, spells[j]);
                if(probability > highestProbabilityThusFar){
                    highestProbabilityThusFar = probability;
                    if(highestProbabilityThusFar == 1){
                        break;
                    }
                }
            }
            System.out.println("Case #" + (i+1) + ": " + df.format(highestProbabilityThusFar));
            writer.println("Case #" + (i+1) + ": " + df.format(highestProbabilityThusFar));
        }
        writer.close();
    }

    private static double getProbability(int zombieHealth, String spell) {
        int offset = 0;
        if(spell.contains("+")){
            String[] split = spell.split("\\+");
            offset = Integer.parseInt(split[1]);
            spell = split[0];
        } else if(spell.contains("-")){
            String[] split = spell.split("-");
            offset = Integer.parseInt(split[1]) * -1;
            spell = split[0];
        }

        int[] diceStats = parseIntsFromStringArray(spell.split("d"));
        int numDice = diceStats[0];
        int diceSides = diceStats[1];

        zombieHealth -= (offset + numDice);

        if (zombieHealth <= 0){
            return 1;
        }

        int max = numDice * (diceSides - 1);
        if (zombieHealth > max){
            return 0;
        }

        long[] waysToGet = new long[max + 1];
        for(int j = 0; j < diceSides; j++){ //initialize
            waysToGet[j] = 1;
        }

        for(int i = 1; i < numDice; i++){
            long[] newWaysToGet = new long[max + 1];
            for(int sum = 0; sum < newWaysToGet.length; sum++){
                int runningWaysToGet = 0;
                for(int j = 0; j < diceSides; j++){
                    if (sum - j >= 0){
                        runningWaysToGet += waysToGet[sum - j];
                    }
                }
                newWaysToGet[sum] = runningWaysToGet;
            }
            waysToGet = newWaysToGet;
        }

        long waysToKill = 0;
        for(int i = zombieHealth; i < waysToGet.length; i++){
            waysToKill += waysToGet[i];
        }

        double totalWays = Math.pow(diceSides, numDice);

        return waysToKill / totalWays;
    }


    private static int[] parseIntsFromStringArray(String[] goalsString) {
        int[] result = new int[goalsString.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(goalsString[i]);
        }
        return result;
    }
}
