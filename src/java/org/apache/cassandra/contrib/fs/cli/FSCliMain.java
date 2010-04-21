package org.apache.cassandra.contrib.fs.cli;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import jline.ArgumentCompletor;
import jline.Completor;
import jline.ConsoleReader;

import org.apache.cassandra.contrib.fs.CassandraFileSystem;
import org.apache.cassandra.contrib.fs.FSConstants;
import org.apache.cassandra.contrib.fs.IFileSystem;
import org.apache.cassandra.contrib.fs.Path;
import org.apache.cassandra.contrib.fs.PathUtil;
import org.apache.cassandra.contrib.fs.util.Bytes;
import org.apache.commons.io.IOUtils;
import org.apache.thrift.transport.TTransportException;

public class FSCliMain {

	private static final String Prompt = "fs> ";

	private ConsoleReader reader;

	private IFileSystem fs;

	private String curWorkingDir;

	private PrintStream out = System.out;

	public FSCliMain() throws IOException, TTransportException {
		this.reader = new ConsoleReader();
		ArgumentCompletor completor = new ArgumentCompletor(new Completor[] {
				new FSComamndCompletor(), new FSPathCompleter(this) });
		this.reader.addCompletor(completor);
		this.reader.setBellEnabled(false);
	}

	public void setFileSystem(IFileSystem fs) {
		this.fs = fs;
	}

	public IFileSystem getFileSystem() {
		return this.fs;
	}

	public String getCWD() {
		return this.curWorkingDir;
	}

	public void setCWD(String cwd) {
		this.curWorkingDir = cwd;
	}

	public void connect() throws TTransportException, IOException{
		this.fs = CassandraFileSystem.getInstance();
		String os = System.getProperty("os.name");
		if (os.toLowerCase().contains("windows")) {
			this.curWorkingDir = "/usr/" + System.getenv("USERNAME");
		} else {
			this.curWorkingDir = "/usr/" + System.getenv("USER");
		}
		this.fs.mkdir(curWorkingDir);
	}
	
	public void run() throws IOException, TTransportException {
		connect();
		
		out.println("Welcome to cassandra fs!");
		out.println("Type 'help' for help. Type 'quit' or 'exit' to quit.");
		String line = null;
		while ((line = reader.readLine(Prompt)) != null
				&& !line.equalsIgnoreCase("quit")
				&& !line.equalsIgnoreCase("exit")) {
			processCommand(line);
		}
	}

	// TODO handle more complex cases,such as spaces, quotation, check path
	// validity
	public void processCommand(String command) throws IOException {
		String[] tokens = command.split("\\s");
		String cmd = tokens[0];
		if (cmd.equalsIgnoreCase("ls")) {
			processLs(tokens);
		} else if (cmd.equalsIgnoreCase("mkdir")) {
			processMkDir(tokens);
		} else if (cmd.equalsIgnoreCase("copyfromlocal")) {
			processCopyFromLocal(tokens);
		} else if (cmd.equalsIgnoreCase("copytolocal")) {
			processCopyToLocal(tokens);
		} else if (cmd.equalsIgnoreCase("newfile")) {
			processNewFile(tokens);
		} else if (cmd.equalsIgnoreCase("rm")) {
			processRM(tokens);
		} else if (cmd.equalsIgnoreCase("rmdir")) {
			processRMDir(tokens);
		} else if (cmd.equalsIgnoreCase("cat")) {
			processCat(tokens);
		} else if (cmd.equalsIgnoreCase("pwd")) {
			out.println(curWorkingDir);
		} else if (cmd.equalsIgnoreCase("cd")) {
			processCD(tokens);
		} else if (cmd.equalsIgnoreCase("touch")) {
			processTouch(tokens);
		} else if (cmd.equalsIgnoreCase("help")) {
			processHelp(tokens);
		} else {
			out.println("Can not recognize command '" + cmd + "'");
		}
	}

	private void processHelp(String[] tokens) {
		out.println("List of all FS-CLI commands:");
		out.println("cd <folder>");
		out.println("copyToLocal <source> <dest>");
		out.println("touch <file>...");
		out.println("rm <file>...");
		out.println("newfile <file> <content>");
		out.println("cat <file>...");
		out.println("rmdir <dir>");
		out.println("copyFromLocal <source> <dest>");
		out.println("mkdir <path>");
		out.println("ls <path>");
	}

