import java.util.ArrayList;
import java.util.HashMap;

/**
 * A Star Search Algorithm - work in progress.
 */
public class AStarSearch implements DistanceCalcInterface {

    ArrayList<Node> openList = new ArrayList<>();
    ArrayList<Node> closedList = new ArrayList<>();
    ArrayList<Link> linksList = new ArrayList<>();

    public ArrayList<Link> executeAStar(Node startNode, Node endNode, HashMap<Double, Node> nodesMap) {

        double linkDist;
        double heuristic; // Euclidean distance between lat and lon
        // Initially, only the start Node is known. Add to Open List.
        openList.add(startNode);

        return linksList;
    }
}

//    /**
//     * The main A Star Algorithm in Java.
//     *
//     * finds an allowed path from start to goal coordinates on this map.
//     * <p>
//     * This method uses the A Star algorithm. The hCosts value is calculated in
//     * the given Node implementation.
//     * <p>
//     * This method will return a LinkedList containing the start node at the
//     * beginning followed by the calculated shortest allowed path ending
//     * with the end node.
//     * <p>
//     * If no allowed path exists, an empty list will be returned.
//     * <p>
//     * <p>
//     * x/y must be bigger or equal to 0 and smaller or equal to width/hight.
//     *
//     * @param oldX x where the path starts
//     * @param oldY y where the path starts
//     * @param newX x where the path ends
//     * @param newY y where the path ends
//     * @return the path as calculated by the A Star algorithm
//     */
//    public final List<T> findPath(int oldX, int oldY, int newX, int newY) {
//        openList = new LinkedList<T>();
//        closedList = new LinkedList<T>();
//        openList.add(nodes[oldX][oldY]); // add starting node to open list
//
//        done = false;
//        T current;
//        while (!done) {
//            current = lowestFInOpen(); // get node with lowest fCosts from openList
//            closedList.add(current); // add current node to closed list
//            openList.remove(current); // delete current node from open list
//
//            if ((current.getxPosition() == newX)
//                    && (current.getyPosition() == newY)) { // found goal
//                return calcPath(nodes[oldX][oldY], current);
//            }
//
//            // for all adjacent nodes:
//            List<T> adjacentNodes = getAdjacent(current);
//            for (int i = 0; i < adjacentNodes.size(); i++) {
//                T currentAdj = adjacentNodes.get(i);
//                if (!openList.contains(currentAdj)) { // node is not in openList
//                    currentAdj.setPrevious(current); // set current node as previous for this node
//                    currentAdj.sethCosts(nodes[newX][newY]); // set h costs of this node (estimated costs to goal)
//                    currentAdj.setgCosts(current); // set g costs of this node (costs from start to this node)
//                    openList.add(currentAdj); // add node to openList
//                } else { // node is in openList
//                    if (currentAdj.getgCosts() > currentAdj.calculategCosts(current)) { // costs from current node are cheaper than previous costs
//                        currentAdj.setPrevious(current); // set current node as previous for this node
//                        currentAdj.setgCosts(current); // set g costs of this node (costs from start to this node)
//                    }
//                }
//            }
//
//            if (openList.isEmpty()) { // no path exists
//                return new LinkedList<T>(); // return empty list
//            }
//        }
//        return null; // unreachable
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//






//    /** Find the path from start to goal using A-Star search
//     *
//     * @param start The starting location
//     * @param goal The goal location
//     * @return The list of intersections that form the shortest path from
//     *   start to goal (including both start and goal).
//     */
//    public List<GeographicPoint> aStarSearch(GeographicPoint start,
//                                             GeographicPoint goal)
//    {
//
//        MapNode startNode = pointNodeMap.get(start);
//        MapNode endNode = pointNodeMap.get(goal);
//
//        // setup for A*
//        HashMap<MapNode,MapNode> parentMap = new HashMap<MapNode,MapNode>();
//        HashSet<MapNode> visited = new HashSet<MapNode>();
//        Map<MapNode, Double> distances = initializeAllToInfinity();
//
//        Queue<MapNode> priorityQueue = initQueue();
//
//        //  enque StartNode, with distance 0
//        startNode.setDistanceToStart(new Double(0));
//        distances.put(startNode, new Double(0));
//        priorityQueue.add(startNode);
//        MapNode current = null;
//
//        while (!priorityQueue.isEmpty()) {
//            current = priorityQueue.remove();
//
//            if (!visited.contains(current) ){
//                visited.add(current);
//                // if last element in PQ reached
//                if (current.equals(endNode)) return reconstructPath(parentMap, startNode, endNode, 0);
//
//                Set<MapNode> neighbors = getNeighbors(current);
//                for (MapNode neighbor : neighbors) {
//                    if (!visited.contains(neighbor) ){
//
//
//                        // -------------- Dif from DAs
//                        // calculate predicted distance to the end node
//                        double predictedDistance = neighbor.getLocation().distance(endNode.getLocation());
//
//                        // 1. calculate distance to neighbor. 2. calculate dist from start node
//                        double neighborDistance = current.calculateDistance(neighbor);
//                        double totalDistance = current.getDistanceToStart() + neighborDistance + predictedDistance;
//                        // -------------- Dif from DAs
//
//
//                        // check if distance smaller
//                        if(totalDistance < distances.get(neighbor) ){
//                            // update n's distance
//                            distances.put(neighbor, totalDistance);
//                            // used for PriorityQueue
//                            neighbor.setDistanceToStart(totalDistance);
//                            neighbor.setPredictedDistance(predictedDistance);
//                            // set parent
//                            parentMap.put(neighbor, current);
//                            // enqueue
//                            priorityQueue.add(neighbor);
//                        }
//                    }
//                }
//            }
//        }
//        return null;
//    }



