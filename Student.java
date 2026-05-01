/**
 * Student.java — Represents a student with 3 core subjects (Java, Math, English).
 *
 * KEY DIFFERENCE (3 vs 10 subjects):
 *   - This class uses 3 FIXED subject fields for quick student records.
 *   - EduTrackAnalyzer uses flexible arrays for UP TO 10 subjects per student,
 *     enabling detailed, variable-length academic analysis.
 *   Both classes delegate calculations to EduTrackEngine.
 */
public class Student {

    // Fields
    public String  name;
    public double  javaGrade;
    public double  mathGrade;
    public double  englishGrade;
    public double  average;
    public char    letterGrade;
    public boolean isPassing;

    /**
     * Constructor — validates and stores grades, then uses EduTrackEngine
     * to compute average, letter grade, and passing status.
     */
    public Student(String name, double javaGrade, double mathGrade, double englishGrade) {
        this.name         = name;
        this.javaGrade    = javaGrade;
        this.mathGrade    = mathGrade;
        this.englishGrade = englishGrade;

        // Delegate all calculations to EduTrackEngine
        double[] grades  = { javaGrade, mathGrade, englishGrade };
        this.average     = EduTrackEngine.calculateAverage(grades);
        this.letterGrade = EduTrackEngine.toLetter(this.average);
        this.isPassing   = EduTrackEngine.isPassing(this.average);
    }

    /**
     * Prints a formatted individual student record (matching project output spec).
     */
    public void printRecord() {
        System.out.println("--------------------------------------");
        System.out.printf("  Student   : %s%n", name);
        System.out.printf("  Java      : %.1f%n", javaGrade);
        System.out.printf("  Math      : %.1f%n", mathGrade);
        System.out.printf("  English   : %.1f%n", englishGrade);
        System.out.printf("  Average   : %.1f%n", average);
        System.out.printf("  Grade     : %c%n", letterGrade);
        System.out.printf("  Status    : %s%n", isPassing ? "PASS" : "FAIL");
        System.out.println("--------------------------------------");
    }
}