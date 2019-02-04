package djikstra;

import java.util.Random;

public class Djikstra {

    /**
     * Tryb A - graf spójny, nieskierowany z wagami,
     * conajmniej dwa razy więcej woerzchołków nież krawędzi
     * <p>
     * edgeCount > nodeCount * 2
     * <p>
     * a) wypisać graf zaczynając od wierzchołka 1
     * b) najkrótsza ścieżka z miedzy dowolnymi węzłami
     * <p>
     * - czas liczenia algorytmu
     * - liczba operacji dominujacych
     *
     * @param nodeCount liczba wierzchołków grafu - N(numerowane od 1 do N)
     * @param edgeCount liczba krawędzi grafu -K, spełniająca wymaganie powstania grafu gęstego(*).
     */
    public static void randomGraph(int nodeCount, int edgeCount) {
        Graph graph = new Graph(nodeCount, edgeCount);
        Utils.log(graph.toString());
        String path = graph.fastPath(0, 3);
        Utils.log(path);
    }

    /**
     * Tryb B
     *
     * @param nodeCount   zakres liczb wierzchołków grafów,
     * @param edgePercent procentowo liczba krawędzi w stosunku do maksymalnej liczby krawędzi >= 50%
     */
    public static void randomGraphs(int nodeCount, int edgePercent) {
        int edgeCount = (nodeCount * (nodeCount - 1)) * edgePercent / 200;
        Graph graph = new Graph(nodeCount, edgeCount);
        Utils.log(graph.toString());
        String path = graph.fastPath(0, new Random().nextInt(nodeCount));
        Utils.log(path);
    }

    public static void main(String[] args) {
//        randomGraph(10, 15);
        randomGraphs(10, 80);
    }
}
