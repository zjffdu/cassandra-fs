package org.apache.cassandra.contrib.fs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.prettyprint.cassandra.service.CassandraClientPool;
import me.prettyprint.cassandra.service.CassandraClientPoolFactory;
import me.prettyprint.cassandra.service.CassandraHostConfigurator;
import me.prettyprint.cassandra.service.Keyspace;
import me.prettyprint.cassandra.service.PoolExhaustedException;

import org.apache.cassandra.service.Column;
import org.apache.cassandra.service.ColumnParent;
import org.apache.cassandra.service.ColumnPath;
import org.apache.cassandra.service.InvalidRequestException;
import org.apache.cassandra.service.NotFoundException;
import org.apache.cassandra.service.SlicePredicate;
import org.apache.cassandra.service.SliceRange;
import org.apache.cassandra.service.SuperColumn;
import org.apache.cassandra.service.TimedOutException;
import org.apache.cassandra.service.UnavailableException;
import org.apache.thrift.TException;

/**
 * TODO use the hector java api in future, here use the thrift api just for
 * learning
 * 
 * @author zhanje
 * 
 */

public class CassandraFacade {

	private static CassandraFacade instance;

	private CassandraClientPool clientPool;

	public static CassandraFacade getInstance() throws IOException {
		if (instance == null) {
			synchronized (CassandraFacade.class) {
				if (instance == null) {
					instance = new CassandraFacade();
				}
			}
		}
		return instance;
	}

	private CassandraFacade() throws IOException {
		File clientConfFile = new File(System.getProperty("storage-config")
				+ File.separator + "client-conf.properties");
		if (!clientConfFile.exists()) {
			throw new RuntimeException("'" + clientConfFile.getAbsolutePath()
					+ "' does not exist!");
		}

		ClientConfiguration conf = new ClientConfiguration(clientConfFile
				.getAbsolutePath());
		CassandraHostConfigurator hostConfig = new CassandraHostConfigurator(
				conf.getHosts());
		hostConfig.setCassandraThriftSocketTimeout(conf
				.getCassandraThriftSocketTimeout());
		hostConfig.setExhaustedPolicy(conf.getExhaustedPolicy());
		hostConfig.setMaxActive(conf.getMaxActive());
		hostConfig.setMaxIdle(conf.getMaxIdle());
		hostConfig.setMaxWaitTimeWhenExhausted(conf
				.getMaxWaitTimeWhenExhausted());

		clientPool = CassandraClientPoolFactory.getInstance().createNew(
				hostConfig);
	}

