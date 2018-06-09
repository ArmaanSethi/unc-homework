package a1adept;

import java.util.Scanner;

public class A1Adept {
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
        return grades[0]*0.4 + grades[1]*0.1 + grades[2]*0.15 + grades[3]*0.15 + grades[4]*0.20;
    }
    public static double map_percent(double percent)
    {
        if(percent >= 95)
            return 4.0;
        else if(percent >= 90)
            return (percent-90)/10.0+3.5;
        else if(percent >= 80)
            return(percent - 80)/10.0+2.5;
        else if (percent >= 70)
            return (percent - 70)/10.0+1.5;
        else if (percent >= 40)
            return ((percent -40)/20);
        else
            return 0;
    }

    public static void process(Scanner s) {
        // Put your code here.
        int num_assignments = s.nextInt();
        double[] grades = new double[5];
        double total_points_possible = 0;
        for(int i = 0; i < num_assignments; i++)
            total_points_possible += s.nextDouble();
        int num_students = s.nextInt();
        String output[] = new String[num_students];
        for(int z = 0; z < num_students; z++) {
            String first_name = s.next();
            String last_name = s.next();
            grades[1] = map_percent((s.nextDouble() / 15.0) * 100);
            double total_points_earned = 0;
            for (int i = 0; i < num_assignments; i++)
                total_points_earned += s.nextDouble();
            grades[0] = map_percent(100 * total_points_earned / total_points_possible);
            //System.out.println("Assignment 4.0: " + grades[0]);
            for (int i = 0; i < 3; i++)
                grades[i + 2] = s.nextDouble();
            output[z] = first_name.charAt(0) + ". " + last_name + " "  + letter_grade(weighted_average(grades));
        }
        for(int i = 0; i < num_students; i++)
            System.out.println(output[i]);
    }
	// Do not change the main method.	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		process(s);
		
	}
	
}
