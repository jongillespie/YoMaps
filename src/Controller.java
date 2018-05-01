import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class Controller {

    public static TradXMLParse read = new TradXMLParse();
    public static HashMap<Double, Node> nodesMap = read.readXMLNodes("AllWaterford.xml");
    public static HashMap<String, Way> waysMap = read.readXMLWays("AllWaterford.xml");
    public static HashMap<String, Link> linksMap = read.createLinks(read.waysList);

    public static MultipleRoutes multipleRoutes = new MultipleRoutes();
    public ArrayList<ArrayList<Link>> routes = new ArrayList<>();

    @FXML private Slider desiredRoutesSlider;
    @FXML private TextField originField, destinationField, streetsRequiredField, streetsAvoidField;
    @FXML private Toggle quickestRouteToggle, shortestRouteToggle;
    @FXML private Button goButtonAction;
    @FXML private TreeView<String> routeTree;

    @FXML
    public void goButtonAction(){

        // Go Button for Desired Routes 1 through 10 >> Toggle Buttons OFF
        if (!quickestRouteToggle.isSelected() && !shortestRouteToggle.isSelected()){
            ArrayList<Link> route = getNoCostRoute();
            String rootName = "Route 1";
            TreeItem<String> root = new TreeItem<>(rootName);
            for (Link link : route){
                root.getChildren().add(new TreeItem<>(link.getName()));
            }
            routeTree = new TreeView<>(root);
            root.setExpanded(true);
            routeTree.setShowRoot(true);
            routeTree.setRoot(root);
        }
    }

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
