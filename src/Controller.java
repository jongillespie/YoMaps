import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.Toggle;
import javafx.scene.text.Text;

public class Controller {

//    @FXML private Slider desiredRoutesSlider;
//    @FXML private Text startingField, destinationField, waypointsRequiredField, waypointsAvoidField;
//    @FXML private Toggle quickestRouteToggle, shortestRouteToggle;
//    @FXML private Button goButton;


    /**
     * Exits the program
     */
    @FXML
    public void exitProgram() {
        Platform.exit();
        System.exit(0);
    }
}
