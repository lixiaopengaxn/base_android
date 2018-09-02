package com.xp.develop.test.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xp.develop.R;
import com.xp.develop.activity.MainActivity;
import com.xp.develop.customview.ObservableScrollView;
import com.xp.develop.utils.StatusbarUtils;

/**
 * author :  xpxn
 * blog  :  https://blog.csdn.net/qq_38729449
 * time  :  2018/9/2
 * desc  :  带头部并且渐变的activity
 */
public class TestScrollActivity extends AppCompatActivity implements ObservableScrollView.OnObservableScrollViewListener {


    private ObservableScrollView mObservableScrollView;
    private TextView mImageView;
    private LinearLayout mHeaderContent;

    private int mHeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.TestScroll);
        super.onCreate(savedInstanceState);
        //设置透明状态栏
        StatusbarUtils.enableTranslucentStatusbar(this);
        setContentView(R.layout.test_scroll_activity_layout);

        //初始化控件
        mObservableScrollView = (ObservableScrollView) findViewById(R.id.sv_main_content);
        mImageView = (TextView) findViewById(R.id.tv_main_topContent);
        mHeaderContent = (LinearLayout) findViewById(R.id.ll_header_content);

        //获取标题栏高度
        ViewTreeObserver viewTreeObserver = mImageView.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mImageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mHeight = mImageView.getHeight() - mHeaderContent.getHeight();//这里取的高度应该为图片的高度-标题栏
                //注册滑动监听
                mObservableScrollView.setOnObservableScrollViewListener(TestScrollActivity.this);
            }
        });
    }

    @Override
    public void onObservableScrollViewListener(int l, int t, int oldl, int oldt) {
        {
            if (t <= 0) {
                //顶部图处于最顶部，标题栏透明
                mHeaderContent.setBackgroundColor(Color.argb(0, 48, 63, 159));
            } else if (t > 0 && t < mHeight) {
                //滑动过程中，渐变
                float scale = (float) t / mHeight;//算出滑动距离比例
                float alpha = (255 * scale);//得到透明度
                mHeaderContent.setBackgroundColor(Color.argb((int) alpha, 235, 81, 129));
            } else {
                //过顶部图区域，标题栏定色
                mHeaderContent.setBackgroundColor(Color.argb(255, 235, 81, 129));
            }
        }
    }
}
