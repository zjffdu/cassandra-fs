/**
 * Copyright 2009 The Apache Software Foundation
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.cassandra.contrib.fs.util;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/**
 * Utility class that handles byte arrays, conversions to/from other types,
 * comparisons, hash code generation, manufacturing keys for HashMaps or
 * HashSets, etc.
 */
public class Bytes {

	private static final String UTF_ENCODING = "UTF-8";

	/**
	 * Size of boolean in bytes
	 */
	public static final int SIZEOF_BOOLEAN = Byte.SIZE / Byte.SIZE;

	/**
	 * Size of byte in bytes
	 */
	public static final int SIZEOF_BYTE = SIZEOF_BOOLEAN;

	/**
	 * Size of char in bytes
	 */
	public static final int SIZEOF_CHAR = Character.SIZE / Byte.SIZE;

	/**
	 * Size of double in bytes
	 */
	public static final int SIZEOF_DOUBLE = Double.SIZE / Byte.SIZE;

	/**
	 * Size of float in bytes
	 */
	public static final int SIZEOF_FLOAT = Float.SIZE / Byte.SIZE;

	/**
	 * Size of int in bytes
	 */
	public static final int SIZEOF_INT = Integer.SIZE / Byte.SIZE;

	/**
	 * Size of long in bytes
	 */
	public static final int SIZEOF_LONG = Long.SIZE / Byte.SIZE;

	/**
	 * Size of short in bytes
	 */
	public static final int SIZEOF_SHORT = Short.SIZE / Byte.SIZE;

	/**
	 * Estimate of size cost to pay beyond payload in jvm for instance of byte
	 * []. Estimate based on study of jhat and jprofiler numbers.
	 */
	// JHat says BU is 56 bytes.
	// SizeOf which uses java.lang.instrument says 24 bytes. (3 longs?)
	public static final int ESTIMATED_HEAP_TAX = 16;

	/**
	 * Returns a new byte array, copied from the passed ByteBuffer.
	 * 
	 * @param bb
	 *            A ByteBuffer
	 * @return the byte array
	 */
	public static byte[] toBytes(ByteBuffer bb) {
		int length = bb.limit();
		byte[] result = new byte[length];
		System.arraycopy(bb.array(), bb.arrayOffset(), result, 0, length);
		return result;
	}

	/**
	 * @param b
	 *            Presumed UTF-8 encoded byte array.
	 * @return String made from <code>b</code>
	 */
	public static String toString(final byte[] b) {
		if (b == null) {
			return null;
		}
		return toString(b, 0, b.length);
	}

	public static String toString(final byte[] b1, String sep, final byte[] b2) {
		return toString(b1, 0, b1.length) + sep + toString(b2, 0, b2.length);
	}

