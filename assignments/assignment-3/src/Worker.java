import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Worker extends Employee {
    private int baseSalary;
    private int hourlyRate;
    private int hoursWorked;

    public Worker() {
        super("","");
        this.baseSalary = 0;
        this.hourlyRate = 0;
        this.hoursWorked = 0;
    }

    public Worker(int id, String firstName, String lastName, String department,
                  int baseSalary, int hourlyRate, int hoursWorked) {
        super(firstName + " " + lastName, department);
        this.baseSalary = baseSalary;
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public int getSalary() {
        return baseSalary + (hourlyRate * hoursWorked);
    }

    @Override
    public String toString() {
        return "Worker: " + getFullName() +
                " Department: " + getDepartment() +
                " Base Salary: " + baseSalary +
                " Hourly Rate: " + hourlyRate +
                " Hours Worked: " + hoursWorked;
    }

    public ArrayList<Worker> getWorkerList(String filename) {
        ArrayList<Worker> workers = new ArrayList<>();

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
                    int hourlyRate = (data.length > 5 && !data[5].trim().isEmpty()) ?
                            Integer.parseInt(data[5]) : 0;
                    int hoursWorked = (data.length > 6 && !data[6].trim().isEmpty()) ?
                            Integer.parseInt(data[6]) : 0;

                    workers.add(new Worker(id, firstName, lastName, department,
                            baseSalary, hourlyRate, hoursWorked));
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error reading worker file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing number in worker file: " + e.getMessage());
        }

        return workers;
    }
}