package org.news.partheebanj.news.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;
import org.news.partheebanj.news.R;
import org.news.partheebanj.news.http.HttpRequestHelper;
import org.news.partheebanj.news.model.SourceConverter;
import org.news.partheebanj.news.model.Source;
import org.news.partheebanj.news.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivty {

    private String TAG = SearchActivity.class.getName();

    private AutoCompleteTextView mSearch_tv;
    private Button mSearch_btn;

    private List<Source> mSourceList = new ArrayList<Source>();
    private List<String> mSourceNameList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mSearch_tv = (AutoCompleteTextView) findViewById(R.id.search_auto_tv);
        mSearch_btn = (Button) findViewById(R.id.search_btn);
        mSearch_btn.setOnClickListener(searchButtonListener);

        populateNewsSource();
    }

    private void populateNewsSource() {
        HttpRequestHelper.populateNewsSource(SearchActivity.this, callBackNewsSource, errorListenerNewsSource);
    }


    /* CALL-BACK : News Source. */
    Response.Listener<JSONObject> callBackNewsSource = new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {

            SourceConverter converter = new SourceConverter(response);
            mSourceList = converter.getmSourceList();

            for (int i = 0; i < mSourceList.size(); i++) {
                mSourceNameList.add(mSourceList.get(i).getmSourceName());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(SearchActivity.this,
                    android.R.layout.simple_dropdown_item_1line, mSourceNameList);
            mSearch_tv.setAdapter(adapter);
        }
    };


    /* ERROR LISTENER : News Source. */
    Response.ErrorListener errorListenerNewsSource = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.d(TAG, error.toString());
        }
    };


    /* Search Button click listener. */
    View.OnClickListener searchButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String textCompare = String.valueOf(mSearch_tv.getText());
            int size = mSourceNameList.size();

            // Start Next Activity.
            Intent intent = new Intent(SearchActivity.this, ArticleListActivity.class);
            intent.putExtra(StringUtil.INTENT_EXTRA_SOURCE_ID, "");
            for (int i = 0; i < size; i++) {
                if (textCompare.equals(mSourceNameList.get(i))) {
                    intent.putExtra(StringUtil.INTENT_EXTRA_SOURCE_ID, mSourceList.get(i).getmSourceId());
                }
            }
            startActivity(intent);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        mSearch_tv.setText("");
    }
}
