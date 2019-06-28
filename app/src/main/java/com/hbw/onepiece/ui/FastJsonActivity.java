package com.hbw.onepiece.ui;

import android.support.v7.widget.AppCompatTextView;

import com.alibaba.fastjson.JSON;
import com.hbw.onepiece.R;
import com.hbw.onepiece.entity.bean.HuMan;
import com.hebiwen.luffy.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class FastJsonActivity extends BaseActivity {
    AppCompatTextView fj_tv,fj_tv1,fj_tv2,fj_tv3;

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_fast_json;
    }

    @Override
    public void initView() {
        super.initView();
        fj_tv = findViewById(R.id.fj_tv);
        fj_tv1 = findViewById(R.id.fj_tv1);
        fj_tv2 = findViewById(R.id.fj_tv2);
        fj_tv3 = findViewById(R.id.fj_tv3);
    }

    @Override
    public void initData() {
        super.initData();
        HuMan huMan = new HuMan("张三", "男",21);
        HuMan huMan1 = new HuMan("李四", "男",23);
        HuMan huMan2 = new HuMan("王五", "男",27);
        ArrayList<HuMan> list = new ArrayList<>();
        list.add(huMan);
        list.add(huMan1);
        list.add(huMan2);

        // 单个对象
        String jsonStr = JSON.toJSONString(huMan); //序列化
        HuMan mHuMan = JSON.parseObject(jsonStr, HuMan.class); //反序列化

        // 集合
        String jsonList = JSON.toJSONString(list); //序列化
        List<HuMan> jsonList2 = JSON.parseArray(jsonList,HuMan.class); //反序列化

        fj_tv.setText(jsonStr);
        fj_tv1.setText(mHuMan.toString());
        fj_tv2.setText(jsonList);
        fj_tv3.setText(jsonList2.toString());
    }
}
