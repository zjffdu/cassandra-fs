package org.apache.cassandra.contrib.fs;

import org.apache.cassandra.contrib.fs.util.Bytes;

public class FSConstants {

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
	public final static int BlockSize = 10 * 1024 * 1024;
	// client property
	public final static String Hosts = "cassandra.client.hosts";
	public final static String ExhaustedPolicy = "";
	public final static String MaxActive = "cassandra.client.maxActive";
	public final static String MaxIdle = "cassandra.client.maxIdle";
	public final static String MaxWaitTimeWhenExhausted = "cassandra.client.maxWaitTimeWhenExhausted";
	public final static String CassandraThriftSocketTimeout = "cassandra.client.cassandraThriftSocketTimeout";
}
