import java.util.Scanner;
import java.util.Stack;

public class Frisbee2 {

  public static void main(String[] args) throws Exception {
//    Scanner sc = new Scanner(new java.io.File("1.in"));
    Scanner sc = new Scanner(System.in);
    
    int N = sc.nextInt();
    
    long result = (N-1) * 2;
    
    Stack<Cow> stack = new Stack<>();
    
    for (int i = 0; i < N; i++) {
      Cow cow = new Cow(sc.nextInt(), i);
      if (stack.isEmpty()) {
        stack.add(cow);
        continue;
      }
      
      while (!stack.isEmpty() && stack.peek().height < cow.height) {
        if (stack.peek().index != cow.index - 1) {
          result += cow.index - stack.pop().index + 1;
        } else {
          stack.pop();
        }
      }
      if (stack.size() > 0 && stack.peek().index != cow.index-1) {
        result += cow.index - stack.peek().index + 1;
      }
      stack.add(cow);
    }
    
    System.out.println(result);
    
    sc.close();
  }
  
  private static class Cow {
    int height;
    int index;
    public Cow(int h, int i) {
      height = h;
      index = i;
    }
  }

}
