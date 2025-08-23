package org.silverstar.category.common.contant;

public enum YNType {
    Y, N;

    public static boolean toBoolean(String value) {
        if (value == null) return false;
        return "Y".equals(value);
    }

    public static String fromBoolean(Boolean value) {
        if (value == null) return "N";
        return value ? "Y" : "N";
    }
}
