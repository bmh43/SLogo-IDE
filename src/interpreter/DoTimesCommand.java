package interpreter;
import java.util.List;
import java.util.ArrayList;

public class DoTimesCommand implements Command{
	String toExecute;
	String tempVar;
	Command endExpressionCommand;
	CommandTreeBuilder myBuilder;
	Turtle myTurtle;
	protected DoTimesCommand(Command tempVarCommand, Command endExpression, Command toExecuteCommand, Turtle turtle) {
		tempVar = ((StringCommand)tempVarCommand).getString();
		toExecute = ((StringCommand)toExecuteCommand).getString();
		endExpressionCommand = endExpression;
		myBuilder = new CommandTreeBuilder();
		myTurtle = turtle;
	}
	public double execute() throws UnidentifiedCommandException {
		String[] executeArray = toExecute.split(" ");
		double ending = endExpressionCommand.execute();
		double returnVal = 0.0;
		List<Integer> indices = getTempVarIndices(tempVar, executeArray);
		for(Double k = 1.0; k<ending; k+=1) {
			findAndReplace(indices, k, executeArray);
			myBuilder.buildAndExecute(myTurtle, executeArray);
		}
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