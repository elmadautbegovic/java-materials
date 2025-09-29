import java.util.Comparator;
import java.util.List;

public class Student implements Comparable<Student> {
    private int studentId;
    private String name;
    private int age;
    private double grade;

    public Student (int studentId, String name, int age, double grade) {
        this.studentId = studentId;;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public int getStudentId () {
        return studentId;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public double getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Student(" + studentId +
                ", " + name +
                ", " + age +
                ", " + grade +
                ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return studentId == student.studentId;
    }

    @Override
    public int compareTo (Student other) {
        return Double.compare(other.grade, this.grade);
    }

    public static void sortByName (List<Student> students) {
        students.sort (new Comparator<Student>() {
            @Override
            public int compare (Student s1, Student s2) {
                return s1.name.compareTo(s2.name);
            }
        });
    }

    public static void sortByGradeAndName (List <Student> students) {
        students.sort (new Comparator<Student>() {
            @Override
            public int compare (Student s1, Student s2) {
                int gradeComparsion = Double.compare(s2.grade, s1.grade);
                if (gradeComparsion == 0) {
                    return s1.name.compareTo(s2.name);
                }
                return gradeComparsion;
            }
        });
    }


}
