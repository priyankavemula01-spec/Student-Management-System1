import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Welcome to the Student Management System ===");

        while (running) {
            System.out.println("\n1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Search Student by ID");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("Enter ID: ");
                    String id = scanner.nextLine().trim();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine().trim();

                    int age = readIntInput(scanner, "Enter Age: ");
                    double gpa = readDoubleInput(scanner, "Enter GPA: ");

                    if(manager.addStudent(new Student(id, name, age, gpa))) {
                        System.out.println("✅ Student added successfully!");
                    }
                    break;

                case "2":
                    manager.viewAllStudents();
                    break;

                case "3":
                    System.out.print("Enter ID of the student to update: ");
                    String updateId = scanner.nextLine().trim();
                    if (manager.findStudentById(updateId) == null) {
                        System.out.println("❌ Student not found.");
                        break;
                    }
                    System.out.print("Enter New Name: ");
                    String newName = scanner.nextLine().trim();
                    int newAge = readIntInput(scanner, "Enter New Age: ");
                    double newGpa = readDoubleInput(scanner, "Enter New GPA: ");

                    if (manager.updateStudent(updateId, newName, newAge, newGpa)) {
                        System.out.println("✅ Student record updated successfully!");
                    }
                    break;

                case "4":
                    System.out.print("Enter ID of the student to delete: ");
                    String deleteId = scanner.nextLine().trim();
                    if (manager.deleteStudent(deleteId)) {
                        System.out.println("✅ Student record removed successfully.");
                    } else {
                        System.out.println("❌ Student not found.");
                    }
                    break;

                case "5":
                    System.out.print("Enter ID to search: ");
                    String searchId = scanner.nextLine().trim();
                    Student match = manager.findStudentById(searchId);
                    if (match != null) {
                        System.out.println("\nRecord Found:\n" + match);
                    } else {
                        System.out.println("❌ Student not found.");
                    }
                    break;

                case "6":
                    running = false;
                    System.out.println("👋 System configurations saved. Goodbye!");
                    break;

                default:
                    System.out.println("❌ Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private static int readIntInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a valid sequence of integers.");
            }
        }
    }

    private static double readDoubleInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a valid decimal point number.");
            }
        }
    }
}
