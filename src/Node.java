import java.util.ArrayList;

/**
 * Node Class for Cities, Towns, Junctions etc.
 */
public class Node {

    public String id; // always has this value
    public float lat; // always has this value
    public float lon; // always has this value
    public String name; // sometimes has this value

    // Used for the new strategy
    public ArrayList<Link> adjLinks = new ArrayList();

    public Node(){

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

    public ArrayList<Link> getAdjLinks() {
        return adjLinks;
    }

    public void setAdjLinks(ArrayList<Link> adjlinks) {
        this.adjLinks = adjlinks;
    }
}
