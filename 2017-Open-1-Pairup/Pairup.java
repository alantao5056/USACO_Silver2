import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Pairup {
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("pairup.in"));
    PrintWriter pw = new PrintWriter("pairup.out");
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    
    // solve
    Group[] cows = new Group[N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      cows[i] = new Group(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    }

    Arrays.sort(cows, new Comparator<Group>() {
      public int compare(Group a, Group b) {
        return a.milk - b.milk;
      }
    });

    int i = 0;
    int j = N-1;
    int maxTime = 0;

    while (i < j) {
      maxTime = Math.max(maxTime, cows[i].milk + cows[j].milk);
      if (cows[i].amount == cows[j].amount) {
        // same amount
        i++;
        j--;
      } else if (cows[i].amount > cows[j].amount) {
        cows[i].amount -= cows[j].amount;
        j--;
      } else {
        // cows j more than cows i
        cows[j].amount -= cows[i].amount;
        i++;
      }
    }
    if (i == j) {
      maxTime = Math.max(maxTime, cows[i].milk * 2);
    }

    pw.println(maxTime);

    br.close();
    pw.close();
  }

  private static class Group {
    int amount;
    int milk;

    public Group(int amount, int milk) {
      this.amount = amount;
      this.milk = milk;
    }
  }
}