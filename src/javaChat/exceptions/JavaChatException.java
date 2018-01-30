/**
 * 
 */
package javaChat.exceptions;

/**
 * @author gabriel
 *
 */
public class JavaChatException extends Exception {

	private Exception exception;
	/**
	 * 
	 */
	public JavaChatException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public JavaChatException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	public JavaChatException(String message,Exception e) {
		super(message);
		this.exception= e;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public JavaChatException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public JavaChatException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public JavaChatException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	
	public Exception getException(){
		return this.exception;
	}
}
