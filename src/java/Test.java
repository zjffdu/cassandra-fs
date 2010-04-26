import java.io.File;
import java.io.IOException;
import java.net.Socket;

import me.prettyprint.cassandra.service.CassandraClientPool;
import me.prettyprint.cassandra.service.CassandraClientPoolFactory;
import me.prettyprint.cassandra.service.PoolExhaustedException;

import org.apache.cassandra.contrib.fs.cli.FSCliMain;
import org.apache.cassandra.thrift.Cassandra;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class Test {

	static int count = 0;

	private static void doAction(File root, FSCliMain cli) throws IOException {
		for (File file : root.listFiles()) {
			if (file.isDirectory()) {
				doAction(file, cli);
			} else {
				System.out.println((count++) + ":" + file.getAbsolutePath());
				cli.processCommand("copyFromLocal "
						+ file.getAbsolutePath().substring(2) + " "
						+ file.getAbsolutePath().substring(2));
			}
		}
	}

	public static void main(String[] args) throws IllegalStateException,
			PoolExhaustedException, Exception {
		CassandraClientPool clientPool = CassandraClientPoolFactory
				.getInstance().createNew(new String[] { "localhost:9160" });
		// FSCliMain cli = new FSCliMain();
		// cli.connect();
		// cli.processCommand("rmr /Java/tutorial");
		TTransport tr = new TSocket("metrics-stage-06", 9160, 60000);
		TProtocol proto = new TBinaryProtocol(tr);
		Cassandra.Client client = new Cassandra.Client(proto);
		try {
			tr.open();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// List<byte[]> columns = new ArrayList<byte[]>();
		// columns.add(FSConstants.ContentAttr.getBytes());
		// columns.add(FSConstants.GroupAttr.getBytes());
		// Map<String, List<Column>> result = ks.getRangeSlice(new ColumnParent(
		// "File"), new SlicePredicate().setColumn_names(columns), "", "",
		// 100);
		// for (Map.Entry<String, List<Column>> entry : result.entrySet()) {
		// System.out.println(entry.getKey());
		// for (Column col : entry.getValue()) {
		// System.out.println(new String(col.getName()) + ":"
		// + new String(col.getValue()));
		// }
		// }
		// System.out.println(System.getProperty("user.home"));
		// doAction(new File("/Java"), cli);
		// cli.connect();
		// cli.processCommand("copyToLocal /Music2 /Music2");

		// IFileSystem fs=CassandraFileSystem.getInstance();
		// List<Path> paths=fs.list("/Music2");
		// for (Path p:paths){
		// System.out.println(p.getURL());
		// }

		System.out.println(java.nio.charset.Charset.defaultCharset().name());

		// File[] files=new File("E:\\Music").listFiles();
		// for (File file:files){
		// System.out.println(file.getName());
		// }
	}
}
