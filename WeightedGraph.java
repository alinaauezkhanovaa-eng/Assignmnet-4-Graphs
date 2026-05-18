import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

public class WeightedGraph<V> {
    private final boolean undirected;
    private final Map<V, Vertex<V>> vertices;

    public WeightedGraph() {
        this(true);
    }

    public WeightedGraph(boolean undirected) {
        this.undirected = undirected;
        this.vertices = new HashMap<>();
    }

    public void addVertex(V data) {
        vertices.putIfAbsent(data, new Vertex<>(data));
    }

    public void addEdge(V sourceData, V destData, double weight) {
        addVertex(sourceData);
        addVertex(destData);

        Vertex<V> source = vertices.get(sourceData);
        Vertex<V> dest = vertices.get(destData);

        source.addAdjacentVertex(dest, weight);
        if (undirected) {
            dest.addAdjacentVertex(source, weight);
        }
    }

    public Vertex<V> getVertex(V data) {
        return vertices.get(data);
    }

    public Collection<Vertex<V>> getVertices() {
        return vertices.values();
    }
}
