import java.util.ArrayList;

/**
 * Node Class for Cities, Towns, Junctions etc.
 */
public class Node {

    private String id; // always has this value
    private float lat; // always has this value
    private float lon; // always has this value
    private String name; // sometimes has this value

    private double dijkstraValue = Integer.MAX_VALUE; // Used for the Dijkstra Algorithm. All Nodes Start at Infinity

    // Used for the new strategy
    private ArrayList<Link> adjLinks = new ArrayList();

    // Used for the DA Twin Links
    private ArrayList<Link> adjTwinLinks = new ArrayList<>();

    public Node() {

    }

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

    public double getDijkstraValue() {
        return dijkstraValue;
    }

    public void setDijkstraValue(double dijkstraValue) {
        this.dijkstraValue = dijkstraValue;
    }

    public ArrayList<Link> getAdjLinks() {
        return adjLinks;
    }

    public void setAdjLinks(ArrayList<Link> adjLinks) {
        this.adjLinks = adjLinks;
    }

    public ArrayList<Link> getAdjTwinLinks() {
        return adjTwinLinks;
    }

    public void setAdjTwinLinks(ArrayList<Link> adjTwinLinks) {
        this.adjTwinLinks = adjTwinLinks;
    }
}