package a1jedi;

import java.util.Scanner;

public class A1Jedi {

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
        else if(num_grade >= 0.96)
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
    public static double map_percent(double percent)//works for all values
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
    public static double map_normalized(double n)//works for all values 
    {
        if(n >= 1.0)
            return 4.0;
        else if(n>= 0.0)
            return n+3.0;
        else if (n>= -1.0)
            return n+ 3.0;
        else if(n>= -1.5)
            return 2+((n+1)*2);
        else
            return 0;

    }
    public static double[] map_exams(double n[])
    {
        double sum = 0;
        for(int i = 0; i < n.length; i++)
            sum+= (double)n[i];
        double avg = (double)sum/n.length;
        double diff[] = new double[n.length];
        for(int i = 0; i < n.length; i++)
            diff[i] = (double)n[i]-(double)avg;
        double new_sum = 0;
        for(int i = 0; i < n.length; i++)
            new_sum += (double)diff[i]*(double)diff[i];
        double new_avg = (double)new_sum/n.length;
        double std_dev = Math.sqrt(new_avg);
        
        double norm[] = new double[n.length];
        for(int i = 0; i < n.length; i++)
            norm[i] = ((double)n[i]-(double)avg)/(double)std_dev;
        double answer[] = new double[n.length];
        for(int i = 0; i < n.length; i++)
            answer[i] = map_normalized(norm[i]);

        return answer;
    }

    public static void output(String s[])
    {
        int[] counter = new int[11];
        for(int i = 0; i < counter.length; i++)
            counter[i] = 0;

        for(int i = 0; i < s.length; i++)
        {
            if(s[i] == "A")
                counter[0]++;
            else if(s[i] == "A-")
                counter[1]++;
            else if(s[i] == "B+")
                counter[2]++;
            else if(s[i] == "B")
                counter[3]++;
            else if(s[i] == "B-")
                counter[4]++;
            else if(s[i] == "C+")
                counter[5]++;
            else if(s[i] == "C")
                counter[6]++;
            else if(s[i] == "C-")
                counter[7]++;
            else if(s[i] == "D+")
                counter[8]++;
            else if(s[i] == "D")
                counter[9]++;
            else if(s[i] == "F")
                counter[10]++;
        }
        System.out.println("A : " + counter[0]);
        System.out.println("A-: " + counter[1]);
        System.out.println("B+: " + counter[2]);
        System.out.println("B : " + counter[3]);
        System.out.println("B-: " + counter[4]);
        System.out.println("C+: " + counter[5]);
        System.out.println("C : " + counter[6]);
        System.out.println("C-: " + counter[7]);
        System.out.println("D+: " + counter[8]);
        System.out.println("D : " + counter[9]);
        System.out.println("F : " + counter[10]);
    }

    public static void process(Scanner s) {
        // Put your code here.
        int num_assignments = s.nextInt();
        double total_points_possible = 0;
        for(int i = 0; i < num_assignments; i++)
            total_points_possible += s.nextDouble();
        int num_students = s.nextInt();
        double[][] grades = new double[num_students][5];
        String output[] = new String[num_students];
        for(int z = 0; z < num_students; z++) {
            String first_name = s.next();
            String last_name = s.next();
            grades[z][1] = map_percent((s.nextDouble() / 15.0) * 100);
            double total_points_earned = 0;
            for (int i = 0; i < num_assignments; i++)
                total_points_earned += s.nextDouble();
            grades[z][0] = map_percent(100 * total_points_earned / total_points_possible);
            //System.out.println("Assignment 4.0: " + grades[0]);
            for (int i = 0; i < 3; i++)
                grades[z][i + 2] = s.nextDouble();
            //output[z] = first_name.charAt(0) + ". " + last_name + " "  + letter_grade(weighted_average(grades));
        }
        double[] exams = new double[num_students];
        double[] new_exams = new double[num_students];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < num_students; j++)
            {
                exams[j] = grades[j][2+i];
            }
            new_exams = map_exams(exams);
            for(int j = 0; j < num_students; j++)
            {
                grades[j][2+i] = new_exams[j];
            }
        }
        String[] letter_grades = new String[num_students];
        for(int i = 0; i < num_students; i++) {
            for (int z = 0; z < 5; z++) {
                //System.out.print(grades[i][z]+ "   ");
            }
            letter_grades[i] = letter_grade((weighted_average(grades[i])));
//            System.out.println("Num G: " + weighted_average(grades[i]) + " Letter Grade:  " + letter_grade((weighted_average(grades[i]))));
        }
        output(letter_grades);
    }
	// Do not change the main method.	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		process(s);

	}

}
