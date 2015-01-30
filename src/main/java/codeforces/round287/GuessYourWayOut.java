package codeforces.round287;

import java.util.Scanner;

/**
 * Created by ktao on 1/23/15.
 */
public class GuessYourWayOut {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int [] hAndN = parseIntsFromStringArray(scanner.nextLine().split(" "));
        int height = hAndN[0] + 1;
        int exit = hAndN[1];
        BinaryTreeNode[][] nodeMap = new BinaryTreeNode[height][];
        for(int i = 0; i < height; i++){
            int numNodesAtLevel = (int) Math.pow(2, i);
            nodeMap[i] = new BinaryTreeNode[numNodesAtLevel];
            for(int j = 0; j < numNodesAtLevel; j++){
                BinaryTreeNode parent = null;
                boolean isWayOut = false;
                if(i > 0){
                    parent = nodeMap[i-1][j/2];
                }
                if(i == height - 1  && j == exit-1){
                    isWayOut = true;
                }
                nodeMap[i][j] = new BinaryTreeNode(parent, null, null, isWayOut);
                if(parent != null) {
                    if(j % 2 == 0){
                        parent.leftChild = nodeMap[i][j];
                    } else {
                        parent.rightChild = nodeMap[i][j];
                    }
                }
            }
        }

        // now to traverse the tree

        BinaryTreeNode currentNode = nodeMap[0][0];
        currentNode.visited = true;
        boolean isLTheNextStep = true;
        int numCurrentSkips = 0;
        while (!currentNode.isWayOut){
            BinaryTreeNode destination = null;
            if(isLTheNextStep){
                destination = currentNode.leftChild;
            } else {
                destination = currentNode.rightChild;
            }

            if(destination == null){
                currentNode = currentNode.parent;
                numCurrentSkips = 0;
            } else if(destination.visited){
                numCurrentSkips ++;
                isLTheNextStep = (!isLTheNextStep);
                if(numCurrentSkips >= 2){
                    currentNode = currentNode.parent;
                    numCurrentSkips = 0;
                }
            } else {
                currentNode = destination;
                currentNode.visited = true;
                numCurrentSkips = 0;
                isLTheNextStep = (!isLTheNextStep);
            }
        }

        int numberOfNodesVisited = 0;
        for (int i = 0; i < height; i++){
            for (int j = 0; j < nodeMap[i].length; j++){
                if(nodeMap[i][j].visited && !nodeMap[i][j].isWayOut){
                    numberOfNodesVisited++;
                }
            }
        }
        System.out.println(numberOfNodesVisited);

    }





    private static class BinaryTreeNode{
        boolean visited = false;
        BinaryTreeNode parent;
        BinaryTreeNode leftChild;
        BinaryTreeNode rightChild;
        boolean isWayOut;

        public BinaryTreeNode(BinaryTreeNode parent, BinaryTreeNode leftChild, BinaryTreeNode rightChild, boolean isWayOut) {
            this.parent = parent;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.isWayOut = isWayOut;
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
