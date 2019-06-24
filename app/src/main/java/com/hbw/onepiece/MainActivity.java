package com.hbw.onepiece;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.hebiwen.luffy.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    AppCompatTextView module_1;

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        super.initView();
        module_1 = findViewById(R.id.module_1);
    }

    @Override
    public void initEvent() {
        super.initEvent();
        module_1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.module_1:
                Intent it = new Intent(this, NiceDialogActivity.class);
                startActivity(it);
                break;
            default:
                break;
        }
    }
}