	private void processCD(String[] tokens) throws IOException {
		if (tokens.length != 2) {
			out.println("Usage: cd <folder>");
		} else {
			if (!fs.existDir(decoratePath(tokens[1]))) {
				out.println("cd " + tokens[1] + " : No such folder");
			} else {
				curWorkingDir = decoratePath(tokens[1]);
			}
		}
	}

	private void processCopyToLocal(String[] tokens)
			throws FileNotFoundException, IOException {
		if (tokens.length != 3) {
			out.println("Usage: copyToLocal <source> <dest>");
		} else {
			boolean fileExist = fs.existFile(decoratePath(tokens[1]));
			boolean dirExist = fs.existDir(decoratePath(tokens[1]));
			if (!fileExist && !dirExist) {
				out.println("No such file or folder : " + tokens[1]);
			} else {
				if (fileExist) {
					byte[] content = fs.readFile(decoratePath(tokens[1]));
					File localDestFile = new File(decoratePath(tokens[2]));
					if (localDestFile.exists() && localDestFile.isFile()) {
						out
								.println("Local dest File '" + tokens[2]
										+ "' exist");
					} else {
						if (localDestFile.isFile()) {
							IOUtils.write(content, new FileOutputStream(
									localDestFile));
						} else {
							IOUtils.write(content, new FileOutputStream(
									localDestFile.getAbsolutePath()
											+ "/"
											+ new Path(decoratePath(tokens[1]))
													.getName()));
						}
					}
				} else {
					File localFile = new File(decoratePath(tokens[2]));
					if (localFile.exists() && localFile.list().length != 0) {
						out.println("Local dest folder '" + tokens[2]
								+ "' is not empty");
					} else {
						localFile.mkdirs();
						List<Path> paths = fs.list(decoratePath(tokens[1]));
						for (Path path : paths) {
							visitNodeWhenCopyToLocal(path, tokens);
						}
					}
				}
			}
		}
	}

	private void visitNodeWhenCopyToLocal(Path path, String[] tokens)
			throws IOException {
		if (path.isDir()) {
			new File(decoratePath(tokens[2]
					+ strSubtract(path.getURL(), decoratePath(tokens[1]))))
					.mkdirs();
			List<Path> subPaths = fs.list(path.getURL());
			for (Path subPath : subPaths) {
				visitNodeWhenCopyToLocal(subPath, tokens);
			}
		} else {
			byte[] content = fs.readFile(decoratePath(path.getURL()));
			IOUtils.write(content, new FileOutputStream(new File(
					decoratePath(tokens[2]
							+ strSubtract(path.getURL(), tokens[1])))));
		}
	}

	private void processTouch(String[] tokens) throws IOException {
		if (tokens.length < 2) {
			out.println("Usage: touch <file>...");
		} else {
			for (int i = 1; i < tokens.length; ++i) {
				fs.createFile(decoratePath(tokens[i]), "".getBytes());
			}
		}
	}

	private void processRM(String[] tokens) throws IOException {
		if (tokens.length < 2) {
			out.println("Usage: rm <file>...");
		} else {
			for (int i = 1; i < tokens.length; ++i) {
				if (fs.existFile(decoratePath(tokens[i]))) {
					fs.deleteFile(decoratePath(tokens[i]));
				} else {
					out.println("rm: " + tokens[i] + " : No such file");
				}
			}
		}
	}

	private void processNewFile(String[] tokens) throws IOException {
		if (tokens.length != 3) {
			out.println("Usage: newfile <file> <content>");
		} else {
			fs.createFile(decoratePath(tokens[1]), Bytes.toBytes(tokens[2]));
		}
	}

	private void processCat(String[] tokens) throws IOException {
		if (tokens.length < 2) {
			out.println("Usage: cat <file>...");
		} else {
			for (int i = 1; i < tokens.length; ++i) {
				if (fs.existFile(decoratePath(tokens[i]))) {
					String content = new String(fs
							.readFile(decoratePath(tokens[i])));
					out.println(content);
				} else {
					out.println("cat: " + tokens[i] + ": No such file");
				}
			}
		}
	}

