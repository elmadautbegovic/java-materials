import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        ArrayList<Sales> salesList = new Sales().getSalesList("importedcsvs/sales.csv");
        ArrayList<Freelancer> freelancerList = new Freelancer().getFreelancerList("importedcsvs/freelancer.csv");
        ArrayList<Intern> internList = new Intern().getInternList("importedcsvs/intern.csv");
        ArrayList<Manager> managerList = new Manager().getManagerList("importedcsvs/manager.csv");
        ArrayList<Worker> workerList = new Worker().getWorkerList("importedcsvs/worker.csv");

        ArrayList<Employee> allEmployees = new ArrayList<>();
        allEmployees.addAll(salesList);
        allEmployees.addAll(freelancerList);
        allEmployees.addAll(internList);
        allEmployees.addAll(managerList);
        allEmployees.addAll(workerList);

        Collections.sort(allEmployees, (v1, v2) -> v2.getSalary() - v1.getSalary());
        Collections.sort(salesList, (v1, v2) -> v2.getSalary() - v1.getSalary());
        Collections.sort(freelancerList, (v1, v2) -> v2.getSalary() - v1.getSalary());
        Collections.sort(workerList, (v1, v2) -> v2.getSalary() - v1.getSalary());
        Collections.sort(managerList, (v1, v2) -> v2.getSalary() - v1.getSalary());
        Collections.sort(internList, (v1, v2) -> v2.getSalary() - v1.getSalary());

        Map<String, ArrayList<Employee>> departmentSortingList = new HashMap<>();
        allEmployees.stream().forEach(employee -> {
            departmentSortingList.putIfAbsent(employee.getDepartment(), new ArrayList<Employee>());
            departmentSortingList.get(employee.getDepartment()).add(employee);
        });



        writeReport(allEmployees, "AllEmployeesReport");
        writeReport(salesList, "SalesListReport");
        writeReport(freelancerList, "FreelancerListReport");
        writeReport(workerList, "WorkerListReport");
        writeReport(managerList, "ManagerListReport");
        writeReport(internList, "InternListReport");
        writeReport(departmentSortingList.get("sales"), "SalesReport");
        writeReport(departmentSortingList.get("production"), "ProductionReport");
        writeReport(departmentSortingList.get("it"), "ItReport");
        writeReport(departmentSortingList.get("operations"), "OperationsReport");



    }

    public static void writeReport(List<? extends Employee> list, String pathname) {
        if (list == null) {
            throw new IllegalArgumentException("The provided list is null. Cannot write a report.");
        }

        File f = new File("reports/" + pathname);
        
        f.getParentFile().mkdirs();

        try (FileWriter fw = new FileWriter(f)) {
            for (Employee item : list) {
                fw.write(item.toString() + " TotalSalary: " + item.getSalary() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.err.println("An error occurred while writing the report: " + e.getMessage());
            e.printStackTrace();
        }
    }


}