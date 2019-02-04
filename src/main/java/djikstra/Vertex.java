package djikstra;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Vertex {

    private final int number;
    private int height;
    private List<Pair<Integer, Integer>> neighbors = new ArrayList<>();

    public Vertex(int number) {
        this.number = number;
        this.height = Integer.MAX_VALUE;
    }

    public int getNumber() {
        return number;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setNeighbors(Integer[] neighbors, int[] weight){
        for (int i = 0; i < neighbors.length; i++) {
            int point = neighbors[i];
            this.neighbors.add(new Pair<>(point, weight[point]));
        }
    }

    public void addNeighbors(int number, int weight){
        neighbors.add(new Pair<>(number, weight));
    }

    public List<Pair<Integer, Integer>> getNeighbors() {
        return neighbors;
    }
}
