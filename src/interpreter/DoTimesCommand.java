package interpreter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Implements DoTimes command, executing a loop a certain number of times and setting
 * a variable to the current loop count each time
 * @author Sarahbland
 *
 */
class DoTimesCommand extends Command{
	
	private String toExecute;
	private String myTempVar;
	private Command endExpressionCommand;
	private CommandTreeBuilder myBuilder;
	private Turtle myAllTurtles;
	
	protected DoTimesCommand(Command tempVarCommand, Command endExpression, Command toExecuteCommand,Turtle activeTurtles, Turtle allTurtles, 
			Map<String, Double> variables, Map<String, String> userDefCommands, Map<String, Integer> userDefCommandNumArgs) {
		myTempVar = ((StringCommand)tempVarCommand).getString();
		toExecute = ((StringCommand)toExecuteCommand).getString();
		endExpressionCommand = endExpression;
		myBuilder = new CommandTreeBuilder(variables, userDefCommands, userDefCommandNumArgs);
		setActiveTurtles(activeTurtles);
		myAllTurtles = allTurtles;
	}
	@Override
	protected double execute() throws UnidentifiedCommandException {
		String[] executeArray = toExecute.split(" ");
		double ending = endExpressionCommand.execute();
		double returnVal = -1.0;
		List<Integer> indices = getTempVarIndices(myTempVar, executeArray);
		for(Double k = 1.0; k<=ending; k+=1) {
			findAndReplace(indices, k, executeArray);
//			for(int i = 0; i<executeArray.length; i+=1) {
//				System.out.println("executing " + executeArray[i]);
//			}
			try {
			returnVal = myBuilder.buildAndExecute(myAllTurtles, getActiveTurtles(), executeArray, true);
			}
			catch(Exception e) {
				e.printStackTrace();
				throw new UnidentifiedCommandException("One or more commands has incorrect number of arguments");
			}

		}
		return returnVal;
	}
	private List<Integer> getTempVarIndices(String tempVar, String[] toExecute){
		ArrayList<Integer> indices = new ArrayList<>();
		for(int k = 0; k < toExecute.length; k+=1) {
			if(toExecute[k].equals(tempVar)) {
				indices.add(k);
			}
		}
		return indices;
	}
	private void findAndReplace(List<Integer> indices, Double replace, String[] toReplace) {
		for(int i: indices) {
			toReplace[i] = replace.toString();
		}
	}
}
