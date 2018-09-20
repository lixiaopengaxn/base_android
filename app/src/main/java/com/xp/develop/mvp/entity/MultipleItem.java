package com.xp.develop.mvp.entity;


import com.xp.develop.utils.recycler.entity.MultiItemEntity;

/**
 * author :  xpxn
 * blog  :  https://blog.csdn.net/qq_38729449
 * time  :  2018/9/11
 * desc  :  utils about initialization
 */
public class MultipleItem  implements MultiItemEntity {
    public static final int TEXT = 1;
    public static final int IMG = 2;
    private int itemType;
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public MultipleItem(int itemType) {
        this.itemType = itemType;
    }

    public MultipleItem(int itemType,String t) {
        this.itemType = itemType;
        this.data = t;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
