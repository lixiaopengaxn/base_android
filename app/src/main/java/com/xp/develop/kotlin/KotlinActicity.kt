package com.xp.develop.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.xp.develop.R
import com.xp.develop.utils.ToastUtil
import com.xp.develop.utils.log.LogUtils

/**
 *  author :  xpxn
 *  blog  :  https://blog.csdn.net/qq_38729449
 *  time  :  2018/9/12
 *  desc  :  utils about initialization
 */
class KotlinActicity : AppCompatActivity() {

    //var 全局变量
    var c = 9

    var arr = arrayOf("1", "2", "3")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_kotlin_layout)

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
    fun getMax(a : Int , b : Int) : Int{
        if (a > b){
            return a
        } else{
            return b
        }
    }

    // 表达式的简写
    fun getMin(a: Int,b: Int) = if(a > b) a else b

    //设置函数返回类型可为 null
    fun isNull ( a: Int , b: Int) : Int?{
        print(a+b)
        return null
    }



    fun getStringLength(stringSize : Any) : Int ?{
        if(stringSize is String){
            return stringSize.length
        }

        return null
    }

}