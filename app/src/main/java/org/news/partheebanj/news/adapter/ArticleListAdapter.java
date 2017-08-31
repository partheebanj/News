package org.news.partheebanj.news.adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.news.partheebanj.news.R;
import org.news.partheebanj.news.model.Article;
import org.news.partheebanj.news.util.StringUtil;

import java.util.List;

import static org.news.partheebanj.news.R.id.description_tv;

/**
 * Created by partheebanj on 8/31/17.
 */

public class ArticleListAdapter extends ArrayAdapter<Article> {

    private final Activity context;
    private final List<Article> articleList;

    public ArticleListAdapter(Activity context, int resource, List<Article> objects) {
        super(context, resource, objects);
        this.context = context;
        this.articleList = objects;
    }


    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.article_list_row, null, true);

        TextView header_tv = (TextView) rowView.findViewById(R.id.article_header_tv);
        TextView author_tv = (TextView) rowView.findViewById(R.id.article_author_tv);
        TextView description_tv = (TextView) rowView.findViewById(R.id.description_tv);
        TextView published_tv = (TextView) rowView.findViewById(R.id.published_tv);
        ImageView article_iv = (ImageView) rowView.findViewById(R.id.article_iv);


        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!StringUtil.isEmptyString(articleList.get(position).getURL())) {
                    Intent i = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(articleList.get(position).getURL()));
                    context.startActivity(i);
                }
            }
        });


        if (!StringUtil.isEmptyString(articleList.get(position).getTitle())) {
            header_tv.setVisibility(View.VISIBLE);
            header_tv.setText(articleList.get(position).getTitle());
        }

        if (!StringUtil.isEmptyString(articleList.get(position).getAuthor())) {
            author_tv.setVisibility(View.VISIBLE);
            author_tv.setText(articleList.get(position).getAuthor());
        }
        if (!StringUtil.isEmptyString(articleList.get(position).getDescription())) {
            description_tv.setVisibility(View.VISIBLE);
            description_tv.setText(articleList.get(position).getDescription());
        }

        if (!StringUtil.isEmptyString(articleList.get(position).getPublishedAt())) {
            published_tv.setVisibility(View.VISIBLE);
            published_tv.setText(articleList.get(position).getPublishedAt());
        }

        if (!StringUtil.isEmptyString(articleList.get(position).getImageToURL())) {
            article_iv.setVisibility(View.VISIBLE);
            Picasso.with(context).load(articleList.get(position).getImageToURL()).into(article_iv);
        }
        return rowView;
    }
}
