import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.valueOf;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        String[] parts = reader.readLine().split(" ");
        int[] delivery = new int[n];
        for (int i = 0; i < n; i++) {
            delivery[i] = Integer.parseInt(parts[i]);
        }
        parts = reader.readLine().split(" ");
        int[] parent = new int[n];
        //List<Integer> pallets = new ArrayList<>();
        Set<Integer> pallets = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            parent[i] = Integer.parseInt(parts[i]);
            if (parent[i] == 0) {
                pallets.add(i + 1);
            }
        }
        int k = Integer.parseInt(reader.readLine());
        Set<Integer> set = new HashSet<>(k);
        if (k != 0) {
            parts = reader.readLine().split(" ");
            for (int i = 0; i < k; i++) {
                set.add(Integer.parseInt(parts[i]));
            }
        }
        if (n == k) {
            System.out.println(0);
        } else {
            solve(set, delivery, parent, n, pallets);
        }
        reader.close();
    }

    private static void solve(Set<Integer> set, int[] delivery, int[] parent, int n, Set<Integer> pallets) {
        Set<Integer> badPallets = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int num = i + 1;
            if (set.contains(delivery[i])) {
                int p = parent[i];
                if (p == 0) {
                    badPallets.add(num);
                } else {
                    while (true) {
                        if (p - 1 >= n) {
                            break;
                        }
                        if (parent[p - 1] == 0) {
                            break;
                        } else {
                            p = parent[p - 1];
                        }
                    }
                    if (!badPallets.contains(p)) {
                        badPallets.add(p);
                    }
                }
            }
        }
        pallets.removeAll(badPallets);
        StringBuilder sb = new StringBuilder();
        sb.append(pallets.size());
        sb.append("\n");
        for (Integer p : pallets) {
            sb.append(p);
            sb.append("\n");
        }
        System.out.println(sb);
    }
}

