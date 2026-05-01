import java.util.Scanner;

/**
 * EduTrackAnalyzer — Extended analysis for up to 100 students x 10 subjects.
 * Uses raw arrays and Scanner ONLY (no ArrayList, no Streams).
 *
 * KEY DIFFERENCE (3 vs 10 subjects):
 *   - Student.java stores 3 FIXED subjects (Java, Math, English) as individual fields.
 *   - This analyzer uses a 2D array (double[100][10]) to handle a VARIABLE number
 *     of subjects per student, supporting detailed per-student reports AND
 *     class-wide statistics (overall avg, highest, lowest, grade distribution).
 */
public class EduTrackAnalyzer {

    public static final int MAX_STUDENTS = 100;
    public static final int MAX_SUBJECTS = 10;

    /**
     * Runs a full interactive 10-subject analysis session.
     * Reads student data via Scanner, prints per-student reports and class stats.
     */
    public static void runAnalysis(Scanner scanner) {

        System.out.println("\n================================================================");
        System.out.println("|          EduTrack - Extended Analyzer (10 Subjects)          |");
        System.out.println("================================================================");

        System.out.print("Enter number of students (max " + MAX_STUDENTS + "): ");
        int numStudents = readValidInt(scanner, 1, MAX_STUDENTS);

        System.out.print("Enter number of subjects (max " + MAX_SUBJECTS + "): ");
        int numSubjects = readValidInt(scanner, 1, MAX_SUBJECTS);

        String[]   names    = new String[numStudents];
        double[][] grades   = new double[numStudents][numSubjects];
        double[]   averages = new double[numStudents];

        // ---- Input Phase ----
        for (int i = 0; i < numStudents; i++) {
            System.out.printf("%nStudent %d of %d%n", i + 1, numStudents);
            System.out.print("  Name: ");
            names[i] = scanner.nextLine();

            for (int j = 0; j < numSubjects; j++) {
                System.out.printf("  Grade for subject %d (0-100): ", j + 1);
                grades[i][j] = readValidDouble(scanner, 0, 100);
            }
            averages[i] = EduTrackEngine.calculateAverage(grades[i]);
        }

        // ---- Per-Student Reports ----
        System.out.println("\n================ Per-Student Reports =================");
        for (int i = 0; i < numStudents; i++) {
            EduTrackEngine.printStudentReport(names[i], grades[i]);
        }

        // ---- Class Statistics ----
        printClassStats(names, averages, numStudents);
    }

    /**
     * Prints class-wide statistics: overall avg, highest/lowest, distribution, pass/fail.
     */
    public static void printClassStats(String[] names, double[] averages, int count) {
        if (count == 0) {
            System.out.println("\nNo students to analyze.");
            return;
        }

        // Build a trimmed array for calculations
        double[] trimmed = new double[count];
        for (int i = 0; i < count; i++) {
            trimmed[i] = averages[i];
        }

        double overallSum = 0;
        for (int i = 0; i < count; i++) {
            overallSum += trimmed[i];
        }
        double classAvg = Math.round((overallSum / count) * 10.0) / 10.0;

        double highest = EduTrackEngine.findHighestAverage(trimmed);
        double lowest  = EduTrackEngine.findLowestAverage(trimmed);

        // Find top student name
        String topName = names[0];
        for (int i = 1; i < count; i++) {
            if (trimmed[i] == highest) {
                topName = names[i];
            }
        }
        // Find lowest student name
        String lowName = names[0];
        for (int i = 1; i < count; i++) {
            if (trimmed[i] == lowest) {
                lowName = names[i];
            }
        }

        int[] dist = EduTrackEngine.countPerGrade(trimmed);
        int passing = 0, failing = 0;
        for (int i = 0; i < count; i++) {
            if (EduTrackEngine.isPassing(trimmed[i])) passing++;
            else failing++;
        }

        System.out.println("\n================================================================");
        System.out.printf("Class Average    : %.1f%n", classAvg);
        System.out.printf("Top Student      : %s (%.1f)%n", topName, highest);
        System.out.printf("Lowest Student   : %s (%.1f)%n", lowName, lowest);
        System.out.printf("Grade A Count    : %d%n", dist[0]);
        System.out.printf("Grade B Count    : %d%n", dist[1]);
        System.out.printf("Grade C Count    : %d%n", dist[2]);
        System.out.printf("Grade D Count    : %d%n", dist[3]);
        System.out.printf("Grade F Count    : %d%n", dist[4]);
        System.out.printf("Passing          : %d  |  Failing: %d%n", passing, failing);
        System.out.println("================================================================");
    }

    // ==================== Input Validation Helpers ====================

    /** Reads a valid integer within [min, max]. Loops until valid. */
    private static int readValidInt(Scanner sc, int min, int max) {
        int value;
        while (true) {
            String line = sc.nextLine().trim();
            try {
                value = Integer.parseInt(line);
                if (value >= min && value <= max) return value;
                System.out.printf("  [!] Enter a number between %d and %d: ", min, max);
            } catch (NumberFormatException e) {
                System.out.printf("  [!] Invalid input. Enter a number between %d and %d: ", min, max);
            }
        }
    }

    /** Reads a valid double within [min, max]. Loops until valid. */
    private static double readValidDouble(Scanner sc, double min, double max) {
        double value;
        while (true) {
            String line = sc.nextLine().trim();
            try {
                value = Double.parseDouble(line);
                if (value >= min && value <= max) return value;
                System.out.printf("  [!] Enter a value between %.0f and %.0f: ", min, max);
            } catch (NumberFormatException e) {
                System.out.printf("  [!] Invalid input. Enter a number between %.0f and %.0f: ", min, max);
            }
        }
    }
}
