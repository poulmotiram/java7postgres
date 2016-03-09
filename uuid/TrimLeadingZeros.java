package com.postgresql.learning;

public class TrimLeadingZeros {

	public static void main(String[] args) {

		String[] in = { "01234", // "[1234]"
				"0001234a", // "[1234a]"
				"101234", // "[101234]"
				"000002829839", // "[2829839]"
				"0", // "[0]"
				"0000000", // "[0]"
				"0000009", // "[9]"
				"000000z", // "[z]"
				"000000.z", // "[.z]"
		};
		for (String s : in) {
			System.out.println("[" + s.replaceFirst("^0+(?!$)", "") + "]");
			String a1 = removeLeadingZeros(s);
			String a2 = StripLeadingZeros(s);
			String a3 = removeLeadingZeros1(s);
			System.out.println("using for loop a1 " + a1);
			System.out.println("using for loop a2 " + a2);
			System.out.println("using for loop a3 " + a3);

		}
	}

	private static String removeLeadingZeros(String s1) {
		try {
			Integer intVal = Integer.parseInt(s1);
			s1 = intVal.toString();
		} catch (Exception ex) {
			// whatever
		}
		return s1;
	}

	private static String StripLeadingZeros(String source) {
		int length = source.length();

		if (length < 2)
			return source;

		int i;
		for (i = 0; i < length - 1; i++) {
			char c = source.charAt(i);
			if (c != '0')
				break;
		}

		if (i == 0)
			return source;

		return source.substring(i);
	}

	public static String removeLeadingZeros1(String str) {
		if (str == null) {
			return null;
		}
		char[] chars = str.toCharArray();
		int index = 0;
		for (; index < str.length(); index++) {
			if (chars[index] != '0') {
				break;
			}
		}
		return (index == 0) ? str : str.substring(index);
	}
}

