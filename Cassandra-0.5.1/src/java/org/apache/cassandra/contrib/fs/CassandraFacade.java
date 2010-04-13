package org.apache.cassandra.contrib.fs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.cassandra.service.Cassandra;
import org.apache.cassandra.service.Column;
import org.apache.cassandra.service.ColumnOrSuperColumn;
import org.apache.cassandra.service.ColumnParent;
import org.apache.cassandra.service.ColumnPath;
import org.apache.cassandra.service.ConsistencyLevel;
import org.apache.cassandra.service.InvalidRequestException;
import org.apache.cassandra.service.KeySlice;
import org.apache.cassandra.service.NotFoundException;
import org.apache.cassandra.service.SlicePredicate;
import org.apache.cassandra.service.SliceRange;
import org.apache.cassandra.service.SuperColumn;
import org.apache.cassandra.service.TimedOutException;
import org.apache.cassandra.service.UnavailableException;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * TODO use the hector java api in future, here use the thrift api just for
 * learning
 * 
 * @author zhanje
 * 
 */

public class CassandraFacade {

	private static CassandraFacade instance;

	private static int ReadLevel = ConsistencyLevel.ONE;

	private static int WriteLevel = ConsistencyLevel.ALL;

	private Cassandra.Client client;

	public static CassandraFacade getInstance() throws TTransportException {
		if (instance == null) {
			synchronized (CassandraFacade.class) {
				if (instance == null) {
					instance = new CassandraFacade();
				}
			}
		}
		return instance;
	}

	private CassandraFacade() throws TTransportException {
		TTransport transport = new TSocket(FSConstants.HostName, FSConstants.ThriftPort);
		TProtocol protocol = new TBinaryProtocol(transport);
		client = new Cassandra.Client(protocol);
		transport.open();
	}

	public void put(String key, String column, byte[] value) throws IOException {

		try {
			ColumnPath columnPath = extractColumnPath(column);
			client.insert(FSConstants.KeySpace, key, columnPath, value, System
					.currentTimeMillis(), WriteLevel);
		} catch (InvalidRequestException e) {
			throw new IOException(e);
		} catch (UnavailableException e) {
			throw new IOException(e);
		} catch (TimedOutException e) {
			throw new IOException(e);
		} catch (TException e) {
			throw new IOException(e);
		}
	}

	private ColumnPath extractColumnPath(String column) throws IOException {
		String[] subColumns = column.split(":");
		ColumnPath columnPath = new ColumnPath();
		columnPath.setColumn_family(subColumns[0]);
		if (subColumns.length == 2) {
			columnPath.setSuper_column(null);
			columnPath.setColumn(subColumns[1].getBytes());
		} else if (subColumns.length == 3) {
			columnPath.setSuper_column(subColumns[1].getBytes());
			columnPath.setColumn(subColumns[2].getBytes());
		} else {
			throw new IOException("The column is not the right format:"
					+ column);
		}
		return columnPath;
	}

	public byte[] get(String key, String column) throws IOException {
		try {
			ColumnPath columnPath = extractColumnPath(column);
			ColumnOrSuperColumn result = client.get(FSConstants.KeySpace, key,
					columnPath, ReadLevel);
			return result.getColumn().getValue();
		} catch (InvalidRequestException e) {
			throw new IOException(e);
		} catch (UnavailableException e) {
			throw new IOException(e);
		} catch (TimedOutException e) {
			throw new IOException(e);
		} catch (TException e) {
			throw new IOException(e);
		} catch (NotFoundException e) {
			throw new IOException(e);
		}
	}

	public void delete(String key) throws IOException {

		// TODO split the two operation
		try {
			client.remove(FSConstants.KeySpace, key, new ColumnPath(
					FSConstants.FileCF, null, null),
					System.currentTimeMillis(), WriteLevel);
			client.remove(FSConstants.KeySpace, key, new ColumnPath(
					FSConstants.FolderCF, null, null), System
					.currentTimeMillis(), WriteLevel);
		} catch (TException e) {
			throw new IOException(e);
		} catch (InvalidRequestException e) {
			throw new IOException(e);
		} catch (UnavailableException e) {
			throw new IOException(e);
		} catch (TimedOutException e) {
			throw new IOException(e);
		}
	}

	public void delete(String key, String columnFamily, String superColumn)
			throws IOException {
		try {
			ColumnPath columnPath = new ColumnPath(columnFamily, superColumn
					.getBytes(), null);
			client.remove(FSConstants.KeySpace, key, columnPath, System
					.currentTimeMillis(), WriteLevel);
		} catch (TException e) {
			throw new IOException(e);
		} catch (InvalidRequestException e) {
			throw new IOException(e);
		} catch (UnavailableException e) {
			throw new IOException(e);
		} catch (TimedOutException e) {
			throw new IOException(e);
		}
	}

