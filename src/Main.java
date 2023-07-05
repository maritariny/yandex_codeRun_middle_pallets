import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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
        TreeSet<Integer> pallets = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            parent[i] = Integer.parseInt(parts[i]);
            if (parent[i] == 0) {
                pallets.add(i + 1);
            }
        }
        int k = Integer.parseInt(reader.readLine());
        HashSet<Integer> notDelivery = new HashSet<>(k);
        if (k != 0) {
            parts = reader.readLine().split(" ");
            for (int i = 0; i < k; i++) {
                notDelivery.add(Integer.parseInt(parts[i]));
            }
        }
        if (n == k) {
            System.out.println(0);
        } else {
            solve(notDelivery, delivery, parent, n, pallets);
        }
        reader.close();
    }

    private static void solve(HashSet<Integer> notDelivery, int[] delivery, int[] parent, int n, TreeSet<Integer> pallets) {
        Set<Integer> badBoxAndPallet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int num = i + 1;
            if (notDelivery.contains(delivery[i])) {
                if (badBoxAndPallet.contains(num)) {
                    continue;
                }
                int p = parent[i];
                if (p == 0) {
                    pallets.remove(num);
                } else {
                    boolean needRemove = true;
                    while (true) {
                        if (p - 1 >= n) {
                            break;
                        }
                        if (parent[p - 1] == 0) {
                            break;
                        } else {
                            p = parent[p - 1];
                            if (badBoxAndPallet.contains(p)) {
                                needRemove = false;
                                break;
                            } else {
                                badBoxAndPallet.add(p);
                            }
                        }
                    }
                    if (needRemove) {
                        pallets.remove(p);
                    }
                }
            }
        }

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