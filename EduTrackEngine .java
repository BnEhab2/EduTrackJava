package nada;
public class EduTrackEngine {
	
	    // 1- Calculate Average (without modifying original data)
	    public static double calculateAverage(double[] grades) {
	        if (grades == null || grades.length == 0) return 0;

	        double sum = 0;

	        for (int i = 0; i < grades.length; i++) {
	            if (grades[i] >= 0 && grades[i] <= 100) {
	                sum += grades[i];
	            } else {
	                sum += 0; // ignore invalid values
	            }
	        }

	        return sum / grades.length;
	    }

	    // 2- Convert to Letter Grade
	    public static char getLetterGrade(double avg) {
	        if (avg >= 90) return 'A';
	        else if (avg >= 80) return 'B';
	        else if (avg >= 70) return 'C';
	        else if (avg >= 60) return 'D';
	        else return 'F';
	    }

	    // 3- Check Passing
	    public static boolean isPassing(double avg) {
	        return avg >= 60;
	    }

	    // 4- Print Student Report
	    public static void printStudentReport(String name, double[] grades) {

	        double avg = calculateAverage(grades);
	        char grade = getLetterGrade(avg);
	        boolean pass = isPassing(avg);

	        System.out.println("===== Student Report =====");
	        System.out.println("Name: " + name);
	        System.out.println("Average: " + avg);
	        System.out.println("Grade: " + grade);
	        System.out.println("Status: " + (pass ? "PASS" : "FAIL"));
	        System.out.println("==========================");
	    }

	    // 5- Highest Average
	    public static double findHighestAverage(double[] averages) {
	        if (averages == null || averages.length == 0) return 0;

	        double max = averages[0];

	        for (int i = 1; i < averages.length; i++) {
	            if (averages[i] > max) {
	                max = averages[i];
	            }
	        }

	        return max;
	    }

	    // 6- Count Grades
	    public static void countGrades(double[] averages) {

	        int A = 0, B = 0, C = 0, D = 0, F = 0;

	        for (int i = 0; i < averages.length; i++) {

	            char g = getLetterGrade(averages[i]);

	            switch (g) {
	                case 'A': A++; break;
	                case 'B': B++; break;
	                case 'C': C++; break;
	                case 'D': D++; break;
	                case 'F': F++; break;
	            }
	        }

	        System.out.println("Grade Distribution:");
	        System.out.println("A: " + A);
	        System.out.println("B: " + B);
	        System.out.println("C: " + C);
	        System.out.println("D: " + D);
	        System.out.println("F: " + F);
	    }
	}

    