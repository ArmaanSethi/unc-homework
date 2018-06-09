package a2novice;

import java.util.Scanner;

public class A2Novice {
	public static char map(int n){
		
		if(n >= 90)
			return ' ';
		else if(n >= 80)
			return '-';
		else if (n >= 70)
			return ':';
		else if(n >= 60)
			return 's';
		else if (n >= 50)
			return '>';
		else if(n >= 40)
			return '<';
		else if (n >= 30)
			return 'D';
		else if(n >= 20)
			return 'X';
		else if (n >= 10)
			return 'M';
		else
			return '#';
	}
    public static void process(Scanner s) {
    	int cols = s.nextInt();
    	int rows = s.nextInt();
    	int[] intensity= new int[rows*cols];
    	double sum = 0;
    	int min = 100;
    	int max = 0;
    	for(int i = 0; i < (rows*cols); i++)
    	{
    		intensity[i] = s.nextInt();
    		if(intensity[i] > max)
    		{
    			max = intensity[i];
    		}
    		if(intensity[i] < min)
    		{
    			min = intensity[i];
    		}
    		sum += intensity[i];
    	}
    	System.out.println();
    	for(int j = 0; j < rows; j++){
			for(int i = 0; i < cols; i++){
				System.out.print(map(intensity[(rows*(i)+j)]));
			}System.out.println();
    	}
    	System.out.println("Min value = " + min);
    	System.out.println("Max value = " + max);
    	System.out.println("Average value = " + ((double)sum/(rows*cols)));
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		process(s);

	}


}
