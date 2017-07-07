package cn.umbrella.conmmon.genarator;

import java.util.ArrayList;

/**
 * 
 * @create 2017-03-16
 */
public class URLGenarator {

	static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
			'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
			'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
			'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
			'Z' };
	private static byte[] decodes = new byte[256];
	static {
		for (int i = 0; i < DIGITS.length; i++) {
			decodes[DIGITS[i]] = (byte) i;
		}
	}

	// Begin
	public static ArrayList<Integer> base62(int id) {
		ArrayList<Integer> value = new ArrayList<Integer>();
		while (id > 0) {
			int remainder = id % 62;
			value.add(remainder);
			id = id / 62;
		}
		return value;
	}

	public static int base10(ArrayList<Integer> base62) {
		// make sure the size of base62 is 6
		// for (int i = 1; i <= 6 - base62.size(); i++) {
		// base62.add(0, 0);
		// }

		int id = 0;
		int size = base62.size();
		int j = 0;
		for (int i = size - 1; i >= 0; i--) {
			int value = base62.get(i);
			id += (int) (value * Math.pow(62, size - j - 1));
			j++;
		}

		// for (int i = 0; i < size; i++) {
		// int value = base62.get(i);
		// id += (int) (value * Math.pow(62, size - i - 1));
		// }
		return id;
	}

	// End

	// Begin
	public static String en(ArrayList<Integer> base62) {
		// make sure the size of base62 is 6
		int len = (6 - base62.size());
		for (int i = 0; i < len; i++) {
			base62.add(i, 0);
		}
		String res = "";
		int size = base62.size();
		for (int i = 0; i < size; i++) {
			char value = DIGITS[base62.get(i)];
			res += value;
		}
		return res;
	}

	public static ArrayList<Integer> de(String shortUrl) {
		// make sure the size of base62 is 6
		ArrayList<Integer> array = new ArrayList<Integer>();
		char[] data = shortUrl.toCharArray();
		for (int i = 0; i < data.length; i++) {
			int value = String.valueOf(DIGITS).indexOf(data[i]);
			array.add(i, value);
		}
		return array;
	}

	// End

	public static String shorten(String longURL) {
		return null;
	}

	public static void main(String[] args) {
		int id = 965660736;
		// id = 2475668;
		ArrayList<Integer> array = base62(id);
		System.out.println(base62(id));
		System.out.println(en(array));
		System.out.println(base10(array));
	}

}
