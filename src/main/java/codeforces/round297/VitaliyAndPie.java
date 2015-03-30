package codeforces.round297;

import java.util.Scanner;

/**
 * Created by ktao on 3/26/15.
 */
public class VitaliyAndPie {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numRooms = Integer.parseInt(scanner.nextLine());
        String keysAndDoor = scanner.nextLine().toLowerCase();

        char[] keys = new char[numRooms - 1];
        char[] doors = new char[numRooms - 1];

        for(int i = 0; i < numRooms - 1; i++){
            keys[i] = keysAndDoor.charAt(2 * i);
            doors[i] = keysAndDoor.charAt(2 * i + 1);
        }

        int countKeysToBuy = solve(numRooms, keys, doors);
        System.out.println(countKeysToBuy);
    }

    private static int solve(int numRooms, char[] keys, char[] doors) {
        int countKeysToBuy = 0;
        int[] keyInventory = new int[26];
        for(int i = 0; i < numRooms - 1; i++){
            // add key to inventory
            keyInventory[keys[i]-'a']++;
            if(keyInventory[doors[i]-'a'] > 0) {
                keyInventory[doors[i]-'a']--;
            } else {
                countKeysToBuy++;
            }
        }
        return countKeysToBuy;
    }
}
