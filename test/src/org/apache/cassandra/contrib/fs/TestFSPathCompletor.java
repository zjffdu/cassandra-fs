package org.apache.cassandra.contrib.fs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jline.Completor;
import jline.ConsoleReader;
import jline.JLineTestCase;

import org.apache.cassandra.contrib.fs.cli.FSPathCompleter;
import org.apache.cassandra.contrib.fs.cli.FSCliMain;

public class TestFSPathCompletor extends JLineTestCase {

	public TestFSPathCompletor(String test) {
		super(test);
	}

	public void testFSCliCompletor() throws Exception {
		// clear any current completors
		for (Iterator i = console.getCompletors().iterator(); i.hasNext(); console
				.removeCompletor((Completor) i.next())) {
			;
		}

		FSCliMain cliMain=new FSCliMain();
		cliMain.setFileSystem(new FakedFileSystem());
		cliMain.setCWD("/data2");
		console.addCompletor(new FSPathCompleter(cliMain));

		assertBuffer("/data/a.txt ", new Buffer("/data/a").op(ConsoleReader.COMPLETE));
		assertBuffer("/data/b.txt ", new Buffer("/data/b").op(ConsoleReader.COMPLETE));
		assertBuffer("/data", new Buffer("/d").op(ConsoleReader.COMPLETE));
		assertBuffer("/data", new Buffer("/data").op(ConsoleReader.COMPLETE));
		
		assertBuffer("c.txt ", new Buffer("c").op(ConsoleReader.COMPLETE ));
		assertBuffer("d.txt ", new Buffer("d").op(ConsoleReader.COMPLETE ));
	}


}
