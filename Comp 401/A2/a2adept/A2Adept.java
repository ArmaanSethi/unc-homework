package a2adept;

import java.util.Scanner;

public class A2Adept {
	public static int to_percent(double n)
	{
		return (int)(((n*100)+0.5));
	}
    public static void process(Scanner s) {
    	int bin_width = s.nextInt();
    	//System.out.println(100/bin_width);
    	int[] intensity;
    	if(100%bin_width == 0)
    	{
    		intensity= new int[100/bin_width];
    	}else
    	{
        	intensity= new int[100/bin_width+1];

    	}
    	for(int i = 0; i < intensity.length; i++)
    		intensity[i] = 0;
    	//System.out.println(intensity.length);
    	int width = s.nextInt();
    	int height = s.nextInt();
    	int next;
    	//System.out.println("Testing: ");
    	for(int i = 0; i < width*height; i++)
    	{
    		next = s.nextInt();
    		//System.out.println(next/bin_width);
    		intensity[next/bin_width]++;
    	}
    	//System.out.println("Testing: ");

    	for(int i = 0; i < intensity.length; i++)
    	{
    		if(i*bin_width < 10)
    			System.out.print(" " +i*bin_width + ":");
    		else
    			System.out.print(i*bin_width + ":");
    		for(int j = 0; j < to_percent((double)intensity[i]/(width*height)); j++)
    			System.out.print("*");
    		System.out.println();
    	}
    	
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		process(s);
	}

}
