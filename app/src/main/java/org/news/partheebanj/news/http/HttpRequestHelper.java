package org.news.partheebanj.news.http;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import static org.news.partheebanj.news.util.HttpUtil.ARTICLE_BASE_URL;
import static org.news.partheebanj.news.util.HttpUtil.ARTICLE_URL_API_KEY;
import static org.news.partheebanj.news.util.HttpUtil.ARTICLE_URL_SOURCE;
import static org.news.partheebanj.news.util.HttpUtil.SOURCE_URL;

/**
 * Created by partheebanj on 8/30/17.
 */

public class HttpRequestHelper {

    public static RequestQueue mRequestQueue;

    public static RequestQueue getRequestQueue(Context context) {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(context);
        }
        return mRequestQueue;
    }

    /* Method for calling news source. */
    public static void populateNewsSource(Context context,
                                          Response.Listener<JSONObject> callBack,
                                          Response.ErrorListener errorListener) {
        JsonObjectRequest newsSourceObjectRequest = new JsonObjectRequest(Request.Method.GET,
                SOURCE_URL, null,
                callBack, errorListener);
        HttpRequestHelper.getRequestQueue(context).add(newsSourceObjectRequest);
    }

    /*Method for calling news article for specific source. */
    public static void populateArticle(Context context, String sourceId,
                                       Response.Listener<JSONObject> callBack,
                                       Response.ErrorListener errorListener) {

        String ARTICLE_URL = ARTICLE_BASE_URL + ARTICLE_URL_SOURCE + sourceId + ARTICLE_URL_API_KEY;
        JsonObjectRequest newsSourceObjectRequest = new JsonObjectRequest(Request.Method.GET,
                ARTICLE_URL, null,
                callBack, errorListener);
        HttpRequestHelper.getRequestQueue(context).add(newsSourceObjectRequest);
    }
}
