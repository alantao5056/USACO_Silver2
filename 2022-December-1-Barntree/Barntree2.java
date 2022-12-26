import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Barntree2 {
  static StreamTokenizer st;
  static int N;
  static int target;
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
    MyLinkedList answer = recursiveNeeds(cows[0], null);

    pw.println(answer.size);

    Node node = answer.head;

    while (node != null) {
      pw.print(node.from);
      pw.print(" ");
      pw.print(node.to);
      pw.print(" ");
      pw.println(node.value);
      node = node.next;
    }

    br.close();
    pw.close();
  }

  private static MyLinkedList recursiveNeeds(Cow cow, Cow parent) {
    long totalNeed = cow.value - target;
    MyLinkedList steps = new MyLinkedList(cow.id);
    List<MyLinkedList> giveSteps = new ArrayList<>();
    if (parent == null || cow.nbs.size() > 1) {
      for (Cow nb : cow.nbs) {
        if (nb != parent) {
          MyLinkedList nbLL = recursiveNeeds(nb, cow);
          totalNeed += nbLL.need;
          if (nbLL.need > 0) {
            steps.insert(nbLL);
            steps.add(nb.id+1, cow.id+1, nbLL.need);
          } else {
            giveSteps.add(nbLL);
          }
        }
      }

      for (MyLinkedList l : giveSteps) {
        steps.add(cow.id+1, l.id+1, l.need*-1);
        steps.append(l);
      }
    }

    steps.need = totalNeed;
    return steps;
  }

  private static class Cow {    
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

  public static class MyLinkedList {
    public Node head;
    private Node tail;
    private int size;
    public long need;
    public int id;

    public MyLinkedList(int id) {
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.id = id;
    }

    public void add(int from, int to, long v) {
        Node newNode = new Node(from, to, v);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void append(MyLinkedList other) {
        if (other.head == null) {
            return;
        }
        if (head == null) {
            head = other.head;
            tail = other.tail;
        } else {
            tail.next = other.head;
            tail = other.tail;
        }
        size += other.size;
    }

    public void insert(MyLinkedList other) {
      if (other.head == null) {
          return;
      }
      if (head == null) {
        head = other.head;
        tail = other.tail;
        size = other.size;
      } else {
        other.tail.next = head;
        head = other.head;
        size += other.size;
      }
    }
  }

  public static class Node {
    private int from;
    private int to;
    private long value;
    private Node next;

    public Node(int from, int to, long value) {
      this.from = from; 
      this.to = to ;
        this.value = value;
        this.next = null;
    }
  }
}
