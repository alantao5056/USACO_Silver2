import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class GcdAlanTao {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("gcd.in"));
    // Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();
    
    int[] nums = new int[N];
    for (int i = 0; i < N; i++) {
      nums[i] = sc.nextInt();
    }

    // brute force?
    Map<Integer, Integer> hash = new HashMap<>();
    for (int i = 0; i < N; i++) {
      for (int j = i+1; j < N; j++) {
        int gcd = get_gcd(nums[i], nums[j]);

        if (hash.containsKey(gcd)) {
          hash.put(gcd, hash.get(gcd)+1);
        } else {
          hash.put(gcd, 1);
        }
      }
    }

    int maxValue = 0;
    int maxKey = 0;
    for (Map.Entry<Integer, Integer> entry : hash.entrySet()) {
      int key = entry.getKey();
      int value = entry.getValue();
      if (value > maxValue) {
        maxValue = value;
        maxKey = key;
      }
    }

    // maxKey is target

    int count = 0;
    for (int n : nums) {
      if (n % maxKey != 0) {
        count++;
      }
    }

    System.out.println(count);

    sc.close();
  }

  private static int get_gcd(int m, int n) {
    if(m==0 || n==0) return m+n;
    return get_gcd(n, m%n);
  }
}
