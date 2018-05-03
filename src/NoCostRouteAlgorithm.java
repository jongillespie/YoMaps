import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 *
 */
public class NoCostRouteAlgorithm {
//
//    public ArrayList<Link> route;
//
//    public CostedPath findCheapestPathDijkstra(Node startNode, Node lookingfor) {
//
//        CostedPath cp = new CostedPath(); //Create result object for cheapest path
//
//        List<Node> encountered = new ArrayList<>();
//        List<Node> unencountered = new ArrayList<>(); //Create encountered/un-encountered lists
//
//        startNode.setDijkstraValue(0); //Set the starting node value to zero
//        unencountered.add(startNode); //Add the start node as the only value in the un-encountered list to start
//        Node currentNode;
//
//        do { //Loop until un-encountered list is empty
//
//            currentNode = unencountered.remove(0); //Get the first un-encountered node (sorted list, so will have lowest value)
//            encountered.add(currentNode); //Record current node in encountered list
//
//            if (currentNode.equals(lookingfor)) { //Found goal - assemble path list back to start and return it
//                cp.getPathList().add(currentNode); //Add the current (goal) node to the result list (only element)
//                cp.setPathCost(currentNode.getDijkstraValue()); //The total cheapest path cost is the node value of the current/goal node
//
//                while (currentNode != startNode) { //While we're not back to the start node...
//                    boolean foundPrevPathNode = false; //Use a flag to identify when the previous path node is identified
//                    for (Node node : encountered) { //For each node in the encountered list...
//                        for (Link link : node.getAdjTwinLinks()) { //For each edge from that node...
//
//                            if (link.getDestinationNode() == currentNode && currentNode.getDijkstraValue() - link.getDistance() == node.getDijkstraValue()) { //If that edge links to the current node and the difference in node values is the cost of the edge -> found path node!
//                                cp.getPathList().add(0, node); //Add the identified path node to the front of the result list
//                                currentNode = node; //Move the currentNode reference back to the identified path node
//                                foundPrevPathNode = true; //Set the flag to break the outer loop
//                                break; //We've found the correct previous path node and moved the currentNode reference
//                                // back to it so break the inner loop
//                            }
//                        }
//                        if (foundPrevPathNode)
//                            break; //We've identified the previous path node, so break the inner loop to continue
//                    }
//                }
//                //Reset the node values for all nodes to (effectively) infinity so we can search again (leave no footprint!)
//                for (Node n : encountered) n.setDijkstraValue(Integer.MAX_VALUE);
//                for (Node n : unencountered) n.setDijkstraValue(Integer.MAX_VALUE);
//                return cp; //The costed (cheapest) path has been assembled, so return it!
//            }
//            //We're not at the goal node yet, so...
//            for (Link link : currentNode.getAdjTwinLinks()) {//For each edge/link from the current node...
//                if (!encountered.contains(link.getDestinationNode())) { //If the node it leads to has not yet been encountered (i.e. processed)
//                    // TODO CALCULATE THE DISTANCE BETWEEN THE NODES FOR THE LINK AND SET THE LINK DISTANCE! CONFIRM WHERE
//                    link.getDestinationNode().setDijkstraValue(
//                            Double.min(link.getDestinationNode().getDijkstraValue(),
//                                    currentNode.getDijkstraValue() + link.getDistance())); //Update the node value at the end of the edge to the minimum of its current value or the total of the current node's value plus the cost of the edge
//                    unencountered.add(link.getDestinationNode());
//                }
//            }
//            Collections.sort(unencountered, (n1, n2) -> (int) (n1.getDijkstraValue() - n2.getDijkstraValue())); //Sort in ascending node value order
//        } while (!unencountered.isEmpty());
//        return null; //No path found, so return null
//    }
//
//    public Node findNodeByWay(String wayName, HashMap<String, Way> waysMap, HashMap<Double, Node> nodeHashMap) {
//        try {
//            if (waysMap.get(wayName).nodes.size() > 2){
//                // TODO these are both set to 1 to include the street origin/dest - change after further tests second to 0
//                return nodeHashMap.get(waysMap.get(wayName).nodes.get(1));
//            } else return nodeHashMap.get(waysMap.get(wayName).nodes.get(1));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}
