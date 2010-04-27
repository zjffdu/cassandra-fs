package org.apache.cassandra.contrib.fs;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.cassandra.thrift.NotFoundException;

public class CassandraInputStream extends InputStream {

	private InputStream curBlockStream;

	private int blockIndex = 0;

	private String path;

	private CassandraFacade facade;

	public CassandraInputStream(String path, CassandraFacade facade)
			throws IOException {
		this.path = path;
		this.facade = facade;
		byte[] bytes = facade.get(path, FSConstants.FileCF + ":"
				+ FSConstants.ContentAttr);
		this.curBlockStream = new ByteArrayInputStream(bytes);
		this.blockIndex++;
	}

	@Override
	public int read() throws IOException {
		int next = curBlockStream.read();
		if (next != -1) {
			return next;
		} else {
			try {
				byte[] bytes = facade.get(path + "_$" + blockIndex++,
						FSConstants.FileCF + ":" + FSConstants.ContentAttr);
				curBlockStream = new ByteArrayInputStream(bytes);
				return curBlockStream.read();
			} catch (IOException e) {
				if (e.getCause() instanceof NotFoundException) {
					return -1;
				} else {
					throw e;
				}
			}
		}
	}

}
