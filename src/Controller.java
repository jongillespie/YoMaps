import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import java.util.ArrayList;
import java.util.HashMap;
import static javafx.scene.paint.Color.RED;

public class Controller {

    public static TradXMLParse read = new TradXMLParse();
    public static HashMap<Double, Node> nodesMap = read.readXMLNodes("AllWaterford.xml");
    public static HashMap<String, Way> waysMap = read.readXMLWays("AllWaterford.xml");
//    public static HashMap<Double, Node> nodesMap = read.readXMLNodes("IrelandFilteredMapData.xml");
//    public static HashMap<String, Way> waysMap = read.readXMLWays("IrelandFilteredMapData.xml");

    public static HashMap<String, Link> linksMap = read.createLinks(read.waysList);
    public static LinkRouteAlgorithm linkRouteAlgorithm = new LinkRouteAlgorithm();
    public static DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm();
    public Boolean quick;

    private ArrayList<ArrayList<Link>> routes = new ArrayList<>();


    @FXML
    private Slider desiredRoutesSlider;
    @FXML
    private TextField originField, destinationField, streetsRequiredField, streetsAvoidField;
    @FXML
    private Toggle quickestRouteToggle, shortestRouteToggle;
    @FXML
    private Button goButtonAction;
    @FXML
    private AnchorPane treeAnchor;
    @FXML
    private TreeView<String> routeTree;
    @FXML
    private ImageView mapView;

    @FXML
    public void goButtonAction() {
        // Removes the lines from the previous routes drawn.
        Main.yoMapsUI.getChildren().removeIf((x) -> x.getClass() == Line.class);
        // ----- SINGLE ROUTE AND MULTI ROUTE
        // Go Button for Desired Routes 1 through 10 >> Toggle Buttons OFF
        if (!quickestRouteToggle.isSelected() && !shortestRouteToggle.isSelected()) {
            ArrayList<Link> route = getNoCostRoute();
            createTree(route);
            drawLinkRoute(route);
            System.out.println("Single and Multi Route EXECUTED");
        }
        // ----- SHORTEST ROUTE > Dijkstra’s algorithm (DISTANCE)
        if (shortestRouteToggle.isSelected() && !quickestRouteToggle.isSelected()) {
            quick = false;
            CostedPath nodeRoute = executeDijkstrasAlgo();
            ArrayList<Link> linkRoute = translateNodePathToLinkRoute(nodeRoute);
            drawNodeRoute(nodeRoute, linkRoute);
            ArrayList<Link> noDupeLinkRoute = dupeLinkRemovalForTreeDisplay(linkRoute);
            createTree(noDupeLinkRoute);
            System.out.println("Dijkstra's Algorithm EXECUTED");
        }
        // ----- QUICKEST ROUTE > Dijkstra’s algorithm WITH TIME CALCULATION (DISTANCE / SPEED)
        if (quickestRouteToggle.isSelected() && !shortestRouteToggle.isSelected()) {
            quick = true;
            CostedPath nodeRoute = executeDijkstrasAlgo();
            ArrayList<Link> linkRoute = translateNodePathToLinkRoute(nodeRoute);
            drawNodeRoute(nodeRoute, linkRoute);
            ArrayList<Link> noDupeLinkRoute = dupeLinkRemovalForTreeDisplay(linkRoute);
            createTree(noDupeLinkRoute);
            System.out.println("Quickest Route EXECUTED");
        }
    }


