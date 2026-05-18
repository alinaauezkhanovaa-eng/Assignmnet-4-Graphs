import java.util.*;

public class DijkstraSearch<V> extends Search<V> {
    private final Map<Vertex<V>, Double> distTo;
    private final PriorityQueue<VertexNode<V>> priorityQueue;

    private static class VertexNode<V> implements Comparable<VertexNode<V>> {
        Vertex<V> vertex;
        double distance;

        VertexNode(Vertex<V> vertex, double distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(VertexNode<V> o) {
            return Double.compare(this.distance, o.distance);
        }
    }

    public DijkstraSearch(WeightedGraph<V> graph, V sourceData) {
        super(graph.getVertex(sourceData));
        this.distTo = new HashMap<>();
        this.priorityQueue = new PriorityQueue<>();

        if (this.source != null) {
            for (Vertex<V> v : graph.getVertices()) {
                distTo.put(v, Double.POSITIVE_INFINITY);
            }
            distTo.put(source, 0.0);
            priorityQueue.add(new VertexNode<>(source, 0.0));

            dijkstra();
        }
    }

    private void dijkstra() {
        while (!priorityQueue.isEmpty()) {
            VertexNode<V> node = priorityQueue.poll();
            Vertex<V> v = node.vertex;

            if (marked.contains(v)) continue;
            marked.add(v);

            for (Map.Entry<Vertex<V>, Double> entry : v.getAdjacentVertices().entrySet()) {
                Vertex<V> w = entry.getKey();
                double weight = entry.getValue();

                if (distTo.get(w) > distTo.get(v) + weight) {
                    distTo.put(w, distTo.get(v) + weight);
                    edgeTo.put(w, v);
                    priorityQueue.add(new VertexNode<>(w, distTo.get(w)));
                }
            }
        }
    }

    public double getDistanceTo(V destData) {
        Vertex<V> target = edgeTo.keySet().stream()
                .filter(v -> v.getData().equals(destData))
                .findFirst()
                .orElse(null);

        if (target == null && source.getData().equals(destData)) return 0.0;
        return target != null ? distTo.get(target) : Double.POSITIVE_INFINITY;
    }
}
