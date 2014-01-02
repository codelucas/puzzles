package JavaAlgo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class KingdomConnectivity {
    static HashMap<Integer, Integer> paths;
    static HashMap<Integer, ArrayList<Integer>> maps;
    static boolean[] hit;
    static boolean[] cycle;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        int N = Integer.parseInt(line.split(" ")[0]);
        int M = Integer.parseInt(line.split(" ")[1]);

        paths = new HashMap<Integer, Integer>();
        maps = new HashMap<Integer, ArrayList<Integer>>();

        hit = new boolean[N + 1];
        cycle = new boolean[N + 1];

        // Initialize the children map
        for (int i = 0; i < M; i++) {
            String[] ll = in.nextLine().split(" ");
            int u = Integer.parseInt(ll[0]);
            int v = Integer.parseInt(ll[1]);
            if (maps.get(u) == null) {
                ArrayList<Integer> a = new ArrayList<Integer>();
                a.add(v);
                maps.put(u, a);
            } else {
                ArrayList<Integer> newlist = maps.get(u);
                newlist.add(v);
                maps.put(u, newlist);
            }
        }
        in.close();

        hit[1] = true;
        dpTraverse(1, N);
        System.out.println(paths.get(1));
    }

    public static int dpTraverse(int start, int dest) {
        if (start == dest)
            return 1;

        if (maps.get(start) == null)
            return 0;

        for (int i = 0; i < maps.get(start).size(); i++) {
            int edge = maps.get(start).get(i);

            if (hit[edge]) {
                cycle[edge] = true;
                continue;
            }

            hit[edge] = true;
            int val;
            if (paths.get(edge) != null)
                val = paths.get(edge);

            else
                val = dpTraverse(edge, dest);

            if (paths.get(start) == null)
                paths.put(start, 0);

            int tmp = (val + paths.get(start)) % 1000000000;
            paths.put(start, tmp);

            if (paths.get(start) > 0 && cycle[start]) {
                System.out.println("INFINITE PATHS");
                System.exit(2);
            }
            hit[edge] = false;
        }
        return paths.get(start);
    }
}