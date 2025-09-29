public class Student {
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

    public int getStudentiD () {
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

}
