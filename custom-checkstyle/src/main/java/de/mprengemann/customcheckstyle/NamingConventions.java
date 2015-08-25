package de.mprengemann.customcheckstyle;

import java.util.regex.Pattern;

class NamingConventions {

    private static final Pattern WRONG_CONSTANT_NAME_PATTERN = Pattern.compile(".*[a-z].*");

    private NamingConventions() {}

    public static boolean isWrongConstantNaming(String variableName) {
        return WRONG_CONSTANT_NAME_PATTERN.matcher(variableName).matches();
    }
}
