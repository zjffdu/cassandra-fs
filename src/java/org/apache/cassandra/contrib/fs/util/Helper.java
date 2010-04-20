package org.apache.cassandra.contrib.fs.util;

public class Helper {

	public static boolean isNullOrEmpty(String str){
		if (str==null || str.trim().length()==0){
			return true;
		}else{
			return false;
		}
	}
}
