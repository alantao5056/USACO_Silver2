import java.util.*;
import java.io.PrintWriter;

public class Milkvisits {
  static int N;
  static int M;
  static Farm[] farms;
  static Farm root;

  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("milkvisits.in"));
    PrintWriter pw = new PrintWriter("milkvisits.out");
    // Scanner sc = new Scanner(System.in);

    N = sc.nextInt();
    M = sc.nextInt();
    
    farms = new Farm[N];

    char[] s = sc.next().toCharArray();
    for (int i = 0; i < N; i++) {
      farms[i] = new Farm(s[i]=='H', i);
    }

    for (int i = 0; i < N-1; i++) {
      int a = sc.nextInt()-1;
      int b = sc.nextInt()-1;

      farms[a].children.add(farms[b]);
      farms[b].children.add(farms[a]);
    }

    // create tree
    root = farms[0];

    ArrayDeque<Farm> q = new ArrayDeque<>();
    q.add(root);

    while (!q.isEmpty()) {
      Farm farm = q.poll();

      for (Farm f : farm.children) {
        f.parent = farm;
        f.children.remove(farm);
        q.add(f);
      }
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < M; i++) {
      int start = sc.nextInt()-1;
      int end = sc.nextInt()-1;
      boolean type = sc.next().charAt(0) == 'H';

      sb.append(check(start, end, type)?1:0);
    }

    pw.println(sb.toString());

    pw.close();
    sc.close();
  }

  private static boolean check(int start, int end, boolean type) {
    if (start == end) return type == farms[start].type;
    if (farms[start].type == type || farms[end].type == type) return true;
    List<Farm> startParents = getParents(start);
    List<Farm> endParents = getParents(end);

    int startLength = startParents.size()-1;
    int endLength = endParents.size()-1;
    
    while (startLength >= 0 && endLength >= 0 && startParents.get(startLength) == endParents.get(endLength)) {
      startLength--;
      endLength--;
    }

    Farm commonParent = null;
    if (farms[start] == root || farms[end] == root) {
      commonParent = root;
    } else if (startLength == -1) {
      commonParent = startParents.get(0);
    } else if (endLength == -1) {
      commonParent = endParents.get(0);
    } else {
      // same
      commonParent = startParents.get(startLength+1);
    }

    if (commonParent.type == type) return true;

    // traverse to common parent
    Farm curStart = farms[start];
    while (curStart != commonParent) {
      if (curStart.type == type) {
        return true;
      }
      curStart = curStart.parent;
    }

    Farm curEnd = farms[end];
    while (curEnd != commonParent) {
      if (curEnd.type == type) {
        return true;
      }
      curEnd = curEnd.parent;
    }

    return false;
  }

  private static List<Farm> getParents(int start) {
    List<Farm> result = new ArrayList<>();
    Farm cur = farms[start];
    while (cur.parent != null) {
      result.add(cur.parent);
      cur = cur.parent;
    }
    return result;
  }

  public static class Farm {
    Farm parent = null;
    List<Farm> children = new ArrayList<>();
    boolean type;
    int id;

    public Farm(boolean type, int id) {
      this.type = type;
      this.id = id;
    }
  }
}
