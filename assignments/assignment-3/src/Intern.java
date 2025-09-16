import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Intern extends Employee {
    private int stipend;

    public Intern() {
        super("", "");
        this.stipend = 0;
    }

    public Intern(int id, String firstName, String lastName, String department, int stipend) {
        super(firstName + " " + lastName, department);
        this.stipend = stipend;
    }

    @Override
    public int getSalary() {
        return stipend;
    }

    @Override
    public String toString() {
        return "Intern: " + getFullName() +
                " Department: " + getDepartment() +
                " Stipend: " + stipend;
    }

    public ArrayList<Intern> getInternList(String filename) {
        ArrayList<Intern> interns = new ArrayList<>();

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
                    int stipend = (data.length > 4 && !data[4].trim().isEmpty()) ?
                            Integer.parseInt(data[4]) : 0;

                    interns.add(new Intern(id, firstName, lastName, department, stipend));
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error reading intern file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing number in intern file: " + e.getMessage());
        }

        return interns;
    }
}