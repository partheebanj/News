package org.news.partheebanj.news.presentation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;
import org.news.partheebanj.news.R;
import org.news.partheebanj.news.adapter.ArticleListAdapter;
import org.news.partheebanj.news.http.HttpRequestHelper;
import org.news.partheebanj.news.model.Article;
import org.news.partheebanj.news.model.ArticleConverter;
import org.news.partheebanj.news.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by partheebanj on 8/30/17.
 */

public class ArticleListActivity extends BaseActivty {

    private ListView mArticle_lv;
    private String TAG = ArticleListActivity.class.getName();
    private String mSourceId = "";
    private List<Article> mArticleList = new ArrayList<Article>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);
        mSourceId = getIntent().getExtras().getString(StringUtil.INTENT_EXTRA_SOURCE_ID);

        mArticle_lv = (ListView) findViewById(R.id.article_list);

        if (!StringUtil.isEmptyString(mSourceId)) {
            populateArticleList(mSourceId);
        } else {
            showInvalidSourceAlert();
        }

    }

    private void populateArticleList(String sourceId) {
        HttpRequestHelper.populateArticle(ArticleListActivity.this, sourceId, callBackArticle, errorListenerArticle);
    }

    private void showInvalidSourceAlert() {

        AlertDialog.Builder ad = new AlertDialog.Builder(ArticleListActivity.this);
        ad.setTitle("ALERT");
        ad.setMessage("Please provide a valid news source.");
        ad.setCancelable(false);

        ad.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        finish();
                    }
                });

        AlertDialog alert = ad.create();
        alert.show();
    }


    /* CALL-BACK : Article for specific News Source. */
    Response.Listener<JSONObject> callBackArticle = new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            ArticleConverter converter = new ArticleConverter(response);
            mArticleList = converter.getArticleList();

            ArticleListAdapter adapter = new ArticleListAdapter(ArticleListActivity.this, R.layout.article_list_row, mArticleList);
            mArticle_lv.setAdapter(adapter);
        }
    };


    /* ERROR LISTENER : Article for specific News Source. */
    Response.ErrorListener errorListenerArticle = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.d(TAG, error.toString());
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
