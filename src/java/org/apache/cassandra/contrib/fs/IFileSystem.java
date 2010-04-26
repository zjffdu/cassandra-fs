package org.apache.cassandra.contrib.fs;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface IFileSystem {

	public abstract void createFile(String path, byte[] content)
			throws IOException;

	public abstract void createFile(String path, InputStream in)
		throws IOException;
	
	public abstract boolean deleteFile(String path) throws IOException;

	public abstract boolean deleteDir(String path, boolean recursive)
			throws IOException;

	public abstract InputStream readFile(String path) throws IOException;
	
	public abstract boolean mkdir(String path) throws IOException;

	public abstract List<Path> list(String path) throws IOException;

	public abstract List<Path> listAll(String path) throws IOException;

	public abstract boolean existDir(String path) throws IOException;

	public abstract boolean existFile(String path) throws IOException;

	public abstract boolean exist(String path) throws IOException;

}