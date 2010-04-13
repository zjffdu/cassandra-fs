package org.apache.cassandra.contrib.fs;

import junit.framework.TestCase;

public class TestPath extends TestCase {

	public void testPath() {
		Path path = new Path("/data", true);
		assertEquals("/data", path.getURL());
		assertTrue(path.isDir());
	}
}
