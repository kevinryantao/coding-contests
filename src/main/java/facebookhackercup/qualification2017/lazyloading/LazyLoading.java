package facebookhackercup.qualification2017.lazyloading;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class LazyLoading {

    public static void main(String[] args) throws FileNotFoundException {
        //Scanner scanner = new Scanner(System.in);

        Scanner scanner = new Scanner(new File("lazy_loading.txt"));
        PrintWriter writer = new PrintWriter("lazy_loading_out.txt");

        int numDays = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numDays; i++) {
            int numItems = Integer.parseInt(scanner.nextLine());
            int[] items = new int[numItems];
            for (int j = 0; j < numItems; j++) {
                items[j] = Integer.parseInt(scanner.nextLine());
            }
            Arrays.sort(items);
            System.out.println("Case #" + (i+1) + ": " + getNumTrips(items));
            writer.println("Case #" + (i+1) + ": " + getNumTrips(items));

        }
        writer.close();

    }

    // Greedy works
    private static int getNumTrips(int[] items) {
        if (items.length == 0){
            return 0;
        }
        if (items[items.length - 1] >= 50) {
            int[] newArray = Arrays.copyOfRange(items, 0, items.length - 1);
            return getNumTrips(newArray) + 1;
        }
        int largestItem = items[items.length - 1];

        int numBoxesRequired = 50 / largestItem;
        if (50 % largestItem > 0) {
            numBoxesRequired++;
        }

        if (items.length < numBoxesRequired) {
            return 0;
        }
        if (items.length == numBoxesRequired) {
            return 1;
        }
        if (items.length > numBoxesRequired) {
            int[] newArray = Arrays.copyOfRange(items, numBoxesRequired - 1, items.length - 1);
            return getNumTrips(newArray) + 1;
        }
        return 0;
    }
}
