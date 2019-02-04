package djikstra;

import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("all")
public class Graph {
    private static final int MAX_WEIGHT = 9;
    private static final Comparator<Pair<Integer, Integer>> PAIR_COMPARATOR = (a, b) -> {
        int x = a.getValue();
        int y = b.getValue();
        return x < y ? (x == y ? 0 : -1) : 1;
    };
    private static final Comparator<Vertex> VERTEX_COMPARATOR = (a, b) -> {
        int x = a.getHeight();
        int y = b.getHeight();
        return x < y ? (x == y ? 0 : -1) : 1;
    };

    private int n;
    private int halfN;
    private int edgeCount;
    private int edge[][];
    private Integer neighbors[][];
    private Vertex[] vertices;

    /**
     * k >=n-1
     *
     * @param n
     * @param edgeCount
     */
    public Graph(int n, final int edgeCount) {
        this.n = n;
        this.edgeCount = edgeCount;
        this.halfN = n / 2;
        Utils.log("[Graph] n: " + n + " edgeCount: " + edgeCount);
        verifyEdge(n, edgeCount);
        this.edge = new int[n][n];

        Random random = new Random();
        int[] visited = new int[n];

        /* Graph minimum */
        int weigh;
        int current = 0;
        for (int i = 0; i < n - 1; ) {
            int selected = random.nextInt(n);
            if (selected != current && visited[selected] == 0) {
                weigh = random.nextInt(MAX_WEIGHT) + 1;
                Utils.log("[Edge m] selected: " + current + " -> " + selected + " weight: " + weigh);
                ++visited[current];
                ++visited[selected];
                edge[selected][current] = weigh;
                edge[current][selected] = weigh;
                current = selected;
                ++i;
            }
        }
        weigh = random.nextInt(MAX_WEIGHT) + 1;
        Utils.log("[Edge m] selected: " + current + " -> " + 0 + " weight: " + weigh);
        edge[0][current] = weigh;   // ponowne odwiedzenei pierwszego
        edge[current][0] = weigh;
        ++visited[current];
        ++visited[0];


        /* Graph fulfill other edges */
        for (int i = n - 1; i < edgeCount; ) {
            int from = random.nextInt(n);
            int to = random.nextInt(n);
            if (from != to && edge[from][to] == 0 && edge[to][from] == 0) {
                weigh = random.nextInt(MAX_WEIGHT) + 1;
                Utils.log("[Edge o] selected: " + from + " -> " + to + " weight: " + weigh);
                edge[from][to] = weigh;
                edge[to][from] = weigh;
                ++visited[from];
                ++visited[to];
                i++;
            }
        }

        /* Init neighbors */
        neighbors = initNeighbors(n, edge);
        showNeighbors();
    }

    /**
     * [Graph] n: 5 edgeCount: 6
     * [Edge m] selected: 0 -> 2 weight: 3
     * [Edge m] selected: 2 -> 3 weight: 9
     * [Edge m] selected: 3 -> 1 weight: 2
     * [Edge m] selected: 1 -> 4 weight: 8
     * [Edge m] selected: 4 -> 0 weight: 9
     * [Edge o] selected: 4 -> 2 weight: 7
     * [Edge o] selected: 4 -> 3 weight: 6
     */
    public Graph() {
        this(5, new int[][]{
                {0, 0, 3, 0, 9,},
                {0, 0, 0, 2, 8,},
                {3, 0, 0, 9, 7,},
                {0, 2, 9, 0, 6,},
                {9, 8, 7, 6, 0,},
        });
    }

    public Graph(int n, int[][] edge) {
        this.n = n;
        this.edge = edge;
        neighbors = initNeighbors(n, edge);
        showNeighbors();
    }

    private Integer[][] initNeighbors(int n, int[][] edge) {
        Integer[][] result = new Integer[n][];
        for (int i = 0; i < n; i++) {
            TreeSet<Pair<Integer, Integer>> set = new TreeSet<Pair<Integer, Integer>>(PAIR_COMPARATOR);
            for (int j = 0; j < n; j++) {
                int value = edge[i][j];
                if (value != 0) {
                    set.add(new Pair<>(j, value));
                }
            }
            result[i] = set.stream()
                    .map(Pair::getKey)
                    .collect(Collectors.toList())
                    .toArray(new Integer[0]);
        }
        vertices = new Vertex[n];
        for (int i = 0; i < n; i++) {
            Vertex vertex = new Vertex(i);
            vertex.setNeighbors(result[i], edge[i]);
            vertices[i] = vertex;
        }
        return result;
    }

    private void verifyEdge(int n, int edgeCount) {
        if (edgeCount <= n) {
            throw new IllegalStateException("Invalid parameter n: " + n + " edgeCount:" + edgeCount);
        }
    }

