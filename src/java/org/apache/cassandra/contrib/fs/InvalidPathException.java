package org.apache.cassandra.contrib.fs;

public class InvalidPathException extends Exception{

	public InvalidPathException() {
		super();
	}

	public InvalidPathException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidPathException(String message) {
		super(message);
	}

	public InvalidPathException(Throwable cause) {
		super(cause);
	}

}
