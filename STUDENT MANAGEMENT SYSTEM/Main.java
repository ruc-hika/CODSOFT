import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Save Data");
            System.out.println("6. Load Data");
            System.out.println("7. Update Student");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Roll Number: ");
                    int roll = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Grade: ");
                    String grade = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();

                    if (name.isEmpty() || grade.isEmpty() || email.isEmpty()) {
                        System.out.println("⚠️ All fields must be filled!");
                    } else {
                        sms.addStudent(new Student(name, roll, grade, email));
                    }
                    break;

                case 2:
                    System.out.print("Enter Roll Number to remove: ");
                    sms.removeStudent(sc.nextInt());
                    break;

                case 3:
                    System.out.print("Enter Roll Number to search: ");
                    Student found = sms.searchStudent(sc.nextInt());
                    System.out.println(found != null ? "✅ Found: " + found : "⚠️ Student not found.");
                    break;

                case 4:
                    sms.displayAllStudents();
                    break;

                case 5:
                    sms.saveToFile("students.dat");
                    break;

                case 6:
                    sms.loadFromFile("students.dat");
                    break;

                case 7:
                    System.out.print("Enter Roll Number to update: ");
                    int rollUpdate = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter New Name (blank to keep): ");
                    String newName = sc.nextLine();
                    System.out.print("Enter New Grade (blank to keep): ");
                    String newGrade = sc.nextLine();
                    System.out.print("Enter New Email (blank to keep): ");
                    String newEmail = sc.nextLine();
                    sms.updateStudent(rollUpdate, newName, newGrade, newEmail);
                    break;

                case 8:
                    System.out.println("👋 Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("⚠️ Invalid choice.");
            }
        }
    }
}
