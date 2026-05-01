public class student {

    // Public Fields
    public String name;
    public double javaGrade;
    public double mathGrade;
    public double englishGrade;
    public double average;
    public char letterGrade;
    public boolean isPassing;

    // Constructor - must match class name exactly
    public student(String name, double javaGrade, double mathGrade, double englishGrade) {

        this.name = name;
        this.javaGrade = javaGrade;
        this.mathGrade = mathGrade;
        this.englishGrade = englishGrade;

        // Store grades in array
        double[] grades = {javaGrade, mathGrade, englishGrade};

        // Calculate average using EduTrackEngine
        average = EduTrackEngine.calculateAverage(grades);

        // Calculate letter grade using EduTrackEngine
        letterGrade = EduTrackEngine.getLetterGrade(average);

        // Check passing status using EduTrackEngine
        isPassing = EduTrackEngine.isPassing(average);
    }

    // Print student record
    public void printRecord() {

        System.out.println("-----------------------------------");
        System.out.println("Name: " + name);
        System.out.println("Java Grade: " + javaGrade);
        System.out.println("Math Grade: " + mathGrade);
        System.out.println("English Grade: " + englishGrade);
        System.out.println("Average: " + average);
        System.out.println("Letter Grade: " + letterGrade);
        System.out.println("Passing: " + isPassing);
        System.out.println("-----------------------------------");
    }
}