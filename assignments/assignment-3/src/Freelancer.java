import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Freelancer extends Employee {
    private int hourlyRate;
    private int hoursWorked;

    public Freelancer() {
        super("", "");
        this.hourlyRate = 0;
        this.hoursWorked = 0;
    }

    public Freelancer(int id, String firstName, String lastName, String department,
                      int hourlyRate, int hoursWorked) {
        super(firstName + " " + lastName, department);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public int getSalary() {
        return hourlyRate * hoursWorked;
    }

    @Override
    public String toString() {
        return "Freelancer: " + getFullName() +
                " Department: " + getDepartment() +
                " Hourly Rate: " + hourlyRate +
                " Hours Worked: " + hoursWorked;
    }

    public ArrayList<Freelancer> getFreelancerList(String filename) {
        ArrayList<Freelancer> freelancers = new ArrayList<>();

        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            if (scanner.hasNextLine()) scanner.nextLine();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");

                if (data.length >= 4) {
                    int id = Integer.parseInt(data[0]);
                    String firstName = data[1];
                    String lastName = data[2];
                    String department = data[3];
                    int hourlyRate = (data.length > 4 && !data[4].trim().isEmpty()) ?
                            Integer.parseInt(data[4]) : 0;
                    int hoursWorked = (data.length > 5 && !data[5].trim().isEmpty()) ?
                            Integer.parseInt(data[5]) : 0;

                    freelancers.add(new Freelancer(id, firstName, lastName, department,
                            hourlyRate, hoursWorked));
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error reading freelancer file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing number in freelancer file: " + e.getMessage());
        }

        return freelancers;
    }
}