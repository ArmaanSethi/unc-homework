package a1novice;

import java.util.Scanner;

public class A1Novice {

    public static String letter_grade(double num_grade){
        if(num_grade >= 3.85)
            return"A";
        else if(num_grade >= 3.5)
            return "A-";
        else if(num_grade >= 3.15)
            return "B+";
        else if(num_grade >= 2.85)
            return "B";
        else if(num_grade >= 2.5)
            return "B-";
        else if(num_grade >= 2.15)
            return "C+";
        else if(num_grade >= 1.85)
            return "C";
        else if(num_grade >= 1.5)
            return "C-";
        else if(num_grade >= 1.15)
            return "D+";
        else if(num_grade >= 0.85)
            return "D";
        else
            return "F";
    }

    public static double weighted_average(double[] grades){
        //Assignment_Grade(40%) Recitation_Grade(10%) Midterm_1(15%) Midterm_2(15%) Final_Exam(20%)
        double average = grades[0]*0.4 + grades[1]*0.1 + grades[2]*0.15 + grades[3]*0.15 + grades[4]*0.20;
        return average;
    }
    public static void process(Scanner s) {
        // Put your code here.
        //First_Name Last_Name Assignment_Grade Recitation_Grade Midterm_1 Midterm_2 Final_Exam
        int num_students = s.nextInt();
        String output[] = new String[num_students];
        for (int z = 0; z < num_students; z++) {
            String first_name = s.next();
            String last_name = s.next();
            int num_grades = 5;//Assignment_Grade Recitation_Grade Midterm_1 Midterm_2 Final_Exam
            double[] grades = new double[num_grades];
            for (int i = 0; i < num_grades; i++) {
                grades[i] = s.nextDouble();
            }
            output[z] = first_name.charAt(0) + ". " + last_name + " " + letter_grade(weighted_average(grades));
            //System.out.println(first_name.charAt(0) + ". " + last_name + " " + letter_grade(weighted_average(grades)));
        }
        for(int i = 0; i < num_students; i++)
        {
            System.out.println(output[i]);
        }
    }
	// Do not change the main method.	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		process(s);
	}
	
}
