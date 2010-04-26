import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.cassandra.contrib.fs.CassandraFileSystem;
import org.apache.cassandra.contrib.fs.IFileSystem;
import org.apache.cassandra.contrib.fs.Path;
import org.apache.cassandra.thrift.InvalidRequestException;
import org.apache.cassandra.thrift.NotFoundException;
import org.apache.cassandra.thrift.TimedOutException;
import org.apache.cassandra.thrift.UnavailableException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransportException;

public class CassandraExample {
	static IFileSystem fs;
	static {
		System.setProperty("storage-config", "conf");
		try {
			fs = CassandraFileSystem.getInstance();
		} catch (TTransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static List<String> files = new ArrayList<String>();

	public static void visitNode(Path path) throws IOException {
		if (path.isDir()) {
			List<Path> children = fs.list(path.getURL());
			for (Path child : children) {
				if (child.isDir()) {
					visitNode(child);
				} else {
					files.add(child.getURL());
				}
			}
		}else{
			files.add(path.getURL());
		}
	}

	public static void main(String[] args) throws InvalidRequestException,
			UnavailableException, TimedOutException, TException,
			NotFoundException, IOException {

//		visitNode(new Path("/data/partatom",true));
//		for (String file:files){
//			System.out.println(file);
//		}
//		System.out.println("Size:"+files.size());
		FileUtils.touch(new File("/zjf/a.txt"));
		IOUtils.write("f".getBytes(), new FileOutputStream("/zjf/a.txt"));
	}
}
