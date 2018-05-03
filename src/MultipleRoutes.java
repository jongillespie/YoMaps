import java.util.ArrayList;
import java.util.HashMap;

/**
 * Multiple route permutations between a starting point and a destination
 * (can limit it to a maximum user-specified number of routes where there are too many permutations).
 */
public class MultipleRoutes implements DistanceCalcInterface {

    // New Array List of Route Array Lists
    //public ArrayList routesList = new ArrayList<ArrayList>();

    // Find Way
    public Way findWayByName(String wayName, HashMap<String, Way> waysMap) {
        try {
            return waysMap.get(wayName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Link findLinkByName(String linkName, HashMap<String, Link> linksMap) {
        try {
            return linksMap.get(linkName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Gets one node from the Way in question.
    public Node getANodeOfWay(Way way, HashMap<Double, Node> nodesMap){
        Double nodeID = way.nodes.get(0);
        return nodesMap.get(nodeID);
    }

    /**
     * Currently returns a single path, will need to re-run with encountered list of previous paths used?
     */
    // SEND IN THE WAY USING THE ABOVE FIND WAY BY NAME
    public ArrayList<Link> multipleRouteBFS(ArrayList<ArrayList<Link>> agenda, ArrayList<Link> encountered, Link lookingFor){
        if (agenda.isEmpty()){
            System.out.println("MR-BFS Agenda is EMPTY!");
            return null; // Search Failed
        }

        ArrayList<Link> nextLink = agenda.remove(0); // Get the first item (next Way to consider) off agenda
        Link currentLink = nextLink.get(0); // The first item in the nextWay list is the current way
        if (currentLink.equals(lookingFor)) return nextLink; // If that's the goal, found our path so return
        if (encountered == null) encountered = new ArrayList<>(); // First way considered in search so create new (empty) encountered list
        encountered.add(currentLink); // Record current way as encountered so it isn't revisited again

        for (Node node : currentLink.getAdjNodesList()) {
            for (Link link : node.getAdjLinks()) {
                if (!encountered.contains(link)) {
                    ArrayList<Link> newLinks = new ArrayList<>(nextLink); // Create a new path list as a copy of the current/next path
                    newLinks.add(0, link); // And add the adjacent node to the front of the new copy
                    agenda.add(newLinks); // Add the new path to the end of the agenda (end -> BFS!)
                }
            }
        }
        return multipleRouteBFS(agenda, encountered, lookingFor); // Tail Call
    }

}
