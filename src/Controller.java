import javafx.application.Platform;
import javafx.fxml.FXML;

public class Controller {



    /**
     * Exits the program
     */
    @FXML
    public void exitProgram() {
        Platform.exit();
        System.exit(0);
    }
}
