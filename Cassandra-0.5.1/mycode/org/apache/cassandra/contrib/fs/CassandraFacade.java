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
		TTransport transport = new TSocket("localhost", 9160);
		TProtocol protocol = new TBinaryProtocol(transport);
		client = new Cassandra.Client(protocol);
		transport.open();
	}

	public void put(String key, String column, String value) throws IOException {

		try {
			ColumnPath columnPath = extractColumnPath(column);
			client.insert(FSConstants.KeySpace, key, columnPath, value
					.getBytes(), System.currentTimeMillis(), WriteLevel);
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

	public boolean delete(String key) throws IOException {
		try {
			client.send_remove(FSConstants.KeySpace, key, new ColumnPath(
					FSConstants.FileCF, null, FSConstants.TypeAttr.getBytes()),
					System.currentTimeMillis(), WriteLevel);
			client.send_remove(FSConstants.KeySpace, key, new ColumnPath(
					FSConstants.FileCF, null, FSConstants.ContentAttr.getBytes()),
					System.currentTimeMillis(), WriteLevel);
			
		} catch (TException e) {
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

	public List<String> list(String key, String columnFamily)
			throws IOException {
		try {
			List<String> children = new ArrayList<String>();
			List<ColumnOrSuperColumn> result = client.get_slice(
					FSConstants.KeySpace, key, new ColumnParent(columnFamily,
							null),
					new SlicePredicate(null, new SliceRange(new byte[0],
							new byte[0], false, Integer.MAX_VALUE)), ReadLevel);
			for (ColumnOrSuperColumn column : result) {
				SuperColumn sc = column.getSuper_column();
				if (sc != null) {
					String name = new String(sc.name);
					if (!name.equals(FSConstants.FolderFlag)) {
						children.add(name);
					}
				}
				Column col = column.getColumn();
				if (col != null) {
					String name = new String(col.name);
					if (!name.equals(FSConstants.FolderFlag)) {
						children.add(name);
					}
				}
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
