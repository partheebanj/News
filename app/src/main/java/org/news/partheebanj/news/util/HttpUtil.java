package org.news.partheebanj.news.util;

/**
 * Created by partheebanj on 8/30/17.
 */

public class HttpUtil {

    public static String BASE_URL = "https://newsapi.org";
    public static String SOURCE_URL = BASE_URL + "/v1/sources";

    // ARTICLE URL Strings
    public static String ARTICLE_BASE_URL = BASE_URL + "/v1/articles?";
    public static String ARTICLE_URL_SOURCE = "source=";
    public static String ARTICLE_URL_API_KEY = "&apiKey=3450ff04fd0643ca865c49808f49ac98";
}
