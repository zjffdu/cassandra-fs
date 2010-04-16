package org.apache.cassandra.contrib.fs;

import java.io.IOException;

import org.apache.cassandra.thrift.CassandraDaemon;
import org.apache.thrift.transport.TTransportException;

public class EmbeddedCassandra extends Thread {

	private CassandraDaemon daemon;

	public EmbeddedCassandra() {
		daemon = new CassandraDaemon();
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
		EmbeddedCassandra thread = new EmbeddedCassandra();
		thread.setDaemon(true);
		thread.start();
	}

}
