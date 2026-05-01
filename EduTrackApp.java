public class EduTrackApp {

    private String username = "admin";
    private String password = "123456";
    private int completedCredits = 0;
    private int totalCredits = 130;

    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public double calculateGPA(double[] graded, double[] credits) {
        double totalGradePoints = 0;
        int totalCreditsUsed = 0;

        for (int i = 0; i < graded.length; i++) {
            totalGradePoints += graded[i] * credits[i];
            totalCreditsUsed += credits[i];
        }
        if (totalCreditsUsed == 0) {
            return 0;
        }
        return totalGradePoints / totalCreditsUsed;
    }

    public void updateProgress(int NewCredits) {
        this.completedCredits += NewCredits;
    }

    public String[] AcademicAlerts(double CurrentGPA) {
        String[] alerts = new String[2];
        int count = 0;
        if (CurrentGPA < 2.0) {
            alerts[count++] = "Your GPA is below 2.0";
        }
        if (CurrentGPA == 0) {
            alerts[count++] = "No credits completed yet";
        }

        String[] finalAlert = new String[count];
        for (int i = 0; i < count; i++) {
            finalAlert[i] = alerts[i];
        }
        return finalAlert;
    }

    public boolean SaveRecords(String studentID, String recordData) {
        try {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String generateReport(String studentID, double GPA, String[] alerts) {
        String report = "Academic Report for Student ID: " + studentID + "\n";
        report += "Current GPA: " + GPA + "\n";
        report += "Academic Progress: " + completedCredits + " / " + totalCredits + " credits\n";

        if (alerts.length == 0) {
            report += "Alerts: None\n";
        } else {
            report += "Alerts:\n";
            for (int i = 0; i < alerts.length; i++) {
                report += "- " + alerts[i] + "\n";
            }
        }
        return report;
    }

    // ============================================
    //  MAIN METHOD - Entry Point for the system
    // ============================================
    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println("   Welcome to EduTrack System");
        System.out.println("========================================\n");

        // --- 1) Authentication via EduTrackApp ---
        EduTrackApp app = new EduTrackApp();

        boolean loginSuccess = app.authenticate("admin", "123456");
        System.out.println("[Login] Authentication: " + (loginSuccess ? "SUCCESS" : "FAILED"));

        if (!loginSuccess) {
            System.out.println("Access denied. Exiting...");
            return;
        }

        System.out.println();

        // --- 2) Create students using the student class (which uses EduTrackEngine internally) ---
        student s1 = new student("Nour Ahmed", 85.5, 90.0, 78.5);
        student s2 = new student("Ali Kamel", 55.0, 60.5, 45.0);
        student s3 = new student("Sara Hassan", 92.0, 88.0, 95.0);

        System.out.println("[Student Records]");
        s1.printRecord();
        s2.printRecord();
        s3.printRecord();

        // --- 3) Use EduTrackEngine for class-level analysis ---
        System.out.println("\n[Engine] Full Student Reports:");
        double[] s1AllGrades = {85.5, 90.0, 78.5, 92.0, 88.0, 95.0, 89.0, 76.5, 91.0, 84.0};
        double[] s2AllGrades = {55.0, 60.5, 45.0, 50.0, 65.0, 40.0, 58.0, 62.0, 48.0, 52.0};

        EduTrackEngine.printStudentReport("Nour Ahmed", s1AllGrades);
        EduTrackEngine.printStudentReport("Ali Kamel", s2AllGrades);

        // Highest average
        double avg1 = EduTrackEngine.calculateAverage(s1AllGrades);
        double avg2 = EduTrackEngine.calculateAverage(s2AllGrades);
        double[] classAverages = {avg1, avg2};

        double highest = EduTrackEngine.findHighestAverage(classAverages);
        System.out.println("\n[Engine] Highest Average in class: " + highest);

        // Grade distribution
        System.out.println();
        EduTrackEngine.countGrades(classAverages);

        // Overloaded method test
        double singleResult = EduTrackEngine.calculateAverage(95.5);
        System.out.println("\n[Engine] Overloaded single-grade test: " + singleResult);

        // --- 4) GPA & Academic tracking via EduTrackApp ---
        System.out.println("\n[App] GPA & Progress Tracking:");
        double[] gpaGrades = {3.5, 4.0, 3.0, 3.7};
        double[] gpaCredits = {3, 4, 3, 3};
        double gpa = app.calculateGPA(gpaGrades, gpaCredits);
        System.out.println("Calculated GPA: " + gpa);

        app.updateProgress(13);
        String[] alerts = app.AcademicAlerts(gpa);

        // Save records
        boolean saved = app.SaveRecords("20240001", "Semester 1 Data");
        System.out.println("Records Saved: " + saved);

        // Generate full report
        System.out.println("\n[App] Academic Report:");
        String report = app.generateReport("20240001", gpa, alerts);
        System.out.println(report);

        // --- 5) Run EduTrackAnalyzer demo ---
        System.out.println("[Analyzer] Running EduTrackAnalyzer demo...\n");
        EduTrackAnalyzer.main(new String[]{});

        System.out.println("========================================");
        System.out.println("   EduTrack System - Complete");
        System.out.println("========================================");
    }
}