public abstract class Employee {
    protected String fullName;
    protected String department;

    public Employee(String fullName, String department) {
        this.fullName = fullName;
        this.department = department;
    }

    public abstract int getSalary();

    public String getFullName() {
        return fullName;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "Name: " + fullName + ", Department: " + department;
    }
}