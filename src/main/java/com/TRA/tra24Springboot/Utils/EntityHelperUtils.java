package com.TRA.tra24Springboot.Utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public final class EntityHelperUtils {

    private EntityHelperUtils() {
    }

    public static boolean isNull(Object o) {
        return o == null;
    }

    public static boolean isNotNull(Object o) {
        return !isNull(o);
    }

    public static boolean isListPopulated(List<?> list) {
        return list != null && !list.isEmpty();
    }

    public static boolean isListNotPopulated(List<?> list) {
        return list == null || list.isEmpty();
    }

    public static boolean isSetPopulated(Set<?> set) {
        return set != null && !set.isEmpty();
    }

    public static Boolean isListPopulatedNotNull(List<?> list) {
        if (null != list) {
            list.removeAll(Collections.singleton(null));
            return !list.isEmpty();
        }
        return false;
    }

    public static boolean isStringSet(String str) {
        return ((str != null) && !str.trim().isEmpty());
    }

    public static boolean isStringSet(String... strings) {
        if (strings == null) {
            return false;
        }
        for (String string : strings) {
            if ((string == null) || (string.isEmpty()))
                return false;
        }

        return true;
    }

    public static boolean isStringNotSet(String str) {
        return ((str == null) || str.isEmpty());
    }

    public static boolean isIdSet(Integer... ids) {
        if (ids == null) {
            return false;
        }
        for (Integer id : ids) {
            if (!((id != null) && (id > 0)))
                return false;
        }

        return true;
    }

    public static boolean isIdNotSet(Integer... ids) {
        if (ids == null) {
            return true;
        }
        for (Integer id : ids) {
            if ((id != null) && (id > 0))
                return false;
        }

        return true;
    }

    public static <T> List<T> safeList(List<T> list) {
        return list == null ? Arrays.<T>asList() : list;
    }

    public static boolean equalsIgnoreCaseAndSpecialChar(String str1, String str2) {
        if (str1.equals(str2))
            return true;
        if (str1 == null || str2 == null)
            return false;
        str1 = str1.replaceAll("[^a-zA-Z0-9]", "");
        str2 = str2.replaceAll("[^a-zA-Z0-9]", "");
        return (str1.length() == str2.length())
                && str1.regionMatches(true, 0, str2, 0, str1.length());
    }
}