    private ArrayList<String> waypointAvoidEngine() {
        try {
            // Attain any AVOIDABLE Waypoints
            if (streetsAvoidField.getText() != null) {
                String streetsAvoid = streetsAvoidField.getText();
                String[] waypointsAvoid = streetsAvoid.split(", ");
                // find nodes of LINK or Way?
                ArrayList<String> linkAvoid = new ArrayList<>();
                for (String avoid : waypointsAvoid) {
                    linkAvoid.add(avoid);
                }
                return linkAvoid;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<String> waypointRequiredEngine() {
        try {
            // Attain any Waypoints
            if (streetsRequiredField.getText() != null) {
                System.out.println("IT DOES HAVE PROPERTIES!! ");
                String streetsRequired = streetsRequiredField.getText();
                String[] waypointsRequired = streetsRequired.split(", ");
                // find nodes of LINK or Way?
                ArrayList<String> linkRequired = new ArrayList<>();
                for (String avoid : waypointsRequired) {
                    linkRequired.add(avoid);
                }
                return linkRequired;
            }
        } catch (Exception e) {
            System.out.println("WAYPOINT AVOID CONTROLLER TRACE");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Takes an ArrayList of routes and creates a tree for display in window.
     * WILL NEED TO ADJUST THIS TO ALLOW FOR MULTIPLE TREES.
     *
     * @param route
     */
    private void createTree(ArrayList<Link> route) {
        String routeNumber = "Route " + "1";
        TreeItem<String> rootItem = new TreeItem<>(routeNumber);
        rootItem.setExpanded(true);
        int number = 1;
        for (Link link : route) {
            TreeItem<String> item = new TreeItem<>(number + ":  " + link.getName() + "   for: " + link.getDistance() + "m");
            rootItem.getChildren().add(item);
            number++;
        }
        TreeView<String> tree = new TreeView<>(rootItem);
        tree.setPrefSize(291, 766);
        treeAnchor.getChildren().add(tree);
    }

    /**
     * Takes an array List of Links and draws them on the map.
     *
     * @param route
     */
    private void drawLinkRoute(ArrayList<Link> route) {
        double latMaxY = 52.270;
        double lonMaxX = 7.1899;
        double latYRange = (52.2790 - 52.2229);
        double lonXRange = (7.1899 - 7.0470);
        double mapX = 1194;
        double mapY = 764;
        int number = 1;
        for (Link link : route) {
            Node one = link.getAdjNodesList().get(0);
            double lon1 = one.getLon();
            double lat1 = one.getLat();
            double x1 = (((lonMaxX - (lon1 * -1)) / lonXRange)) * mapX;
            double y1 = (((latMaxY - lat1) / latYRange)) * mapY;
            Node two = link.getAdjNodesList().get(1);
            double lon2 = two.getLon();
            double lat2 = two.getLat();
            double x2 = (((lonMaxX - (lon2 * -1)) / lonXRange)) * mapX;
            double y2 = (((latMaxY - lat2) / latYRange)) * mapY;
            // -----------
            double xOffset = 388;
            double yOffset = 361;
            Line line = new Line(x1 + xOffset, y1 + yOffset, x2 + xOffset, y2 + yOffset);
            line.setStroke(RED);
            line.setVisible(true);
            line.setStrokeWidth(3);

            Tooltip tip = new Tooltip();
            tip.setText(number + ":  " + link.getName() + "   for: " + link.getDistance() + "m");
            Tooltip.install(line, tip);
            number++;

            Main.yoMapsUI.getChildren().add(line);
        }
    }

    /**
     * Takes an array List of Links and draws them on the map.
     *
     * @param route
     */
    private void drawNodeRoute(CostedPath route, ArrayList<Link> linkForTIP) {
        double latMaxY = 52.270;
        double lonMaxX = 7.1899;
        double latYRange = (52.2790 - 52.2229);
        double lonXRange = (7.1899 - 7.0470);
        double mapX = 1194;
        double mapY = 764;
        int linknumber = 1;
        ArrayList<Node> nodeRoute = route.getPathList();
        for (int i = 0; i < nodeRoute.size() - 1; i++) {
            Node one = nodeRoute.get(i);
            double lon1 = one.getLon();
            double lat1 = one.getLat();
            double x1 = (((lonMaxX - (lon1 * -1)) / lonXRange)) * mapX;
            double y1 = (((latMaxY - lat1) / latYRange)) * mapY;
            Node two = nodeRoute.get(i + 1);
            double lon2 = two.getLon();
            double lat2 = two.getLat();
            double x2 = (((lonMaxX - (lon2 * -1)) / lonXRange)) * mapX;
            double y2 = (((latMaxY - lat2) / latYRange)) * mapY;
            // -----------
            double xOffset = 388;
            double yOffset = 361;
            Line line = new Line(x1 + xOffset, y1 + yOffset, x2 + xOffset, y2 + yOffset);
            line.setStroke(RED);
            line.setVisible(true);
            line.setStrokeWidth(3);

            Tooltip tip = new Tooltip();
            tip.setText(linknumber + ":  " + linkForTIP.get(i).getName() + "   for: " + linkForTIP.get(i).getDistance() + "m");
            Tooltip.install(line, tip);
            linknumber++;

            Main.yoMapsUI.getChildren().add(line);
        }
    }

    /**
     * Takes in the user input and spits out the routes - NO COST
     *
     * @return
     */
    private ArrayList<Link> getNoCostRoute() {
        int routeNumber = (int) desiredRoutesSlider.getValue();
        System.out.println("Number of Routes Wanted: " + routeNumber);
        String origin = originField.getText();
        String destination = destinationField.getText();
        Link originLink = linkRouteAlgorithm.findLinkByName(origin, linksMap);
        System.out.println(originLink);
        Link lookingForLink = linkRouteAlgorithm.findLinkByName(destination, linksMap);
        System.out.println(lookingForLink);
        ArrayList<Link> initial = new ArrayList<>();
        initial.add(originLink);
        routes.add(initial);
        ArrayList<Link> result = new ArrayList<>(linkRouteAlgorithm.multipleRouteBFS(
                routes, null, lookingForLink));
        for (Link link : result) {
            System.out.println("Link: " + link.getName());
        }
        System.out.println("COMPLETE");
        return result;
    }

    private CostedPath executeDijkstrasAlgo() {
        String origin = originField.getText();
        String destination = destinationField.getText();
        ArrayList<String> waypoint = waypointRequiredEngine();
        ArrayList<String> avoid = waypointAvoidEngine();
        CostedPath temp;
        CostedPath result = new CostedPath();
        // NO WAY POINTS IT RUNS A SINGLE INSTANCE OF THE ALGORITHM
        if (waypoint.get(0).equals("")) {
            System.out.println("------------------------------------");
            System.out.println("NO WAY POINT");
            result = dijkstraAlgorithm.findCheapestPathDijkstra(
                    dijkstraAlgorithm.findNodeByWay(origin, waysMap, nodesMap),
                    dijkstraAlgorithm.findNodeByWay(destination, waysMap, nodesMap),
                    avoid, quick);
        } else {
            // WAY POINTS EXIST SO IT WILL CYCLE THROUGH THEM AND CREATE A SUMMED PATH
            // get the first waypoint and make it the destination for run one.
            String tempDest = waypoint.get(0);
            String tempLastWayPoint = waypoint.get(0);
            temp = dijkstraAlgorithm.findCheapestPathDijkstra(
                    dijkstraAlgorithm.findNodeByWay(origin, waysMap, nodesMap),
                    dijkstraAlgorithm.findNodeByWay(tempDest, waysMap, nodesMap),
                    avoid, quick);
            for (int i = 0; i < temp.getPathList().size() - 1; i ++){
                result.getPathList().add(temp.getPathList().get(i));
            }
            // if there is more than one way point
            if (waypoint.size() > 1) {
                for (int i = 1; i < waypoint.size(); i++) {
                    tempDest = waypoint.get(i);
                    tempLastWayPoint = waypoint.get(i - 1);
                    temp = dijkstraAlgorithm.findCheapestPathDijkstra(
                            dijkstraAlgorithm.findNodeByWay(tempLastWayPoint, waysMap, nodesMap),
                            dijkstraAlgorithm.findNodeByWay(tempDest, waysMap, nodesMap),
                            avoid, quick);
                    for (int n = 0; n < temp.getPathList().size() - 1; n ++){
                        result.getPathList().add(temp.getPathList().get(n));
                    }
                }
                tempLastWayPoint = tempDest;
            }
            // get the final way point to destination
            temp = dijkstraAlgorithm.findCheapestPathDijkstra(
                    dijkstraAlgorithm.findNodeByWay(tempLastWayPoint, waysMap, nodesMap),
                    dijkstraAlgorithm.findNodeByWay(destination, waysMap, nodesMap),
                    avoid, quick);
            for (int f = 0; f < temp.getPathList().size(); f ++){
                result.getPathList().add(temp.getPathList().get(f));
            }
        }
        return result;
    }

    /**
     * Node Route translated into a LINK Route for the display and Tree View
     */
    private ArrayList<Link> translateNodePathToLinkRoute(CostedPath nodePath) {
        ArrayList<Link> translateToLink = new ArrayList();
        for (int i = 0; i < nodePath.getPathList().size() - 1; i++) {
            Node currentNode = nodePath.getPathList().get(i);
            Node nextNode = nodePath.getPathList().get(i + 1);
            for (Link link : currentNode.getAdjTwinLinks())
                if (link.getDestinationNode() == nextNode) {
                    translateToLink.add(link);
                }
        }
        return translateToLink;
    }

    /**
     * Tool to remove the duplication of Link names for display in the Tree Route View.
     * @param translatedLinkPath
     * @return
     */
    private ArrayList<Link> dupeLinkRemovalForTreeDisplay(ArrayList<Link> translatedLinkPath){
        ArrayList<Link> dupeRemovedRoute = new ArrayList<>();
        // Removes the duplication of Ways effect from Node Jumping.
        Link temp = translatedLinkPath.get(0);
        dupeRemovedRoute.add(temp);
        for (Link link : translatedLinkPath){
            if (!link.getName().equals(temp.getName()) && !link.getName().equals(" ")){
                dupeRemovedRoute.add(link);
                temp = link;
            }
        }
        System.out.println("---------- DUPE REMOVE ROUTE ----------");
        for (Link link : dupeRemovedRoute){
            System.out.println(link.getName());
        }
        return dupeRemovedRoute;
    }

    /**
     * Exits the program
     */
    @FXML
    public void exitProgram() {
        Platform.exit();
        System.exit(0);
    }
}


//    private ArrayList<Node> waypointAvoidController(){
//        try {
//            // Attain any AVOIDABLE Waypoints
//            if (!streetsAvoidField.getText().equals(null) || !streetsAvoidField.getText().trim().isEmpty()){
//                System.out.println("IT DOES HAVE PROPERTIES!! ");
//                String streetsAvoid = streetsAvoidField.getText();
//                waypointsAvoid = streetsAvoid.split(", ");
//                // find nodes of LINK or Way?
//                nodesAvoid = new ArrayList<>();
//                for (String avoid : waypointsAvoid){
//                    ArrayList<Node> nodes = findAllNodesByWay(avoid, waysMap, nodesMap);
//                    for (Node node : nodes){
//                        nodesAvoid.add(node);
//                    }
//                }
//                return nodesAvoid;
//            }
//        } catch (Exception e) {
//            System.out.println("WAYPOINT AVOID CONTROLLER TRACE");
//            e.printStackTrace();
//        } return null;
//    }

//    public ArrayList<Node> findAllNodesByWay(String wayName, HashMap<String, Way> waysMap, HashMap<Double, Node> nodeHashMap) {
//        try {
//            ArrayList<Node> allNodes = new ArrayList<>();
//            for (Double nodeAddress : waysMap.get(wayName).nodes) {
//                allNodes.add(nodeHashMap.get(nodeAddress));
//            }
//            ArrayList<Node> filtered = new ArrayList<>();
//            if (!allNodes.isEmpty() && allNodes.size() > 2){
//                for (int i = 1; i < allNodes.size() - 1 ; i ++){
//                    filtered.add(allNodes.get(i));
//                }
//                return filtered;
//            }
//            return allNodes;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }