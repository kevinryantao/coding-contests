package codechef.manybananas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by ktao on 1/25/15.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Village village = new Village();

        int initialNumDiffFamilySizes = Integer.parseInt(scanner.nextLine());

        for(int i = 0; i < initialNumDiffFamilySizes; i++){
            int[] initialFamily = parseIntsFromStringArray(scanner.nextLine().split(" "));
            village.addInitialFamilies(initialFamily[0], initialFamily[1]);
        }

        int numQueries = Integer.parseInt(scanner.nextLine());
        String[] lines = new String[numQueries];
        Map<Integer, Integer> bananasDistributedCounts = new HashMap<Integer, Integer>();
        for(int i = 0; i < numQueries; i++){
            String s = scanner.nextLine();
            String[] query = s.split(" ");
            if(query[0].equals("?")){
                int value = Integer.parseInt(query[1]);
                Integer count = bananasDistributedCounts.get(value);
                if(count == null){
                    count = 0;
                }
                bananasDistributedCounts.put(value, count + 1);
            }
            lines[i] = s;
        }

        for(String line : lines){
            String[] query = line.split(" ");
            int value = Integer.parseInt(query[1]);
            if(query[0].equals("+")){
                village.addFamily(value);
            } else if(query[0].equals("-")){
                village.removeFamily(value);
            } else{
                System.out.println(village.distributeBananas(value, bananasDistributedCounts));
            }
        }
    }

    public static class Village{

        Map<Integer, Integer> villagePopulationInfo;

        Map<Integer, Long> bananaDistributionCache;

        public Village(){
            villagePopulationInfo = new HashMap<Integer, Integer>();
            bananaDistributionCache = new HashMap<Integer, Long>();
        }

        public void addInitialFamilies(int size, int quantity){
            villagePopulationInfo.put(size, quantity);
        }

        public void addFamily(int size){
            Integer integer = villagePopulationInfo.get(size);
            if(integer == null){
                integer = 0;
            }
            villagePopulationInfo.put(size, integer + 1);
            for(int bananasPerFamily : bananaDistributionCache.keySet()){
                long bananasReturned = bananaDistributionCache.get(bananasPerFamily);
                int newFamilyBananas = bananasPerFamily % size;
                if(newFamilyBananas > 0){
                    bananaDistributionCache.put(bananasPerFamily, bananasReturned + newFamilyBananas);
                }
            }
        }

        public void removeFamily(int size){
            int quantity = villagePopulationInfo.get(size) - 1;
            if(quantity > 0){
                villagePopulationInfo.put(size, quantity);
            } else {
                villagePopulationInfo.remove(size);
            }
            for(int bananasPerFamily : bananaDistributionCache.keySet()){
                long bananasReturned = bananaDistributionCache.get(bananasPerFamily);
                int lostFamilyBananas = bananasPerFamily % size;
                if(lostFamilyBananas > 0){
                    bananaDistributionCache.put(bananasPerFamily, bananasReturned - lostFamilyBananas);
                }
            }
        }

        public long distributeBananas(int bananasPerFamily, Map<Integer, Integer> bananasDistributedCounts){
            if(bananaDistributionCache.containsKey(bananasPerFamily)){
                return bananaDistributionCache.get(bananasPerFamily);
            }
            long bananasReturned = 0;
            for(Map.Entry<Integer, Integer> entry : villagePopulationInfo.entrySet()){
                int size = entry.getKey();
                long bananasBackPerFamily = bananasPerFamily % size;
                long bananasReturnedForSize = bananasBackPerFamily * entry.getValue();
                bananasReturned += bananasReturnedForSize;
            }
            if(bananasDistributedCounts.get(bananasPerFamily) > 2){
                bananaDistributionCache.put(bananasPerFamily, bananasReturned);
            }
            return bananasReturned;
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