    public String fastPathOld(int from, int to) {
        Date start = new Date();
        Utils.log("[Path] Start from: " + from + " to: " + to);
        List<Integer> steps = new ArrayList<Integer>(n);
        int[] dest = Utils.createLinearArray(edgeCount, Integer.MAX_VALUE);             // minimal path to destination
        int[] toDest = Utils.createLinearArray(edgeCount, Integer.MAX_VALUE);               // vertex before destination

        PriorityQueue<Pair<Integer, Integer>> queue = new PriorityQueue<>(PAIR_COMPARATOR);
        queue.add(new Pair<>(from, 0));
        int addCounter = 1, pollCounter = 0;
        for (; !queue.isEmpty(); pollCounter++) {
            Pair<Integer, Integer> lower = queue.poll();
            Integer current = lower.getKey();
            Integer currentHeight = dest[current] != Integer.MAX_VALUE ? dest[current] : lower.getValue();
            Utils.log("[Path] poll " + current + " (" + currentHeight + ")");
            dest[from] = 0;
            for (int i = 0; i < neighbors[current].length; i++) {
                Integer next = neighbors[current][i];
                int newHeigh = edge[current][next] + currentHeight;
                if (dest[next] == Integer.MAX_VALUE) {
                    dest[next] = newHeigh;
                    toDest[next] = current;
                    queue.add(new Pair<>(next, dest[next]));
                    addCounter++;
                    Utils.log("[add] " + current + " -> " + next + " v:" + dest[next]);
                } else if (dest[next] > newHeigh) {
                    Utils.log("[relax] vertex: " + next + " v: " + newHeigh + " old: " + dest[next]);
                    dest[next] = newHeigh;
                    toDest[next] = current;
                }
                if (next == to) {
                    Utils.log("[dest] vertex: " + next + " path: " + dest[next]);
                }
            }
        }
        Date stop = new Date();
        Utils.log("[Path] Finish - " + (stop.getTime() - start.getTime()) + " millis");
        Utils.log("[Path] Finish - add: " + addCounter + " poll: " + pollCounter);
        StringBuilder points = new StringBuilder("[Points] ").append(to);
        for (int back = to; back != from; ) {
            System.out.printf(".");
            back = toDest[back];
            points.append(" -> ").append(back);
        }
        return points.toString() + " = " + dest[to];
    }

    public String fastPath(int from, int to) {
        Date start = new Date();
        Utils.log("[Path] Start from: " + from + " to: " + to);
        int[] dest = Utils.createLinearArray(n, Integer.MAX_VALUE);             // minimal path to destination
        int[] toDest = Utils.createLinearArray(n, Integer.MAX_VALUE);           // vertex before destination

        /* All vertex with weight */
        vertices[from].setHeight(0);
        PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>(VERTEX_COMPARATOR);
        for (Vertex v : vertices) {
            queue.add(v);
        }
        int relaxCounter = 1, setCounter = 0;
        dest[from] = 0;
        toDest[from] = 0;
        for (; !queue.isEmpty(); setCounter++) {
            queue = new PriorityQueue<>(queue);
            Vertex lower = queue.poll();
            Integer current = lower.getNumber();
//            Integer currentHeight = dest[current] != Integer.MAX_VALUE ? dest[current] : lower.getHeight();
            Integer currentHeight = lower.getHeight();
            Utils.log("[Path] poll " + current + " (" + currentHeight + ")");

            for (Pair<Integer, Integer> next : lower.getNeighbors()) {
                Integer nextKey = next.getKey();
                Integer edgeHeigh = next.getValue();
                if (vertices[nextKey].getHeight() == Integer.MAX_VALUE) {
                    setCounter++;
                    vertices[nextKey].setHeight(edgeHeigh + lower.getHeight());  // new aclual discance from
                    dest[nextKey] = edgeHeigh + lower.getHeight();
                    toDest[nextKey] = current;
                } else if (vertices[nextKey].getHeight() > currentHeight + edgeHeigh) {
                    relaxCounter++;
                    vertices[nextKey].setHeight(currentHeight + edgeHeigh);  // new aclual discance from
                    dest[nextKey] = edgeHeigh + lower.getHeight();
                    toDest[nextKey] = current;
                }
            }
        }
        Date stop = new Date();
        Utils.log("[Path] Finish - " + (stop.getTime() - start.getTime()) + " millis");
        Utils.log("[Path] Finish - relax: " + relaxCounter + " set: " + setCounter);
        StringBuilder points = new StringBuilder("[Points] ").append(to);
        for (int back = to; back != from; ) {
            System.out.printf(".");
            back = toDest[back];
            points.append(" -> ").append(back);
        }
        return points.toString() + " = " + vertices[to].getHeight();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("\nint[][] graph = {\n");
        for (int i = 0; i < n; i++) {
            result.append(" { ");
            for (int j = 0; j < n; j++) {
                result.append(edge[i][j]).append(", ");
            }
            result.append("},\n");
        }
        result.append("}");

        return result.toString();
    }

    public void showNeighbors() {
        StringBuilder result = new StringBuilder("\n");
        for (int i = 0; i < neighbors.length; i++) {
            result.append("[" + i + "] ");
            for (int j = 0; j < neighbors[i].length; j++) {
                result.append(neighbors[i][j])
                        .append(" ");
            }
            result.append("\n");
        }
        Utils.log("Neighbors:\n" + result.toString());
    }

    public static void main(String[] args) {
//        Graph graph = new Graph(5, 6);
        Graph graph = new Graph();
        System.out.printf(graph.toString());
        String path = graph.fastPath(3, 0);
        System.out.println("[Result] " + path);
    }
}
