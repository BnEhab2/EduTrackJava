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
        for(int i = 0; i < count ; i++){
            finalAlert[i] = alerts[i];
        }
        return finalAlert;
    }

    public boolean SaveRecords(String studentID, String recordData){
        try{
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public String generateReport(String studentID, double GPA, String[] alerts){
        String report = "Academic Report for Student ID: " + studentID + "\n";
        report += "Current GPA: " + GPA + "\n";
        report += "Academic Progress: " + completedCredits + " / " + totalCredits + " credits\n";

        if(alerts.length == 0){
            report += "Alerts: None\n";
        }else{
            report += "Alerts:\n";
            for(int i = 0; i < alerts.length; i++){
                report += "- " + alerts[i] + "\n";
            }
        }
        return report;
    }
}