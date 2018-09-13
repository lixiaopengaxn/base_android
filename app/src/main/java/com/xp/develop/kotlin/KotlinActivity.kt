package com.xp.develop.kotlin

import android.os.Bundle
import android.view.View
import android.widget.Button
import com.xp.develop.R
import com.xp.develop.base.BaseActivity
import com.xp.develop.base.BasePresenter
import com.xp.develop.base.BaseView
import com.xp.develop.utils.ToastUtil
import com.xp.develop.utils.log.LogUtils

/**
 *  author :  xpxn
 *  blog  :  https://blog.csdn.net/qq_38729449
 *  time  :  2018/9/12
 *  desc  :  utils about initialization
 */
class KotlinActivity : BaseActivity<BaseView, BasePresenter<BaseView>>() {

    override fun onClick(p0: View?) {

    }

    override fun getLayoutId(): Int {
        return R.layout.test_kotlin_layout;
    }

    override fun init(savedInstanceState: Bundle?) {
        //val 局部成量
        val a: Int = 8

        val b = 9

        findViewById<Button>(R.id.kotlin_button_1).setOnClickListener {
            System.out.print(getIntNub(a, b))

            LogUtils.trace("我是有返回值的ggetIntNub，我的结果是:" + getIntNub(a, b))
            LogUtils.trace("我是有返回值的，我的结果是getNUm:" + getNum(b, a))
            LogUtils.trace("我是有返回值的，getlist:" + getlist(arr))
        }
    }

    override fun initOnClick(){

    }

    override fun IsSwipeBackPage(): Boolean {

        return true
    }

    override fun isTemp(): Int {

        return 0
    }

    override fun createPresenter(): BasePresenter<BaseView> ? {

        return null
    }

    override fun createView(): BaseView ? {
        return null
    }

    //var 全局变量
    var c = 9

    var arr = arrayOf("1", "2", "3")

    //指定返回值
    fun getIntNub(a: Int, b: Int): Int {

        return a + b
    }

    //自动判断返回值
    fun getNum(a: Int, b: Int) = a + b


    //无返回值的集合
    fun getlist(list: Array<String>) {
        if (list.size == 0) return
        //${}获取指定index的数据
        ToastUtil.showLongToast("我是第五个${list[1]}")

    }

    //条件表达式
    fun getMax(a: Int, b: Int): Int {
        if (a > b) {
            return a
        } else {
            return b
        }
    }

    // 表达式的简写
    fun getMin(a: Int, b: Int) = if (a > b) a else b

    //设置函数返回类型可为 null
    fun isNull(a: Int, b: Int): Int? {
        print(a + b)
        return null
    }


    fun getStringLength(stringSize: Any): Int? {
        if (stringSize is String) {
            return stringSize.length
        }

        return null
    }

}