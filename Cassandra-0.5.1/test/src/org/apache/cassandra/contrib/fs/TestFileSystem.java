package org.apache.cassandra.contrib.fs;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

import org.apache.cassandra.service.CassandraDaemon;
import org.apache.commons.io.FileUtils;
import org.apache.thrift.transport.TTransportException;

public class TestFileSystem extends TestCase {

	private CassandraDaemon daemon;
	
	// clear cassandra stuff
	private void clearCassandra() throws IOException {
		FileUtils.deleteDirectory(new File("build/test/cassandra"));
	}

	@Override
	protected void setUp() throws Exception {
		clearCassandra();
		daemon = new CassandraDaemon();
		daemon.init(new String[]{});
		daemon.start();
	}

	@Override
	protected void tearDown() throws Exception {
		clearCassandra();
		daemon.stop();
	}

	public void testMkDir() throws TTransportException, IOException {
		FileSystem fs = FileSystem.getInstance();
		fs.mkdir("/data");
		assertTrue(fs.existDir("/data"));
		assertTrue(fs.exist("/data"));
		
	}
}
