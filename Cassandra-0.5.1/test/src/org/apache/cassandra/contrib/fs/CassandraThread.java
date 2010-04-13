package org.apache.cassandra.contrib.fs;

import java.io.IOException;

import org.apache.cassandra.service.CassandraDaemon;
import org.apache.thrift.transport.TTransportException;

public class CassandraThread extends Thread {

	private CassandraDaemon daemon;

	public CassandraThread() {
		CassandraDaemon daemon = new CassandraDaemon();
		try {
			daemon.init(null);
		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		daemon.start();
	}

	public static void main(String[] args) {
		CassandraThread thread = new CassandraThread();
		thread.setDaemon(true);
		thread.start();
	}

}
