package org.apache.cassandra.contrib.fs;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cassandra.contrib.fs.util.Bytes;
import org.apache.log4j.Logger;
import org.apache.thrift.transport.TTransportException;

/**
 * The path here must be absolute, the relative path is handled by FSCliMain
 * 
 * @author zhanje
 * 
 */
public class CassandraFileSystem implements IFileSystem {

	private static Logger LOGGER = Logger.getLogger(CassandraFileSystem.class);

	private static SimpleDateFormat format = new SimpleDateFormat(
			"yyyy/MM/dd hh:mm");

	private static IFileSystem instance;

	private CassandraFacade facade;

	private byte[] buffer = new byte[FSConstants.BlockSize];

	public static IFileSystem getInstance() throws TTransportException,
			IOException {
		if (instance == null) {
			synchronized (CassandraFileSystem.class) {
				if (instance == null) {
					instance = new CassandraFileSystem();
				}
			}
		}
		return instance;
	}

	private CassandraFileSystem() throws TTransportException, IOException {
		this.facade = CassandraFacade.getInstance();
		if (!existDir("/")) {
			mkdir("/");
		}
	}

	public void createFile(String path, byte[] content) throws IOException {
		PathUtil.checkPath(path);
		path = PathUtil.normalizePath(path);
		String parent = PathUtil.getParent(path);
		if (!existDir(parent)) {
			mkdir(parent);
		}

		for (int i = 0; i < content.length / FSConstants.BlockSize + 1; ++i) {
			int from = i * FSConstants.BlockSize;
			int to = ((i + 1) * FSConstants.BlockSize > content.length) ? content.length
					: (i + 1) * FSConstants.BlockSize;
			facade.put(path, FSConstants.FileCF + ":" + FSConstants.ContentAttr
					+ "_" + i, Arrays.copyOfRange(content, from, to));
		}

		Map<byte[], byte[]> map = new HashMap<byte[], byte[]>();
		map.put(Bytes.toBytes(FSConstants.TypeAttr), Bytes.toBytes("File"));
		map.put(Bytes.toBytes(FSConstants.LengthAttr), Bytes
				.toBytes(content.length));
		map.put(Bytes.toBytes(FSConstants.LastModifyTime), Bytes.toBytes(format
				.format(new Date())));
		map.put(Bytes.toBytes(FSConstants.OwnerAttr), FSConstants.DefaultOwner);
		map.put(Bytes.toBytes(FSConstants.GroupAttr), FSConstants.DefaultGroup);
		facade.batchPut(path, FSConstants.FileCF, null, map, false);

		// add meta data for parent, except the Content
		map = new HashMap<byte[], byte[]>();
		map.put(Bytes.toBytes(FSConstants.TypeAttr), Bytes.toBytes("File"));
		map.put(Bytes.toBytes(FSConstants.LengthAttr), Bytes
				.toBytes(content.length));
		map.put(Bytes.toBytes(FSConstants.LastModifyTime), Bytes.toBytes(format
				.format(new Date())));
		map.put(Bytes.toBytes(FSConstants.OwnerAttr), FSConstants.DefaultOwner);
		map.put(Bytes.toBytes(FSConstants.GroupAttr), FSConstants.DefaultGroup);

		facade.batchPut(parent, FSConstants.FolderCF, path, map, true);
	}

	@Override
	public void createFile(String path, InputStream in) throws IOException {
		PathUtil.checkPath(path);
		path = PathUtil.normalizePath(path);
		String parent = PathUtil.getParent(path);
		if (!existDir(parent)) {
			mkdir(parent);
		}

		int length = 0;
		int index = 0;
		int num = 0;
		while (true) {
			num = in.read(buffer);
			if (num == -1) {
				break;
			}
			byte[] content = new byte[num];
			System.arraycopy(buffer, 0, content, 0, num);
			length += num;
			facade.put(path, FSConstants.FileCF + ":" + FSConstants.ContentAttr
					+ "_" + index++, content);
		}
		Map<byte[], byte[]> map = new HashMap<byte[], byte[]>();
		map.put(Bytes.toBytes(FSConstants.TypeAttr), Bytes.toBytes("File"));
		map.put(Bytes.toBytes(FSConstants.LengthAttr), Bytes.toBytes(length));
		map.put(Bytes.toBytes(FSConstants.LastModifyTime), Bytes.toBytes(format
				.format(new Date())));
		map.put(Bytes.toBytes(FSConstants.OwnerAttr), FSConstants.DefaultOwner);
		map.put(Bytes.toBytes(FSConstants.GroupAttr), FSConstants.DefaultGroup);
		facade.batchPut(path, FSConstants.FileCF, null, map, false);

		// add meta data for parent, except the Content
		map = new HashMap<byte[], byte[]>();
		map.put(Bytes.toBytes(FSConstants.TypeAttr), Bytes.toBytes("File"));
		map.put(Bytes.toBytes(FSConstants.LengthAttr), Bytes.toBytes(length));
		map.put(Bytes.toBytes(FSConstants.LastModifyTime), Bytes.toBytes(format
				.format(new Date())));
		map.put(Bytes.toBytes(FSConstants.OwnerAttr), FSConstants.DefaultOwner);
		map.put(Bytes.toBytes(FSConstants.GroupAttr), FSConstants.DefaultGroup);

		facade.batchPut(parent, FSConstants.FolderCF, path, map, true);
	}

