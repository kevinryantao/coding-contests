package interviewstreet;
public class MultiplicationTables{

    public static void main(String[] args){
        int maxFactor = 12;
        int boxLength = Integer.toString(maxFactor * maxFactor).length() + 2;
        printTable(calculateTable(12), boxLength);
    }

    public static int[][] calculateTable(int size){
        int[][] integerMatrix = new int[size][size];
        for(int i = 1 ; i <= size ; i++){
            for(int j = 1 ; j <= size ; j++){
                integerMatrix[i-1][j-1] = i * j;
            }
        }
        return integerMatrix;
    }

    public static void printTable(int[][] table, int boxLength){
        StringBuffer[] myStringBufferArray = new StringBuffer[table.length];
        for(int i = 0; i < table.length; i++){
            StringBuffer myStringBuffer = new StringBuffer();
            for(int j = 0; j < table[i].length; j++){
                myStringBuffer.append(formatNumber(table[i][j], boxLength));
            }
            myStringBufferArray[i] = myStringBuffer;
        }
        for(StringBuffer myStringBuffer : myStringBufferArray){
            System.out.println(myStringBuffer.toString());
        }
    }

    public static String formatNumber(int number, int boxLength){
        int length = Integer.toString(number).length();
        StringBuffer myStringBuffer = new StringBuffer();
        for(int i = 0 ; i < boxLength - length ; i++){
            myStringBuffer.append(" ");
        }
        myStringBuffer.append(number);
        return myStringBuffer.toString();
    }


}