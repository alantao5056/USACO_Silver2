import java.util.*;
import java.io.PrintWriter;

public class Flights {
  static int N;
  static int M;
  static int minSteps = Integer.MAX_VALUE;

  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("flights.in"));
    // PrintWriter pw = new PrintWriter("1.out");
    // Scanner sc = new Scanner(System.in);

    N = sc.nextInt();
    M = sc.nextInt();
    int src = sc.nextInt()-1;
    int dst = sc.nextInt()-1;

    City[] cities = new City[N];

    for (int i = 0; i < N; i++) {
      cities[i] = new City(i);
    }

    for (int i = 0; i < M; i++) {
      cities[sc.nextInt()-1].next.add(cities[sc.nextInt()-1]);
    }

    // bfs
    getMinFlights(cities[dst], cities[src], 0, new boolean[N]);

    System.out.println(minSteps);
    
    // pw.close();
    sc.close();
  }

  private static void getMinFlights(City end, City curCity, int steps, boolean[] visited) {
    if (curCity == end) {
      minSteps = Math.min(minSteps, steps);
      return;
    }
    for (City c : curCity.next) {
      if (!visited[c.index]) {
        boolean[] visited2 = Arrays.copyOf(visited, N);
        visited2[c.index] = true;
        getMinFlights(end, c, steps+1, visited2);
      }
    }
  }

  public static class City {
    int index;
    boolean visited = false;
    List<City> next = new ArrayList<>();

    public City(int index) {
      this.index = index;
    }
  }
}