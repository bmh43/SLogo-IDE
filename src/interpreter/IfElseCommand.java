package interpreter;

import java.util.Map;

class IfElseCommand extends Command {
	private Turtle myTurtle;
	private Command myIfExprCommand; 
	private String myIfBody; 
	private String myElseBody;
	private Map<String, Double> myVariables; 
	private Map<String, String> myUserDefCommands; 

	protected IfElseCommand(Command ifExprCommand, Command ifBody, Command elseBody, Turtle turtle, 
			Map<String, Double> variables, Map<String, String> userDefCommands) {
		myTurtle = turtle;
		myIfExprCommand = ifExprCommand;
		myIfBody = ((StringCommand)ifBody).getString(); ;
		myElseBody = ((StringCommand)elseBody).getString(); ; 
		myVariables = variables; 
		myUserDefCommands = userDefCommands;
	}

	@Override
	protected double execute() throws UnidentifiedCommandException {
		double ifExprRetVal = 0;
		double ifElseRetVal = 0; 
		try {
			ifExprRetVal = myIfExprCommand.execute();
		} catch (UnidentifiedCommandException e1) {
			return ifElseRetVal; 
		} 
		String[] userInput;
		if (ifExprRetVal > 0) {
			System.out.println("if executed");
			CommandTreeBuilder buildIfBody = new CommandTreeBuilder(myVariables, myUserDefCommands); 
			userInput = myIfBody.split("\\s+");
			try {
				ifElseRetVal = buildIfBody.buildAndExecute(myTurtle, userInput, true);
			} catch (BadFormatException | UnidentifiedCommandException | MissingInformationException e) {
				return ifElseRetVal; 
			}
		}
		else {
			System.out.println("else executed");
			CommandTreeBuilder buildElseBody = new CommandTreeBuilder(myVariables, myUserDefCommands); 
			userInput = myElseBody.split("\\s+");
			try {
				ifElseRetVal = buildElseBody.buildAndExecute(myTurtle, userInput, true);
			} catch (BadFormatException | UnidentifiedCommandException | MissingInformationException e) {
				return ifElseRetVal; 
			}
		}
		return ifElseRetVal; 
	}

}