	public boolean deleteFile(String path) throws IOException {
		PathUtil.checkPath(path);
		path = PathUtil.normalizePath(path);
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
		PathUtil.checkPath(path);
		path = PathUtil.normalizePath(path);
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

	public byte[] readFileToBytes(String path) throws IOException {
		PathUtil.checkPath(path);
		path = PathUtil.normalizePath(path);
		LOGGER.debug("Reading file '" + path + "'");
		return facade.get(path, FSConstants.FileCF + ":"
				+ FSConstants.ContentAttr);
	}

	@Override
	public InputStream readFile(String path) throws IOException {
		PathUtil.checkPath(path);
		path = PathUtil.normalizePath(path);
		LOGGER.debug("Reading file '" + path + "'");
		return new CassandraInputStream(path, facade);
	}

	public boolean mkdir(String path) throws IOException {
		PathUtil.checkPath(path);
		path = PathUtil.normalizePath(path);
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
			Map<byte[], byte[]> map = new HashMap<byte[], byte[]>();
			map.put(Bytes.toBytes(FSConstants.TypeAttr), Bytes
					.toBytes("Folder"));
			map.put(Bytes.toBytes(FSConstants.LengthAttr), Bytes.toBytes(0));
			map.put(Bytes.toBytes(FSConstants.LastModifyTime), Bytes
					.toBytes(format.format(new Date())));
			map.put(Bytes.toBytes(FSConstants.OwnerAttr),
					FSConstants.DefaultOwner);
			map.put(Bytes.toBytes(FSConstants.GroupAttr),
					FSConstants.DefaultGroup);

			facade.batchPut(parent, FSConstants.FolderCF, path, map, true);
		}
		LOGGER.debug("Creat dir '" + path + "' succesfully");
		return true;
	}

	public List<Path> list(String path) throws IOException {
		PathUtil.checkPath(path);
		List<Path> result = new ArrayList<Path>();
		path = PathUtil.normalizePath(path);
		if (existDir(path)) {
			result = facade.list(path, FSConstants.FolderCF, false);
		} else if (existFile(path)) {
			result = facade.list(path, FSConstants.FileCF, false);
		} else {
			return result;
		}

		// set the max size length, the max size length is for cli formatting
		int max = 0;
		for (Path child : result) {
			if ((child.getLength() + "").length() > max) {
				max = (child.getLength() + "").length();
			}
		}
		Path.MaxSizeLength = max;

		return result;
	}

	public List<Path> listAll(String path) throws IOException {
		PathUtil.checkPath(path);
		path = PathUtil.normalizePath(path);
		return facade.list(path, FSConstants.FolderCF, true);
	}

	public boolean existDir(String path) throws IOException {
		PathUtil.checkPath(path);
		path = PathUtil.normalizePath(path);
		return facade.list(path, FSConstants.FolderCF, true).size() != 0;
	}

	public boolean existFile(String path) throws IOException {
		PathUtil.checkPath(path);
		path = PathUtil.normalizePath(path);
		return facade.list(path, FSConstants.FileCF, true).size() != 0;
	}

	public boolean exist(String path) throws IOException {
		return existDir(path) || existFile(path);
	}

	public static void main(String[] args) throws IOException,
			TTransportException {
		System.setProperty("storage-config", "conf");
		IFileSystem fs = CassandraFileSystem.getInstance();
		List<Path> children = fs.list("/data");
		List<Path> files = new ArrayList<Path>();

		// fs.mkdir("/data");
		// System.out.println(fs.exist("/data"));
		//		
		// fs.deleteDir("/data",true);
		// System.out.println(fs.exist("/data"));
		// fs.createFile("/data/1.txt", Bytes.toBytes("hello world3"));
		// System.out.println(fs.exist("/data"));
		// System.out.println(fs.exist("/data/a.txt"));
		// fs.deleteFile("/data/1.txt");
		// System.out.println(fs.exist("/data/a.txt"));

	}

}
