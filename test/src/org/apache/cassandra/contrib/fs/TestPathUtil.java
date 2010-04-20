package org.apache.cassandra.contrib.fs;

import junit.framework.TestCase;

public class TestPathUtil extends TestCase {

	public void testGetParent() {
		assertEquals("/data/metrics", PathUtil.getParent("/data/metrics/a.txt"));
		assertEquals("/", PathUtil.getParent("/data"));
		assertEquals(null, PathUtil.getParent("/"));
	}

	public void testRemoveDirSuffix() {
		assertEquals("/", PathUtil.removeTrailingSlash("/"));
		assertEquals("/data", PathUtil.removeTrailingSlash("/data/"));
	}
}
