import org.apache.cassandra.service.CassandraDaemon;



public class StartCassandra {

	public static void main(String[] args) {
		System.setProperty("storage-config", "conf");
		CassandraDaemon.main(args);
	}
}