	public boolean exist(String key) throws IOException {
		try {
			ColumnOrSuperColumn folderResult = client.get(FSConstants.KeySpace,
					key, new ColumnPath(FSConstants.FolderCF,
							FSConstants.FolderFlag.getBytes(),
							FSConstants.TypeAttr.getBytes()), ReadLevel);

		} catch (InvalidRequestException e) {
			throw new IOException(e);
		} catch (UnavailableException e) {
			throw new IOException(e);
		} catch (TimedOutException e) {
			throw new IOException(e);
		} catch (TException e) {
			throw new IOException(e);
		} catch (NotFoundException e) {
			// do nothing
		}

		try {
			ColumnOrSuperColumn fileResult = client.get(FSConstants.KeySpace,
					key, new ColumnPath(FSConstants.FileCF, null,
							FSConstants.ContentAttr.getBytes()), ReadLevel);
			return true;
		} catch (InvalidRequestException e) {
			throw new IOException(e);
		} catch (UnavailableException e) {
			throw new IOException(e);
		} catch (TimedOutException e) {
			throw new IOException(e);
		} catch (TException e) {
			throw new IOException(e);
		} catch (NotFoundException e) {
			// do nothing
		}

		return false;
	}

	public List<Path> list(String key, String columnFamily,
			boolean includeFolderFlag) throws IOException {
		try {
			List<Path> children = new ArrayList<Path>();
			List<ColumnOrSuperColumn> result = client.get_slice(
					FSConstants.KeySpace, key, new ColumnParent(columnFamily,
							null),
					new SlicePredicate(null, new SliceRange(new byte[0],
							new byte[0], false, Integer.MAX_VALUE)), ReadLevel);

			if (columnFamily.equals(FSConstants.FolderCF)) {
				for (ColumnOrSuperColumn column : result) {
					SuperColumn sc = column.getSuper_column();
					if (sc != null) {
						String name = new String(sc.name);
						List<Column> attributes = sc.getColumns();
						Path path = new Path(name, attributes);
						if (includeFolderFlag) {
							children.add(path);
						} else if (!name.equals(FSConstants.FolderFlag)) {
							children.add(path);
						}
					}
				}
			} else if (columnFamily.equals(FSConstants.FileCF)) {
				List<Column> attrColumn = new ArrayList<Column>();
				for (ColumnOrSuperColumn column : result) {
					Column col = column.getColumn();
					if (col != null) {
						attrColumn.add(col);
					}
				}
				if (attrColumn.size()!=0){
					Path path = new Path(key, attrColumn);
					children.add(path);
				}
			} else {
				throw new RuntimeException("Do not support CF:'" + columnFamily
						+ "' now");
			}

			return children;
		} catch (InvalidRequestException e) {
			throw new IOException(e);
		} catch (UnavailableException e) {
			throw new IOException(e);
		} catch (TimedOutException e) {
			throw new IOException(e);
		} catch (TException e) {
			throw new IOException(e);
		}
	}

	public Path listFile(String file) throws IOException {
		try {
			List<ColumnOrSuperColumn> result = client.get_slice(
					FSConstants.KeySpace, file, new ColumnParent(
							FSConstants.FileCF, null), new SlicePredicate(null,
							new SliceRange(new byte[0], new byte[0], false,
									Integer.MAX_VALUE)), ReadLevel);
			List<Column> attrColumns = new ArrayList<Column>();
			for (ColumnOrSuperColumn col : result) {
				attrColumns.add(col.getColumn());
			}
			return new Path(file, attrColumns);
		} catch (InvalidRequestException e) {
			throw new IOException(e);
		} catch (UnavailableException e) {
			throw new IOException(e);
		} catch (TimedOutException e) {
			throw new IOException(e);
		} catch (TException e) {
			throw new IOException(e);
		}
	}

	private List<String> listall(String keyspace)
			throws InvalidRequestException, UnavailableException,
			TimedOutException, TException {

		List<String> result = new ArrayList<String>();
		SlicePredicate predicate = new SlicePredicate(null, new SliceRange(
				new byte[0], new byte[0], false, Integer.MAX_VALUE));
		List<KeySlice> items = client.get_range_slice(FSConstants.KeySpace,
				new ColumnParent(FSConstants.FolderCF, null), predicate, "",
				"", 100, ReadLevel);
		for (KeySlice item : items) {
			result.add(item.key);
		}

		// items = client.get_range_slice(FSConstants.KeySpace, new
		// ColumnParent(
		// FSConstants.AttributeCF, null), predicate, "", "",
		// 100, CLevel);
		//
		// for (KeySlice item : items) {
		// result.add(item.key);
		// }
		return result;
	}

	public static void main(String[] args) throws InvalidRequestException,
			UnavailableException, TimedOutException, TException {
		CassandraFacade facade = CassandraFacade.getInstance();
		List<String> files = facade.listall(FSConstants.KeySpace);
		for (String file : files) {
			System.out.println(file);
		}
	}
}
