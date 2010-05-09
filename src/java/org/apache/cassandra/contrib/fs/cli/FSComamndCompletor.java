package org.apache.cassandra.contrib.fs.cli;

import jline.SimpleCompletor;

public class FSComamndCompletor extends SimpleCompletor {

	private static final String[] commands = { "ls", "pwd", "rm", "rmr",
			"copyFromLocal", "copyToLocal", "copyFromHDFS", "copyToHDFS",
			"newFile", "cd", "touch", "exit", "mkdir", "help", "quit", };

	public FSComamndCompletor(String[] candidateStrings) {
		super(candidateStrings);
	}

	public FSComamndCompletor() {
		this(commands);
	}

}
