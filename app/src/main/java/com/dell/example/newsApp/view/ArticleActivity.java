package com.dell.example.newsApp.view;

/*
Activity which display the article image and little bit description and a button to open the full article. Include Glide to load the image from url
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dell.example.newsApp.R;
import com.dell.example.newsApp.model.Constants;

public class ArticleActivity extends AppCompatActivity {

    private String URL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        createToolbar();

        receiveFromDataAdapter();
        buttonLinktoFullArticle();
    }

    private void buttonLinktoFullArticle() {
        Button linkToFullArticle = findViewById(R.id.article_button);
        linkToFullArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebViewActivity();
            }
        });
    }

    private void openWebViewActivity() {
        Intent browserIntent = new Intent(ArticleActivity.this, WebViewActivity.class);
        browserIntent.putExtra(Constants.INTENT_URL, URL);
        startActivity(browserIntent);
    }

    private void receiveFromDataAdapter() {
        String headLine = getIntent().getStringExtra(Constants.INTENT_HEADLINE);
        String description = getIntent().getStringExtra(Constants.INTENT_DESCRIPTION);
        String imgURL = getIntent().getStringExtra(Constants.INTENT_IMG_URL);
        URL = getIntent().getStringExtra(Constants.INTENT_ARTICLE_URL);
        TextView content_Headline = findViewById(R.id.content_Headline);
        content_Headline.setText(headLine);
        TextView content_Description = findViewById(R.id.content_Description);
        content_Description.setText(description);
        ImageView collapsingImage = findViewById(R.id.collapsingImage);
        Glide.with(this)
                .load(imgURL)
                .centerCrop()
                .error(R.drawable.ic_placeholder)
                .crossFade()
                .into(collapsingImage);
    }

    private void createToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_article);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }
}
