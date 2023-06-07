package exceptions;

public class InvalidLocatorStrategy extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public InvalidLocatorStrategy(String message) {
		super(message);
	}
}
