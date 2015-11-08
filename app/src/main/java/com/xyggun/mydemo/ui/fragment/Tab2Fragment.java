package com.xyggun.mydemo.ui.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.xyggun.mydemo.R;
import com.xyggun.mydemo.app.Constant;

public class Tab2Fragment extends Fragment {
    private WebView myWebView; // 显示的内容
    private ProgressBar progressBar;
    private View view;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("test", "Tab2Fragment onAttach()");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("test", "Tab2Fragment onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab2, container, false);
        Log.d("test", "Tab2Fragment onCreateView() view=" + view);
        initWebView(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("test", "Tab2Fragment onViewCreated()");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("test", "Tab2Fragment onActivityCreated()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("test", "Tab2Fragment onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("test", "Tab2Fragment onResume()");
    }
    public void changeContent(String targetUrl){
        Log.d("test", "tab2 changeContent() view=" + view + " myWebView=" + myWebView);
    }

    private void initWebView(View view) {
        myWebView = (WebView) view.findViewById(R.id.mywebview);
        progressBar = (ProgressBar) view.findViewById(R.id.my_progressbar);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // 开启缓存
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheMaxSize(1024 * 1024 * 10); // 设置缓冲大小
        String appCacheDir = getActivity().getDir("cache", Context.MODE_PRIVATE).getPath();
        webSettings.setAppCachePath(appCacheDir);
        webSettings.setAllowFileAccess(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);

        // 支持屏幕缩放
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        // 不显示webview缩放按钮
        webSettings.setDisplayZoomControls(false);

        myWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                Toast.makeText(getActivity(), errorCode + "", Toast.LENGTH_SHORT).show();
                // TODO 自定义错误页面
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.d("test", "tab2 onfinished() url=" + url);
            }

        });

        myWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });
        /**
         * 屏蔽webview长按
         */
        myWebView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });

        myWebView.loadUrl(Constant.WebViewUrl.TAB2);
    }

}
