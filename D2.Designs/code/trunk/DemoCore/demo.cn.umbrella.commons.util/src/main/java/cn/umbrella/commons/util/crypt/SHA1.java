package cn.umbrella.commons.util.crypt;

//安全哈希算法（Secure Hash Algorithm）主要适用于数字签名标准 （Digital Signature Standard DSS）里面定义的数字签名算法（Digital Signature Algorithm DSA）
public class SHA1 {

	public SHA1() {
		digestInt = new int[5];
		tmpData = new int[80];
	}

	private int process_input_bytes(byte bytedata[]) {
		System.arraycopy(abcde, 0, digestInt, 0, abcde.length);
		byte newbyte[] = byteArrayFormatData(bytedata);
		int MCount = newbyte.length / 64;
		for (int pos = 0; pos < MCount; pos++) {
			for (int j = 0; j < 16; j++)
				tmpData[j] = byteArrayToInt(newbyte, pos * 64 + j * 4);
			encrypt();
		}
		return 20;
	}

	private byte[] byteArrayFormatData(byte bytedata[]) {
		int zeros = 0;
		int size = 0;
		int n = bytedata.length;
		int m = n % 64;
		if (m < 56) {
			zeros = 55 - m;
			size = (n - m) + 64;
		} else if (m == 56) {
			zeros = 63;
			size = n + 8 + 64;
		} else {
			zeros = (63 - m) + 56;
			size = ((n + 64) - m) + 64;
		}
		byte newbyte[] = new byte[size];
		System.arraycopy(bytedata, 0, newbyte, 0, n);
		int l = n;
		newbyte[l++] = -128;
		for (int i = 0; i < zeros; i++)
			newbyte[l++] = 0;

		long N = n * 8L;
		byte h8 = (byte) (int) (N & 255L);
		byte h7 = (byte) (int) (N >> 8 & 255L);
		byte h6 = (byte) (int) (N >> 16 & 255L);
		byte h5 = (byte) (int) (N >> 24 & 255L);
		byte h4 = (byte) (int) (N >> 32 & 255L);
		byte h3 = (byte) (int) (N >> 40 & 255L);
		byte h2 = (byte) (int) (N >> 48 & 255L);
		byte h1 = (byte) (int) (N >> 56);
		newbyte[l++] = h1;
		newbyte[l++] = h2;
		newbyte[l++] = h3;
		newbyte[l++] = h4;
		newbyte[l++] = h5;
		newbyte[l++] = h6;
		newbyte[l++] = h7;
		newbyte[l++] = h8;
		return newbyte;
	}

	private int f1(int x, int y, int z) {
		return x & y | ~x & z;
	}

	private int f2(int x, int y, int z) {
		return x ^ y ^ z;
	}

	private int f3(int x, int y, int z) {
		return x & y | x & z | y & z;
	}

	private int f4(int x, int y) {
		return x << y | x >>> 32 - y;
	}

	private void encrypt() {
		for (int i = 16; i <= 79; i++)
			tmpData[i] = f4(tmpData[i - 3] ^ tmpData[i - 8] ^ tmpData[i - 14]
					^ tmpData[i - 16], 1);

		int tmpabcde[] = new int[5];
		for (int i1 = 0; i1 < tmpabcde.length; i1++)
			tmpabcde[i1] = digestInt[i1];

		for (int j = 0; j <= 19; j++) {
			int tmp = f4(tmpabcde[0], 5)
					+ f1(tmpabcde[1], tmpabcde[2], tmpabcde[3]) + tmpabcde[4]
					+ tmpData[j] + 0x5a827999;
			tmpabcde[4] = tmpabcde[3];
			tmpabcde[3] = tmpabcde[2];
			tmpabcde[2] = f4(tmpabcde[1], 30);
			tmpabcde[1] = tmpabcde[0];
			tmpabcde[0] = tmp;
		}

		for (int k = 20; k <= 39; k++) {
			int tmp = f4(tmpabcde[0], 5)
					+ f2(tmpabcde[1], tmpabcde[2], tmpabcde[3]) + tmpabcde[4]
					+ tmpData[k] + 0x6ed9eba1;
			tmpabcde[4] = tmpabcde[3];
			tmpabcde[3] = tmpabcde[2];
			tmpabcde[2] = f4(tmpabcde[1], 30);
			tmpabcde[1] = tmpabcde[0];
			tmpabcde[0] = tmp;
		}

		for (int l = 40; l <= 59; l++) {
			int tmp = f4(tmpabcde[0], 5)
					+ f3(tmpabcde[1], tmpabcde[2], tmpabcde[3]) + tmpabcde[4]
					+ tmpData[l] + 0x8f1bbcdc;
			tmpabcde[4] = tmpabcde[3];
			tmpabcde[3] = tmpabcde[2];
			tmpabcde[2] = f4(tmpabcde[1], 30);
			tmpabcde[1] = tmpabcde[0];
			tmpabcde[0] = tmp;
		}

		for (int m = 60; m <= 79; m++) {
			int tmp = f4(tmpabcde[0], 5)
					+ f2(tmpabcde[1], tmpabcde[2], tmpabcde[3]) + tmpabcde[4]
					+ tmpData[m] + 0xca62c1d6;
			tmpabcde[4] = tmpabcde[3];
			tmpabcde[3] = tmpabcde[2];
			tmpabcde[2] = f4(tmpabcde[1], 30);
			tmpabcde[1] = tmpabcde[0];
			tmpabcde[0] = tmp;
		}

		for (int i2 = 0; i2 < tmpabcde.length; i2++)
			digestInt[i2] = digestInt[i2] + tmpabcde[i2];

		for (int n = 0; n < tmpData.length; n++)
			tmpData[n] = 0;

	}

	private int byteArrayToInt(byte bytedata[], int i) {
		return (bytedata[i] & 0xff) << 24 | (bytedata[i + 1] & 0xff) << 16
				| (bytedata[i + 2] & 0xff) << 8 | bytedata[i + 3] & 0xff;
	}

	private void intToByteArray(int intValue, byte byteData[], int i) {
		byteData[i] = (byte) (intValue >>> 24);
		byteData[i + 1] = (byte) (intValue >>> 16);
		byteData[i + 2] = (byte) (intValue >>> 8);
		byteData[i + 3] = (byte) intValue;
	}

	private static String byteToHexString(byte ib) {
		char Digit[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
				'b', 'c', 'd', 'e', 'f' };
		char ob[] = new char[2];
		ob[0] = Digit[ib >>> 4 & 0xf];
		ob[1] = Digit[ib & 0xf];
		String s = new String(ob);
		return s;
	}

	private static String byteArrayToHexString(byte bytearray[]) {
		String strDigest = "";
		for (int i = 0; i < bytearray.length; i++)
			strDigest = strDigest + byteToHexString(bytearray[i]);

		return strDigest;
	}

	public byte[] getDigestOfBytes(byte byteData[]) {
		process_input_bytes(byteData);
		byte digest[] = new byte[20];
		for (int i = 0; i < digestInt.length; i++)
			intToByteArray(digestInt[i], digest, i * 4);

		return digest;
	}

	public String getDigestOfString(byte byteData[]) {
		return byteArrayToHexString(getDigestOfBytes(byteData));
	}

	public static void main(String args[]) {
		String data = "1";
		System.out.println(data);
		String digest = (new SHA1()).getDigestOfString(data.getBytes());
		System.out.println(digest);
	}

	private final int abcde[] = { 0x67452301, 0xefcdab89, 0x98badcfe,
			0x10325476, 0xc3d2e1f0 };
	private int digestInt[];
	private int tmpData[];
}