	public void put(String key, String column, byte[] value) throws IOException {

		Keyspace ks = null;
		try {
			ColumnPath columnPath = extractColumnPath(column);
			ks = clientPool.borrowClient().getKeyspace(FSConstants.KeySpace);
			ks.insert(key, columnPath, value);
		} catch (Exception e) {
			throw new IOException(e);
		} finally {
			if (ks != null) {
				try {
					clientPool.releaseKeyspace(ks);
				} catch (Exception e) {
					throw new IOException(e);
				}
			}
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

		Keyspace ks = null;
		try {
			ColumnPath columnPath = extractColumnPath(column);
			ks = clientPool.borrowClient().getKeyspace(FSConstants.KeySpace);

			Column result = ks.getColumn(key, columnPath);
			return result.getValue();
		} catch (Exception e) {
			throw new IOException(e);
		} finally {
			if (ks != null) {
				try {
					clientPool.releaseKeyspace(ks);
				} catch (Exception e) {
					throw new IOException(e);
				}
			}
		}
	}

	public void delete(String key) throws IOException {

		Keyspace ks = null;
		// TODO split the two operation
		try {
			ks = clientPool.borrowClient().getKeyspace(FSConstants.KeySpace);
			ks.remove(key, new ColumnPath(FSConstants.FileCF, null, null));
			ks.remove(key, new ColumnPath(FSConstants.FolderCF, null, null));
		} catch (Exception e) {
			throw new IOException(e);
		} finally {
			if (ks != null) {
				try {
					clientPool.releaseKeyspace(ks);
				} catch (Exception e) {
					throw new IOException(e);
				}
			}
		}
	}

	public void delete(String key, String columnFamily, String superColumn)
			throws IOException {
		Keyspace ks = null;
		try {
			ks = clientPool.borrowClient().getKeyspace(FSConstants.KeySpace);
			ColumnPath columnPath = new ColumnPath(columnFamily, superColumn
					.getBytes(), null);
			ks.remove(key, columnPath);
		} catch (Exception e) {
			throw new IOException(e);
		} finally {
			if (ks != null) {
				try {
					clientPool.releaseKeyspace(ks);
				} catch (Exception e) {
					throw new IOException(e);
				}
			}
		}
	}

	public boolean exist(String key) throws IOException {
		Keyspace ks = null;
		try {
			ks = clientPool.borrowClient().getKeyspace(FSConstants.KeySpace);
			Column column = ks.getColumn(key, new ColumnPath(
					FSConstants.FolderCF, FSConstants.FolderFlag.getBytes(),
					FSConstants.TypeAttr.getBytes()));

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
		} catch (IllegalArgumentException e) {
			throw new IOException(e);
		} catch (IllegalStateException e) {
			throw new IOException(e);
		} catch (PoolExhaustedException e) {
			throw new IOException(e);
		} catch (Exception e) {
			throw new IOException(e);
		} finally {
			if (ks != null) {
				try {
					clientPool.releaseKeyspace(ks);
				} catch (Exception e) {
					throw new IOException(e);
				}
			}
		}

		try {
			ks = clientPool.borrowClient().getKeyspace(FSConstants.KeySpace);
			Column column = ks.getColumn(key, new ColumnPath(
					FSConstants.FileCF, null, FSConstants.ContentAttr
							.getBytes()));
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
		} catch (IllegalArgumentException e) {
			throw new IOException(e);
		} catch (IllegalStateException e) {
			throw new IOException(e);
		} catch (PoolExhaustedException e) {
			throw new IOException(e);
		} catch (Exception e) {
			throw new IOException(e);
		} finally {
			if (ks != null) {
				try {
					clientPool.releaseKeyspace(ks);
				} catch (Exception e) {
					throw new IOException(e);
				}
			}
		}

		return false;
	}

	public List<Path> list(String key, String columnFamily,
			boolean includeFolderFlag) throws IOException {
		Keyspace ks = null;
		try {
			List<Path> children = new ArrayList<Path>();
			ks = clientPool.borrowClient().getKeyspace(FSConstants.KeySpace);

			if (columnFamily.equals(FSConstants.FolderCF)) {
				List<SuperColumn> result = ks.getSuperSlice(key,
						new ColumnParent(columnFamily, null),
						new SlicePredicate(null, new SliceRange(new byte[0],
								new byte[0], false, Integer.MAX_VALUE)));
				for (SuperColumn sc : result) {
					String name = new String(sc.name);
					List<Column> attributes = sc.getColumns();
					Path path = new Path(name, attributes);
					if (includeFolderFlag) {
						children.add(path);
					} else if (!name.equals(FSConstants.FolderFlag)) {
						children.add(path);
					}
				}
			} else if (columnFamily.equals(FSConstants.FileCF)) {
				List<Column> attrColumn = ks.getSlice(key, new ColumnParent(
						columnFamily, null), new SlicePredicate(null,
						new SliceRange(new byte[0], new byte[0], false,
								Integer.MAX_VALUE)));

				Path path = new Path(key, attrColumn);
				children.add(path);

			} else {
				throw new RuntimeException("Do not support CF:'" + columnFamily
						+ "' now");
			}

			return children;
		} catch (Exception e) {
			throw new IOException(e);
		} finally {
			if (ks != null) {
				try {
					clientPool.releaseKeyspace(ks);
				} catch (Exception e) {
					throw new IOException(e);
				}
			}
		}
	}

	public Path listFile(String file) throws IOException {
		Keyspace ks = null;
		try {
			ks = clientPool.borrowClient().getKeyspace(FSConstants.KeySpace);
			List<Column> attrColumns = ks.getSlice(file, new ColumnParent(
					FSConstants.FileCF, null), new SlicePredicate(null,
					new SliceRange(new byte[0], new byte[0], false,
							Integer.MAX_VALUE)));
			return new Path(file, attrColumns);
		} catch (Exception e) {
			throw new IOException(e);
		} finally {
			if (ks != null) {
				try {
					clientPool.releaseKeyspace(ks);
				} catch (Exception e) {
					throw new IOException(e);
				}
			}
		}
	}

	private List<String> listall(String keyspace)
			throws InvalidRequestException, UnavailableException,
			TimedOutException, TException {

		List<String> result = new ArrayList<String>();
		// SlicePredicate predicate = new SlicePredicate(null, new SliceRange(
		// new byte[0], new byte[0], false, Integer.MAX_VALUE));
		// List<KeySlice> items = client.get_range_slice(FSConstants.KeySpace,
		// new ColumnParent(FSConstants.FolderCF, null), predicate, "",
		// "", 100, ReadLevel);
		// for (KeySlice item : items) {
		// result.add(item.key);
		// }

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
			UnavailableException, TimedOutException, TException, IOException {
		CassandraFacade facade = CassandraFacade.getInstance();
		List<String> files = facade.listall(FSConstants.KeySpace);
		for (String file : files) {
			System.out.println(file);
		}
	}
}
