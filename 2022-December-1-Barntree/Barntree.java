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
  static List<Command> commands = new ArrayList<>();
  static int t = 0;

  public static void main(String[] args) throws Exception {
    // read input
    // BufferedReader br = new BufferedReader(new FileReader("barntree.in"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    // PrintWriter pw = new PrintWriter(new File("barntree.out"));
    PrintWriter pw = new PrintWriter(System.out);
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
    recursiveNeeds(cows[0]);

    recursive(cows[0]);

    pw.println(t);

    for (Command c : commands) {
      pw.print(c.start);
      pw.print(" ");
      pw.print(c.end);
      pw.print(" ");
      pw.println(c.value);
    }

    br.close();
    pw.close();
  }

  private static void add(int s, int e, long value) {
    commands.add(new Command(s, e, value));
  }

  private static void recursive(Cow cow) {
    // recieve
    for (Cow nb : cow.nbs) {
      if (nb != cow.parent) {
        if (nb.need >= 0) {
          // can recieve
          recursive(nb);
          if (nb.need != 0) {
            add(nb.id+1, cow.id+1, nb.need);
            nb.value = target;
            cow.value += nb.need;
            t++;
          }
        }
      }
    }

    // give
    for (Cow nb : cow.nbs) {
      if (nb != cow.parent) {
        if (nb.need < 0) {
          add(cow.id+1, nb.id+1, nb.need*-1);
          nb.value += nb.need*-1;
          cow.value -= nb.need*-1;
          recursive(nb);
          t++;
        }
      }
    }
  }

  private static long recursiveNeeds(Cow cow) {
    long totalNeed = cow.value - target;
    for (Cow nb : cow.nbs) {
      if (nb != cow.parent) {
        nb.parent = cow;
        totalNeed += recursiveNeeds(nb);
      }
    }

    cow.need = totalNeed;
    return totalNeed;
  }

  // private static void assignParent(Cow c) {
  //   for (Cow nb : c.nbs) {
  //     if (nb != c.parent) {
  //       nb.parent = c;
  //       assignParent(nb);
  //     }
  //   }
  // }

  private static class Cow {
    Cow parent;
    List<Cow> nbs = new ArrayList<>();
    int value;
    int id;
    long need;

    public Cow(int value, int id) {
      this.value = value;
      this.id = id;
    }
  }

  private static class Command {
    int start;
    int end;
    long value;

    public Command(int start, int end, long value) {
      this.start = start;
      this.end = end;
      this.value = value;
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