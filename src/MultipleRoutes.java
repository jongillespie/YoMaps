import java.awt.image.AreaAveragingScaleFilter;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Multiple route permutations between a starting point and a destination
 * (can limit it to a maximum user-specified number of routes where there are too many permutations).
 */
public class MultipleRoutes implements DistanceCalcInterface {

    // New Array List of Route Array Lists
    public ArrayList routesList = new ArrayList<ArrayList>();

    // Find Way
    public Way findWayByName(String wayName){
        for (Way way : Main.ways){
            if (way.getName().equals(wayName)){
                // Matched a Way
                return way;
            }
        }
        return null;
    }

    // Multiple Finder
//    public static ArrayList<Node> multipleRouteBFS(ArrayList<ArrayList<Node>> agenda, ArrayList<Node> encountered, Node lookingFor){
//
//        if (agenda.isEmpty()) return null; // Search Failed
//        ArrayList<Node> nextPath = agenda.remove(0); // Get the first item (next path to consider) off agenda
//        Node currentNode = nextPath.get(0); // The first item in the next path is the current node
//
//        if (currentNode.name.equals(lookingFor)) return nextPath; // If that's the goal, foudn our path so return
//
//        if (encountered == null) encountered = new ArrayList<>(); // First node considered in search so create new (empty) encountered list
//
//        encountered.add(currentNode); // Record current node as encountered so it isn't revisited again
//
//        for (Node adjNode : currentNode.adjList) { // For each adjacent node
//            if (!encountered.contains(adjNode)) { // If it hasn't already been encountered
//                ArrayList<Node> newPath = new ArrayList<>(nextPath); // Create a new path list as a copy of the current/next path
//                newPath.add(0, adjNode); // And add the adjacent node to the front of the new copy
//                agenda.add(newPath); // Add the new path to the end of the agenda (end -> BFS!)
//            }
//        }
//
//        return multipleRouteBFS(agenda, encountered, lookingFor); // Tail Call
//
//    }

    // destination


    // BFS for Ways

    /**
     * Find Way ORIGIN
     * Cycle Nodes of Way and Add to Agenda
     * Find Ways Attached to Nodes from Agenda (These Nodes are now VISITED)
     * repeat
     *
     * How to track the path?
     */

}
