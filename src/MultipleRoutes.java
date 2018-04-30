import java.util.ArrayList;

/**
 * Multiple route permutations between a starting point and a destination
 * (can limit it to a maximum user-specified number of routes where there are too many permutations).
 */
public class MultipleRoutes implements DistanceCalcInterface {

    // New Array List of Route Array Lists
    //public ArrayList routesList = new ArrayList<ArrayList>();

    // Find Way
    public Way findWayByName(String wayName, ArrayList<Way> ways) {
        for (Way way : ways) {
            if (way.getName().equals(wayName)) {
                // Matched a Way
                return way;
            }
        }
        return null;
    }

    /**
     * Currently returns a single path, will need to re-run with encountered list of previous paths used?
     */
    // SEND IN THE WAY USING THE ABOVE FIND WAY BY NAME
    public ArrayList<Way> multipleRouteBFS(ArrayList<Way> ways, ArrayList<ArrayList<Way>> agenda, ArrayList<Way> encountered, Way lookingFor){
        if (agenda.isEmpty()){
            System.out.println("MR-BFS Agenda is EMPTY!");
            return null; // Search Failed
        }
        ArrayList<Way> nextWay = agenda.remove(0); // Get the first item (next Way to consider) off agenda
        Way currentWay = nextWay.get(0); // The first item in the nextWay list is the current way
        if (currentWay.equals(lookingFor)) return nextWay; // If that's the goal, found our path so return
        if (encountered == null) encountered = new ArrayList<>(); // First way considered in search so create new (empty) encountered list
        encountered.add(currentWay); // Record current way as encountered so it isn't revisited again
        // -----------------------------
        // Logic for the ways connected through Node search:
        // For each node within the Way's list of contained Nodes
        for (Node node : currentWay.nodes){
            // Look for the contained node in all other Ways - find the shared nodes AKA - connecting Ways
            for (Way way : ways){
                // And if the way has not already been touched / encountered.
                if (!encountered.contains(way)){
                    // If some other Way contains the same node, they are connected, mark the way and get all nodes.
                    if (way.nodes.contains(node)){
                        ArrayList<Way> newWays = new ArrayList<>(nextWay); // Create a new path list as a copy of the current/next path
                        newWays.add(0, way); // And add the adjacent node to the front of the new copy
                        agenda.add(newWays); // Add the new path to the end of the agenda (end -> BFS!)
                    }
                }
            }
        }
        return multipleRouteBFS(ways, agenda, encountered, lookingFor); // Tail Call
    }
}