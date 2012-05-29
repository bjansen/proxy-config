package com.excilys.proxyconfig.internal.util;

public class Strings {

    public static String uncapFirst(String str) {
        if (isEmpty(str)) {
            return "";
        } else if (str.length() == 1) {
            return str.toLowerCase();
        }

        char firstChar = str.charAt(0);

        if (isUpperCase(firstChar)) {
            firstChar = toLowerCase(firstChar);
        }

        return firstChar + str.substring(1);
    }

    public static char toLowerCase(char c) {
        return (char) (c ^ 0x20);
    }

    public static boolean isUpperCase(char c) {
        return (c >= 'A') && (c <= 'Z');
    }

    public static boolean isEmpty(String str) {
        return (str == null) || str.equals("");
    }

    public static String camelCaseToDots(String str) {
        return camelCaseToSeparator(str, '.');
    }

    public static String camelCaseToUnderscores(String str) {
        return camelCaseToSeparator(str, '_');
    }

    public static String camelCaseToSeparator(String str, char separator) {
        if (isEmpty(str)) {
            return "";
        }

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (isUpperCase(c)) {
                builder.append(separator).append(toLowerCase(c));
            } else {
                builder.append(c);
            }
        }

        if (builder.charAt(0) == separator) {
            builder.deleteCharAt(0);
        }

        return builder.toString();
    }
}
