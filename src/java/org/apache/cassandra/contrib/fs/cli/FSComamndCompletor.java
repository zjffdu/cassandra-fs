package org.apache.cassandra.contrib.fs.cli;

import jline.SimpleCompletor;

public class FSComamndCompletor extends SimpleCompletor {

	private static final String[] commands = { "ls", "rm", "rmr",
			"copyFromLocal", "copyToLocal", "copyFromHDFS","newFile", "cd", "touch", "exit", "mkdir",
			"help", "quit", };

	public FSComamndCompletor(String[] candidateStrings) {
		super(candidateStrings);
	}
	
	public FSComamndCompletor(){
		this(commands);
	}

}
