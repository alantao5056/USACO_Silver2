import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Subset {
  public static void main(String[] args) throws Exception {
    // Scanner sc = new Scanner(new java.io.File("1.in"));
    Scanner sc = new Scanner(System.in);

    char[] s = sc.next().toCharArray();
    char[] t = sc.next().toCharArray();
    int N = sc.nextInt();

    for (int i = 0; i < N; i++) {
      char[] forbidden = sc.next().toCharArray();
      Set<Character> forbiddenSet = new HashSet<>();

      for (var f : forbidden) {
        forbiddenSet.add(f);
      }

      List<Character> newS = new ArrayList<>();
      List<Character> newT = new ArrayList<>();

      for (var c : s) {
        if (forbiddenSet.contains(c)) {
          newS.add(c);
        }
      }

      for (var c : t) {
        if (forbiddenSet.contains(c)) {
          newT.add(c);
        }
      }

      if (newS.equals(newT)) {
        // found
        System.out.print("Y");
      } else {
        System.out.print("N");
      }
    }

    System.out.println();
    sc.close();
  }
}