import java.util.ArrayList;

/**
 * Node Class for Junctions and Way Breaks.
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

    /**
     * Empty constructor used in the XML Parse.
     */
    public Node() { }

    /**
     * Node Constructor
     * @param id
     * @param lat
     * @param lon
     * @param name
     */
    public Node(String id, float lat, float lon, String name) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.name = name;
    }

    /**
     * Returns the ID of the Node
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the Node ID
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the Latitude of the Node
     * @return
     */
    public float getLat() {
        return lat;
    }

    /**
     * Sets the Latitude of the Node
     * @param lat
     */
    public void setLat(float lat) {
        this.lat = lat;
    }

    /**
     * Gets the Longitude of the Node.
     * @return
     */
    public float getLon() {
        return lon;
    }

    /**
     * Sets the Longitude of the Node
     * @param lon
     */
    public void setLon(float lon) {
        this.lon = lon;
    }

    /**
     * Gets the name of the Node if one exists.
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the Node
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the DA value.
     * @return
     */
    public double getDijkstraValue() {
        return dijkstraValue;
    }

    /**
     * Sets the DA value.
     * @param dijkstraValue
     */
    public void setDijkstraValue(double dijkstraValue) {
        this.dijkstraValue = dijkstraValue;
    }

    /**
     * Gets the Link Adjacency List.
     * @return
     */
    public ArrayList<Link> getAdjLinks() {
        return adjLinks;
    }

    /**
     * Sets the Link Adjacency List.
     * @param adjLinks
     */
    public void setAdjLinks(ArrayList<Link> adjLinks) {
        this.adjLinks = adjLinks;
    }

    /**
     * Gets the One Directional Twin Link List
     * @return
     */
    public ArrayList<Link> getAdjTwinLinks() {
        return adjTwinLinks;
    }

    /**
     * Sets the one directional twin link list.
     * @param adjTwinLinks
     */
    public void setAdjTwinLinks(ArrayList<Link> adjTwinLinks) {
        this.adjTwinLinks = adjTwinLinks;
    }
}