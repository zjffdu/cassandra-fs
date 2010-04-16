import org.apache.cassandra.thrift.Cassandra;
import org.apache.cassandra.thrift.ColumnPath;
import org.apache.cassandra.thrift.ConsistencyLevel;
import org.apache.cassandra.thrift.InvalidRequestException;
import org.apache.cassandra.thrift.NotFoundException;
import org.apache.cassandra.thrift.TimedOutException;
import org.apache.cassandra.thrift.UnavailableException;
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

		client.insert("FS", "/data", new ColumnPath("Folder"), "value".getBytes(),System
				.currentTimeMillis(), ConsistencyLevel.ONE);

	}
}
