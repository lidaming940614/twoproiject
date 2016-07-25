package feicui.newsapp02.activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import feicui.newsapp02.R;
import feicui.newsapp02.base.BaseActivity;
import feicui.newsapp02.db.NewsDbManager;
import feicui.newsapp02.db.News;

/**
 * Created by 1099057173 on 2016/7/23.
 */
public class WebViewActivity extends BaseActivity {
    WebView webView;
    Intent intent;
    String inputurl;
    NewsDbManager newsDbManager;
    private FloatingActionButton actionbtn;
    News news;
    ProgressBar mypro;
    @Override
    public void setlayout() {
        setContentView(R.layout.webview_show);
    }

    @Override
    public void settoolbar() {
    }
    @Override
    public void getview() {
        newsDbManager = NewsDbManager.getDBManger(this);
        webView= (WebView) findViewById(R.id.webviewshow_webview);
        actionbtn= (FloatingActionButton) findViewById(R.id.actionbtn);
        intent=getIntent();
        mypro= (ProgressBar) findViewById(R.id.webview_pro);
    }

    @Override
    public void setview() {

        actionbtn.setOnClickListener(this);
        if (intent!=null){
            Bundle b =intent.getExtras();
            if (b!=null){
                inputurl=b.getString("url","www.baidu.com");
                news= (News) b.getSerializable("news");
                Log.i("webview",news.toString());
            }
        }
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl(inputurl);
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress==100){
                    mypro.setVisibility(View.VISIBLE);
                }else {
                    mypro.setProgress(newProgress);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.actionbtn:
                newsDbManager.savenews("collect",news.getImgurl(),news.getNewsname(),news.getNewsurl(),news.getNewsdate(),news.getNewstype());
                Snackbar.make(actionbtn, "收藏成功", Snackbar.LENGTH_LONG).show();
                break;
        }
    }
}
