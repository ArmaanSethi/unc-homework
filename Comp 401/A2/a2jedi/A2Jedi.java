package a2jedi;

import java.util.Scanner;

public class A2Jedi {
	public static int to_percent(double n)
	{
		return (int)(((n*100)+0.5));
	}
    public static void process(Scanner s) {
    	int bin_width = s.nextInt();
    	int[] intensity;
    	if(100%bin_width == 0)
    		intensity= new int[100/bin_width];
    	else
        	intensity= new int[100/bin_width+1];
    	for(int i = 0; i < intensity.length; i++)
    		intensity[i] = 0;
    	int width = s.nextInt();
    	int height = s.nextInt();
    	for(int i = 0; i < width*height; i++)
    		intensity[s.nextInt()/bin_width]++;
    	int[]num_star = new int[intensity.length];
    	int max = 0;
    	for(int i = 0; i < intensity.length; i++)
    	{
    		num_star[i] = to_percent((double)intensity[i]/(width*height));
    		if(num_star[i] > max)
    			max = num_star[i];
    	}
//    	System.out.println(max);
    	for(int r = 0; r < max; r++)
    	{
			for(int i = 0; i < num_star.length; i++)
			{
				//System.out.print(max-num_star[i]);
				if(r < max-num_star[i])
					System.out.print(" ");
				else
					System.out.print("*");
			}
			System.out.println();
    	}
    	for(int i=0; i < num_star.length; i++)
    		System.out.print("-");
    	System.out.println();
    	int zero = 0;
    	for(int i = 0; i < num_star.length; i++)
    	{
    		if((int)(i*bin_width/10)%10 == 0)
    			System.out.print(" ");
    		else
    			System.out.print((int)(i*bin_width/10)%10);
    	}
    	System.out.println();
    	for(int i = 0; i < num_star.length; i++)
    		System.out.print((i*bin_width)%10);
    	
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		process(s);
	}

}
