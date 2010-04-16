package org.apache.cassandra.contrib.fs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cassandra.contrib.fs.permission.FsPermission;
import org.apache.cassandra.contrib.fs.util.Bytes;
import org.apache.cassandra.thrift.Column;

public class Path {

	private String url;

	private String name;

	private boolean isDir;

	private int version = 1;

	private int length;

	private String last_modification_time;

	private FsPermission permission;

	private String owner;

	private String group;

	// add other attributes,

	private Map<String, String> attributes = new HashMap<String, String>();

	public Path(String url) {
		this(url, false);
	}

	public Path(String url, boolean isDir) {
		this.url = url;
		this.name = getNameFromURL(url);
		this.isDir = isDir;
	}

	private String getNameFromURL(String url) {
		int index = url.lastIndexOf("/");
		if (index == 0) {
			return "/";
		} else {
			return url.substring(index + 1);
		}
	}

	public Path(String url, List<Column> attributes) {
		this.url = url;
		this.name = getNameFromURL(url);
		for (Column attr : attributes) {
			String attrName = new String(attr.name);
			if (attrName.equals(FSConstants.TypeAttr)) {
				String value = new String(attr.value);
				if (value.equals("File")) {
					this.isDir = false;
				} else {
					this.isDir = true;
				}
			} else if (attrName.equals(FSConstants.LastModifyTime)) {
				this.last_modification_time = Bytes.toString(attr.value);
			} else if (attrName.equals(FSConstants.OwnerAttr)) {
				this.owner = Bytes.toString(attr.value);
			} else if (attrName.equals(FSConstants.GroupAttr)) {
				this.group = Bytes.toString(attr.value);
			} else if (attrName.equals(FSConstants.LengthAttr)) {
				this.length = Bytes.toInt(attr.value);
			}
		}
	}

	public boolean isDir() {
		return this.isDir;
	}

	public String getURL() {
		return this.url;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(isDir ? "d " : "- ");
		builder.append(String.format("%-8s", owner));
		builder.append(String.format("%-14s", group));
		builder.append(String.format("%-4d ", length));
		builder.append(String.format("%16s", last_modification_time));
		builder.append(" " + url);
		return builder.toString();
	}

	public String getName() {
		return this.name;
	}

	public static void main(String[] args) {
		System.out.printf("%-10s", "zjf");
	}

}
