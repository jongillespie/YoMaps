/**
 * Class for Edges representing Roads between Cities / Towns
 * p.52 in NOTES
 */
public class Edge {

    public Node<?> startPoint, endPoint;
    public int distance;
    public int speedLimit;

    public Edge(Node<?> startPoint, int distance, int speedLimit){
        this.startPoint = startPoint;
        this.distance = distance;
        this.speedLimit = speedLimit;
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
