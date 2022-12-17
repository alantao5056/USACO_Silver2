import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Mootube {
  static StreamTokenizer st;
  static int N;
  static int Q;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("mootube.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("mootube.out"));
    // PrintWriter pw = new PrintWriter(System.out);
    N = nextInt();
    Q = nextInt();
    
    // solve
    Video[] videos = new Video[N];
    for (int i = 0; i < N-1; i++) {
      int a = nextInt()-1;
      int b = nextInt()-1;
      int weight = nextInt();

      if (videos[a] == null) {
        videos[a] = new Video(a);
      }
      if (videos[b] == null) {
        videos[b] = new Video(b);
      }

      videos[a].nbs.add(videos[b]);
      videos[a].weight.add(weight);
      videos[b].nbs.add(videos[a]);
      videos[b].weight.add(weight);
    }

    for (int i = 0; i < Q; i++) {
      int K = nextInt();
      int start = nextInt()-1;

      // dfs
      ArrayDeque<Video> q = new ArrayDeque<>();
      ArrayDeque<Video> previous = new ArrayDeque<>();
      q.add(videos[start]);
      previous.add(new Video(-1));

      int count = 0;

      while (!q.isEmpty()) {
        Video curVideo = q.poll();
        Video preVideo = previous.poll();
        count++;
        
        for (int j = 0; j < curVideo.nbs.size(); j++) {
          if (curVideo.nbs.get(j) != preVideo && curVideo.weight.get(j) >= K) {
            // can add
            q.add(curVideo.nbs.get(j));
            previous.add(curVideo);
          }
        }
      }

      pw.println(count-1);
    }

    br.close();
    pw.close();
  }

  private static class Video {
    int id;
    List<Video> nbs = new ArrayList<>();
    List<Integer> weight = new ArrayList<>();

    public Video(int id) {
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