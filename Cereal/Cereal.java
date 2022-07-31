import java.io.PrintWriter;
import java.io.File;
import java.util.*;

public class Cereal {
  static int N;
  static int M;
  static int count = 0;
  static Cow[] cerealTaken;
  static Cow[] cows;

  public static void main(String[] args) throws Exception {
    // read input
    Scanner sc = new Scanner(new File("cereal.in"));
    PrintWriter pw = new PrintWriter("cereal.out");
    N = sc.nextInt();
    M = sc.nextInt();
    
    // solve
    cerealTaken = new Cow[M];
    cows = new Cow[N];

    for (int i = 0; i < N; i++) {
      cows[i] = new Cow(sc.nextInt()-1, sc.nextInt()-1, i);
    }

    int[] results = new int[N];
    for (int i = N-1; i >= 0; i--) {
      take(cows[i], cows[i].firstChoice, 1);
      results[i] = count;
    }

    for (int i = 0; i < N; i++) {
      pw.println(results[i]);
    }

    sc.close();
    pw.close();
  }

  private static void take(Cow c, int cereal, int pick) {
    Cow takenCow = cerealTaken[cereal];
    if (takenCow == null) {
      // no one is taking it
      cerealTaken[cereal] = c;
      c.pick = pick;
      count++;
      return;
    }

    if (c.id < takenCow.id) {
      // has smaller id so can take
      cerealTaken[cereal] = c;
      c.pick = pick;
      if (takenCow.pick == 1) {
        take(takenCow, takenCow.secondChoice, 1);
      } else {
        takenCow.pick = -1;
      }
    }
  }

  private static class Cow {
    int firstChoice;
    int secondChoice;
    int id;
    int pick = -1;

    public Cow(int firstChoice, int secondChoice, int id) {
      this.firstChoice = firstChoice;
      this.secondChoice = secondChoice;
      this.id = id;
    }
  }
}