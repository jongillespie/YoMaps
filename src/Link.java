import java.util.ArrayList;

/**
 * Class for Links representing Road Sections
 * p.52 in NOTES
 */
public class Link {

    private int id; // TODO not sure yet if this will be used
    private String name;
    private ArrayList<Node> adjNodesList;
    private double distance; // can store the calculation if required.
    private int speedLimit;

    // FOR TWINING PROCESS
    private int twinID;
    private Node destinationNode;
    private int quickness;

    /**
     * Edge Constructor - creates the instance of a roadway
     * @param name of the Road
     * @param speedLimit of the Road Section
     */
    public Link(String name, ArrayList<Node> adjNodesList, int speedLimit){
        this.name = name;
        this.adjNodesList = adjNodesList;
        this.speedLimit = speedLimit;
    }

    /**
     * Constructor for Links when used as one way edges in DA's Algo
     * @param name
     * @param destinationNode
     * @param speedLimit
     */
    public Link(String name, Node destinationNode, double distance, int speedLimit, int quickness){
        this.name = name;
        this.destinationNode = destinationNode;
        this.distance = distance;
        this.speedLimit = speedLimit;
        this.quickness = quickness;
    }

    /**
     * Get the adjacent Nodes List.
     * @return
     */
    public ArrayList<Node> getAdjNodesList() {
        return adjNodesList;
    }

    /**
     * Set the adjacent nodes list.
     * @param adjNodesList
     */
    public void setAdjNodesList(ArrayList<Node> adjNodesList) {
        this.adjNodesList = adjNodesList;
    }

    /**
     * Returns the name of the Road Section
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the Road Section
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the ID of the Link
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Set the ID of the link.
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the distance of the Road Section
     * @return
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Sets the Distance of the Road Section
     * @param distance
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * Gets the Speed Limit of the Road Section
     * @return
     */
    public int getSpeedLimit() {
        return speedLimit;
    }

    /**
     * Sets the Speed Limit of the Road Section
     * @param speedLimit
     */
    public void setSpeedLimit(int speedLimit) {
        this.speedLimit = speedLimit;
    }

    /**
     * Get the Twin Link ID.
     * @return
     */
    public int getTwinID() {
        return twinID;
    }

    /**
     * Set the Twin Link ID
     * @param twinID
     */
    public void setTwinID(int twinID) {
        this.twinID = twinID;
    }

    /**
     * Get the destination node from the link.
     * @return
     */
    public Node getDestinationNode() {
        return destinationNode;
    }

    /**
     * Set the destination node for the link.
     * @param destinationNode
     */
    public void setDestinationNode(Node destinationNode) {
        this.destinationNode = destinationNode;
    }

    /**
     * Get the quickness value.
     * @return
     */
    public int getQuickness() {
        return quickness;
    }

    /**
     * Set the Quickness Value.
     * @param quickness
     */
    public void setQuickness(int quickness) {
        this.quickness = quickness;
    }
}
