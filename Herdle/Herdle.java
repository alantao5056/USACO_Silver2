import java.util.Scanner;

public class Herdle {

  public static void main(String[] args) throws Exception {
//    Scanner sc = new Scanner(new java.io.File("1.in"));
    Scanner sc = new Scanner(System.in);
    
    int[] correct = new int[26];
    int[][] position = new int[3][3];
    
    for (int i = 0; i < 3; i++) {
      char[] line = sc.next().toCharArray();
      
      for (int j = 0; j < 3; j++) {
        correct[charToInt(line[j])]++;
        position[i][j] = charToInt(line[j]);
      }
    }
    
    int green = 0;
    int yellow = 0;
    for (int i = 0; i < 3; i++) {
      char[] line = sc.next().toCharArray();
      
      for (int j = 0; j < 3; j++) {
        int charInt = charToInt(line[j]);
        if (correct[charInt] == 0) continue;
        
        if (position[i][j] == charInt) {
          green++;
        } else {
          yellow++;
        }
        correct[charInt]--;
      }
    }
    
    System.out.println(green);
    System.out.println(yellow);
    
    sc.close();

  }
  
  private static int charToInt(char c) {
    return Character.getNumericValue(c)-10;
  }

}
