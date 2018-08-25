package com.xp.develop.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

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
 * *******\  \::/ /:/    \  \:\/:/    \  \::/       \  \:\  ~~~   \  \::/~~~~      /  /:/
 * ********\  \:\/:/      \  \::/      \  \:\        \  \:\        \  \:\         /__/:/
 * *********\  \::/        \__\/        \  \:\        \  \:\        \  \:\        \__\/
 * **********\__\/                       \__\/         \__\/         \__\/
 * blog  :  https://blog.csdn.net/qq_38729449
 * time  :  2018/8/17
 * desc  :  utils about initialization
 */
public class NumbAddView extends LinearLayout {
    private TextView add, content, reduce;
    public Context context;

    public NumbAddView(Context context) {
        this(context, null);
        this.context = context;

    }

    public NumbAddView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 加载布局
        LayoutInflater.from(context).inflate(R.layout.nubmer_add_layout, this);
        add = findViewById(R.id.numb_add);
        content = findViewById(R.id.nub_content);
        reduce = findViewById(R.id.numb_reduce);
        if (content.getText().toString().equals("1")) {
            reduce.setBackgroundResource(R.drawable.numb_left_gray);
            reduce.setClickable(false);
        }
        initView();
    }

    private void initView() {
        /***
         * 点击加号
         */
        add.setOnClickListener(v -> {
            if (content.getText().toString().equals("1")) {
                reduce.setBackgroundResource(R.drawable.numb_left);
                reduce.setClickable(true);
                content.setText(Integer.parseInt(content.getText().toString()) + 1 + "");
            } else if (content.getText().toString().equals("8")) {
                content.setText(Integer.parseInt(content.getText().toString()) + 1 + "");
                add.setBackgroundResource(R.drawable.numb_right_gray);
                add.setClickable(false);
            } else {
                content.setText(Integer.parseInt(content.getText().toString()) + 1 + "");
            }
        });

        /***
         * 点击减号
         */
        reduce.setOnClickListener(v -> {
            if (content.getText().toString().equals("2")) {
                reduce.setBackgroundResource(R.drawable.numb_left_gray);
                reduce.setClickable(false);
                content.setText(Integer.parseInt(content.getText().toString()) - 1 + "");
            } else {
                int nub = Integer.parseInt(content.getText().toString()) - 1;
                add.setClickable(true);
                add.setBackgroundResource(R.drawable.numb_right);
                if (nub > 0) {
                    content.setText(nub + "");
                }
            }
        });

    }

    /***
     * 获取房间数量
     * @return
     */
    public String getContentText() {
        return content.getText().toString();
    }


    /***
     * 设置房间数量，不能小于1
     * @return
     */
    public void setContentText(String numb) {
        content.setText(numb);
    }
}
