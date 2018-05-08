package vn.edu.tdc.readnews;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class NewsActivity extends AppCompatActivity {

    private WebView webView;
    private String link;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        webView = (WebView) findViewById(R.id.webView);

        Bundle bundle = getIntent().getExtras();
        //link = bundle.getString(Variables.LINK);
        //String title = bundle.getString(Variables.TITLE);
        setTitle("Nội Dung Chi Tiết");
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);
        webView.setWebViewClient(new NeroWebView());
        //webView.getSettings().setJavaScriptEnabled(true);

        dialog = ProgressDialog.show(this, "", "Loading...");
        webView.loadUrl(link);

    }

    class NeroWebView extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            if (dialog != null) {
                dialog.dismiss();
            }
            super.onPageFinished(view, url);

        }
    }
}
