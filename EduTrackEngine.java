public class EduTrackEngine {

    public static final double GRADE_A_MIN = 90.0;
    public static final double GRADE_B_MIN = 80.0;
    public static final double GRADE_C_MIN = 70.0;
    public static final double GRADE_D_MIN = 60.0;
    public static final double MAX_GRADE    = 100.0;
    public static final double MIN_GRADE    = 0.0;

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

    public static char toLetter(double avg) {
        if (avg >= GRADE_A_MIN) return 'A';
        if (avg >= GRADE_B_MIN) return 'B';
        if (avg >= GRADE_C_MIN) return 'C';
        if (avg >= GRADE_D_MIN) return 'D';
        return 'F';
    }

    public static boolean isPassing(double avg) {
        return avg >= GRADE_D_MIN;
    }

    public static double findHighestAverage(double[] averages) {
        if (averages == null || averages.length == 0) return 0.0;
        double max = averages[0];
        for (int i = 1; i < averages.length; i++) {
            if (averages[i] > max) max = averages[i];
        }
        return max;
    }

    public static double findLowestAverage(double[] averages) {
        if (averages == null || averages.length == 0) return 0.0;
        double min = averages[0];
        for (int i = 1; i < averages.length; i++) {
            if (averages[i] < min) min = averages[i];
        }
        return min;
    }

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

    public static void printStudentReport(String name, double[] grades) {
        if (name == null || grades == null) {
            System.out.println("Invalid student data.");
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