import java.util.Scanner;

public class EduTrackApp {

    public static final int MAX_STUDENTS = 100;

    public static void main(String[] args) {

        Scanner   scanner      = new Scanner(System.in);
        Student[] students     = new Student[MAX_STUDENTS];
        int       studentCount = 0;
        boolean   running      = true;

        while (running) {
            printMenu();
            int choice = readValidChoice(scanner, 1, 7);

            switch (choice) {
                case 1:
                    if (studentCount >= MAX_STUDENTS) {
                        System.out.println("\n  Maximum student limit reached (" + MAX_STUDENTS + ").");
                        break;
                    }
                    System.out.print("\n  Enter student name : ");
                    String name = scanner.nextLine().trim();
                    if (name.isEmpty()) {
                        System.out.println("  Name cannot be empty.");
                        break;
                    }

                    double java_g = readGrade(scanner, "Java");
                    double math_g = readGrade(scanner, "Math");
                    double eng_g  = readGrade(scanner, "English");

                    students[studentCount] = new Student(name, java_g, math_g, eng_g);
                    studentCount++;
                    System.out.println("\n  Student added successfully!");
                    students[studentCount - 1].printRecord();
                    break;

                case 2:
                    if (studentCount == 0) {
                        System.out.println("\n  No students registered yet.");
                        break;
                    }
                    printClassReport(students, studentCount);
                    break;

                case 3:
                    if (studentCount == 0) {
                        System.out.println("\n  No students registered yet.");
                        break;
                    }
                    System.out.print("\n  Enter name to search: ");
                    String searchName = scanner.nextLine().trim().toLowerCase();
                    boolean found = false;
                    for (int i = 0; i < studentCount; i++) {
                        if (students[i].name.toLowerCase().contains(searchName)) {
                            students[i].printRecord();
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("  No student found with name: \"" + searchName + "\"");
                    }
                    break;

                case 4:
                    if (studentCount == 0) {
                        System.out.println("\n  No students registered yet.");
                        break;
                    }
                    int topIdx = 0;
                    for (int i = 1; i < studentCount; i++) {
                        if (students[i].average > students[topIdx].average) {
                            topIdx = i;
                        }
                    }
                    System.out.println("\n  *** Top Performer ***");
                    students[topIdx].printRecord();
                    break;

                case 5:
                    if (studentCount == 0) {
                        System.out.println("\n  No students registered yet.");
                        break;
                    }
                    double[] avgs = new double[studentCount];
                    String[] names = new String[studentCount];
                    for (int i = 0; i < studentCount; i++) {
                        avgs[i]  = students[i].average;
                        names[i] = students[i].name;
                    }
                    EduTrackAnalyzer.printClassStats(names, avgs, studentCount);
                    break;

                case 6:
                    EduTrackAnalyzer.runAnalysis();
                    break;

                case 7:
                    System.out.println("\n  Goodbye! Thank you for using EduTrack.");
                    running = false;
                    break;
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n===============================================");
        System.out.println("|               Welcome to EduTrack             |");
        System.out.println("===============================================");
        System.out.println("[1] Add New Student");
        System.out.println("[2] Display All Students");
        System.out.println("[3] Search Student by Name");
        System.out.println("[4] Show Top Performer");
        System.out.println("[5] Show Class Statistics");
        System.out.println("[6] Extended Analyzer (10 Subjects)");
        System.out.println("[7] Exit");
        System.out.println("===============================================");
        System.out.print("Enter your choice: ");
    }

    private static void printClassReport(Student[] students, int count) {
        System.out.println("\n================================================================");
        System.out.println("|              EduTrack - Class Report                         |");
        System.out.println("================================================================");
        System.out.printf("%-4s %-18s %-7s %-7s %-7s %-7s %-6s %s%n",
                "No.", "Name", "Java", "Math", "Eng", "Avg", "Grade", "Status");
        System.out.printf("%-4s %-18s %-7s %-7s %-7s %-7s %-6s %s%n",
                "---", "----", "----", "----", "---", "---", "-----", "------");

        for (int i = 0; i < count; i++) {
            Student s = students[i];
            System.out.printf("%-4d %-18s %-7.1f %-7.1f %-7.1f %-7.1f %-6c %s%n",
                    (i + 1), s.name, s.javaGrade, s.mathGrade, s.englishGrade,
                    s.average, s.letterGrade, s.isPassing ? "PASS" : "FAIL");
        }

        double[] avgs = new double[count];
        String[] nameArr = new String[count];
        for (int i = 0; i < count; i++) {
            avgs[i]    = students[i].average;
            nameArr[i] = students[i].name;
        }

        double overallSum = 0;
        for (int i = 0; i < count; i++) overallSum += avgs[i];
        double classAvg = Math.round((overallSum / count) * 10.0) / 10.0;

        double highest = EduTrackEngine.findHighestAverage(avgs);

        String topName = nameArr[0];
        for (int i = 0; i < count; i++) {
            if (avgs[i] == highest) topName = nameArr[i];
        }

        int[] dist = EduTrackEngine.countPerGrade(avgs);
        int passing = 0, failing = 0;
        for (int i = 0; i < count; i++) {
            if (EduTrackEngine.isPassing(avgs[i])) passing++;
            else failing++;
        }

        System.out.println("================================================================");
        System.out.printf("Class Average    : %.1f%n", classAvg);
        System.out.printf("Top Student      : %s (%.1f)%n", topName, highest);
        System.out.printf("Grade A Count    : %d%n", dist[0]);
        System.out.printf("Grade B Count    : %d%n", dist[1]);
        System.out.printf("Grade C Count    : %d%n", dist[2]);
        System.out.printf("Grade D Count    : %d%n", dist[3]);
        System.out.printf("Grade F Count    : %d%n", dist[4]);
        System.out.printf("Passing          : %d  |  Failing: %d%n", passing, failing);
        System.out.println("================================================================");
    }

    private static int readValidChoice(Scanner sc, int min, int max) {
        while (true) {
            String line = sc.nextLine().trim();
            try {
                int value = Integer.parseInt(line);
                if (value >= min && value <= max) return value;
                System.out.printf("  Please enter a number between %d and %d: ", min, max);
            } catch (NumberFormatException e) {
                System.out.printf("  Invalid input. Enter a number between %d and %d: ", min, max);
            }
        }
    }

    private static double readGrade(Scanner sc, String subject) {
        System.out.printf("  Enter %s grade (0-100): ", subject);
        while (true) {
            String line = sc.nextLine().trim();
            try {
                double value = Double.parseDouble(line);
                if (value >= 0 && value <= 100) return value;
                System.out.printf("  %s grade must be 0-100. Try again: ", subject);
            } catch (NumberFormatException e) {
                System.out.printf("  Invalid input. Enter %s grade (0-100): ", subject);
            }
        }
    }
}