public class Main {
    public static void main(String[] args) {
        WeightedGraph<String> fashionGraph = new WeightedGraph<>(true);

        // Building the NY Fashion Network with distances/travel times between hubs
        fashionGraph.addEdge("JFK Airport", "Manhattan Hub", 45.0);
        fashionGraph.addEdge("Manhattan Hub", "Times Square Studio", 15.0);
        fashionGraph.addEdge("Manhattan Hub", "Brooklyn Runway", 25.0);
        fashionGraph.addEdge("Times Square Studio", "Soho Fashion District", 10.0);
        fashionGraph.addEdge("Brooklyn Runway", "Met Gala Venue", 30.0);
        fashionGraph.addEdge("Soho Fashion District", "Met Gala Venue", 20.0);
        fashionGraph.addEdge("Soho Fashion District", "Model Agency HQ", 12.0);
        fashionGraph.addEdge("Model Agency HQ", "Met Gala Venue", 15.0);

        System.out.println("=== Testing BFS (BreadthFirstSearch) ===");
        BreadthFirstSearch<String> bfs = new BreadthFirstSearch<>(fashionGraph, "JFK Airport");
        Vertex<String> targetBfs = fashionGraph.getVertex("Met Gala Venue");

        System.out.print("BFS Path from JFK Airport to Met Gala Venue: ");
        if (bfs.hasPathTo(targetBfs)) {
            for (Vertex<String> v : bfs.pathTo(targetBfs)) {
                System.out.print(v + " -> ");
            }
            System.out.println("DONE");
        } else {
            System.out.println("Path not found");
        }

        System.out.println("\n=== Testing Dijkstra (DijkstraSearch) ===");
        DijkstraSearch<String> dijkstra = new DijkstraSearch<>(fashionGraph, "JFK Airport");
        Vertex<String> targetDijkstra = fashionGraph.getVertex("Met Gala Venue");

        System.out.print("Shortest Dijkstra Path from JFK Airport to Met Gala Venue: ");
        if (dijkstra.hasPathTo(targetDijkstra)) {
            for (Vertex<String> v : dijkstra.pathTo(targetDijkstra)) {
                System.out.print(v + " -> ");
            }
            System.out.println("DONE");
            System.out.println("Total Travel Time: " + dijkstra.getDistanceTo("Met Gala Venue") + " mins");
        } else {
            System.out.println("Path not found");
        }

        System.out.println("\n=== Additional Route Check (Soho to Model Agency) ===");
        DijkstraSearch<String> sohoDijkstra = new DijkstraSearch<>(fashionGraph, "Soho Fashion District");
        System.out.println("Distance from Soho to Model Agency HQ: " + sohoDijkstra.getDistanceTo("Model Agency HQ") + " mins");
    }
}