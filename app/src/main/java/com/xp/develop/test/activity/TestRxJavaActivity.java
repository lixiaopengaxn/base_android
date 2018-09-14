package com.xp.develop.test.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.xp.develop.R;
import com.xp.develop.base.BaseActivity;
import com.xp.develop.base.BasePresenter;
import com.xp.develop.base.BaseView;

import java.util.ArrayList;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * https://blog.csdn.net/csdn_lqr/article/details/59056749
 * <p>
 * 1.Observable和Subscriber可以做任何事情
 * Observable可以是一个数据库查询，Subscriber用来显示查询结果；
 * Observable可以是屏幕上的点击事件，Subscriber用来响应点击事件；
 * Observable可以是一个网络请求，Subscriber用来显示请求结果。
 * <p>
 * 2.Observable和Subscriber是独立于中间的变换过程的。
 * 在Observable和Subscriber中间可以增减任何数量的map。整个系统是高度可组合的，操作数据是一个很简单的过程。
 */
public class TestRxJavaActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.test_rx_java_activity;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void initOnClick() {

    }

    @Override
    protected boolean IsSwipeBackPage() {
        return true;
    }

    @Override
    protected int isTemp() {
        return 0;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected BaseView createView() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         myRXTest();
    }

    private void myRXTest() {
        //TODO 简单的文案输出
        Observable.just("qwe")
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        //打印
                    }
                });

        // TODO 处理数据中间转换的问题map
        Observable.just("123")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + "qwe";
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        //打印
                    }
                });

        // TODO 处理集合数据
        // flatMap合并的数据可能出现交错的情况，导致数据输出顺序不一致。
        // concatMap正好跟flatMap相反。
        // switchMap在发射数据的时候，只会发射最新的数据，放弃旧的数据
        ArrayList<String> list = new ArrayList<>();
        list.add("123");
        list.add("qwe");
        Observable.just(list)
                .flatMap(new Func1<ArrayList<String>, Observable<String>>() {
                    @Override
                    public Observable<String> call(ArrayList<String> list) {
                        return Observable.from(list);   //相当于遍历  挨个返回字符串
                    }
                })
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        ArrayList<String> list2 = new ArrayList<>();
                        if (s.endsWith("3")) {
                            list2.add(s);
                        }
                        return Observable.from(list2);  //这里过滤了末尾为3的数据
                    }
                })
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return s != null;//挨个做判断是否为空
                    }
                })
                .take(1)//控制输出的结果
                .doOnNext(s -> {
                    //打印之前可以做的事情
                })
                .subscribe(o -> {   //打印
                });


        //TODO 线程的切换
        Observable.just("123")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {  //打印
                });


        //TODO  方法包装成Observer对象（绝大多数时候Observable.just() 和 Observable.from() 能够帮助你从遗留代码中创建 Observable 对象）
        newMethod();


    }
    //   –使用defer()来包装缓慢的代码  可提高转化效率
        private Observable<Object> newMethod(){
            return Observable.defer(new Func0<Observable<Object>>() {
                @Override
                public Observable<Object> call() {
                    return Observable.just(oldMethod());
                }
            });
        }
        private Object  oldMethod(){
            return "";
        }

    @Override
    public void onClick(View v) {

    }
}
