
public class WrongInputValueException extends Exception {
public WrongInputValueException() {
	super("Invalid number");
}

public WrongInputValueException(String exceptionDescription) {
	super(exceptionDescription);
}

@Override 
public String getMessage() {
	return super.getMessage();
}


}
