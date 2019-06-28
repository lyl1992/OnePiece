package com.hbw.onepiece.ui;

import android.content.Intent;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

import com.hbw.onepiece.R;
import com.hbw.onepiece.entity.bean.HuMan;
import com.hebiwen.luffy.base.BaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    AppCompatTextView module_1, module_2, module_3, module_4, module_5;

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        super.initView();
        module_1 = findViewById(R.id.module_1);
        module_2 = findViewById(R.id.module_2);
        module_3 = findViewById(R.id.module_3);
        module_4 = findViewById(R.id.module_4);
        module_5 = findViewById(R.id.module_5);
    }

    @Override
    public void initEvent() {
        super.initEvent();
        module_1.setOnClickListener(this);
        module_2.setOnClickListener(this);
        module_3.setOnClickListener(this);
        module_4.setOnClickListener(this);
        module_5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.module_1:
                Intent it = new Intent(this, NiceDialogActivity.class);
                startActivity(it);
                break;
            case R.id.module_2:
                Intent it2 = new Intent(this, SmartRefreshActivity.class);
                startActivity(it2);
                break;
            case R.id.module_3:
                Intent it3 = new Intent(this, ToFirstCharActivity.class);
                startActivity(it3);
                break;
            case R.id.module_4:
                Intent it4 = new Intent(this, FastJsonActivity.class);
                startActivity(it4);
                break;
            case R.id.module_5:
                Intent it5 = new Intent(this, EventBusActivity.class);
                startActivity(it5);
                break;
            default:
                break;
        }
    }

}