	private void processRMDir(String[] tokens) throws IOException {
		if (tokens.length != 2) {
			out.println("Usage: rmdir <dir>");
		} else {
			if (fs.existDir(decoratePath(tokens[1]))) {
				if (fs.list(decoratePath(tokens[1])).size() != 0) {
					out.println("rmdir: " + tokens[1]
							+ ": The folder is not empty");

				} else {
					fs.deleteDir(decoratePath(tokens[1]), false);
				}
			} else {
				out.println("rmdir: " + tokens[1] + ": No such directory");
			}
		}
	}

	private void processCopyFromLocal(String[] tokens) throws IOException,
			FileNotFoundException {
		if (tokens.length != 3) {
			out.println("Usage: copyFromLocal <source> <dest>");
		} else {
			File localFile = new File(decoratePath(tokens[1]));
			if (localFile.exists()) {
				visitNodeWhenCopyFromLocal(localFile, tokens);
			} else {
				out.println("Source '" + tokens[1] + "' does not exist");
			}
		}
	}

	private void visitNodeWhenCopyFromLocal(File file, String[] tokens)
			throws FileNotFoundException, IOException {
		if (file.isFile()) {
			copyFileFromLocal(decoratePath(file.getAbsolutePath()),
					decoratePath(tokens[2]
							+ strSubtract(decoratePath(file.getAbsolutePath()),
									decoratePath(tokens[1]))));
		} else {
			File[] files = file.listFiles();
			for (File child : files) {
				if (child.isFile()) {
					copyFileFromLocal(decoratePath(child.getAbsolutePath()),
							decoratePath(tokens[2]
									+ strSubtract(decoratePath(child
											.getAbsolutePath()),
											decoratePath(tokens[1]))));
				} else {
					visitNodeWhenCopyFromLocal(child, tokens);
				}
			}
		}
	}

	private String strSubtract(String str1, String str2) {
		int index = str1.indexOf(str2);
		if (index == 0) {
			return str1.substring(str2.length());
		} else {
			throw new RuntimeException("Can not subtract '" + str2 + "' from '"
					+ str1 + "'");
		}
	}

	private void copyFileFromLocal(String source, String dest)
			throws FileNotFoundException, IOException {
		byte[] content = IOUtils.toByteArray(new FileInputStream(
				decoratePath(source)));
		if (content.length > FSConstants.MaxFileSize) {
			out.println("Size of file '" + source
					+ "' is too large, the size limitation is "
					+ FSConstants.MaxFileSize);
		} else {
			fs.createFile(decoratePath(dest), content);
		}
	}

	private void processMkDir(String[] tokens) throws IOException {
		if (tokens.length == 1) {
			out.println("Usage: mkdir <path>");
		} else {
			for (int i = 1; i < tokens.length; ++i) {
				fs.mkdir(decoratePath(tokens[1]));
			}
		}
	}

	private void processLs(String[] tokens) throws IOException {
		List<String> lsDirs = new ArrayList<String>();
		if (tokens.length == 1) {
			lsDirs.add(decoratePath("."));
		} else {
			for (int i = 1; i < tokens.length; ++i) {
				lsDirs.add(decoratePath(tokens[i]));
			}
		}
		for (String lsDir : lsDirs) {
			List<Path> children = fs.list(lsDir);
			out.println("Found " + children.size() + " items");
			for (Path child : children) {
				out.println(child);
			}
		}
	}

	private String decoratePath(String path) {
		path = PathUtil.normalizePath(path);
		// transform windows path, remove the driver part
		if (path.length() >= 2 && path.charAt(1) == ':' && path.charAt(0) < 'z'
				&& path.charAt(0) > 'A') {
			path = path.substring(2);
		}

		if (path.equals(".")) {
			return curWorkingDir;
		} else if (path.equals("..")) {
			if (curWorkingDir.equals("/")) {
				return "/";
			}
			int index = curWorkingDir.lastIndexOf("/");
			if (index == 0) {
				return "/";
			} else if (index != -1) {
				return curWorkingDir.substring(0, index);
			} else {
				throw new RuntimeException();
			}
		} else if (path.startsWith("/")) {
			return path;
		} else {
			return curWorkingDir + "/" + path;
		}
	}

	public static void main(String[] args) throws IOException,
			TTransportException {

		FSCliMain cli = new FSCliMain();
		cli.run();
		// cli.processLs(new String[]{"ls","/da"});
		// System.out.println(System.getenv("USERNAME"));
	}
}
