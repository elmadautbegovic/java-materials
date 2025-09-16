import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager extends Employee {
    private int baseSalary;
    private int bonus;

    public Manager() {
        super("", "");
        this.baseSalary = 0;
        this.bonus = 0;
    }

    public Manager(int id, String firstName, String lastName, String department, int baseSalary, int bonus) {
        super(firstName + " " + lastName, department);
        this.baseSalary = baseSalary;
        this.bonus = bonus;
    }

    @Override
    public int getSalary() {
        return baseSalary + bonus;
    }

    @Override
    public String toString() {
        return "Manager: " + getFullName() +
                " Department: " + getDepartment() +
                " Base Salary: " + baseSalary +
                " Bonus: " + bonus;
    }

    public ArrayList<Manager> getManagerList(String filename) {
        ArrayList<Manager> managers = new ArrayList<>();

        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");

                if (data.length == 6) {
                    int id = Integer.parseInt(data[0]);
                    String firstName = data[1];
                    String lastName = data[2];
                    String department = data[3];
                    int baseSalary = Integer.parseInt(data[4]);
                    int bonus = Integer.parseInt(data[5]);

                    managers.add(new Manager(id, firstName, lastName, department, baseSalary, bonus));
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error reading manager file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing number in manager file: " + e.getMessage());
        }

        return managers;
    }
}