	/**
	 * @param b
	 *            Presumed UTF-8 encoded byte array.
	 * @param off
	 * @param len
	 * @return String made from <code>b</code>
	 */
	public static String toString(final byte[] b, int off, int len) {
		if (b == null) {
			return null;
		}
		if (len == 0) {
			return "";
		}
		String result = null;
		try {
			result = new String(b, off, len, UTF_ENCODING);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String toStringBinary(final byte[] b) {
		return toStringBinary(b, 0, b.length);
	}

	public static String toStringBinary(final byte[] b, int off, int len) {
		StringBuilder result = new StringBuilder();
		try {
			String first = new String(b, off, len, "ISO-8859-1");
			for (int i = 0; i < first.length(); ++i) {
				int ch = first.charAt(i) & 0xFF;
				if ((ch >= '0' && ch <= '9') || (ch >= 'A' && ch <= 'Z')
						|| (ch >= 'a' && ch <= 'z') || ch == ',' || ch == '_'
						|| ch == '-' || ch == ':' || ch == ' ' || ch == '<'
						|| ch == '>' || ch == '=' || ch == '/' || ch == '.') {
					result.append(first.charAt(i));
				} else {
					result.append(String.format("\\x%02X", ch));
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	/**
	 * Converts a string to a UTF-8 byte array.
	 * 
	 * @param s
	 * @return the byte array
	 */
	public static byte[] toBytes(String s) {
		if (s == null) {
			throw new IllegalArgumentException("string cannot be null");
		}
		byte[] result = null;
		try {
			result = s.getBytes(UTF_ENCODING);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Convert a boolean to a byte array.
	 * 
	 * @param b
	 * @return <code>b</code> encoded in a byte array.
	 */
	public static byte[] toBytes(final boolean b) {
		byte[] bb = new byte[1];
		bb[0] = b ? (byte) -1 : (byte) 0;
		return bb;
	}

	/**
	 * @param b
	 * @return True or false.
	 */
	public static boolean toBoolean(final byte[] b) {
		if (b == null || b.length > 1) {
			throw new IllegalArgumentException("Array is wrong size");
		}
		return b[0] != (byte) 0;
	}

	/**
	 * Convert a long value to a byte array
	 * 
	 * @param val
	 * @return the byte array
	 */
	public static byte[] toBytes(long val) {
		byte[] b = new byte[8];
		for (int i = 7; i > 0; i--) {
			b[i] = (byte) (val);
			val >>>= 8;
		}
		b[0] = (byte) (val);
		return b;
	}

	/**
	 * Converts a byte array to a long value
	 * 
	 * @param bytes
	 * @return the long value
	 */
	public static long toLong(byte[] bytes) {
		return toLong(bytes, 0);
	}

	/**
	 * Converts a byte array to a long value
	 * 
	 * @param bytes
	 * @param offset
	 * @return the long value
	 */
	public static long toLong(byte[] bytes, int offset) {
		return toLong(bytes, offset, SIZEOF_LONG);
	}

	/**
	 * Converts a byte array to a long value
	 * 
	 * @param bytes
	 * @param offset
	 * @param length
	 * @return the long value
	 */
	public static long toLong(byte[] bytes, int offset, final int length) {
		if (bytes == null || length != SIZEOF_LONG
				|| (offset + length > bytes.length)) {
			return -1L;
		}
		long l = 0;
		for (int i = offset; i < (offset + length); i++) {
			l <<= 8;
			l ^= (long) bytes[i] & 0xFF;
		}
		return l;
	}

	/**
	 * Presumes float encoded as IEEE 754 floating-point "single format"
	 * 
	 * @param bytes
	 * @return Float made from passed byte array.
	 */
	public static float toFloat(byte[] bytes) {
		return toFloat(bytes, 0);
	}

	/**
	 * Presumes float encoded as IEEE 754 floating-point "single format"
	 * 
	 * @param bytes
	 * @param offset
	 * @return Float made from passed byte array.
	 */
	public static float toFloat(byte[] bytes, int offset) {
		int i = toInt(bytes, offset);
		return Float.intBitsToFloat(i);
	}

	/**
	 * @param f
	 * @return the float represented as byte []
	 */
	public static byte[] toBytes(final float f) {
		// Encode it as int
		int i = Float.floatToRawIntBits(f);
		return Bytes.toBytes(i);
	}

	/**
	 * @param bytes
	 * @return Return double made from passed bytes.
	 */
	public static double toDouble(final byte[] bytes) {
		return toDouble(bytes, 0);
	}

	/**
	 * @param bytes
	 * @param offset
	 * @return Return double made from passed bytes.
	 */
	public static double toDouble(final byte[] bytes, final int offset) {
		long l = toLong(bytes, offset);
		return Double.longBitsToDouble(l);
	}

	/**
	 * @param d
	 * @return the double represented as byte []
	 */
	public static byte[] toBytes(final double d) {
		// Encode it as a long
		long l = Double.doubleToRawLongBits(d);
		return Bytes.toBytes(l);
	}

	/**
	 * Convert an int value to a byte array
	 * 
	 * @param val
	 * @return the byte array
	 */
	public static byte[] toBytes(int val) {
		byte[] b = new byte[4];
		for (int i = 3; i > 0; i--) {
			b[i] = (byte) (val);
			val >>>= 8;
		}
		b[0] = (byte) (val);
		return b;
	}

	/**
	 * Converts a byte array to an int value
	 * 
	 * @param bytes
	 * @return the int value
	 */
	public static int toInt(byte[] bytes) {
		return toInt(bytes, 0);
	}

	/**
	 * Converts a byte array to an int value
	 * 
	 * @param bytes
	 * @param offset
	 * @return the int value
	 */
	public static int toInt(byte[] bytes, int offset) {
		return toInt(bytes, offset, SIZEOF_INT);
	}

	/**
	 * Converts a byte array to an int value
	 * 
	 * @param bytes
	 * @param offset
	 * @param length
	 * @return the int value
	 */
	public static int toInt(byte[] bytes, int offset, final int length) {
		if (bytes == null || length != SIZEOF_INT
				|| (offset + length > bytes.length)) {
			return -1;
		}
		int n = 0;
		for (int i = offset; i < (offset + length); i++) {
			n <<= 8;
			n ^= bytes[i] & 0xFF;
		}
		return n;
	}

	/**
	 * Put an int value out to the specified byte array position.
	 * 
	 * @param bytes
	 *            the byte array
	 * @param offset
	 *            position in the array
	 * @param val
	 *            int to write out
	 * @return incremented offset
	 */
	public static int putInt(byte[] bytes, int offset, int val) {
		if (bytes == null || (bytes.length - offset < SIZEOF_INT)) {
			return offset;
		}
		for (int i = offset + 3; i > offset; i--) {
			bytes[i] = (byte) (val);
			val >>>= 8;
		}
		bytes[offset] = (byte) (val);
		return offset + SIZEOF_INT;
	}

	/**
	 * Convert a short value to a byte array
	 * 
	 * @param val
	 * @return the byte array
	 */
	public static byte[] toBytes(short val) {
		byte[] b = new byte[SIZEOF_SHORT];
		b[1] = (byte) (val);
		val >>= 8;
		b[0] = (byte) (val);
		return b;
	}

	/**
	 * Converts a byte array to a short value
	 * 
	 * @param bytes
	 * @return the short value
	 */
	public static short toShort(byte[] bytes) {
		return toShort(bytes, 0);
	}

	/**
	 * Converts a byte array to a short value
	 * 
	 * @param bytes
	 * @param offset
	 * @return the short value
	 */
	public static short toShort(byte[] bytes, int offset) {
		return toShort(bytes, offset, SIZEOF_SHORT);
	}

	/**
	 * Converts a byte array to a short value
	 * 
	 * @param bytes
	 * @param offset
	 * @param length
	 * @return the short value
	 */
	public static short toShort(byte[] bytes, int offset, final int length) {
		if (bytes == null || length != SIZEOF_SHORT
				|| (offset + length > bytes.length)) {
			return -1;
		}
		short n = 0;
		n ^= bytes[offset] & 0xFF;
		n <<= 8;
		n ^= bytes[offset + 1] & 0xFF;
		return n;
	}

	/**
	 * Put a short value out to the specified byte array position.
	 * 
	 * @param bytes
	 *            the byte array
	 * @param offset
	 *            position in the array
	 * @param val
	 *            short to write out
	 * @return incremented offset
	 */
	public static int putShort(byte[] bytes, int offset, short val) {
		if (bytes == null || (bytes.length - offset < SIZEOF_SHORT)) {
			return offset;
		}
		bytes[offset + 1] = (byte) (val);
		val >>= 8;
		bytes[offset] = (byte) (val);
		return offset + SIZEOF_SHORT;
	}

	/**
	 * Convert a char value to a byte array
	 * 
	 * @param val
	 * @return the byte array
	 */
	public static byte[] toBytes(char val) {
		byte[] b = new byte[SIZEOF_CHAR];
		b[1] = (byte) (val);
		val >>= 8;
		b[0] = (byte) (val);
		return b;
	}

	/**
	 * Converts a byte array to a char value
	 * 
	 * @param bytes
	 * @return the char value
	 */
	public static char toChar(byte[] bytes) {
		return toChar(bytes, 0);
	}

	/**
	 * Converts a byte array to a char value
	 * 
	 * @param bytes
	 * @param offset
	 * @return the char value
	 */
	public static char toChar(byte[] bytes, int offset) {
		return toChar(bytes, offset, SIZEOF_CHAR);
	}

	/**
	 * Converts a byte array to a char value
	 * 
	 * @param bytes
	 * @param offset
	 * @param length
	 * @return the char value
	 */
	public static char toChar(byte[] bytes, int offset, final int length) {
		if (bytes == null || length != SIZEOF_CHAR
				|| (offset + length > bytes.length)) {
			return (char) -1;
		}
		char n = 0;
		n ^= bytes[offset] & 0xFF;
		n <<= 8;
		n ^= bytes[offset + 1] & 0xFF;
		return n;
	}

	/**
	 * Put a char value out to the specified byte array position.
	 * 
	 * @param bytes
	 *            the byte array
	 * @param offset
	 *            position in the array
	 * @param val
	 *            short to write out
	 * @return incremented offset
	 */
	public static int putChar(byte[] bytes, int offset, char val) {
		if (bytes == null || (bytes.length - offset < SIZEOF_CHAR)) {
			return offset;
		}
		bytes[offset + 1] = (byte) (val);
		val >>= 8;
		bytes[offset] = (byte) (val);
		return offset + SIZEOF_CHAR;
	}

	/**
	 * Convert a char array value to a byte array
	 * 
	 * @param val
	 * @return the byte array
	 */
	public static byte[] toBytes(char[] val) {
		byte[] bytes = new byte[val.length * 2];
		putChars(bytes, 0, val);
		return bytes;
	}

	/**
	 * Converts a byte array to a char array value
	 * 
	 * @param bytes
	 * @return the char value
	 */
	public static char[] toChars(byte[] bytes) {
		return toChars(bytes, 0, bytes.length);
	}

	/**
	 * Converts a byte array to a char array value
	 * 
	 * @param bytes
	 * @param offset
	 * @return the char value
	 */
	public static char[] toChars(byte[] bytes, int offset) {
		return toChars(bytes, offset, bytes.length - offset);
	}

	/**
	 * Converts a byte array to a char array value
	 * 
	 * @param bytes
	 * @param offset
	 * @param length
	 * @return the char value
	 */
	public static char[] toChars(byte[] bytes, int offset, final int length) {
		int max = offset + length;
		if (bytes == null || (max > bytes.length) || length % 2 == 1) {
			return null;
		}

		char[] chars = new char[length / 2];
		for (int i = 0, j = offset; i < chars.length && j < max; i++, j += 2) {
			char c = 0;
			c ^= bytes[j] & 0xFF;
			c <<= 8;
			c ^= bytes[j + 1] & 0xFF;
			chars[i] = c;
		}
		return chars;
	}

	/**
	 * Put a char array value out to the specified byte array position.
	 * 
	 * @param bytes
	 *            the byte array
	 * @param offset
	 *            position in the array
	 * @param val
	 *            short to write out
	 * @return incremented offset
	 */
	public static int putChars(byte[] bytes, int offset, char[] val) {
		int max = val.length * 2 + offset;
		if (bytes == null || (bytes.length < max)) {
			return offset;
		}
		for (int i = 0, j = offset; i < val.length && j < max; i++, j += 2) {
			char c = val[i];
			bytes[j + 1] = (byte) (c);
			bytes[j] = (byte) (c >>> 8);
		}

		return offset + SIZEOF_CHAR;
	}

	/**
	 * @param t
	 * @return Array of byte arrays made from passed array of Text
	 */
	public static byte[][] toByteArrays(final String[] t) {
		byte[][] result = new byte[t.length][];
		for (int i = 0; i < t.length; i++) {
			result[i] = Bytes.toBytes(t[i]);
		}
		return result;
	}

	/**
	 * @param column
	 * @return A byte array of a byte array where first and only entry is
	 *         <code>column</code>
	 */
	public static byte[][] toByteArrays(final String column) {
		return toByteArrays(toBytes(column));
	}

	/**
	 * @param column
	 * @return A byte array of a byte array where first and only entry is
	 *         <code>column</code>
	 */
	public static byte[][] toByteArrays(final byte[] column) {
		byte[][] result = new byte[1][];
		result[0] = column;
		return result;
	}

}
