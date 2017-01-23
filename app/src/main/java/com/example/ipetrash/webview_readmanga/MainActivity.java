package com.example.ipetrash.webview_readmanga;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    private WebView webView;

    private String urlGrouple = "http://grouple.ru/";
    private String urlReadmanga = "http://readmanga.me/";
    private String urlMintmanga = "http://mintmanga.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // Фильтр ссылок -- позволяем открывать только те, что описаны ниже
                String host = Uri.parse(url).getHost();
                if(host.endsWith("grouple.ru") || host.endsWith("readmanga.me") || host.endsWith("mintmanga.com")) {
                    return false;
                }

                // Игнорирование других ссылок (на том сайте открывалась редиректом рекдама на весь экран
                // что вызывало боль, т.к. ни закрыть ее, ни вернуться назад не получалось)
                return true;
            }
        });

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.loadUrl(urlReadmanga);
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Получим идентификатор выбранного пункта меню
        int id = item.getItemId();

        // Операции для выбранного пункта меню
        switch (id) {
            case R.id.action_grouple:
                webView.loadUrl(urlGrouple);
                return true;

            case R.id.action_readmanga:
                webView.loadUrl(urlReadmanga);
                return true;

            case R.id.action_mintmanga:
                webView.loadUrl(urlMintmanga);
                return true;

            case R.id.action_reload:
                webView.reload();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
