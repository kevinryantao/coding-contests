package codechef.tourmap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    //Tourmap

    public static class City {
        String theName;
        City theNextCity = null;
        int thePrice = 0;

        public City(String aName, City aNextCity) {
            theName = aName;
            theNextCity = aNextCity;
        }

        public int hashCode() {
            return theName.hashCode();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader myBufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int myNumberOfTestCases = Integer.parseInt(myBufferedReader.readLine());

        for (int i = 0; i < myNumberOfTestCases; i++) {
            int myNumberOfCities = Integer.parseInt(myBufferedReader.readLine());
            Set<City> thePotentialHeadCities = new HashSet<City>();
            int theTotalCost = 0;

            Map<String, City> theCityMap = new HashMap<String, City>();

            for (int j = 0; j < myNumberOfCities - 1; j++) {
                String[] myRoute = getRoute(myBufferedReader);
                int myCost = Integer.parseInt(myRoute[2].substring(0,
                                                                   myRoute[2].length() - 1));
                theTotalCost += myCost;
                City myCity2 = theCityMap.get(myRoute[1]);
                if (myCity2 == null) {
                    myCity2 = new City(myRoute[1],
                                       null);
                    theCityMap.put(myRoute[1],
                                   myCity2);
                } else {
                    thePotentialHeadCities.remove(myCity2);
                }
                City myCity1 = theCityMap.get(myRoute[0]);
                if (myCity1 == null) {
                    myCity1 = new City(myRoute[0],
                                       myCity2);
                    theCityMap.put(myRoute[0],
                                   myCity1);
                    myCity1.thePrice = myCost;
                    thePotentialHeadCities.add(myCity1);
                } else {
                    myCity1.theNextCity = myCity2;
                    myCity1.thePrice = myCost;
                }
            }

            City theHead = thePotentialHeadCities.iterator().hasNext() ? thePotentialHeadCities.iterator()
                                                                                               .next() : null;
            while (theHead != null && theHead.theNextCity != null) {
                System.out.println(theHead.theName + " " + theHead.theNextCity.theName + " " + theHead.thePrice + "$");
                theHead = theHead.theNextCity;
            }
            System.out.println(theTotalCost + "$");
        }
    }

    private static String[] getRoute(BufferedReader myBufferedReader) throws IOException {
        return myBufferedReader.readLine().split(" ");
    }
}

