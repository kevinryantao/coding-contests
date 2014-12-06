package codekatas.codekata2;

public class KarateChop1 {

    /**
     * Returns index of aInteger from a sorted integer array
     */
    public static int chop(int aInteger, int[] aIntegerArray) {
        int myStartIndex = 0;
        int myEndIndex = aIntegerArray.length - 1;
        while (myEndIndex >= myStartIndex) {
            int myMiddleIndex = (myEndIndex - myStartIndex) / 2 + myStartIndex;
            if (aIntegerArray[myMiddleIndex] > aInteger) {
                myEndIndex = myMiddleIndex - 1;
            } else if (aIntegerArray[myMiddleIndex] < aInteger) {
                myStartIndex = myMiddleIndex + 1;
            } else {
                return myMiddleIndex;
            }
        }
        return -1;
    }
}
