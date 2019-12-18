package org.nl.magiamerlini.utils;

import com.google.common.base.CaseFormat;

public class EnumUtils {
	public static String getCorrespondingString(String enumName) {
		String snakeCaseName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, enumName);

		return snakeCaseName;
	}
	
	public static boolean correspondingToString(String enumName, String string) {
		return getCorrespondingString(enumName).equals(string);
	}
}
