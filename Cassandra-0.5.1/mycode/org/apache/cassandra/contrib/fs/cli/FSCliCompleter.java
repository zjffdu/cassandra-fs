package org.apache.cassandra.contrib.fs.cli;

import jline.SimpleCompletor;

public class FSCliCompleter extends SimpleCompletor {

	private static final String[] commands = { "cp", "ls", "mv", "rm", "rmdir",
			"exit", "help", "quit", };

	public FSCliCompleter() {
		super(commands);
	}

}
