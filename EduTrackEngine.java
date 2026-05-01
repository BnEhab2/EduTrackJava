/**
 * EduTrackEngine — Core calculation engine for the EduTrack system.
 * Provides static utility methods for grade computation, validation,
 * and reporting. Used by both Student (3 subjects) and EduTrackAnalyzer (10 subjects).
 *
 * No ArrayList or Streams — arrays only.
 */
public class EduTrackEngine {

    // ==================== Grading Constants ====================
    public static final double GRADE_A_MIN = 90.0;
    public static final double GRADE_B_MIN = 80.0;
    public static final double GRADE_C_MIN = 70.0;
    public static final double GRADE_D_MIN = 60.0;   // also the passing threshold
    public static final double MAX_GRADE    = 100.0;
    public static final double MIN_GRADE    = 0.0;

    // ==================== Core Methods ====================

    /**
     * Calculates the average of valid grades (0–100).
     * Handles null/empty arrays safely — returns 0.0.
     */
    public static double calculateAverage(double[] grades) {
        if (grades == null || grades.length == 0) return 0.0;

        double sum = 0;
        int validCount = 0;

        for (int i = 0; i < grades.length; i++) {
            if (grades[i] >= MIN_GRADE && grades[i] <= MAX_GRADE) {
                sum += grades[i];
                validCount++;
            }
        }

        if (validCount == 0) return 0.0;
        return Math.round((sum / validCount) * 10.0) / 10.0;
    }

    /**
     * Converts a numeric average to a letter grade using grading constants.
     */
    public static char toLetter(double avg) {
        if (avg >= GRADE_A_MIN) return 'A';
        if (avg >= GRADE_B_MIN) return 'B';
        if (avg >= GRADE_C_MIN) return 'C';
        if (avg >= GRADE_D_MIN) return 'D';
        return 'F';
    }

    /**
     * Checks if the average meets the passing threshold (>= 60).
     */
    public static boolean isPassing(double avg) {
        return avg >= GRADE_D_MIN;
    }

    /**
     * Finds the highest value in an array of averages.
     * Handles null/empty safely.
     */
    public static double findHighestAverage(double[] averages) {
        if (averages == null || averages.length == 0) return 0.0;
        double max = averages[0];
        for (int i = 1; i < averages.length; i++) {
            if (averages[i] > max) max = averages[i];
        }
        return max;
    }

    /**
     * Finds the lowest value in an array of averages.
     * Handles null/empty safely.
     */
    public static double findLowestAverage(double[] averages) {
        if (averages == null || averages.length == 0) return 0.0;
        double min = averages[0];
        for (int i = 1; i < averages.length; i++) {
            if (averages[i] < min) min = averages[i];
        }
        return min;
    }

    /**
     * Counts how many averages fall into each grade category.
     * Returns int[5]: index 0=A, 1=B, 2=C, 3=D, 4=F.
     * Handles null safely.
     */
    public static int[] countPerGrade(double[] averages) {
        int[] counts = new int[5];
        if (averages == null) return counts;

        for (int i = 0; i < averages.length; i++) {
            char letter = toLetter(averages[i]);
            switch (letter) {
                case 'A': counts[0]++; break;
                case 'B': counts[1]++; break;
                case 'C': counts[2]++; break;
                case 'D': counts[3]++; break;
                case 'F': counts[4]++; break;
            }
        }
        return counts;
    }

    /**
     * Prints a formatted report for a single student given name and grades array.
     * Handles null inputs safely.
     */
    public static void printStudentReport(String name, double[] grades) {
        if (name == null || grades == null) {
            System.out.println("[Error] Invalid student data.");
            return;
        }

        double avg   = calculateAverage(grades);
        char   grade = toLetter(avg);
        boolean pass = isPassing(avg);

        System.out.println("===== Student Report =====");
        System.out.printf("  Name    : %s%n", name);
        System.out.printf("  Average : %.1f%n", avg);
        System.out.printf("  Grade   : %c%n", grade);
        System.out.printf("  Status  : %s%n", pass ? "PASS" : "FAIL");
        System.out.println("==========================");
    }
}