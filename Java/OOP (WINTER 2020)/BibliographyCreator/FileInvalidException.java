package ProjectFiles;

/**
 * Exception used to signal an error when parsing an input file.
 *
 */

public class FileInvalidException extends Exception {
	
	/**
	 * Default constructor using "Error: Input file cannot be parsed due to missing information" as the error  message.
	 */
	public FileInvalidException() {
		super("Error: Input file cannot be parsed due to missing information");
	}
	
	/**
	 * Constructor using user defined string as the error message.
	 */
	public FileInvalidException(String message) {
		super(message);
	}

}
