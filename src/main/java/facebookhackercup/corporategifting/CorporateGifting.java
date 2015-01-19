package facebookhackercup.corporategifting;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by ktao on 1/18/15.
 */
public class CorporateGifting {

    private final int numberOfEmployees;
    private Map<Integer, Employee> employeeDirectory;
    private Employee theCEO;

    public CorporateGifting(int numberOfEmployees, int[] hierarchy) {
        employeeDirectory = new HashMap<Integer, Employee>();

        // create a bunch of employees
        this.numberOfEmployees = numberOfEmployees;
        for(int i = 1; i <= this.numberOfEmployees; i++){
            Employee employee = new Employee(i);
            employeeDirectory.put(i, employee);
        }
        theCEO = employeeDirectory.get(1);

        for(int i = 1; i < hierarchy.length; i++){
            int directReportId = i + 1;
            int managerId = hierarchy[i];
            Employee directReport = employeeDirectory.get(directReportId);
            Employee manager = employeeDirectory.get(managerId);
            createManagerReportRelationship(manager, directReport);
        }

        sortAllReportsBasedOnTreeSize();
    }

    public void greedyAssign1s(){

    }

    private void sortAllReportsBasedOnTreeSize() {
        for(int i = 1; i <= numberOfEmployees; i++){
            Employee employee = employeeDirectory.get(i);
            Collections.sort(employee.directReports, new Comparator<Employee>() {
                @Override
                public int compare(Employee o1, Employee o2) {
                    return o2.totalResponsibleFor - o1.totalResponsibleFor;
                }
            });
        }
    }

    private void createManagerReportRelationship(Employee manager, Employee directReport) {
        manager.addDirectReport(directReport);
        directReport.assignManager(manager);
        incrementAllManagement(manager);
    }

    private void incrementAllManagement(Employee manager) {
        Employee nextManager = manager;
        while (nextManager != null) {
            nextManager.incrementTotalResponsible();
            nextManager = nextManager.getManager();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("gifting.txt"));
        int numCases = Integer.parseInt(scanner.nextLine());
        for(int i = 1; i <= numCases; i++){
            int numberOfEmployees = Integer.parseInt(scanner.nextLine());
            int[] hierarchy = parseIntsFromStringArray(scanner.nextLine().split(" "));
            CorporateGifting corporateGifting = new CorporateGifting(numberOfEmployees, hierarchy);
        }



    }


    private static int[] parseIntsFromStringArray(String[] goalsString) {
        int[] result = new int[goalsString.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(goalsString[i]);
        }
        return result;
    }

    private static class Employee{

        private int employeeId;
        private int giftCost;
        private Employee manager;
        private List<Employee> directReports;
        private int totalResponsibleFor;


        public Employee(int employeeId) {
            this.employeeId = employeeId;
            directReports = new ArrayList<Employee>();
        }

        public void addDirectReport(Employee directReport) {
            directReports.add(directReport);
        }

        public void assignManager(Employee manager) {
            this.manager = manager;
        }

        public List<Employee> getDirectReports() {
            return directReports;
        }

        public void incrementTotalResponsible() {
            totalResponsibleFor++;
        }

        public Employee getManager() {
            return manager;
        }
    }
}
