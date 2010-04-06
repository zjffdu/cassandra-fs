package org.apache.cassandra.contrib.fs;

public class PathUtil {

	public static String getParent(String path) {

		if (path.equals("/")) {
			return null;
		}
		if (path.endsWith("/")) {
			path = path.substring(0, path.length() - 1);
		}

		int index = path.lastIndexOf("/");
		if (index == -1) {
			throw new RuntimeException(path
					+ " is a relative path which is not supported here.");
		}
		if (index == 0) {
			return "/";
		} else {
			return path.substring(0, index);
		}
	}

	public static String removeDirSuffix(String path) {
		if (path.endsWith("/")) {
			path = path.substring(0, path.length() - 1);
		}
		return path;
	}

	public static void checkDirPath(String path) {
		if (path.contains(":")) {
			throw new RuntimeException("Path can not contains ':'");
		}
	}

	public static void checkFilePath(String path) {
		if (path.contains(":")) {
			throw new RuntimeException("Path can not contains ':'");
		}
	}
}
