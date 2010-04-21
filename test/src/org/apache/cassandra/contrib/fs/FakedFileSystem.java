package org.apache.cassandra.contrib.fs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakedFileSystem implements IFileSystem {

	public Map<String, List<Path>> map = new HashMap<String, List<Path>>();

	public FakedFileSystem() {
		List<Path> paths = new ArrayList<Path>();
		paths.add(new Path("/data"));
		paths.add(new Path("/data2"));
		map.put("/", paths);

		paths = new ArrayList<Path>();
		paths.add(new Path("/data/a.txt"));
		paths.add(new Path("/data/b.txt"));
		map.put("/data", paths);
		
		paths = new ArrayList<Path>();
		paths.add(new Path("/data2/c.txt"));
		paths.add(new Path("/data2/d.txt"));
		map.put("/data2", paths);
	}

	@Override
	public void createFile(String path, byte[] content) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean deleteDir(String path, boolean recursive) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteFile(String path) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean exist(String path) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean existDir(String path) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean existFile(String path) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Path> list(String path) throws IOException {
		List<Path> p = map.get(path);
		return p == null ? new ArrayList<Path>() : p;
	}

	@Override
	public List<Path> listAll(String path) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean mkdir(String path) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public byte[] readFile(String path) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}