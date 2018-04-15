///**
// * Class for Edges representing Roads between Cities / Towns
// * p.52 in NOTES
// */
//public class Edge {
//
//    public String name;
//    public Node<?> startPoint, endPoint; // TODO: endPoint included for now, how to manage sections without Nodes?
//    public int distance;
//    public int speedLimit;
//
//    /**
//     * Edge Constructor - creates the instance of a roadway
//     * @param name of the Road
//     * @param startPoint of the Road Section
//     * @param distance of the Road Section
//     * @param speedLimit of the Road Section
//     */
//    public Edge(String name, Node<?> startPoint, int distance, int speedLimit){
//        this.name = name;
//        this.startPoint = startPoint;
//        this.distance = distance;
//        this.speedLimit = speedLimit;
//    }
//
//    /**
//     * Returns the name of the Road Section
//     * @return
//     */
//    public String getName() {
//        return name;
//    }
//
//    /**
//     * Sets the name of the Road Section
//     * @param name
//     */
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    /**
//     * Returns the distance of the Road Section
//     * @return
//     */
//    public int getDistance() {
//        return distance;
//    }
//
//    /**
//     * Sets the Distance of the Road Section
//     * @param distance
//     */
//    public void setDistance(int distance) {
//        this.distance = distance;
//    }
//
//    /**
//     * Gets the Speed Limit of the Road Section
//     * @return
//     */
//    public int getSpeedLimit() {
//        return speedLimit;
//    }
//
//    /**
//     * Sets the Speed Limit of the Road Section
//     * @param speedLimit
//     */
//    public void setSpeedLimit(int speedLimit) {
//        this.speedLimit = speedLimit;
//    }
//}
