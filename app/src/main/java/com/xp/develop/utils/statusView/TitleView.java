package com.xp.develop.utils.statusView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xp.develop.R;


public class TitleView extends RelativeLayout {

    //标题栏包括状态栏的颜色
    private LinearLayout linearLayout;

    //标题拦的颜色
    private RelativeLayout relativeLayout;

    //左边的文字
    private TextView textLeft;

    //左边的图片
    private ImageView imgLeft;

    //中间的文字
    private TextView textCenter;

    //中间的图片
    private ImageView imgCenter;

    //右边的文字
    private TextView textRight;

    //右边的图片
    private ImageView imgRight;


    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 加载布局
        LayoutInflater.from(context).inflate(R.layout.title_view_toobar, this);

        // 获取控件
        linearLayout = (LinearLayout) findViewById(R.id.bar_all_layout);
        relativeLayout = (RelativeLayout) findViewById(R.id.title_all_layout);

        textLeft = (TextView) findViewById(R.id.title_left_text);
        textCenter = (TextView) findViewById(R.id.title_center_text);
        textRight = (TextView) findViewById(R.id.title_right_text);

        imgLeft = (ImageView) findViewById(R.id.title_left_img);
        imgCenter = (ImageView) findViewById(R.id.title_center_img);
        imgRight = (ImageView) findViewById(R.id.title_right_img);
    }

    /**
     * 设置标题栏包括状态栏的颜色
     *
     * @param color
     */
    @Deprecated
    public void setBarLayoutColor(int color) {
        if (linearLayout != null) {
            linearLayout.setBackgroundColor(color);
        }

    }


    public View getLeftView(){
        if(textLeft != null){
            return textLeft;
        }
        return textLeft;
    }

    /**
     * 设置标题栏的颜色
     *
     * @param color
     */
    public void setTitleLayoutColor(int color) {
        if (relativeLayout != null) {
            relativeLayout.setBackgroundColor(color);
        }
    }

    public void getBackImgVisible(int visible){
        if(imgLeft != null){
            imgLeft.setVisibility(visible);
        }
    }

    /**
     * 设置title的文字
     *
     * @param res
     */
    public void setLeftText(String res) {
        if (textLeft != null) {
            textLeft.setText(res);
        }
    }


    /**
     * 设置title的图标
     *
     * @param res
     */
    public void setLeftImg(int res) {
        if (imgLeft != null) {
            textLeft.setVisibility(View.INVISIBLE);
            imgLeft.setVisibility(View.VISIBLE);
            imgLeft.setBackgroundResource(res);
        }
    }

    /**
     * 设置title的图标
     *
     * @param
     */
    public void setGoneLeftView() {
        if (imgLeft != null) {
            textLeft.setVisibility(View.GONE);
            imgLeft.setVisibility(View.GONE);
        }
    }

    /**
     * 设置title中间的文字
     *
     * @param res
     */
    public void setCenterText(String res) {
        if (textCenter != null) {
            textCenter.setText(res);
        }
    }

    public void setCenterText(int res) {
        if (textCenter != null) {
            textCenter.setText(res);
        }
    }

    /**
     * 设置title右边的文字
     *
     * @param res
     */
    public void setRightText(int res) {
        if (textRight != null) {
            textRight.setVisibility(View.VISIBLE);
            textRight.setText(res);
        }
    }

    /**
     * 设置title右边的文字
     *
     * @param res
     */
    public void setRightText(String res) {
        if (textRight != null) {
            textRight.setVisibility(View.VISIBLE);
            textRight.setText(res);
        }
    }


    /**
     * 左边的监听
     *
     * @param onclickLiseter
     */
    public void setLeftTitleOnclickListener(OnClickListener onclickLiseter) {
        if (textLeft != null) {
            textLeft.setOnClickListener(onclickLiseter);
        }
    }

    /**
     * 左边的图标的监听
     *
     * @param onclickLiseter
     */
    public void setLeftTitleImgOnclickListener(OnClickListener onclickLiseter) {
        if (imgLeft != null) {
            imgLeft.setOnClickListener(onclickLiseter);
        }
    }


    /**
     * 中间的监听
     *
     * @param onclickListener
     */
    public void setCenterTitleOnclickListener(OnClickListener onclickListener) {
        if (textCenter != null) {
            textCenter.setOnClickListener(onclickListener);
        }
    }

    /**
     * 右边的监听
     *
     * @param onclickListener
     */
    public void setRightTitleOnclickListener(OnClickListener onclickListener) {
        if (textRight != null) {
            textRight.setOnClickListener(onclickListener);
        }
    }
}