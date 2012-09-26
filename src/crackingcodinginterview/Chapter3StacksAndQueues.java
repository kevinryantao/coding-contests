package crackingcodinginterview;

public class Chapter3StacksAndQueues {

    class MultiStack{
        Object[] theStack = new Object[1000000];
        int[] theIndices;
        public MultiStack(int aSize){
            theIndices = new int[aSize];
            for(int i = 0; i < theIndices.length; i++){
                theIndices[i] = i;
            }
        }

        public void push(Object aObject, int aStackNumber){
            theIndices[aStackNumber] += theIndices.length;
            theStack[theIndices[aStackNumber]] = aObject;
        }

        public Object pop(int aStackNumber){
            if(theIndices[aStackNumber] < 0){
                return null;
            }
            Object myValue = theStack[theIndices[aStackNumber]];
            theIndices[aStackNumber] -= theIndices.length;
            return myValue;
        }
    }

    public class MinHeapStack{

        public Comparable theMinimum;

        public void push(Comparable aObject){
            if (aObject.compareTo(theMinimum) < 0){
                theMinimum = aObject;
            }

        }

        public Comparable pop(){
            return null;
        }

        public Comparable getMinimum(){
            return null;
        }



    }


}
