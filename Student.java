public class Student {

    public String  name;
    public double  javaGrade;
    public double  mathGrade;
    public double  englishGrade;
    public double  average;
    public char    letterGrade;
    public boolean isPassing;

    public Student(String name, double javaGrade, double mathGrade, double englishGrade) {
        this.name         = name;
        this.javaGrade    = javaGrade;
        this.mathGrade    = mathGrade;
        this.englishGrade = englishGrade;

        double[] grades  = { javaGrade, mathGrade, englishGrade };
        this.average     = EduTrackEngine.calculateAverage(grades);
        this.letterGrade = EduTrackEngine.toLetter(this.average);
        this.isPassing   = EduTrackEngine.isPassing(this.average);
    }

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