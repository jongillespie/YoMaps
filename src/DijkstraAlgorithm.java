import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 *  Dijkstra’s algorithm
 *  Permits either Shortest Route Calculation or Quickest Route based on Time.
 */
public class DijkstraAlgorithm implements DistanceCalcInterface {

    public ArrayList<Link> route;

    /**
     * Main method for finding the cheapest path.
     * Permits the use of either distance for shortest route or time for quickest route.
     * @param startNode The Origin node
     * @param lookingfor The Destination node
     * @param avoid Nodes / Links to avoid along the route.
     * @param quick Flag for Distance or Time compute.
     * @return
     */
    public CostedPath findCheapestPathDijkstra(Node startNode, Node lookingfor, ArrayList<String> avoid, Boolean quick) {

        CostedPath cp = new CostedPath(); //Create result object for cheapest path
        int linkVariable = 0;
        List<Node> encountered = new ArrayList<>();
        List<Node> unencountered = new ArrayList<>(); //Create encountered/un-encountered lists

        startNode.setDijkstraValue(0); //Set the starting node value to zero
        unencountered.add(startNode); //Add the start node as the only value in the un-encountered list to start
        Node currentNode;

        do { //Loop until un-encountered list is empty

            currentNode = unencountered.remove(0); //Get the first un-encountered node (sorted list, so will have lowest value)
            encountered.add(currentNode); //Record current node in encountered list

            if (currentNode.equals(lookingfor)) { //Found goal - assemble path list back to start and return it
                cp.getPathList().add(currentNode); //Add the current (goal) node to the result list (only element)
                cp.setPathCost(currentNode.getDijkstraValue()); //The total cheapest path cost is the node value of the current/goal node

                while (currentNode != startNode) { //While we're not back to the start node...
                    boolean foundPrevPathNode = false; //Use a flag to identify when the previous path node is identified
                    for (Node node : encountered) { //For each node in the encountered list...
                            for (Link link : node.getAdjTwinLinks()) { //For each edge from that node...

                                // --- Shortest / Quickest SHIFTER
                                if (!quick) {
                                    linkVariable = (int) link.getDistance();
                                } else {
                                    linkVariable = link.getQuickness();
                                }

                                if (link.getDestinationNode() == currentNode && currentNode.getDijkstraValue() - linkVariable == node.getDijkstraValue()) { //If that edge links to the current node and the difference in node values is the cost of the edge -> found path node!
                                    cp.getPathList().add(0, node); //Add the identified path node to the front of the result list
                                    currentNode = node; //Move the currentNode reference back to the identified path node
                                    foundPrevPathNode = true; //Set the flag to break the outer loop
                                    break; //We've found the correct previous path node and moved the currentNode reference
                                    // back to it so break the inner loop
                                }
                            }
                        if (foundPrevPathNode)
                            break; //We've identified the previous path node, so break the inner loop to continue
                    }
                }
                //Reset the node values for all nodes to (effectively) infinity so we can search again (leave no footprint!)
                for (Node n : encountered) n.setDijkstraValue(Integer.MAX_VALUE);
                for (Node n : unencountered) n.setDijkstraValue(Integer.MAX_VALUE);

                System.out.println("Path Cost: " + cp.getPathCost());

                return cp; //The costed (cheapest) path has been assembled, so return it!
            }
            //We're not at the goal node yet, so...
            for (Link link : currentNode.getAdjTwinLinks()) {//For each edge/link from the current node...
                //--------- THIS IF STATEMENT ENSURES THE ROUTE AVOIDS STREETS IF THE LIST CONTAINS AN ELEMENT
                if (!avoid.contains(link.getName())) {

                    // --- Shortest / Quickest SHIFTER
                    if (!quick) {
                        linkVariable = (int) link.getDistance();
                    } else {
                        linkVariable = link.getQuickness();
                    }

                    if (!encountered.contains(link.getDestinationNode())) { //If the node it leads to has not yet been encountered (i.e. processed)
                        // TODO CALCULATE THE DISTANCE BETWEEN THE NODES FOR THE LINK AND SET THE LINK DISTANCE! CONFIRM WHERE
                        link.getDestinationNode().setDijkstraValue(
                                Double.min(link.getDestinationNode().getDijkstraValue(),
                                        currentNode.getDijkstraValue() + linkVariable)); //Update the node value at the end of the edge to the minimum of its current value or the total of the current node's value plus the cost of the edge
                        unencountered.add(link.getDestinationNode());
                    }
                }
            }
            Collections.sort(unencountered, (n1, n2) -> (int) (n1.getDijkstraValue() - n2.getDijkstraValue())); //Sort in ascending node value order
        } while (!unencountered.isEmpty());
        return null; //No path found, so return null
    }

    /**
     * Finds a mid-node given the incoming Way name Identifer.
     * @param wayName
     * @param waysMap
     * @param nodeHashMap
     * @return
     */
    public Node findNodeByWay(String wayName, HashMap<String, Way> waysMap, HashMap<Double, Node> nodeHashMap) {
        try {
            if (waysMap.get(wayName).nodes.size() >= 2){
                // TODO these are both set to 1 to include the street origin/dest - change after further tests second to 0
                return nodeHashMap.get(waysMap.get(wayName).nodes.get(1));
            } else return nodeHashMap.get(waysMap.get(wayName).nodes.get(1));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
