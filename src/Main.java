import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

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
        yoMapsUI = FXMLLoader.load(getClass().getClassLoader().getResource("yoMapsGUI.fxml"));
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
 * ✓  XML File Import (Export Relevant data for future use as primary Import File) //import done - work on export next
 * ✓ Parse of Required Data  // need to link node id's with placeanames
 *  Constructors for said data (objects)
 *
 * -- NUTS AND BOLTS -- Done - will need further tweaking as we determine the properties needed in our edges and nodes.
 * Node Class
 * Edges
 * Graph (Matrix of some type) >> Using AdjacencyLists in the Node Class instead of a Matrix.
 *  ASK PETER >> "What about edges that meet up with other edges instead of nodes, ex. Rural Road meets Highway."
 *
 * -- PROCESSING --
 * DONE JONTY >> Distance Calculation using LAT and LON
 * TODO: JONTY >> Multiple route permutations between a starting point and a destination (can limit it to a maximum user-specified number of routes where there are too many permutations).
 * TODO: TBD >> Shortest route (in terms of distance) between the starting point and destination. ** Dijkstra’s algorithm (see p. 61)
 * TODO: COBAAS :) >> Quickest route (based on road speed limits and distance) between the starting point and destination. JG - "Use Dijkstra's but with a time calculation... distance/speed=time .. IE Least time.
 * TODO: TBD >> Waypoint - Desired >> Break into parts and sum total routes.
 * TODO: TBD >> Waypoint - Avoid >> check temp routes - reject matches
 *
 * // OPTIONS FROM NOTES
 * - Depth First
 * - Breadth First
 *
 *
 * -- DISPLAY / RETURN --
 * TODO: TBD >> Map Tiles (Zoomed Tiles....>)
 * TODO: TBD >> Stroke/Line Route Indication
 * TODO: TBD >> Tooltips for Road Details (bonus)
 *
 * TODO: -- JUNIT TESTING AS WE DEVELOP!!!! --
 *
 *
 * ---------------------------------------------
 * OPEN MAP DETAILS
 * Main Wiki: https://wiki.openstreetmap.org/wiki/Tags#Keys_and_values
 *
 * REQUIRED TAG VALUES
 * ---
 * https://wiki.openstreetmap.org/wiki/Way
 * SEEMS TO BE FOR 'WAYS' aka OUR EDGES... (makes sense as all roads)
 * Key: highway
 * Values >>
 * motorway
 * motorway_link
 * primary
 * primary_link
 * residential
 * secondary
 * secondary_link
 * service
 * tertiary
 * tertiary_link
 * trunk
 * trunk_link
 * unclassified
 *
 * ---
 * Key:
 * Values >>
 *
 * https://github.com/graphhopper/graphhopper/blob/master/tools/src/main/java/com/graphhopper/tools/Measurement.java
 *https://github.com/graphhopper/graphhopper/blob/master/README.md
 *https://www.optaplanner.org/learn/useCases/vehicleRoutingProblem.html
 *
 * ///////////
 * LATER FOR THE MAPPING>>>
 *
 * There's a few open source online tools we could use:
 * https://wiki.openstreetmap.org/wiki/Mapsforge
 * https://wiki.openstreetmap.org/wiki/Cruiser
 *
 *
 */

