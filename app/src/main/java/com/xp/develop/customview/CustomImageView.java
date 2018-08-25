package com.xp.develop.customview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.xp.develop.R;

/**
 * author :
 * ---------------------------------------___           ___           ___         ___
 * ----------_____                       /  /\         /__/\         /__/|       /  /\
 * ---------/  /::\                     /  /::\        \  \:\       |  |:|      /  /:/
 * --------/  /:/\:\    ___     ___    /  /:/\:\        \  \:\      |  |:|     /__/::\
 * -------/  /:/~/::\  /__/\   /  /\  /  /:/~/::\   _____\__\:\   __|  |:|     \__\/\:\
 * ------/__/:/ /:/\:| \  \:\ /  /:/ /__/:/ /:/\:\ /__/::::::::\ /__/\_|:|____    \  \:\
 * ******\  \:\/:/~/:/  \  \:\  /:/  \  \:\/:/__\/ \  \:\~~\~~\/ \  \:\/:::::/     \__\:\
 * *******\  \::/ /:/    \  \:\/:/    \  \::/       \  \:\  ~~~  \YUIOP[]\XZ\  \::/~~~~      /  /:/
 * ********\  \:\/:/      \  \::/      \  \:\        \  \:\        \  \:\         /__/:/
 * *********\  \::/        \__\/        \  \:\        \  \:\        \  \:\        \__\/
 * **********\__\/                       \__\/         \__\/         \__\/
 * blog  :  https://blog.csdn.net/qq_38729449
 * time  :  2018/8/20
 * desc  :  utils about initialization
 */
public class CustomImageView extends android.support.v7.widget.AppCompatImageView {
    private Context context;
    public CustomImageView(Context context) {
        this(context,null);
        this.context = context;
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
    }

    /***
     * 默认加载url的glide
     * @param url
     */
    public void setImageViewUrl(String url){
        //站位图
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.NONE);
        Glide.with(getContext()).asBitmap().load(url).apply(options).into(this);
    }

    public void setGifImageViewUrl(String url){
        Glide.with(getContext()).asGif().load(url).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE)).into(this);



    }

    /****
     * 设定图片的大小
     * @param url
     * @param width
     * @param height
     */
    public void setImageViewSizeUrl(String url, int width, int height){
        //占位图
        RequestOptions options = new RequestOptions()
                .override(width,height)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.NONE);
        Glide.with(getContext()).load(url).apply(options).into(this);
    }
}
