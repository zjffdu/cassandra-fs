package org.apache.cassandra.contrib.fs.cli;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import jline.ConsoleReader;

import org.apache.cassandra.contrib.fs.FSConstants;
import org.apache.cassandra.contrib.fs.FileSystem;
import org.apache.cassandra.contrib.fs.Path;
import org.apache.cassandra.contrib.fs.util.Bytes;
import org.apache.commons.io.IOUtils;
import org.apache.thrift.transport.TTransportException;

public class FSCliMain {

	private static final String Prompt = "fs> ";

	private ConsoleReader reader;

	private FileSystem fs;

	private String curWorkingDir;

	private PrintStream out = System.out;

	public FSCliMain() throws IOException, TTransportException {
		this.reader = new ConsoleReader();
		this.reader.addCompletor(new FSCliCompleter());
		this.reader.setBellEnabled(false);
		this.fs = FileSystem.getInstance();
		this.curWorkingDir = "/usr/" + System.getenv("USERNAME");
		this.fs.mkdir(curWorkingDir);
	}

	public void run() throws IOException {
		out.println("Welcome to cassandra fs!");
		String line = null;
		while ((line = reader.readLine(Prompt)) != null
				&& !line.equalsIgnoreCase("quit")
				&& !line.equalsIgnoreCase("exit")) {
			processCommand(line);
		}
	}

	// TODO handle more complex cases,such as spaces, quotation, check path
	// validity
	private void processCommand(String command) throws IOException {
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
		} else {
			out.println("Can not recognize command '" + cmd + "'");
		}
	}

	private void processCD(String[] tokens) throws IOException {
		if (tokens.length!=2){
			out.println("Usage: cd <folder>");
		}else{
			if (!fs.existDir(decoratePath(tokens[1]))){
				out.println("cd "+tokens[1]+" : No such folder");
			}else{
				curWorkingDir = decoratePath(tokens[1]);
			}
		}
	}

	private void processCopyToLocal(String[] tokens)
			throws FileNotFoundException, IOException {
		if (tokens.length != 3) {
			out.println("Usage: copyToLocal <source> <dest>");
		} else {
			if (!fs.existFile(decoratePath(tokens[1]))) {
				out.println("No such file : " + tokens[1]);
			} else {
				byte[] content = fs.readFile(decoratePath(tokens[1]));
				IOUtils.write(content,
						new FileOutputStream(new File(tokens[2])));
			}
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
				fs.deleteDir(decoratePath(tokens[1]), true);
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
			if (!new File(tokens[1]).exists()) {
				out.println("Source '" + tokens[1] + "' does not exist");
			} else {
				byte[] content = IOUtils.toByteArray(new FileInputStream(
						tokens[1]));
				if (content.length > FSConstants.MaxFileSize) {
					out.println("Size of file '" + tokens[1]
							+ "' is too large, the size limitation is "
							+ FSConstants.MaxFileSize);
				} else {
					fs.createFile(decoratePath(tokens[2]), content);
				}
			}
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
		path = path.replace("\\", "/");
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

	private void doAction(File root) throws IOException {
		for (File file : root.listFiles()) {
			if (file.isDirectory()) {
				doAction(file);
			} else {
				processCommand("copyFromLocal "
						+ file.getAbsolutePath().substring(2) + " "
						+ file.getAbsolutePath().substring(2));
			}
		}
	}

	public static void main(String[] args) throws IOException,
			TTransportException {

		FSCliMain cli = new FSCliMain();
//		File root = new File("/data/product");
//		cli.doAction(root);
		 cli.run();
		// System.out.println(System.getenv("USERNAME"));
	}
}
