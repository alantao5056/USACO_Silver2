import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Highcard {
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("highcard.in"));
    PrintWriter pw = new PrintWriter("highcard.out");
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    
    // solve
    int[] elsieCards = new int[N];
    for (int i = 0; i < N; i++) {
      elsieCards[i] = Integer.parseInt(br.readLine());
    }
    Arrays.sort(elsieCards);
    int[] bessieCards = new int[N];
    int lastElsieCard = 0;
    int bessieIdx = 0;
    for (int i = 0; i < N; i++) {
      int curElsieCard = elsieCards[i];

      for (int j = lastElsieCard+1; j < curElsieCard; j++) {
        bessieCards[bessieIdx] = j;
        bessieIdx++;
      }
      lastElsieCard = curElsieCard;
    }

    while (bessieIdx < N) {
      bessieCards[bessieIdx] += ++lastElsieCard;
      bessieIdx++;
    }

    int i = 0;
    int j = 0;
    int count = 0;
    while (i < N && j < N) {
      if (elsieCards[i] < bessieCards[j]) {
        // bessie wins
        count++;
        i++;
        j++;
      } else {
        j++;
      }
    }

    pw.println(count);

    br.close();
    pw.close();
  }
}