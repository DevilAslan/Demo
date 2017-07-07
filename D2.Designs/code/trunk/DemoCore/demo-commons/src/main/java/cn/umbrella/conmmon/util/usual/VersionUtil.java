package cn.umbrella.conmmon.util.usual;

public class VersionUtil {

	/**
	 * 
	 * @param first
	 * @param second
	 * @return if first > second, return 1, if equal, return 0, else return -1
	 */
	public static int compare(String first, String second) {
		if (first == null || first.length() == 0 || second == null
				|| second.length() == 0)
			throw new IllegalArgumentException("Invalid parameter!");

		int index1 = 0;
		int index2 = 0;
		while (index1 < first.length() && index2 < second.length()) {
			int[] number1 = getValue(first, index1);
			int[] number2 = getValue(second, index2);

			if (number1[0] < number2[0])
				return -1;
			else if (number1[0] > number2[0])
				return 1;
			else {
				index1 = number1[1] + 1;
				index2 = number2[1] + 1;
			}
		}
		if (index1 == first.length() && index2 == second.length())
			return 0;
		if (index1 < first.length())
			return 1;
		else
			return -1;
	}

	/**
	 * 
	 * @param version
	 * @param index
	 *            the starting point
	 * @return the number between two dots, and the index of the dot
	 */
	public static int[] getValue(String version, int index) {
		int[] value_index = new int[2];
		StringBuilder sb = new StringBuilder();
		while (index < version.length() && version.charAt(index) != '.') {
			sb.append(version.charAt(index));
			index++;
		}
		value_index[0] = Integer.parseInt(sb.toString());
		value_index[1] = index;

		return value_index;
	}

	public static void main(String[] args) {
		String version1 = "2.20.3";
		String version2 = "2.6.9";

		int result = compare(version1, version2);
		if (result == 1) {
			System.out.println(version1 + " is newer.");
		} else if (result == 0) {
			System.out.println("The two versions are the same.");
		} else {
			System.out.println(version2 + " is newer.");
		}
	}
}
