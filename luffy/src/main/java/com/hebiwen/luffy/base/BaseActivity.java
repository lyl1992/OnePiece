package com.hebiwen.luffy.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.orhanobut.logger.Logger;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.i("已进入 %s",this.getClass().getSimpleName());
        //设置为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(initPageLayoutId());
        initView();
        initData();
        initEvent();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    @Override
    public void finish() {
        super.finish();
    }

    public void initView() {
    }

    public void initData() {
    }

    public void initEvent() {
    }

    protected abstract int initPageLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
