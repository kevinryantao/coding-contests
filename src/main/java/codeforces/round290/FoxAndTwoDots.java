package codeforces.round290;

import java.util.*;

/**
 * Created by ktao on 2/2/15.
 */
public class FoxAndTwoDots {

    private int maxRow;
    private int maxCol;
    char[][] grid;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int[] nAndM = parseIntsFromStringArray(scanner.nextLine().split(" "));
        int rows = nAndM[0];
        int cols = nAndM[1];

        char[][] grid = new char[rows][cols];

        for(int i = 0; i < rows; i++){
            grid[i] = scanner.nextLine().toCharArray();
        }
        FoxAndTwoDots foxAndTwoDots = new FoxAndTwoDots(rows, cols, grid);
        boolean solve = foxAndTwoDots.solve();
        if(solve){
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

    }

    public FoxAndTwoDots(int maxRow, int maxCol, char[][] grid){
        this.maxRow = maxRow;
        this.maxCol = maxCol;
        this.grid = grid;
    }


    private boolean solve() {
        List<Group> liveGroups = new ArrayList<Group>();

        for(int r = 0; r < maxRow; r++){
            for(int c = 0; c < maxCol; c++){
                Point thisPoint = new Point(r, c);
                char thisColor = grid[r][c];

                // first check to see if it can be added to existing group
                List<Group> groupsJoined = new ArrayList<Group>();
                for(Group group : liveGroups){
                    if(thisColor == group.color){
                        if(group.isAdjacent(thisPoint)){
                            group.addMember(thisPoint);
                            groupsJoined.add(group);
                        }
                    }
                }
                if(groupsJoined.size() == 2){
                    // merge the two groups
                    // first one is the older one
                    Group olderGroup = groupsJoined.get(0);
                    Group youngerGroup = groupsJoined.get(1);

                    for(Point p : youngerGroup.members){
                        olderGroup.addMember(p);
                    }
                    liveGroups.remove(youngerGroup);
                }
                if(groupsJoined.size() > 0){
                    continue;
                }

                // now check to see if a new group can be created
                if(c > 0){
                    if(grid[r][c-1] == thisColor){
                        Group group = new Group(thisColor);
                        group.addMember(thisPoint);
                        group.addMember(new Point(r, c - 1));
                        liveGroups.add(group);
                    }
                }
            }
            // after each row, eliminate all groups that did not receive a fresh member
            List<Group> newLiveGroups = new ArrayList<Group>();
            for(Group group : liveGroups){
                if(group.hasFreshMember()){
                    newLiveGroups.add(group);
                }
            }
            // after dead groups have been pruned, we re-set each group
            List<Group> groupsWithMoreThan2Members = new ArrayList<Group>();
            for(Group group : newLiveGroups){
                group.newRow(r);
                if(group.members.size() > 1){
                    groupsWithMoreThan2Members.add(group);
                }
            }
            // check to see if we have any cycles now
            for(Group group : groupsWithMoreThan2Members){
                if(group.isCycle()){
                    return true;
                }
            }

            liveGroups = groupsWithMoreThan2Members;

        }
        return false;
    }


    private class Group{
        private boolean hasFreshMember;
        private Set<Point> members;
        private char color;

        private Group(char color){
            this.color = color;
            members = new HashSet<Point>();
        }

        private void addMember(Point newMember){
            members.add(newMember);
            hasFreshMember = true;
        }

        private void newRow(int r){
            trim(r);
            hasFreshMember = false;
        }

        private boolean hasFreshMember(){
            return hasFreshMember;
        }

        private boolean isCycle(){
            for(Point point : members){
                List<Point> adjacents = getAdjacentTo(point.row, point.col);
                int numberOfAdjacents = numberOfMembersInList(adjacents);
                if(numberOfAdjacents <= 1){
                    return false;
                }
            }
            return true;
        }

        private void trim(int currentRow){
            List<Point> danglingMembers = new ArrayList<Point>();
            for(Point point : members){
                List<Point> adjacents = getAdjacentTo(point.row, point.col);
                int numberOfAdjacents = numberOfMembersInList(adjacents);

                if(numberOfAdjacents == 1){
                    danglingMembers.add(point);
                }
            }
            for(Point point : danglingMembers){
                attemptToTrim(currentRow, point);
            }
        }

        private void attemptToTrim(int currentRow, Point point) {
            if(point.row == currentRow){
                return;
            }
            List<Point> adjacents = getAdjacentTo(point.row, point.col);
            int numberOfAdjacents = numberOfMembersInList(adjacents);

            if(numberOfAdjacents == 1){
                members.remove(point);
                attemptToTrim(currentRow, adjacents.get(0));
            }
            if(numberOfAdjacents == 0){
                members.remove(point);
            }
        }


        private int numberOfMembersInList(List<Point> points) {
            int numberOfMembers = 0;
            for(Point adjacent : points){
                if(members.contains(adjacent)){
                    numberOfMembers++;
                }
            }
            return numberOfMembers;
        }

        private boolean isAdjacent(Point point){
            List<Point> adjacents = getAdjacentTo(point.row, point.col);
            for(Point p : adjacents){
                if(members.contains(p)){
                    return true;
                }
            }
            return false;
        }
    }

    private static class Point{
        private int row;
        private int col;

        private Point(int row, int col){

            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            if (col != point.col) return false;
            if (row != point.row) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = row;
            result = 301 * result + col;
            return result;
        }
    }

    private List<Point> getAdjacentTo(int row, int col){
        List<Point> results = new ArrayList<Point>();

        if(row > 0){
            results.add(new Point(row - 1, col));
        }
        if(col > 0){
            results.add(new Point(row, col - 1));
        }

        if(row < maxRow - 1){
            results.add(new Point(row + 1, col));
        }
        if(col < maxCol - 1){
            results.add(new Point(row, col + 1));
        }
        return results;
    }

    private List<Point> getUpLeftAdjacentTo(int row, int col){
        List<Point> results = new ArrayList<Point>();

        if(row > 0){
            results.add(new Point(row - 1, col));
        }
        if(col > 0){
            results.add(new Point(row, col - 1));
        }

        return results;
    }




    private static int[] parseIntsFromStringArray(String[] goalsString) {
        int[] result = new int[goalsString.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(goalsString[i]);
        }
        return result;
    }
}
