package cn.umbrella.conmmons.util.crypt;

public class Base64 {

	public Base64() {
	}

	private static final byte encodingTable[] = { 65, 66, 67, 68, 69, 70, 71,
			72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88,
			89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108,
			109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121,
			122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
	private static final byte decodingTable[];

	static {
		decodingTable = new byte[128];
		for (int i = 0; i < 128; i++)
			decodingTable[i] = -1;

		for (int i = 65; i <= 90; i++)
			decodingTable[i] = (byte) (i - 65);

		for (int i = 97; i <= 122; i++)
			decodingTable[i] = (byte) ((i - 97) + 26);

		for (int i = 48; i <= 57; i++)
			decodingTable[i] = (byte) ((i - 48) + 52);

		decodingTable[43] = 62;
		decodingTable[47] = 63;
	}

	private static char base64Code[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G',
			'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
			'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
			'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
			'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', '+', '/', };

	private static byte base64Decode[] = { -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1,
			-1,
			-1, // ע��}��63��Ϊ����SMP��
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 63,
			-1,
			63, // ��/���͡�-���������63��
			52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, 0, -1, -1, -1,
			0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
			14, // ע��}��0��
			15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1,
			-1, // ��A���͡�=���������0��
			-1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41,
			42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, };

	public static String encodeByte(byte[] b) {
		int code = 0;

		StringBuffer sb = new StringBuffer(((b.length - 1) / 3) << 2 + 4);

		for (int i = 0; i < b.length; i++) {
			code |= (b[i] << (16 - i % 3 * 8)) & (0xff << (16 - i % 3 * 8));
			if (i % 3 == 2 || i == b.length - 1) {
				sb.append(base64Code[(code & 0xfc0000) >>> 18]);
				sb.append(base64Code[(code & 0x3f000) >>> 12]);
				sb.append(base64Code[(code & 0xfc0) >>> 6]);
				sb.append(base64Code[code & 0x3f]);
				code = 0;
			}
		}

		if (b.length % 3 > 0) {
			sb.setCharAt(sb.length() - 1, '=');
		}
		if (b.length % 3 == 1) {
			sb.setCharAt(sb.length() - 2, '=');
		}
		return sb.toString();
	}

	public static byte[] encode(byte data[]) {
		int modulus = data.length % 3;
		byte bytes[];
		if (modulus == 0)
			bytes = new byte[(4 * data.length) / 3];
		else
			bytes = new byte[4 * (data.length / 3 + 1)];
		int dataLength = data.length - modulus;
		int i = 0;
		for (int j = 0; i < dataLength; j += 4) {
			int a1 = data[i] & 0xff;
			int a2 = data[i + 1] & 0xff;
			int a3 = data[i + 2] & 0xff;
			bytes[j] = encodingTable[a1 >>> 2 & 0x3f];
			bytes[j + 1] = encodingTable[(a1 << 4 | a2 >>> 4) & 0x3f];
			bytes[j + 2] = encodingTable[(a2 << 2 | a3 >>> 6) & 0x3f];
			bytes[j + 3] = encodingTable[a3 & 0x3f];
			i += 3;
		}

		switch (modulus) {
		case 1: // '\001'
		{
			int d1 = data[data.length - 1] & 0xff;
			int b1 = d1 >>> 2 & 0x3f;
			int b2 = d1 << 4 & 0x3f;
			bytes[bytes.length - 4] = encodingTable[b1];
			bytes[bytes.length - 3] = encodingTable[b2];
			bytes[bytes.length - 2] = 61;
			bytes[bytes.length - 1] = 61;
			break;
		}

		case 2: // '\002'
		{
			int d1 = data[data.length - 2] & 0xff;
			int d2 = data[data.length - 1] & 0xff;
			int b1 = d1 >>> 2 & 0x3f;
			int b2 = (d1 << 4 | d2 >>> 4) & 0x3f;
			int b3 = d2 << 2 & 0x3f;
			bytes[bytes.length - 4] = encodingTable[b1];
			bytes[bytes.length - 3] = encodingTable[b2];
			bytes[bytes.length - 2] = encodingTable[b3];
			bytes[bytes.length - 1] = 61;
			break;
		}
		}
		return bytes;
	}

	public static byte[] decodeStr(String code) {
		if (code == null) {
			return new byte[0];
		}
		int len = code.length();
		if (len % 4 != 0) {
			throw new IllegalArgumentException(
					"Base64 string length must be 4*n");
		}
		if (code.length() == 0) {
			return new byte[0];
		}

		int pad = 0;
		if (code.charAt(len - 1) == '=') {
			pad++;
		}
		if (code.charAt(len - 2) == '=') {
			pad++;
		}

		int retLen = len / 4 * 3 - pad;

		byte[] ret = new byte[retLen];

		char ch1, ch2, ch3, ch4;
		int i;
		for (i = 0; i < len; i += 4) {
			int j = i / 4 * 3;
			ch1 = code.charAt(i);
			ch2 = code.charAt(i + 1);
			ch3 = code.charAt(i + 2);
			ch4 = code.charAt(i + 3);
			int tmp = (base64Decode[ch1] << 18) | (base64Decode[ch2] << 12)
					| (base64Decode[ch3] << 6) | (base64Decode[ch4]);
			ret[j] = (byte) ((tmp & 0xff0000) >> 16);
			if (i < len - 4) {
				ret[j + 1] = (byte) ((tmp & 0x00ff00) >> 8);
				ret[j + 2] = (byte) ((tmp & 0x0000ff));
			} else {
				if (j + 1 < retLen) {
					ret[j + 1] = (byte) ((tmp & 0x00ff00) >> 8);
				}
				if (j + 2 < retLen) {
					ret[j + 2] = (byte) ((tmp & 0x0000ff));
				}
			}
		}
		return ret;
	}

	public static byte[] decode(byte data[]) {
		data = discardNonBase64Bytes(data);
		byte bytes[];
		if (data[data.length - 2] == 61)
			bytes = new byte[(data.length / 4 - 1) * 3 + 1];
		else if (data[data.length - 1] == 61)
			bytes = new byte[(data.length / 4 - 1) * 3 + 2];
		else
			bytes = new byte[(data.length / 4) * 3];
		int i = 0;
		for (int j = 0; i < data.length - 4; j += 3) {
			byte b1 = decodingTable[data[i]];
			byte b2 = decodingTable[data[i + 1]];
			byte b3 = decodingTable[data[i + 2]];
			byte b4 = decodingTable[data[i + 3]];
			bytes[j] = (byte) (b1 << 2 | b2 >> 4);
			bytes[j + 1] = (byte) (b2 << 4 | b3 >> 2);
			bytes[j + 2] = (byte) (b3 << 6 | b4);
			i += 4;
		}

		if (data[data.length - 2] == 61) {
			byte b1 = decodingTable[data[data.length - 4]];
			byte b2 = decodingTable[data[data.length - 3]];
			bytes[bytes.length - 1] = (byte) (b1 << 2 | b2 >> 4);
		} else if (data[data.length - 1] == 61) {
			byte b1 = decodingTable[data[data.length - 4]];
			byte b2 = decodingTable[data[data.length - 3]];
			byte b3 = decodingTable[data[data.length - 2]];
			bytes[bytes.length - 2] = (byte) (b1 << 2 | b2 >> 4);
			bytes[bytes.length - 1] = (byte) (b2 << 4 | b3 >> 2);
		} else {
			byte b1 = decodingTable[data[data.length - 4]];
			byte b2 = decodingTable[data[data.length - 3]];
			byte b3 = decodingTable[data[data.length - 2]];
			byte b4 = decodingTable[data[data.length - 1]];
			bytes[bytes.length - 3] = (byte) (b1 << 2 | b2 >> 4);
			bytes[bytes.length - 2] = (byte) (b2 << 4 | b3 >> 2);
			bytes[bytes.length - 1] = (byte) (b3 << 6 | b4);
		}
		return bytes;
	}

	public static byte[] decode(String data) {
		data = discardNonBase64Chars(data);
		byte bytes[];
		if (data.charAt(data.length() - 2) == '=')
			bytes = new byte[(data.length() / 4 - 1) * 3 + 1];
		else if (data.charAt(data.length() - 1) == '=')
			bytes = new byte[(data.length() / 4 - 1) * 3 + 2];
		else
			bytes = new byte[(data.length() / 4) * 3];
		int i = 0;
		for (int j = 0; i < data.length() - 4; j += 3) {
			byte b1 = decodingTable[data.charAt(i)];
			byte b2 = decodingTable[data.charAt(i + 1)];
			byte b3 = decodingTable[data.charAt(i + 2)];
			byte b4 = decodingTable[data.charAt(i + 3)];
			bytes[j] = (byte) (b1 << 2 | b2 >> 4);
			bytes[j + 1] = (byte) (b2 << 4 | b3 >> 2);
			bytes[j + 2] = (byte) (b3 << 6 | b4);
			i += 4;
		}

		if (data.charAt(data.length() - 2) == '=') {
			byte b1 = decodingTable[data.charAt(data.length() - 4)];
			byte b2 = decodingTable[data.charAt(data.length() - 3)];
			bytes[bytes.length - 1] = (byte) (b1 << 2 | b2 >> 4);
		} else if (data.charAt(data.length() - 1) == '=') {
			byte b1 = decodingTable[data.charAt(data.length() - 4)];
			byte b2 = decodingTable[data.charAt(data.length() - 3)];
			byte b3 = decodingTable[data.charAt(data.length() - 2)];
			bytes[bytes.length - 2] = (byte) (b1 << 2 | b2 >> 4);
			bytes[bytes.length - 1] = (byte) (b2 << 4 | b3 >> 2);
		} else {
			byte b1 = decodingTable[data.charAt(data.length() - 4)];
			byte b2 = decodingTable[data.charAt(data.length() - 3)];
			byte b3 = decodingTable[data.charAt(data.length() - 2)];
			byte b4 = decodingTable[data.charAt(data.length() - 1)];
			bytes[bytes.length - 3] = (byte) (b1 << 2 | b2 >> 4);
			bytes[bytes.length - 2] = (byte) (b2 << 4 | b3 >> 2);
			bytes[bytes.length - 1] = (byte) (b3 << 6 | b4);
		}
		return bytes;
	}

	private static byte[] discardNonBase64Bytes(byte data[]) {
		byte temp[] = new byte[data.length];
		int bytesCopied = 0;
		for (int i = 0; i < data.length; i++)
			if (isValidBase64Byte(data[i]))
				temp[bytesCopied++] = data[i];

		byte newData[] = new byte[bytesCopied];
		System.arraycopy(temp, 0, newData, 0, bytesCopied);
		return newData;
	}

	private static String discardNonBase64Chars(String data) {
		StringBuffer sb = new StringBuffer();
		int length = data.length();
		for (int i = 0; i < length; i++)
			if (isValidBase64Byte((byte) data.charAt(i)))
				sb.append(data.charAt(i));

		return sb.toString();
	}

	private static boolean isValidBase64Byte(byte b) {
		if (b == 61)
			return true;
		if (b < 0 || b >= 128)
			return false;
		return decodingTable[b] != -1;
	}

}
