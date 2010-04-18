package org.apache.cassandra.contrib.fs.site;

import org.apache.thrift.transport.TTransportException;

public class CfsSiteException extends Exception{
	private String errorCode;
	public CfsSiteException(String errorCode,String msg){
		super(msg);
		this.errorCode = errorCode;
	}
	public String getErrorCode(){
		return this.errorCode;
	}
	
	public static CfsSiteException fromTTransportException(TTransportException te){
		return new CfsSiteException("TransportException",te.getMessage());
	}
}
