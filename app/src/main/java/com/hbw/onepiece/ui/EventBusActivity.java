package com.hbw.onepiece.ui;

import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.hbw.onepiece.R;
import com.hbw.onepiece.entity.bean.HuMan;
import com.hbw.onepiece.utils.ToastUtil;
import com.hebiwen.luffy.base.BaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusActivity extends BaseActivity {
    AppCompatButton eb_btn;

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_event_bus;
    }

    @Override
    public void initView() {
        super.initView();
        eb_btn = findViewById(R.id.eb_btn);
    }

    @Override
    public void initEvent() {
        super.initEvent();
        eb_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new HuMan("张三", "男", 27));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(HuMan huMan) {
        ToastUtil.getInstance().show(huMan.toString());
    }

}
