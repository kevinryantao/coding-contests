package hackerrank.weeklychallenges14.subtreesandpaths;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;


/**
 * https://www.hackerrank.com/contests/w14/challenges/subtrees-and-paths
 */
public class Solution {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);

        int numNodes = Integer.parseInt(scanner.nextLine());

        Map<Integer, Node> nodeDirectory = new HashMap<Integer, Node>();
        nodeDirectory.put(1, new Node(1, null));

        Queue<int[]> connectionList = new LinkedList<int[]>();
        for (int i = 0; i < numNodes - 1; i++) {
            int[] connection = parseIntsFromStringArray(scanner.nextLine().split(" "));
            connectionList.add(connection);
        }

        while (!connectionList.isEmpty()) {
            int[] connection = connectionList.poll();
            int parentId = 0;
            int childId = 0;
            if (nodeDirectory.containsKey(connection[0])) {
                parentId = connection[0];
                childId = connection[1];
            } else if (nodeDirectory.containsKey(connection[1])) {
                parentId = connection[1];
                childId = connection[0];
            }
            if (parentId != 0 && childId != 0) {
                Node parentNode = nodeDirectory.get(parentId);
                Node childNode = new Node(childId, parentNode);
                nodeDirectory.put(childId, childNode);
            } else {
                // neither parent nor child is in the directory yet
                connectionList.offer(connection);
            }
        }

        // note: this is pretty inefficient, because of the fact that we have to wait until we know exactly which one is the child and which is the parent

        // a future optimization will acknowledge the connections and only assign directionality once the connection to the root tree is established.

        int numCommands = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < numCommands; i++) {
            String[] split = scanner.nextLine().split(" ");
            if (split[0].equals("add")) {
                int nodeId = Integer.parseInt(split[1]);
                int valueToAdd = Integer.parseInt(split[2]);
                Node node = nodeDirectory.get(nodeId);
                node.addValue(valueToAdd);
                // add values to entire subtree
            } else if (split[0].equals("max")) {
                System.out.println(max(nodeDirectory, split));
            }
        }
    }

    private static int max(Map<Integer, Node> nodeDirectory, String[] split) {
        // plot a path between the two params, and find the max value between those two nodes.
        int nodeId1 = Integer.parseInt(split[1]);
        int nodeId2 = Integer.parseInt(split[2]);

        Node node1 = nodeDirectory.get(nodeId1);
        Node node2 = nodeDirectory.get(nodeId2);

        List<Integer> parentChain1 = node1.getParentChain();
        List<Integer> parentChain2 = node2.getParentChain();

        int commonAncestorId = 1;
        int commonAncestorIndex = 0;
        int commonAncestorSum = 0;

        for (int j = 0; j < parentChain1.size() && j < parentChain2.size(); j++) {
            if (parentChain1.get(j).equals(parentChain2.get(j))) {
                commonAncestorId = parentChain1.get(j);
                commonAncestorIndex = j;
                commonAncestorSum += nodeDirectory.get(commonAncestorId).value;
            } else {
                break;
            }
        }

        List<Integer> parentSubchain1 = parentChain1.subList(commonAncestorIndex + 1, parentChain1.size());

        List<Integer> parentSubchain2 = parentChain2.subList(commonAncestorIndex + 1, parentChain2.size());


        //List<Integer> path = findPath(parentChain1, parentChain2, commonAncestorId, commonAncestorIndex);

        int maxSoFar = commonAncestorSum;
        int valueSoFar = commonAncestorSum;
        for (int index : parentSubchain1) {
            int value = nodeDirectory.get(index).value;
            valueSoFar += value;
            if (valueSoFar > maxSoFar) {
                maxSoFar = valueSoFar;
            }
        }
        valueSoFar = commonAncestorSum;
        for (int index : parentSubchain2) {
            int value = nodeDirectory.get(index).value;
            valueSoFar += value;
            if (valueSoFar > maxSoFar) {
                maxSoFar = valueSoFar;
            }
        }

        return maxSoFar;
    }

    private static class Node {

        int id;
        Node parent;
        Set<Node> children = new HashSet<Node>();
        int value = 0;

        private Node(int id, Node parent) {
            this.id = id;
            if (parent != null) {
                this.parent = parent;
                parent.addChild(this);
            }
        }

        private void addChild(Node child) {
            children.add(child);
        }

        private void addValue(int valueToAdd) {
            value += valueToAdd;
        }

        private List<Integer> getParentChain() {
            if (parent == null) {
                List<Integer> parentChain = new ArrayList<Integer>();
                parentChain.add(id);
                return parentChain;
            }
            List<Integer> parentChain = parent.getParentChain();
            parentChain.add(id);
            return parentChain;
            // todo: will my call stack blow up? recursive calls like this are probably not ideal. I need to test this on a 10k node deep tree
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
