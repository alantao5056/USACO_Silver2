import java.io.PrintWriter;
import java.io.File;
import java.util.*;

public class Measurement {
  static int N;
  static int G;

  public static void main(String[] args) throws Exception {
    // read input
    Scanner sc = new Scanner(new File("measurement.in"));
    PrintWriter pw = new PrintWriter("measurement.out");
    N = sc.nextInt();
    G = sc.nextInt();
    
    // solve
    LogEntry[] logs = new LogEntry[N];
    HashMap<Integer, Integer> cowHash = new HashMap<>();
    for (int i = 0; i < N; i++) {
      int day = sc.nextInt();
      int cowId = sc.nextInt();
      char[] milkStr = sc.next().toCharArray();
      int milkValue = 0;
      int count = 0;
      for (int j = milkStr.length - 1; j > 0; j--) {
        milkValue += ((int) Math.pow(10, count)) * (milkStr[j] - '0');
        count += 1;
      }

      if (!cowHash.containsKey(cowId)) {
        cowHash.put(cowId, G);
      }

      logs[i] = new LogEntry(day, cowId, milkStr[0] == '+' ? milkValue : -1 * milkValue);
    }

    Arrays.sort(logs, new Comparator<LogEntry>() {
      public int compare(LogEntry a, LogEntry b) {
        return a.day - b.day;
      }
    });

    // start simulation
    Set<Integer> leaderBoard = new HashSet<>();
    for (var e : cowHash.keySet()) {
      leaderBoard.add(e);
    }
    int maxMilk = G;
    int count = 0;

    for (int i = 0; i < N; i++) {
      int cowId = logs[i].cowId;
      int milk = logs[i].milk;

      cowHash.put(cowId, cowHash.get(cowId) + milk);
      if (leaderBoard.contains(cowId)) {
        // on leader board
        if (leaderBoard.size() == 1 && milk > 0 && i!=0) {
          // only person
          maxMilk = cowHash.get(cowId);
          continue;
        }
        if (milk < 0) {
          // maybe fallen off
          leaderBoard.remove(cowId);
          if (leaderBoard.size() == 0) {
            int curMaxMilk = 0;
            Set<Integer> newLeaderboard = new HashSet<>();
            for (var e : cowHash.entrySet()) {
              if (e.getValue() > curMaxMilk) {
                newLeaderboard.clear();
                newLeaderboard.add(e.getKey());
                curMaxMilk = e.getValue();
              } else if (e.getValue() == curMaxMilk) {
                newLeaderboard.add(e.getKey());
              }
            }

            maxMilk = curMaxMilk;
            leaderBoard = newLeaderboard;
            if (maxMilk < G) {
              maxMilk = G;
            }
            if (leaderBoard.size() != 1 || !leaderBoard.contains(cowId)) {
              // have fallen off
              count++;
            }
          }
        } else {
          // only person on leader board
          leaderBoard.clear();
          leaderBoard.add(cowId);
          maxMilk = cowHash.get(cowId);
          count++;
        }
      } else {
        if (cowHash.get(cowId) > maxMilk) {
          // top one
          leaderBoard.clear();
          leaderBoard.add(cowId);
          maxMilk = cowHash.get(cowId);
          count++;
        } else if (cowHash.get(cowId) == maxMilk) {
          // add to leader board
          leaderBoard.add(cowId);
          count++;
        }
      }
    }

    pw.println(count);

    sc.close();
    pw.close();
  }

  private static class LogEntry {
    int day;
    int cowId;
    int milk;

    public LogEntry (int day, int cowId, int milk) {
      this.day = day;
      this.cowId = cowId;
      this.milk = milk;
    }
  }
}