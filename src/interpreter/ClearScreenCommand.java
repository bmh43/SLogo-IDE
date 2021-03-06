package interpreter;


/**
 * Command class that, when executed, moves the turtle back to the center of the screen and erases all previous pen
 * lines. Dependent on the CommandFactory to make it correctly and on the Turtle class to properly clear the pen
 * and move the turtle.
 * @author Sarahbland
 *
 */
class ClearScreenCommand extends Command{
    /**
     * Creates new instance of command to be executed at the proper time
     * @param turtle is turtle who needs to be returned to home
     */
    protected ClearScreenCommand(Turtle turtles) {
    		setActiveTurtles(turtles);
    }
    /**
     * Moves the turtle to the center of the screen, then clears all of the pen lines on the screen
     * @return distance turtle moved
     * @see interpreter.Command#execute()
     */
    @Override
    protected double execute() throws UnidentifiedCommandException{
    		double returnVal = getActiveTurtles().setXY(0, 0);
    		getActiveTurtles().setAngle(0);
    		getActiveTurtles().clearPen();
    		return returnVal;
    }
}
