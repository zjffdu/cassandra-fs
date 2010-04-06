import java.util.List;

import org.apache.cassandra.service.Cassandra;
import org.apache.cassandra.service.ColumnOrSuperColumn;
import org.apache.cassandra.service.ColumnParent;
import org.apache.cassandra.service.ColumnPath;
import org.apache.cassandra.service.InvalidRequestException;
import org.apache.cassandra.service.KeySlice;
import org.apache.cassandra.service.NotFoundException;
import org.apache.cassandra.service.SlicePredicate;
import org.apache.cassandra.service.SliceRange;
import org.apache.cassandra.service.TimedOutException;
import org.apache.cassandra.service.UnavailableException;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class CassandraExample {

	public static void main(String[] args) throws InvalidRequestException,
			UnavailableException, TimedOutException, TException,
			NotFoundException {

		TTransport transport = new TSocket("localhost", 9160);
		TProtocol protocol = new TBinaryProtocol(transport);
		Cassandra.Client client = new Cassandra.Client(protocol);
		transport.open();

		client.insert("FS", "/data", new ColumnPath("Folder", "/data/a.txt"
				.getBytes(), "Size".getBytes()), "1".getBytes(), System
				.currentTimeMillis(), 1);

		// List<ColumnOrSuperColumn> result = client.get_slice("Keyspace1",
		// "zjf",
		// new ColumnParent("Super1", "s".getBytes()), new SlicePredicate(
		// null, new SliceRange(new byte[0], new byte[0], false,
		// Integer.MAX_VALUE)), 1);
		// for (ColumnOrSuperColumn item : result) {
		// System.out.println(new String(item.getColumn().getValue()));
		// }
		//
		// List<KeySlice> keys = client.get_range_slice("FS",
		// new ColumnParent("Content", null), new SlicePredicate(null,
		// new SliceRange(new byte[0], new byte[0], false,
		// Integer.MAX_VALUE)), "", "", 100, 1);

		ColumnOrSuperColumn result = client.get("FS", "/data", new ColumnPath(
				"Folder", "/data/a2.txt".getBytes(), "Size".getBytes()), 1);
		
		System.out.println(new String(result.getColumn().value));
		// SlicePredicate predicate = new SlicePredicate(null, new SliceRange(
		// new byte[0], new byte[0], false, Integer.MAX_VALUE));
		// List<KeySlice> keys = client.get_range_slice("FS",
		// new ColumnParent("Folder",
		// null), new SlicePredicate(null, new SliceRange(
		// new byte[0], new byte[0], false, Integer.MAX_VALUE)),
		// "", "", Integer.MAX_VALUE/200, 1);
		// for (KeySlice key : keys) {
		// System.out.println(key.key);
		// }
	}
}
