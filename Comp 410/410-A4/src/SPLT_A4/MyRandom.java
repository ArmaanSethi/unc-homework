package SPLT_A4;

import java.util.*;

public class MyRandom {

  private static Random rn = new Random();

  private MyRandom(){ }

  public static int rand(int lo, int hi) {
     int n = hi - lo + 1;
     int i = rn.nextInt() % n;
     if (i < 0) i = -i;
     return lo + i;
  }

  @SuppressWarnings("deprecation")
  public static String nextString(int lo, int hi) {
     int n = rand(lo, hi);
     byte b[] = new byte[n];
     for (int i = 0; i < n; i++)
     b[i] = (byte)rand('a', 'z');
     return new String(b, 0);
  }

  public static String nextString() {
     return nextString(5, 25);
  }
  
}
