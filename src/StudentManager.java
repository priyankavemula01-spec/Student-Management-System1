import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private List<Student> students;
    private final String filePath = "students.csv";

    public StudentManager() {
        this.students = new ArrayList<>();
        loadFromFile(); // Automatically load existing records on start
    }

    // CREATE
    public boolean addStudent(Student student) {
        if (findStudentById(student.getId()) != null) {
            System.out.println("❌ Error: A student with ID " + student.getId() + " already exists.");
            return false;
        }
        students.add(student);
        saveToFile(); // Persist changes
        return true;
    }

    // READ
    public void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("ℹ️ No records found.");
            return;
        }
        System.out.println("\n--- Student Records ---");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    // UPDATE
    public boolean updateStudent(String id, String newName, int newAge, double newGpa) {
        Student student = findStudentById(id);
        if (student != null) {
            student.setName(newName);
            student.setAge(newAge);
            student.setGpa(newGpa);
            saveToFile(); // Persist changes
            return true;
        }
        return false;
    }

    // DELETE
    public boolean deleteStudent(String id) {
        Student student = findStudentById(id);
        if (student != null) {
            students.remove(student);
            saveToFile(); // Persist changes
            return true;
        }
        return false;
    }

    public Student findStudentById(String id) {
        for (Student s : students) {
            if (s.getId().equalsIgnoreCase(id)) {
                return s;
            }
        }
        return null;
    }

    // File Handling: Save all runtime objects to the CSV file
    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Student s : students) {
                writer.write(s.toCsvRow());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ Error saving data to file: " + e.getMessage());
        }
    }

    // File Handling: Parse flat data file back into system state
    private void loadFromFile() {
        File file = new File(filePath);
        if (!file.exists()) return; // Nothing to load yet

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Student s = Student.fromCsvRow(line);
                    if (s != null) {
                        students.add(s);
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("⚠️ Warning reading data file. File structure might be altered.");
        }
    }
}
