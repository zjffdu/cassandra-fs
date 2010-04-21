package org.apache.cassandra.contrib.fs.cli;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import jline.Completor;

import org.apache.cassandra.contrib.fs.Path;
import org.apache.cassandra.contrib.fs.PathUtil;

public class FSPathCompleter implements Completor {

	private FSCliMain cli;

	public FSPathCompleter(FSCliMain cli) {
		this.cli = cli;
	}

	public int complete(final String buf, final int cursor,
			final List candidates) {
		String buffer = (buf == null) ? "" : buf;
		String inputPath = buffer.substring(buffer.lastIndexOf(" ") + 1);

		// change relative path to absolute path
		if (!(inputPath.startsWith("/"))) {
			inputPath = cli.getCWD() + "/" + inputPath;
		}

		String parentURL = null;
		if (inputPath.endsWith("/")) {
			parentURL = inputPath.substring(0, inputPath.length() - 1);
		} else {
			parentURL = PathUtil.getParent(inputPath);
		}

		try {
			final List<Path> entries = cli.getFileSystem().list(parentURL);
			return matchFiles(buffer, inputPath, entries, candidates);
		} catch (IOException e) {
			return -1;
		} finally {
			// we want to output a sorted list of files
			sortFileNames(candidates);
		}
	}

	protected void sortFileNames(final List fileNames) {
		Collections.sort(fileNames);
	}

	/**
	 * Match the specified <i>buffer</i> to the array of <i>entries</i> and
	 * enter the matches into the list of <i>candidates</i>. This method can be
	 * overridden in a subclass that wants to do more sophisticated file name
	 * completion.
	 * 
	 * @param buffer
	 *            the untranslated buffer
	 * @param inputPath
	 *            the buffer with common characters replaced
	 * @param entries
	 *            the list of files to match
	 * @param candidates
	 *            the list of candidates to populate
	 * 
	 * @return the offset of the match
	 */
	public int matchFiles(String buffer, String inputPath, List<Path> entries,
			List candidates) {
		if (entries == null) {
			return -1;
		}

		int matches = 0;

		// first pass: just count the matches
		for (int i = 0; i < entries.size(); i++) {
			if (entries.get(i).getURL().startsWith(inputPath)) {
				matches++;
			}
		}

		// green - executable
		// blue - directory
		// red - compressed
		// cyan - symlink
		for (int i = 0; i < entries.size(); i++) {
			if (entries.get(i).getURL().startsWith(inputPath)) {
				String name = entries.get(i).getName()
						+ (((matches == 1) && entries.get(i).isDir()) ? "/"
								: " ");

				/*
				 * if (entries [i].isDirectory ()) { name = new ANSIBuffer
				 * ().blue (name).toString (); }
				 */
				candidates.add(name);
			}
		}

		final int index = buffer.lastIndexOf("/");

		return index + "/".length();
	}

}
