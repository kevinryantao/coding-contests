package codeforces.round287;

import java.util.*;

/**
 * Created by ktao on 1/23/15.
 */
public class AmrAndMusic {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nAndK = parseIntsFromStringArray(scanner.nextLine().split(" "));
        int n = nAndK[0];
        int k = nAndK[1];
        int[] instrumentTimes = parseIntsFromStringArray(scanner.nextLine().split(" "));

        Instrument[] instruments = new Instrument[n];
        for(int i = 0; i < n; i++){
            instruments[i] = new Instrument(i+1, instrumentTimes[i]);
        }

        Arrays.sort(instruments, new Comparator<Instrument>() {
            @Override
            public int compare(Instrument o1, Instrument o2) {
                return o1.timeToLearn - o2.timeToLearn;
            }
        });

        int timeLeft = k;
        List<Instrument> instrumentsLearned = new ArrayList<Instrument>();
        for(int i = 0; i < n; i++){
            if(instruments[i].timeToLearn > timeLeft){
                break;
            }
            instrumentsLearned.add(instruments[i]);
            timeLeft -= instruments[i].timeToLearn;
        }

        System.out.println(instrumentsLearned.size());
        if(instrumentsLearned.size() > 0){
            String instrumentIds = createInstrumentIds(instrumentsLearned);
            System.out.println(instrumentIds);
        }

    }

    private static String createInstrumentIds(List<Instrument> instrumentsLearned) {
        StringBuilder stringBuilder = new StringBuilder();
        for(Instrument instrument : instrumentsLearned){
            stringBuilder.append(instrument.index).append(" ");
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(" "));
        return stringBuilder.toString();
    }

    private static class Instrument{
        private int index;
        private int timeToLearn;

        private Instrument(int index, int timeToLearn){

            this.index = index;
            this.timeToLearn = timeToLearn;
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
