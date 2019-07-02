package com.hbw.onepiece.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.widget.Toast;

public class ToastUtil {

    private Context mContext; // 上下文对象

    private ToastUtil() {
    } // 私有化构造

    private static final class Helper { // 内部帮助类，实现单例
        static final ToastUtil INSTANCE = new ToastUtil();
    }

    public static ToastUtil getInstance() { // 获取单例对象
        return Helper.INSTANCE;
    }

    public static void init(@NonNull Context context) { // 初始化Context
        Helper.INSTANCE.mContext = context;
    }

    public void show(@StringRes int strResID) { // 根据资源id弹Toast
        if (mContext == null) {
            throw new RuntimeException("Please init the Context before showToast");
        }
        show(mContext.getResources().getText(strResID));
    }

    public void show(CharSequence str) { // 根据字符串弹Toast
        if (mContext == null) {
            throw new RuntimeException("Please init the Context before showToast");
        }
        Toast.makeText(mContext, str, Toast.LENGTH_SHORT).show();
    }
}
