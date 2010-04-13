package org.apache.cassandra.contrib.fs;

import org.apache.cassandra.contrib.fs.util.Bytes;

public class FSConstants {

	// cluster config
	public final static String HostName = "local";
	public final static int ThriftPort = 9160;

	// schema config
	public final static String KeySpace = "FS";
	public final static String FolderCF = "Folder";
	public final static String FileCF = "File";
	public final static String FolderFlag = "$_Folder_$";

	// attribute
	public final static String TypeAttr = "Type"; // file or folder
	public final static String ContentAttr = "Content";
	public final static String LengthAttr = "Length";
	public final static String LastModifyTime = "LastModifyTime";
	public final static String OwnerAttr = "Owner";
	public final static String GroupAttr = "Group";

	// default owner and group

	public final static byte[] DefaultOwner = Bytes.toBytes("root");
	public final static byte[] DefaultGroup = Bytes.toBytes("supergroup");

	// size limitation
	public final static int MaxFileSize = 500 * 1024 * 1024;
}
