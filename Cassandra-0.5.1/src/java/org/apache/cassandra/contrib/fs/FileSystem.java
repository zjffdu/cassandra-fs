package org.apache.cassandra.contrib.fs;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.cassandra.contrib.fs.util.Bytes;
import org.apache.log4j.Logger;
import org.apache.thrift.transport.TTransportException;

/**
 * The path here must be absolute, the relative path is handled by FSCliMain
 * 
 * @author zhanje
 * 
 */
public class FileSystem {

	private static Logger LOGGER = Logger.getLogger(FileSystem.class);

	private static SimpleDateFormat format = new SimpleDateFormat(
			"yyyy/MM/dd hh:mm");

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

	public void createFile(String path, byte[] content) throws IOException {
		PathUtil.checkFilePath(path);
		String parent = PathUtil.getParent(path);
		if (!existDir(parent)) {
			mkdir(parent);
		}

		facade.put(path, FSConstants.FileCF + ":" + FSConstants.ContentAttr
				+ ":", content);
		facade.put(path, FSConstants.FileCF + ":" + FSConstants.TypeAttr, Bytes
				.toBytes("File"));
		facade.put(path, FSConstants.FileCF + ":" + FSConstants.LengthAttr,
				Bytes.toBytes(content.length));
		facade.put(path, FSConstants.FileCF + ":" + FSConstants.LastModifyTime,
				Bytes.toBytes(format.format(new Date())));
		facade.put(path, FSConstants.FileCF + ":" + FSConstants.OwnerAttr,
				FSConstants.DefaultOwner);
		facade.put(path, FSConstants.FileCF + ":" + FSConstants.GroupAttr,
				FSConstants.DefaultGroup);

		// add meta data for parent, except the Content
		facade.put(parent, FSConstants.FolderCF + ":" + path + ":"
				+ FSConstants.TypeAttr, Bytes.toBytes("File"));
		facade.put(parent, FSConstants.FolderCF + ":" + path + ":"
				+ FSConstants.LengthAttr, Bytes.toBytes(content.length));
		facade.put(parent, FSConstants.FolderCF + ":" + path + ":"
				+ FSConstants.LastModifyTime, Bytes.toBytes(format
				.format(new Date())));
		facade.put(parent, FSConstants.FolderCF + ":" + path + ":"
				+ FSConstants.OwnerAttr, FSConstants.DefaultOwner);
		facade.put(parent, FSConstants.FolderCF + ":" + path + ":"
				+ FSConstants.GroupAttr, FSConstants.DefaultGroup);
	}

	public boolean deleteFile(String path) throws IOException {
		if (!existFile(path)) {
			LOGGER.warn("File '" + path
					+ "' can not been deleted, because it doesn't exist");
			return false;
		}
		String parent = PathUtil.getParent(path);
		facade.delete(path);
		facade.delete(parent, FSConstants.FolderCF, path);
		return true;
	}

	public boolean deleteDir(String path, boolean recursive) throws IOException {
		if (!exist(path)) {
			LOGGER.warn("Folder '" + path
					+ "' can not been deleted, because it doesn't exist");
			return false;
		}
		if (!recursive) {
			List<Path> paths = list(path);
			if (paths.size() > 0) {
				LOGGER.warn("Folder '" + path
						+ "' is not empty, and can not been deleted");
				return false;
			} else {
				String parent = PathUtil.getParent(path);
				facade.delete(path);
				facade.delete(parent, FSConstants.FolderCF, path);
				return true;
			}
		} else {
			List<Path> paths = list(path);
			for (Path p : paths) {
				if (p.isDir()) {
					deleteDir(p.getURL(), true);
				} else {
					deleteFile(p.getURL());
				}
			}
			deleteDir(path, false);
			return true;
		}
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
				+ ":" + FSConstants.TypeAttr, Bytes.toBytes("Dummy"));
		if (parent != null) {
			facade.put(parent, FSConstants.FolderCF + ":" + path + ":"
					+ FSConstants.TypeAttr, Bytes.toBytes("Folder"));
			facade.put(parent, FSConstants.FolderCF + ":" + path + ":"
					+ FSConstants.LengthAttr, Bytes.toBytes(0));
			facade.put(parent, FSConstants.FolderCF + ":" + path + ":"
					+ FSConstants.LastModifyTime, Bytes.toBytes(format
					.format(new Date())));
			facade.put(parent, FSConstants.FolderCF + ":" + path + ":"
					+ FSConstants.OwnerAttr, FSConstants.DefaultOwner);
			facade.put(parent, FSConstants.FolderCF + ":" + path + ":"
					+ FSConstants.GroupAttr, FSConstants.DefaultGroup);
		}
		LOGGER.debug("Creat dir '" + path + "' succesfully");
		return true;
	}

	public List<Path> list(String path) throws IOException {
		if (existDir(path)) {
			return facade.list(path, FSConstants.FolderCF, false);
		} else if (existFile(path)) {
			return facade.list(path, FSConstants.FileCF, false);
		} else {
			return new ArrayList<Path>();
		}
	}

	public List<Path> listAll(String path) throws IOException {
		return facade.list(path, FSConstants.FolderCF, true);
	}

	public boolean existDir(String path) throws IOException {
		return facade.list(path, FSConstants.FolderCF, true).size() != 0;
	}

	public boolean existFile(String path) throws IOException {
		return facade.list(path, FSConstants.FileCF, true).size() != 0;
	}

	public boolean exist(String path) throws IOException {
		return existDir(path) || existFile(path);
	}

	public static void main(String[] args) throws IOException,
			TTransportException {
		System.setProperty("storage-config", "conf");
		FileSystem fs = FileSystem.getInstance();
		fs.mkdir("/data");
		System.out.println(fs.exist("/data"));
		
		fs.deleteDir("/data",true);
		System.out.println(fs.exist("/data"));
		fs.createFile("/data/1.txt", Bytes.toBytes("hello world3"));
		System.out.println(fs.exist("/data"));
		System.out.println(fs.exist("/data/a.txt"));
		 fs.deleteFile("/data/1.txt");
		 System.out.println(fs.exist("/data/a.txt"));
//		// List<Path> files = fs.list("/");
//		// // for (String file : files) {
//		// // System.out.println(file);
//		// // }
//		System.out.println(fs.existFile("/data/1.txt"));
//		System.out.println(new String(fs.readFile("/data/1.txt")));
//
//		// // System.out.println(fs.existDir("/data"));
//		// fs.deleteDir("/data");
//		//
//		List<Path> files = fs.list("/");
//		for (Path path : files) {
//			System.out.println(path);
//		}
//
//		files = fs.list("/data");
//		for (Path path : files) {
//			System.out.println(path);
//		}

		// System.out.println(fs.deleteDir("/usr", true));
		// System.out.println(fs.);
		// String content = new String(fs.readFile("/data/1.txt"));
		// System.out.println(content);
		// System.out.println(fs.existDir("/data"));
		// System.out.println(fs.existFile("/data/1.txt"));
		// System.out.println(new String(fs.readFile("/data/1.txt")));
		// System.out.println(fs.existDir("/data/metrics2"));
	}
}
