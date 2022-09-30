import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Rental {
  static int N;
  static int M;
  static int R;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("rental.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter("rental.out");
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    R = Integer.parseInt(st.nextToken());
    
    int[] cows = new int[N];
    for (int i = 0; i < N; i++) {
      cows[i] = Integer.parseInt(br.readLine());
    }

    Arrays.sort(cows);

    int[][] offers = new int[M][2]; // 0 is price, 1 is the amount left
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int amount = Integer.parseInt(st.nextToken());
      int price = Integer.parseInt(st.nextToken());

      offers[i][0] = price;
      offers[i][1] = amount;
    }

    Arrays.sort(offers, new Comparator<int[]>() {
      public int compare(int[] a, int[] b) {
        return b[0] - a[0];
      }
    });

    Integer[] rents = new Integer[R];
    for (int i = 0; i < R; i++) {
      rents[i] = Integer.parseInt(br.readLine());
    }

    Arrays.sort(rents, Collections.reverseOrder());
    
    // solve
    int i = 0;
    int j = N-1;
    int offerIdx = 0;
    int rentsIdx = 0;
    boolean offered = false;
    long total = 0;

    int offerIdx2 = offerIdx;
    long offerCost = 0;
    int left = cows[j];
    while (i < j && (offerIdx < M || rentsIdx < R)) {
      // calculate offer
      if (offerIdx < M && !offered) {
        offerIdx2 = offerIdx;
        offerCost = 0;
        left = cows[j];
        while (offerIdx2 < M && offers[offerIdx2][1] <= left) {
          offerCost += offers[offerIdx2][0] * offers[offerIdx2][1];
          left -= offers[offerIdx2][1];
          offerIdx2++;
        }
        if (offerIdx2 < M) {
          offerCost += offers[offerIdx2][0] * left;
        } else {
          offerIdx2 = M-1;
        }
      }

      if ((offerIdx2 < M && rentsIdx >= R) || (offerIdx2 < M && offerCost > rents[rentsIdx])) {
        offerIdx = offerIdx2;
        offers[offerIdx][1] -= left;
        total += offerCost;
        j--;
        offered = false;
        if (offerIdx == M-1 && offers[M-1][1] <= 0) {
          // ran out
          offerIdx = M;
        }
      } else if (rentsIdx < R) {
        total += rents[rentsIdx];
        rentsIdx++;
        i++;
        offered = true;
      }
    }
    if (!offered) {
      offerIdx2 = offerIdx;
      offerCost = 0;
      left = cows[j];
      while (offerIdx2 < M && offers[offerIdx2][1] <= left) {
        offerCost += offers[offerIdx2][0] * offers[offerIdx2][1];
        left -= offers[offerIdx2][1];
        offerIdx2++;
      }
      if (offerIdx2 < M) {
        offerCost += offers[offerIdx2][0] * left;
      }
    }
    total += Math.max(rentsIdx < R ? rents[rentsIdx] : -1, offerCost);

    pw.println(total);

    br.close();
    pw.close();
  }
}