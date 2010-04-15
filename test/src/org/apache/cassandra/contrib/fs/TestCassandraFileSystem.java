package org.apache.cassandra.contrib.fs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;

import org.apache.cassandra.config.DatabaseDescriptor;
import org.apache.commons.io.FileUtils;
import org.apache.thrift.transport.TTransportException;

public class TestCassandraFileSystem extends TestCase {

	private EmbeddedCassandra daemon;

	// clear cassandra stuff
	private void clearCassandraStuff() throws IOException {
		List<String> dirs = new ArrayList<String>();
		for (String dataDir : DatabaseDescriptor.getAllDataFileLocations()) {
			dirs.add(dataDir);
		}
		dirs.add(DatabaseDescriptor.getLogFileLocation());

		for (String dir : dirs) {
			FileUtils.deleteDirectory(new File(dir));
		}
	}

	@Override
	protected void setUp() throws Exception {
		clearCassandraStuff();
		daemon = new EmbeddedCassandra();
		daemon.start();
	}

	public void testBasicOperation() throws TTransportException, IOException {
		// mkdir
		IFileSystem fs = CassandraFileSystem.getInstance();
		fs.mkdir("/data");
		assertTrue(fs.existDir("/data"));
		assertTrue(fs.exist("/data"));

		fs.mkdir("/usr/zjffdu");
		assertTrue(fs.existDir("/usr/zjffdu"));
		assertTrue(fs.existDir("/usr"));

		// list files
		List<Path> paths = fs.list("/");
		Set<String> expected = new HashSet<String>();
		expected.add("/data");
		expected.add("/usr");
		for (Path path : paths) {
			assertTrue(expected.contains(path.getURL()));
			assertTrue(path.isDir());
		}

		// create file
		String DummyContent = "dummy content";
		fs.createFile("/data/a.txt", DummyContent.getBytes());
		assertTrue(fs.existFile("/data/a.txt"));
		assertTrue(new String(fs.readFile("/data/a.txt")).equals(DummyContent));

		paths = fs.list("/data");
		assertEquals(1, paths.size());
		assertEquals("/data/a.txt", paths.get(0).getURL());
		assertFalse(paths.get(0).isDir());

		// delete folder
		assertFalse(fs.deleteDir("/data", false));
		assertTrue(fs.deleteDir("/usr/zjffdu", false));

		// delete file
		assertTrue(fs.deleteFile("/data/a.txt"));
		assertEquals(0, fs.list("/data").size());
	}
}
