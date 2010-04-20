import java.io.File;
import java.io.IOException;

import org.apache.cassandra.contrib.fs.cli.FSCliMain;
import org.apache.thrift.transport.TTransportException;


public class Test {

	static int count=0;
	
	private static void doAction(File root,FSCliMain cli) throws IOException {
		for (File file : root.listFiles()) {
			if (file.isDirectory()) {
				doAction(file,cli);
			} else {
				System.out.println(count++);
				cli.processCommand("copyFromLocal "
						+ file.getAbsolutePath().substring(2) + " "
						+ file.getAbsolutePath().substring(2));
			}
		}
	}
	
	public static void main(String[] args) throws TTransportException, IOException{
		FSCliMain cli = new FSCliMain();
		doAction(new File("/data/partatom"),cli);
	}
}
