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
    public static HashMap<String, Link> linksMap = read.createLinks(read.waysList);
    public static MultipleRoutes multipleRoutes = new MultipleRoutes();

    private  ArrayList<ArrayList<Link>> routes = new ArrayList<>();

    @FXML private Slider desiredRoutesSlider;
    @FXML private TextField originField, destinationField, streetsRequiredField, streetsAvoidField;
    @FXML private Toggle quickestRouteToggle, shortestRouteToggle;
    @FXML private Button goButtonAction;
    @FXML private AnchorPane treeAnchor;
    @FXML private TreeView<String> routeTree;
    @FXML private ImageView mapView;

    @FXML
    public void goButtonAction(){
        // Removes the lines from the previous routes drawn.
        Main.yoMapsUI.getChildren().removeIf((x)->x.getClass()==Line.class);

        // ----- SINGLE ROUTE AND MULTI ROUTE
        // Go Button for Desired Routes 1 through 10 >> Toggle Buttons OFF
        if (!quickestRouteToggle.isSelected() && !shortestRouteToggle.isSelected()){
            ArrayList<Link> route = getNoCostRoute();
            createTree(route);
            drawRoute(route);
        }

        // ----- SHORTEST ROUTE > Dijkstra’s algorithm (DISTANCE)
        if (!quickestRouteToggle.isSelected() && shortestRouteToggle.isSelected()){
            // ADD IN METHOD CALLS HERE
            // get list
            // create tree
            // draw route
        }

        // ----- QUICKEST ROUTE > Dijkstra’s algorithm WITH TIME CALCULATION (SPEED / DISTANCE)
        if (quickestRouteToggle.isSelected() && !shortestRouteToggle.isSelected()){
            // ADD IN METHOD CALLS HERE
            // get list
            // create tree
            // draw route
        }
    }

    /**
     * Takes an ArrayList of routes and creates a tree for display in window.
     * WILL NEED TO ADJUST THIS TO ALLOW FOR MULTIPLE TREES.
     * @param route
     */
    private void createTree(ArrayList<Link> route){
        String routeNumber = "Route " + "1";
        TreeItem<String> rootItem = new TreeItem<> (routeNumber);
        rootItem.setExpanded(true);
        for (Link link : route){
            TreeItem<String> item = new TreeItem<>(link.getName());
            rootItem.getChildren().add(item);
        }
        TreeView<String> tree = new TreeView<> (rootItem);
        tree.setPrefSize(291, 766);
        treeAnchor.getChildren().add(tree);
    }

    /**
     * Takes an array List of Links and draws them on the map.
     * @param route
     */
    private void drawRoute(ArrayList<Link> route) {
        double latMaxY = 52.270;
        double lonMaxX = 7.1899;
        double latYRange = (52.2790 - 52.2229);
        double lonXRange = (7.1899 - 7.0470);
        double mapX = 1194;
        double mapY = 764;
        for (Link link : route) {
            Node one = link.getAdjNodesList().get(0);
            double lon1 = one.getLon();
            System.out.println("Actual LON 1: " + lon1);
            double lat1 = one.getLat();
            System.out.println("Actual LAT 1: " + lat1);

            double x1 = (((lonMaxX - (lon1 * -1)) / lonXRange)) * mapX;
            System.out.println("CALCULATION RESULT x1: " + x1);
            double y1 = (((latMaxY - lat1) / latYRange)) * mapY;
            System.out.println("CALCULATION RESULT y1: " + y1);

            Node two = link.getAdjNodesList().get(1);
            double lon2 = two.getLon();
            System.out.println("Actual LON 2: " + lon2);
            double lat2 = two.getLat();
            System.out.println("Actual LAT 2: " + lat2);

            double x2 = (((lonMaxX - (lon2 * -1)) / lonXRange)) * mapX;
            System.out.println("CALCULATION RESULT x2: " + x2);
            double y2 = (((latMaxY - lat2) / latYRange)) * mapY;
            System.out.println("CALCULATION RESULT y2: " + y2);

            // -----------
            double xOffset = 388;
            double yOffset = 361;
            Line line = new Line(x1 + xOffset, y1 + yOffset, x2 + xOffset, y2 + yOffset);
            line.setStroke(RED);
            line.setVisible(true);
            line.setStrokeWidth(3);
            Main.yoMapsUI.getChildren().add(line);
        }
    }

    /**
     * Takes in the user input and spits out the routes - NO COST
     * @return
     */
    private ArrayList<Link> getNoCostRoute(){
        int routeNumber = (int) desiredRoutesSlider.getValue();
        System.out.println("Number of Routes Wanted: " + routeNumber);
        String origin = originField.getText();
        String destination = destinationField.getText();
        Link originLink = multipleRoutes.findLinkByName(origin, linksMap);
        System.out.println(originLink);
        Link lookingForLink = multipleRoutes.findLinkByName(destination, linksMap);
        System.out.println(lookingForLink);
        ArrayList<Link> initial = new ArrayList<>();
        initial.add(originLink);
        routes.add(initial);
        ArrayList<Link> result = new ArrayList<>(multipleRoutes.multipleRouteBFS(
                routes, null, lookingForLink));
        for (Link link : result){
            System.out.println("Link: " + link.getName());
        }
        System.out.println("COMPLETE");
        return result;
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