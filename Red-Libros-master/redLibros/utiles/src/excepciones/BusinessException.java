package excepciones;

public class BusinessException extends Exception {
	public BusinessException() {
		super();
	}
	public BusinessException(String msg) {
		super(msg);
	}
}
