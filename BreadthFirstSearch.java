import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch<V> extends Search<V> {

    public BreadthFirstSearch(WeightedGraph<V> graph, V sourceData) {
        super(graph.getVertex(sourceData));
        if (this.source != null) {
            bfs();
        }
    }

    private void bfs() {
        Queue<Vertex<V>> queue = new LinkedList<>();
        marked.add(source);
        queue.add(source);

        while (!queue.isEmpty()) {
            Vertex<V> v = queue.poll();

            for (Vertex<V> vertex : v.getAdjacentVertices().keySet()) {
                if (!marked.contains(vertex)) {
                    edgeTo.put(vertex, v);
                    marked.add(vertex);
                    queue.add(vertex);
                }
            }
        }
    }
}