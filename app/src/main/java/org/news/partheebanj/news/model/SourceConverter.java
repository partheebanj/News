package org.news.partheebanj.news.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by partheebanj on 8/30/17.
 */

public class SourceConverter {

    private List<Source> mSourceList = new ArrayList<Source>();

    public SourceConverter(JSONObject response) {

        try {
            JSONArray sourceArray = response.getJSONArray(JsonMap.SOURCES);
            for (int i = 0; i < sourceArray.length(); i++) {
                Source tempSourceObj = new Source();
                tempSourceObj.setmSourceId(sourceArray.getJSONObject(i).getString(JsonMap.ID));
                tempSourceObj.setmSourceName(sourceArray.getJSONObject(i).getString(JsonMap.NAME));
                mSourceList.add(tempSourceObj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Source> getmSourceList() {
        return mSourceList;
    }


    private interface JsonMap {
        String SOURCES = "sources";
        String ID = "id";
        String NAME = "name";
    }
}
