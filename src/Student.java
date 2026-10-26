import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private int age;
    private double gpa;

    public Student(String id, String name, int age, double gpa) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public double getGpa() { return gpa; }
    public void setGpa(double gpa) { this.gpa = gpa; }

    // Convert Object to CSV formatted string row
    public String toCsvRow() {
        return id + "," + name + "," + age + "," + gpa;
    }

    // Parse CSV row back into a Student Object
    public static Student fromCsvRow(String csvLine) {
        String[] tokens = csvLine.split(",");
        if (tokens.length == 4) {
            String id = tokens[0].trim();
            String name = tokens[1].trim();
            int age = Integer.parseInt(tokens[2].trim());
            double gpa = Double.parseDouble(tokens[3].trim());
            return new Student(id, name, age, gpa);
        }
        return null;
    }

    @Override
    public String toString() {
        return String.format("ID: %-8s | Name: %-20s | Age: %-4d | GPA: %.2f", id, name, age, gpa);
    }
}
