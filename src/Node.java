import java.util.ArrayList;
import java.util.List;

/**
 * Node Class for Cities, Towns, Junctions etc.
 */
public class Node {

    public String id; // always has this value
    public float lat; // always has this value
    public float lon; // always has this value

    public String name; // sometimes has this value

    public Node(String id, float lat, float lon, String name) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<Way> adjacencyList = new ArrayList<>();
//
//    /**
//     * City / Town Node Constructor
//     * @param place name of the city or town to be created.
//     */
//    public Node(N place) {
//        this.place = place;
//    }
//
//    /**
//     *
//     * @param destinationNode
//     * @param distance
//     * @param speedLimit
//     */
//    public void connectToNodeDirected(Node<N> destinationNode, int distance, int speedLimit) {
//        adjacencyList.add(new Way(destinationNode, distance, speedLimit));
//    }
//
//    public void connectToNodeUndirected(Node<N> destinationNode, int distance, int speedLimit){
//        adjacencyList.add(new Way(destinationNode, distance, speedLimit));
//        destinationNode.adjacencyList.add(new Way(this, distance, speedLimit));
//    }
//
//    /**
//     * Getter for City / Town of a Node
//     * @return the City or Town name.
//     */
//    public N getPlace() {
//        return place;
//    }
}
