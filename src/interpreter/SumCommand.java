package interpreter;

/**
 * returns sum of the values of expr1 and expr2
 * 
 * @author Benjamin Hodgson
 * @date 2/26/18
 *
 */
public class SumCommand implements Command{

    	double EXPR1;
    	double EXPR2;
	protected SumCommand(Command expr1, Command expr2) {
		EXPR1 = expr1.execute();
		EXPR2 = expr2.execute();
	}
	@Override
	public double execute() {
		return EXPR1 + EXPR2;
	}
	public int getNumArgs() {
		return 2;
	}
}