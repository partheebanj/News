package org.news.partheebanj.news.util;

/**
 * Created by partheebanj on 8/30/17.
 */

public class StringUtil {

    public static String INTENT_EXTRA_SOURCE_ID = "ID";

    public static boolean isEmptyString(String str) {
        if (str.equals("") || str == null || str.equals("null")) {
            return true;
        } else {
            return false;
        }
    }
}
