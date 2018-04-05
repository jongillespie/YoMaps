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
 * XML File Import (Export Relevant data for future use as primary Import File)
 * Parse of Required Data
 * Constructors for said data (objects)
 *
 * -- NUTS AND BOLTS --
 * Node Class
 * Edges
 * Graph (Matrix of some type)
 *
 * -- PROCESSING --
 * Permutations (1 to 10) based on Slider Value
 * Shortest Path (Dijkstras)
 * Quickest (speed related)
 * Waypoint - Desired >> Break into parts and sum total routes.
 * Waypoint - Avoid >> check temp routes - reject matches
 *
 * -- DISPLAY / RETURN --
 * Map Tiles (Zoomed Tiles.... how the fuck!>)
 * Stroke/Line Route Indication
 * Tooltips for Road Details (bonus)
 *
 * -- JUNIT TESTING AS WE DEVELOP!!!! --
 *
 */

//TODO
