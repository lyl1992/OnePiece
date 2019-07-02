package com.hbw.onepiece.base;

import android.content.Context;

import com.didichuxing.doraemonkit.DoraemonKit;
import com.hbw.onepiece.utils.ToastUtil;
import com.hebiwen.luffy.base.BaseApplication;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import org.litepal.LitePal;

public class MyApplication extends BaseApplication {
    /**
     * 单例模式application
     */
    private static MyApplication application = null;

    // 上拉加载 下拉刷新
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(android.R.color.white, android.R.color.black);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        // 初始化数据库
        LitePal.initialize(application);
        // 初始化研发助手DoKit
        DoraemonKit.install(application);
        // 初始化log管理工具
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .tag("logger")
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
        // 初始化Toast
        ToastUtil.init(this);
    }

    // 单例模式中获取唯一的BaseApplication实例
    public static MyApplication getInstance() {
        return application;
    }
}
