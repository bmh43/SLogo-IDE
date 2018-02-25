package screen;

import interpreter.Controller;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ErrorScreen implements Screen {
    private final String LANGUAGE_CHOOSER_TEXT = "Select a Language";
    private Parent ROOT;
    private Controller PROGRAM_CONTROLLER;
    private String ERROR_MESSAGE;
    
    public ErrorScreen(Controller programController, String errorMessage) {
	PROGRAM_CONTROLLER = programController;
	ERROR_MESSAGE = errorMessage;
    }

    @Override
    public void makeRoot() {
	Label errorLabel = errorLabel(ERROR_MESSAGE);
	VBox rootBox = new VBox(5, errorLabel);
	rootBox.setId("startScreenRoot");
	ROOT = rootBox;
    }

    @Override
    public Parent getRoot() {
	if (ROOT == null) {
	    makeRoot();
	}
	return ROOT;
    }

    @Override
    public void changeBackgroundColor(String color) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void changeRightPanel(Parent panelRoot) {
	// TODO Auto-generated method stub
	
    }
    
    /**
     * Creates a Label to display to the user on the Error Screen that contains the Error
     * Message indicating the cause of the error.
     * 
     * @param errorMessage: The String defining the Error Message to display to the user
     * @return Label: A JavaFX Label object displaying the Error Message
     */
    private Label errorLabel(String errorMessage) {
	Label errorLabel = new Label(errorMessage);
	errorLabel.setId("errorLabel");
	return errorLabel;
    }

}
