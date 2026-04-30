public class EduTrackAnalyzer {

    public static void main(String[] args) {
        // P8: Construct a Java program that declares and initializes a single-dimensional array
        double[] student1Grades = {85.5, 90.0, 78.5, 92.0, 88.0, 95.0, 89.0, 76.5, 91.0, 84.0};
        double[] student2Grades = {55.0, 60.5, 45.0, 50.0, 65.0, 40.0, 58.0, 62.0, 48.0, 52.0};

        System.out.println("--- EduTrack System Testing ---");

        // Testing Method 4 (which internally uses Methods 1, 2, and 3)
        // This demonstrates modularity and reusability
        EduTrackEngine.printStudentReport("Nour Ahmed", student1Grades);
        EduTrackEngine.printStudentReport("Ali Kamel", student2Grades);

        // Testing Method 5: Highest Average
        double avg1 = EduTrackEngine.calculateAverage(student1Grades);
        double avg2 = EduTrackEngine.calculateAverage(student2Grades);
        double[] classAverages = {avg1, avg2};
        
        double highest = EduTrackEngine.findHighestAverage(classAverages);
        System.out.println("\n--- Class Statistics ---");
        System.out.printf("Highest Average in the class: %.2f\n", highest);

        // P9: Demonstrate method overloading (Testing Bonus Method)
        double singleGrade = 95.5;
        double overloadedResult = EduTrackEngine.calculateAverage(singleGrade);
        System.out.println("\n--- Overloaded Method Test ---");
        System.out.println("Single Grade Output: " + overloadedResult);
    }
}
