import java.util.*;

public abstract class Search<V> {
    protected Set<Vertex<V>> marked;
    protected Map<Vertex<V>, Vertex<V>> edgeTo;
    protected final Vertex<V> source;

    public Search(Vertex<V> source) {
        this.source = source;
        this.marked = new HashSet<>();
        this.edgeTo = new HashMap<>();
    }

    public boolean hasPathTo(Vertex<V> v) {
        return marked.contains(v);
    }
    public Iterable<Vertex<V>> pathTo(Vertex<V> v) {
        if (!hasPathTo(v)) return null;

        LinkedList<Vertex<V>> path = new LinkedList<>();
        for (Vertex<V> x = v; x != null; x = edgeTo.get(x)) {
            path.addFirst(x);
        }
        return path;
    }
}