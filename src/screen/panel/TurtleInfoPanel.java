package screen.panel;

import interpreter.Controller;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import screen.UserScreen;

/**
 * 
 * @author Benjamin Hodgson
 * 
 * Class to create a side panel to display information about a specific turtle
 */
public class TurtleInfoPanel extends SpecificPanel {

    private Parent PANEL;
    private String TURTLE_ID;
    
    public TurtleInfoPanel(String id) {
	TURTLE_ID = id;
    }
    
    @Override
    public void makePanel() {
	// TODO Auto-generated method stub

    }
    
    @Override
    public Parent getPanel() {
	if (PANEL == null) {
	    makePanel();
	}
	return PANEL;
    }

    @Override
    protected BorderPane getPane() {
	// TODO Auto-generated method stub
	return null;
    }

  

    @Override
    protected UserScreen getUserScreen() {
	// TODO Auto-generated method stub
	return null;
    }

}