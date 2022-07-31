import java.io.PrintWriter;
import java.util.*;

public class Planting2 {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("planting.in"));
    // Scanner sc = new Scanner(System.in);
    PrintWriter pw = new PrintWriter("planting.out");

    int N = sc.nextInt();
    
    List<List<Integer>> connected = new ArrayList<>();
    for (int i = 1; i < N+2; i++) {
      connected.add(new ArrayList<>());
    }

    for (int i = 1; i < N; i++) {
      int a = sc.nextInt();
      int b = sc.nextInt();
      connected.get(a).add(b);
      connected.get(b).add(a);
    }

    int max = 0;
    for (int i = 1; i < N+1; i++) {
      max = Math.max(max, connected.get(i).size()+1);
    }
    
    pw.println(max);
    
    pw.close();
    sc.close();
  }
}
