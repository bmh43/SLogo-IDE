package interpreter;

public class UnidentifiedCommandException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected UnidentifiedCommandException(String text) {
		super("UnidentifiedCommandException "+ text);
	}
}
