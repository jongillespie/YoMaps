import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main Application
 * Kicks off the program and initialises the FXML display;
 * as static, to be accessed in the Controller as well.
 */
public class Main extends Application {

    // Static and accessible in Controller.
    public static AnchorPane yoMapsUI;

    /**
     * Loads the FXML file for display
     * @param stage creates, loads and shows the initial stage.
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception{
        yoMapsUI = FXMLLoader.load(getClass().getResource("yoMapsGUI.fxml"));
        stage.setScene(new Scene(yoMapsUI));
        stage.show();
    }

    /**
     * Main Java run sequence initializer.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}


/**
 *
 * -- DATA --
 * TODO: XML File Import (Export Relevant data for future use as primary Import File)
 * TODO: Parse of Required Data
 * TODO: Constructors for said data (objects)
 *
 * -- NUTS AND BOLTS -- Done - will need further tweaking as we determine the properties needed in our edges and nodes.
 * Node Class
 * Edges
 * Graph (Matrix of some type) >> Using AdjacencyLists in the Node Class instead of a Matrix.
 * TODO: ASK PETER >> "What about edges that meet up with other edges instead of nodes, ex. Rural Road meets Highway."
 *
 * -- PROCESSING --
 * TODO: Multiple route permutations between a starting point and a destination (can limit it to a maximum user-specified number of routes where there are too many permutations).
 * TODO: Shortest route (in terms of distance) between the starting point and destination. ** Dijkstra’s algorithm (see p. 61)
 * TODO: Quickest route (based on road speed limits and distance) between the starting point and destination.
 * TODO: Waypoint - Desired >> Break into parts and sum total routes.
 * TODO: Waypoint - Avoid >> check temp routes - reject matches
 *
 * // OPTIONS FROM NOTES
 * - Depth First
 * - Breadth First
 *
 *
 * -- DISPLAY / RETURN --
 * TODO: Map Tiles (Zoomed Tiles.... how the fuck!>)
 * TODO: Stroke/Line Route Indication
 * TODO: Tooltips for Road Details (bonus)
 *
 * TODO: -- JUNIT TESTING AS WE DEVELOP!!!! --
 *
 */

