package org.apache.cassandra.contrib.fs.cli;

import jline.SimpleCompletor;

public class FSComamndCompletor extends SimpleCompletor {

	private static final String[] commands = { "cp", "ls", "mv", "rm", "rmdir",
			"copyFromLocal", "copyToLocal", "newFile", "cd", "touch", "exit", "mkdir",
			"help", "quit", };

	public FSComamndCompletor(String[] candidateStrings) {
		super(candidateStrings);
	}
	
	public FSComamndCompletor(){
		this(commands);
	}

}
