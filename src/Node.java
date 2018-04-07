import java.util.ArrayList;
import java.util.List;

/**
 * Abstract Node Class for Cities and Towns.
 * @param <N>
 */
public class Node<N> {

    public N place;
    public List<Edge> adjacencyList = new ArrayList<>();

    /**
     * City / Town Node Constructor
     * @param place name of the city or town to be created.
     */
    public Node(N place) {
        this.place = place;
    }

    /**
     *
     * @param destinationNode
     * @param distance
     * @param speedLimit
     */
    public void connectToNodeDirected(Node<N> destinationNode, int distance, int speedLimit) {
        adjacencyList.add(new Edge(destinationNode, distance, speedLimit));
    }

    public void connectToNodeUndirected(Node<N> destinationNode, int distance, int speedLimit){
        adjacencyList.add(new Edge(destinationNode, distance, speedLimit));
        destinationNode.adjacencyList.add(new Edge(this, distance, speedLimit));
    }

    /**
     * Getter for City / Town of a Node
     * @return the City or Town name.
     */
    public N getPlace() {
        return place;
    }
}
