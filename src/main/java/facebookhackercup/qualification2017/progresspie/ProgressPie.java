package facebookhackercup.qualification2017.progresspie;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ProgressPie {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("progress_pie.txt"));
        int numberOfCases = Integer.parseInt(scanner.nextLine());
        PrintWriter writer = new PrintWriter("progress_pie_out.txt");

        for(int i = 0; i < numberOfCases; i++){
            String line = scanner.nextLine();
            String[] split = line.split(" ");
            int percentage = Integer.parseInt(split[0]);
            int x = Integer.parseInt(split[1]);
            int y = Integer.parseInt(split[2]);

            //System.out.println("Case #" + (i + 1) + ": " + getColorOfPoint(percentage, x, y));
            writer.println("Case #" + (i + 1) + ": " + getColorOfPoint(percentage, x, y));
        }

        writer.close();
    }

    public static String getColorOfPoint(int percentageFilled, int xCoordinate, int yCoordinate){

        if(percentageFilled == 0){
            return "white";
        }

        if(!insideCircle(xCoordinate, yCoordinate)){
            return "white";
        }
        if(angleOfPoint(xCoordinate, yCoordinate) <= angleFilled(percentageFilled)){
            return "black";
        }
        return "white";
    }

    public static double angleOfPoint(int xCoordinate, int yCoordinate){
        int xOffset = xCoordinate - 50;
        int yOffset = yCoordinate - 50;

        return (360 + Math.atan2(xOffset, yOffset) * 180 / Math.PI) % 360;
    }

    public static double angleFilled(int percentageFilled){
        return 3.6 * percentageFilled;
    }

    public static boolean insideCircle(int xCoordinate, int yCoordinate){
        double distanceFromX = Math.abs(xCoordinate - 50);
        double distanceFromY = Math.abs(yCoordinate - 50);
        double distanceFromCenter = Math.sqrt(distanceFromX * distanceFromX + distanceFromY * distanceFromY);
        return distanceFromCenter <= 50;
    }
}
