import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Barntree {
  static StreamTokenizer st;
  static int N;
  static int target;
  static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("barntree.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("barntree.out"));
    // PrintWriter pw = new PrintWriter(System.out);
    N = nextInt();
    
    // solve
    Cow[] cows = new Cow[N];
    long total = 0;
    for (int i = 0; i < N; i++) {
      int v = nextInt();
      total += v;
      cows[i] = new Cow(v, i);
    }

    target = (int) (total/N);

    for (int i = 0; i < N-1; i++) {
      int a = nextInt()-1;
      int b = nextInt()-1;
      cows[a].nbs.add(cows[b]);
      cows[b].nbs.add(cows[a]);
    }

    // make tree
    assignParent(cows[0]);

    recursive(cows[0]);

    pw.print(sb.toString());

    br.close();
    pw.close();
  }

  private static int recursive(Cow cow) {
    if (cow.id != 0 && cow.nbs.size() == 1) {
      // leaf
      return target-cow.value;
    }
    // recieve
    Queue<Integer> needs = new LinkedList<>();
    for (Cow nb : cow.nbs) {
      if (nb != cow.parent) {
        int need = recursive(nb);
        needs.add(need);
        if (need < 0) {
          sb.append(String.format("%d %d %d\n", nb.id+1, cow.id+1, need*-1));
          nb.value = target;
          cow.value += need * -1;
        }
      }
    }

    int needFromParent = 0;
    for (Cow nb : cow.nbs) {
      if (nb != cow.parent) {
        int need = needs.poll();
        if (need > 0) {
          if (need <= cow.value - target) {
            // can give
            sb.append(String.format("%d %d %d\n", cow.id+1, nb.id+1, need));
            nb.value = target;
            cow.value -= need;
          } else if (cow.value > target) {
            // still can give a part
            sb.append(String.format("%d %d %d\n", cow.id+1, nb.id+1, cow.value - target));
            needFromParent += target - nb.value - (cow.value-target);
            nb.value += cow.value - target;
            cow.value = target;
          } else {
            needFromParent += target - nb.value;
          }
        }
      }
    }

    if (needFromParent == 0) {
      return target - cow.value;
    }
    return needFromParent;
  }


  private static void assignParent(Cow c) {
    for (Cow nb : c.nbs) {
      if (nb != c.parent) {
        nb.parent = c;
        assignParent(nb);
      }
    }
  }

  private static class Cow {
    Cow parent;
    List<Cow> nbs = new ArrayList<>();
    int value;
    int id;

    public Cow(int value, int id) {
      this.value = value;
      this.id = id;
    }
  }

  private static int nextInt() throws Exception {
    st.nextToken();
    return (int) st.nval;
  }

  private static String nextString() throws Exception {
    st.nextToken();
    return st.sval;
  }
}