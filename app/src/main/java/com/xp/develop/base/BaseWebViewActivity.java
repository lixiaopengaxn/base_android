package com.xp.develop.base;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xp.develop.R;
import com.xp.develop.customview.WebLayout;
import com.xp.develop.utils.webjs.AbsAgentWebUIController;
import com.xp.develop.utils.webjs.AgentWeb;
import com.xp.develop.utils.webjs.AgentWebSettingsImpl;
import com.xp.develop.utils.webjs.AgentWebUIControllerImplBase;
import com.xp.develop.utils.webjs.DefaultWebClient;
import com.xp.develop.utils.webjs.IAgentWebSettings;
import com.xp.develop.utils.webjs.IWebLayout;
import com.xp.develop.utils.webjs.MiddlewareWebChromeBase;
import com.xp.develop.utils.webjs.MiddlewareWebClientBase;
import com.xp.develop.utils.webjs.NestedScrollAgentWebView;
import com.xp.develop.utils.webjs.PermissionInterceptor;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author :  xpxn
 * blog  :  https://blog.csdn.net/qq_38729449
 * time  :  2018/8/29
 * desc  :  utils about initialization
 */
public abstract class BaseWebViewActivity extends BaseActivity {


    private ErrorLayoutEntity mErrorLayoutEntity;

    protected AgentWeb mAgentWeb;

    @BindView(R.id.base_web_view_container)
    LinearLayout baseContainer;

    protected abstract void initView(Bundle savedInstanceState);

    protected abstract String getUrl();

    /***
     * 加载本地页面和网络页面的type
     * @return
     */
    protected abstract int type();

    @Override
    public int getLayoutId() {
        return R.layout.base_web_view_linear_layout;
    }

    @Override
    public void init(Bundle savedInstanceState) {

        if (type() == 1) {
            scrollAgentWeb();
        } else {
            buildAgentWeb();
        }

        initView(savedInstanceState);
    }

    /****
     * 适合本地页面，或者静态页面
     */
    protected void scrollAgentWeb() {
        AbsAgentWebUIController.HAS_DESIGN_LIB = false;
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(baseContainer, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(getIndicatorColor())
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(mWebViewClient)
                .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .setWebLayout(new WebLayout(this))
//                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)//打开其他应用时，弹窗咨询用户是否前往其他应用
                .interceptUnkownUrl() //拦截找不到相关页面的Scheme
                .createAgentWeb()
                .ready()
                .go(getUrl());
        addBGChild(mAgentWeb.getWebCreator().getWebParentLayout()); // 得到 AgentWeb 最底层的控件
    }

    /***
     * 适合网络界面，比如：百度，京东   不支持下载
     */
    protected void buildAgentWeb() {
        NestedScrollAgentWebView webView = new NestedScrollAgentWebView(this);
        ErrorLayoutEntity mErrorLayoutEntity = getErrorLayoutEntity();
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(baseContainer, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(getIndicatorColor(), getIndicatorHeight())
                .setWebView(webView)
                .setPermissionInterceptor(getPermissionInterceptor())
                .setWebLayout(getWebLayout())
                .setAgentWebUIController(getAgentWebUIController())
                .interceptUnkownUrl()
                .setOpenOtherPageWays(getOpenOtherAppWay())
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(mWebViewClient)
                .setAgentWebWebSettings(getAgentWebSettings())
                .setMainFrameErrorView(mErrorLayoutEntity.layoutRes, mErrorLayoutEntity.reloadId)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .createAgentWeb()
                .ready()
                .go(getUrl());
    }

    protected @NonNull
    ErrorLayoutEntity getErrorLayoutEntity() {
        if (this.mErrorLayoutEntity == null) {
            this.mErrorLayoutEntity = new ErrorLayoutEntity();
        }
        return mErrorLayoutEntity;
    }

    protected AgentWeb getAgentWeb() {
        return this.mAgentWeb;
    }

    @Override
    public void initOnClick() {

    }

    @Override
    public boolean IsSwipeBackPage() {
        return true;
    }

    @Override
    public int isTemp() {
        return 0;
    }

    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            //do you work
//            Log.i("Info","onProgress:"+newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            if (titleView != null) {
                titleView.setCenterText(title);
            }
        }
    };


    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            //do you  work
            Log.i("Info", "BaseWebActivity onPageStarted");
        }
    };


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.i("Info", "onResult:" + requestCode + " onResult:" + resultCode);
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //mAgentWeb.destroy();
        mAgentWeb.getWebLifeCycle().onDestroy();
    }

    @OnClick(R.id.base_web_view_container)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.base_web_view_container:
                break;
        }
    }

    protected void addBGChild(FrameLayout frameLayout) {

        TextView mTextView = new TextView(frameLayout.getContext());
        mTextView.setText("技术由 北京华云智汇科技有限公司 提供");
        mTextView.setTextSize(16);
        mTextView.setTextColor(Color.parseColor("#727779"));
        frameLayout.setBackgroundColor(Color.parseColor("#272b2d"));
        FrameLayout.LayoutParams mFlp = new FrameLayout.LayoutParams(-2, -2);
        mFlp.gravity = Gravity.CENTER_HORIZONTAL;
        final float scale = frameLayout.getContext().getResources().getDisplayMetrics().density;
        mFlp.topMargin = (int) (15 * scale + 0.5f);
        frameLayout.addView(mTextView, 0, mFlp);
    }


    protected static class ErrorLayoutEntity {
        private int layoutRes = R.layout.agentweb_error_page;
        private int reloadId;

        public void setLayoutRes(int layoutRes) {
            this.layoutRes = layoutRes;
            if (layoutRes <= 0) {
                layoutRes = -1;
            }
        }

        public void setReloadId(int reloadId) {
            this.reloadId = reloadId;
            if (reloadId <= 0) {
                reloadId = -1;
            }
        }
    }

    public @Nullable
    IAgentWebSettings getAgentWebSettings() {
        return AgentWebSettingsImpl.getInstance();
    }

    protected @Nullable
    WebChromeClient getWebChromeClient() {
        return null;
    }

    protected @ColorInt
    int getIndicatorColor() {
        return ContextCompat.getColor(BaseWebViewActivity.this, R.color.colorPrimary);
    }

    protected int getIndicatorHeight() {
        return -1;
    }

    protected @Nullable
    WebViewClient getWebViewClient() {
        return null;
    }


    protected @Nullable
    WebView getWebView() {
        return null;
    }

    protected @Nullable
    IWebLayout getWebLayout() {
        return null;
    }

    protected @Nullable
    PermissionInterceptor getPermissionInterceptor() {
        return null;
    }

    public @Nullable
    AgentWebUIControllerImplBase getAgentWebUIController() {
        return null;
    }

    public @Nullable
    DefaultWebClient.OpenOtherPageWays getOpenOtherAppWay() {
        return null;
    }

    protected @NonNull
    MiddlewareWebChromeBase getMiddleWareWebChrome() {
        return new MiddlewareWebChromeBase() {
        };
    }

    protected @NonNull
    MiddlewareWebClientBase getMiddleWareWebClient() {
        return new MiddlewareWebClientBase() {
        };
    }



}
