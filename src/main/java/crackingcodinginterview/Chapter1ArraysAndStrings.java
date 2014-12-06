package crackingcodinginterview;

public class Chapter1ArraysAndStrings {

    public static boolean areAllCharsUnique(String aString){
        char[] myCharArray = aString.toCharArray();
        boolean[] myPresentChars = new boolean[256];
        for(int i = 0; i < myCharArray.length; i++){
            if(myPresentChars[myCharArray[i]]){
                return false;
            }
            myPresentChars[myCharArray[i]] = true;
        }
        return true;
    }

    public static String removeDuplicates(String aString){
        StringBuilder myStringBuilder = new StringBuilder(aString);
        for(int i = 0; i < myStringBuilder.length() - 1; i++){
            char myChar = myStringBuilder.charAt(i);
            for(int j = i + 1; j < myStringBuilder.length(); j++ ){
                if(myStringBuilder.charAt(j) == myChar){
                    myStringBuilder.deleteCharAt(j);
                    j--;
                }
            }
        }
        return myStringBuilder.toString();
    }


}
