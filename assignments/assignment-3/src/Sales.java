import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Sales extends Employee {
    private int baseSalary;
    private int commission;

    public Sales() {
        super("", "");
        this.baseSalary = 0;
        this.commission = 0;
    }

    public Sales(int id, String firstName, String lastName, String department,
                 int baseSalary, int commission) {
        super(firstName + " " + lastName, department);
        this.baseSalary = baseSalary;
        this.commission = commission;
    }

    @Override
    public int getSalary() {
        return baseSalary + commission;
    }

    @Override
    public String toString() {
        return "Sales: " + getFullName() +
                " Department: " + getDepartment() +
                " Base Salary: " + baseSalary +
                " Commission: " + commission;
    }

    public ArrayList<Sales> getSalesList(String filename) {
        ArrayList<Sales> salesPeople = new ArrayList<>();

        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");

                if (data.length >= 4) {
                    int id = Integer.parseInt(data[0]);
                    String firstName = data[1];
                    String lastName = data[2];
                    String department = data[3];
                    int baseSalary = (data.length > 4 && !data[4].trim().isEmpty()) ?
                            Integer.parseInt(data[4]) : 0;
                    int commission = (data.length > 5 && !data[5].trim().isEmpty()) ?
                            Integer.parseInt(data[5]) : 0;

                    salesPeople.add(new Sales(id, firstName, lastName, department,
                            baseSalary, commission));
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error reading sales file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing number in sales file: " + e.getMessage());
        }

        return salesPeople;
    }
}