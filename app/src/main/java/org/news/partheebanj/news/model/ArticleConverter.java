package org.news.partheebanj.news.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.author;

/**
 * Created by partheebanj on 8/30/17.
 */

public class ArticleConverter {

    List<Article> articleList = new ArrayList<Article>();

    public List<Article> getArticleList() {
        return articleList;
    }


    public ArticleConverter(JSONObject response) {
        int size = 0;

        try {
            size = response.getJSONArray(JsonMap.articles).length();
            for (int i = 0; i < size; i++) {
                Article tempArticle = new Article();
                tempArticle.setTitle(response.getJSONArray(JsonMap.articles).getJSONObject(i).getString(JsonMap.title));
                tempArticle.setAuthor(response.getJSONArray(JsonMap.articles).getJSONObject(i).getString(JsonMap.author));
                tempArticle.setDescription(response.getJSONArray(JsonMap.articles).getJSONObject(i).getString(JsonMap.description));
                tempArticle.setImageToURL(response.getJSONArray(JsonMap.articles).getJSONObject(i).getString(JsonMap.urlToImage));
                tempArticle.setURL(response.getJSONArray(JsonMap.articles).getJSONObject(i).getString(JsonMap.url));
                tempArticle.setPublishedAt(response.getJSONArray(JsonMap.articles).getJSONObject(i).getString(JsonMap.publishedAt));
                articleList.add(tempArticle);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private interface JsonMap {
        String articles = "articles";
        String title = "title";
        String author = "author";
        String description = "description";
        String url = "url";
        String urlToImage = "urlToImage";
        String publishedAt = "publishedAt";
    }
}
