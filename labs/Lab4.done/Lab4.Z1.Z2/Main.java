import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<Student>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(Main.class.getResourceAsStream("/students.csv"))))) {

            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int studentId = Integer.parseInt(data[0]);
                String name = data[1];
                int age = Integer.parseInt(data[2]);
                double grade = Double.parseDouble(data[3]);

                students.add(new Student(studentId, name, age, grade));
            }

            System.out.println("Students:");
            for (Student s : students) {
                System.out.println(s);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
