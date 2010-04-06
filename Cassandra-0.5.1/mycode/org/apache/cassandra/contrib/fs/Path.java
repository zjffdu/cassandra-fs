package org.apache.cassandra.contrib.fs;

import java.util.HashMap;
import java.util.Map;

public class Path {

	private String url;

	private boolean isDir;

	private int version = 1;

	// add other attributes, such as permission

	private Map<String, String> attributes = new HashMap<String, String>();

	public Path(String url) {
		this(url, false);
	}

	public Path(String url, boolean isDir) {
		this.url = url;
		this.isDir = isDir;
	}
	

}
