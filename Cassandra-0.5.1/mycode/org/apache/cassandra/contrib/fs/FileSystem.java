package org.apache.cassandra.contrib.fs;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.thrift.transport.TTransportException;

public class FileSystem {

	private static Logger LOGGER = Logger.getLogger(FileSystem.class);

	private static FileSystem instance;

	private CassandraFacade facade;

	public static FileSystem getInstance() throws TTransportException,
			IOException {
		if (instance == null) {
			synchronized (FileSystem.class) {
				if (instance == null) {
					instance = new FileSystem();
				}
			}
		}
		return instance;
	}

	private FileSystem() throws TTransportException, IOException {
		this.facade = CassandraFacade.getInstance();
		if (!existDir("/")) {
			mkdir("/");
		}
	}

	public void createFile(String path, String content) throws IOException {
		PathUtil.checkFilePath(path);
		String parent = PathUtil.getParent(path);
		if (!existDir(parent)) {
			mkdir(parent);
		}
		facade.put(path, FSConstants.FileCF + ":" + FSConstants.ContentAttr
				+ ":", content);
		facade.put(path, FSConstants.FileCF + ":" + FSConstants.TypeAttr + ":",
				"File");
		facade.put(parent, FSConstants.FolderCF + ":" + path + ":"
				+ FSConstants.TypeAttr, "File");
	}

	public boolean deleteFile(String path) throws IOException{
		if(!existFile(path)){
			LOGGER.warn("File '"+path+"' can not been deleted, because it doesn't exist");
			return false;
		}
		String parent = PathUtil.getParent(path);
		facade.delete(path,);
	}
	
	public void deleteDir(String path) throws IOException{
		
	}
	
	public byte[] readFile(String path) throws IOException {
		LOGGER.debug("Read file '" + path + "'");
		return facade.get(path, FSConstants.FileCF + ":"
				+ FSConstants.ContentAttr);
	}

	public boolean mkdir(String path) throws IOException {
		PathUtil.checkDirPath(path);
		if (existDir(path)) {
			LOGGER.warn("'" + path + "' is already existed");
			return false;
		}
		String parent = PathUtil.getParent(path);
		if (parent != null && !existDir(parent)) {
			mkdir(parent);
		}

		facade.put(path, FSConstants.FolderCF + ":" + FSConstants.FolderFlag
				+ ":" + FSConstants.TypeAttr, "");
		if (parent != null) {
			facade.put(parent, FSConstants.FolderCF + ":" + path + ":"
					+ FSConstants.TypeAttr, "Folder");
		}
		LOGGER.debug("Creat dir '" + path + "' succesfully");
		return true;
	}

	public List<String> list(String path) throws IOException {
		return facade.list(path, FSConstants.FolderCF);
	}

	public boolean existDir(String path) throws IOException {
		return facade.list(path, FSConstants.FolderCF).size() != 0;
	}

	public boolean existFile(String path) throws IOException {
		return facade.list(path, FSConstants.FileCF).size() != 0;
	}

	public boolean exist(String path) throws IOException {
		return existDir(path) || existFile(path);
	}

	public static void main(String[] args) throws IOException,
			TTransportException {
		FileSystem fs = FileSystem.getInstance();

		fs.createFile("/data/1.txt", "hello world3");
		fs.mkdir("/data/metrics");
		fs.mkdir("/data/metrics/1");
		fs.mkdir("/data/metrics/2");
		fs.mkdir("/data/metrics1");
		fs.mkdir("/data/metrics23");
		List<String> files = fs.list("/data2");
		for (String file : files) {
			System.out.println(file);
		}
		// String content = new String(fs.readFile("/data/1.txt"));
		// System.out.println(content);
		System.out.println(fs.existDir("/data"));
		System.out.println(fs.existFile("/data/1.txt"));
		// System.out.println(new String(fs.readFile("/data/1.txt")));
		// System.out.println(fs.existDir("/data/metrics2"));
	}
}
