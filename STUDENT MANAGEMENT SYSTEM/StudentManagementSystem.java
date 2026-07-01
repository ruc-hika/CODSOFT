import java.util.*;
import java.io.*;

public class StudentManagementSystem {
    private List<Student> students;

    public StudentManagementSystem() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
        System.out.println("✅ Student added successfully!");
    }

    public void removeStudent(int rollNumber) {
        students.removeIf(s -> s.getRollNumber() == rollNumber);
        System.out.println("🗑️ Student removed successfully!");
    }

    public Student searchStudent(int rollNumber) {
        for (Student s : students) {
            if (s.getRollNumber() == rollNumber) return s;
        }
        return null;
    }

    public void updateStudent(int rollNumber, String newName, String newGrade, String newEmail) {
        Student student = searchStudent(rollNumber);
        if (student != null) {
            if (!newName.isEmpty()) student.setName(newName);
            if (!newGrade.isEmpty()) student.setGrade(newGrade);
            if (!newEmail.isEmpty()) student.setEmail(newEmail);
            System.out.println("✏️ Student details updated successfully!");
        } else {
            System.out.println("⚠️ Student not found.");
        }
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("⚠️ No students found.");
        } else {
            for (Student s : students) System.out.println(s);
        }
    }

    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(students);
            System.out.println("💾 Data saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            students = (List<Student>) ois.readObject();
            System.out.println("📂 Data loaded successfully!");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
