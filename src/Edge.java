/**
 * Class for Edges representing Roads between Cities / Towns
 * p.52 in NOTES
 */
public class Edge {

    public Node<?> startingPoint, destinationPoint;
    public int distance;
    public int speedLimit;

    public Edge(Node<?> startingPoint, Node<?> destinationPoint, int distance, int speedLimit){
        this.startingPoint = startingPoint;
        this.destinationPoint = destinationPoint;
        this.distance = distance;
        this.speedLimit = speedLimit;
    }

    public Node<?> getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(Node<?> startingPoint) {
        this.startingPoint = startingPoint;
    }

    public Node<?> getDestinationPoint() {
        return destinationPoint;
    }

    public void setDestinationPoint(Node<?> destinationPoint) {
        this.destinationPoint = destinationPoint;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(int speedLimit) {
        this.speedLimit = speedLimit;
    }
}
