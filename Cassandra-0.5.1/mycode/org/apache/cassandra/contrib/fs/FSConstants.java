package org.apache.cassandra.contrib.fs;

public class FSConstants {

	public final static String KeySpace = "FS";
	public final static String FolderCF = "Folder";
	public final static String FileCF = "File";
	public final static String FolderFlag = "$_Folder_$";

	// attribute
	public final static String TypeAttr = "Type"; // file or folder
	public final static String ContentAttr = "Content";
	public final static String SizeAttr = "Size";
	public final static String LastModifyDate = "LastModify";
}
