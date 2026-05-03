import java.util.Scanner;

public class EduTrackAnalyzer {

    public static final int MAX_STUDENTS = 100;
    public static final int MAX_SUBJECTS = 10;

    public static void runAnalysis() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n================================================================");
        System.out.println("|          EduTrack - Extended Analyzer (10 Subjects)          |");
        System.out.println("================================================================");

        System.out.print("Enter number of students (max " + MAX_STUDENTS + "): ");
        int numStudents = scanner.nextInt();

        System.out.print("Enter number of subjects (max " + MAX_SUBJECTS + "): ");
        int numSubjects = scanner.nextInt();
        
        scanner.nextLine(); // consume newline

        String[]   names    = new String[numStudents];
        double[][] grades   = new double[numStudents][numSubjects];
        double[]   averages = new double[numStudents];

        for (int i = 0; i < numStudents; i++) {
            System.out.printf("%nStudent %d of %d%n", i + 1, numStudents);
            System.out.print("  Name: ");
            names[i] = scanner.nextLine();

            for (int j = 0; j < numSubjects; j++) {
                System.out.printf("  Grade for subject %d (0-100): ", j + 1);
                grades[i][j] = scanner.nextDouble();
            }
            scanner.nextLine(); // consume newline
            
            averages[i] = EduTrackEngine.calculateAverage(grades[i]);
        }

        System.out.println("\n================ Per-Student Reports =================");
        for (int i = 0; i < numStudents; i++) {
            EduTrackEngine.printStudentReport(names[i], grades[i]);
        }

        printClassStats(names, averages, numStudents);
    }

    public static void printClassStats(String[] names, double[] averages, int count) {
        if (count == 0) {
            System.out.println("\nNo students to analyze.");
            return;
        }

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

        String topName = names[0];
        for (int i = 1; i < count; i++) {
            if (trimmed[i] == highest) {
                topName = names[i];
            }
        }

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
}
