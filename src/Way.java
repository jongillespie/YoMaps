import java.util.ArrayList;

/**
 * Class for Way representing Roads
 * p.52 in NOTES but Structured according to the Open Maps Data Format.
 */
public class Way {

    public String id;
    public String name = " ";    // k=name // Made it a " space " as it cannot be null.
    public String highwayType;  // k=highway
    public int maxSpeed = 50;   // k=maxspeed >> set to 50 in case values are 0.
//    public ArrayList<Node> nodes;

    public ArrayList<Double> nodes = new ArrayList<>();

    /**
     * Empty constructor used in the XML Data processing.
     */
    public Way(){ }

    /**
     * Way Constructor - creates the instance of a roadway
     * @param id the id of the Way
     * @param name of the Way (Road)
     * @param highwayType type of the highway
     * @param maxSpeed max speed of the section
     * @param nodes the list of nodes along the way
     */
    public Way(String id, String name, String highwayType, int maxSpeed, ArrayList<Double> nodes) {
        this.id = id;
        this.name = name;
        this.highwayType = highwayType;
        this.maxSpeed = maxSpeed;
        this.nodes = nodes;
    }

    /**
     * Gets the ID of the Way
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the Way
     * @param id
     */
    public void setId(String id) {
        this.id = id;
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
     * Returns the type of Way
     * @return
     */
    public String getHighwayType() {
        return highwayType;
    }

    /**
     * Sets the type of the Way
     * @param highwayType
     */
    public void setHighwayType(String highwayType) {
        this.highwayType = highwayType;
    }

    /**
     * Gets the Speed Limit of the Road Section
     * @return
     */
    public int getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * Sets the Speed Limit of the Road Section
     * @param maxSpeed
     */
    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    /**
     * Returns the list of Nodes along the Way
     * @return
     */
    public ArrayList<Double> getNodes() {
        return nodes;
    }

    /**
     * Sets the list of Nodes along the Way
     * @param nodes
     */
    public void setNodes(ArrayList<Double> nodes) {
        this.nodes = nodes;
    }

}
