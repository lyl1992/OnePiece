package com.hbw.onepiece.ui;

import android.support.v7.widget.AppCompatTextView;

import com.alibaba.fastjson.JSON;
import com.hbw.onepiece.R;
import com.hbw.onepiece.entity.dbtable.Song;
import com.hebiwen.luffy.base.BaseActivity;

public class FastJsonActivity extends BaseActivity {
    AppCompatTextView fj_tv;

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_fast_json;
    }

    @Override
    public void initView() {
        super.initView();
        fj_tv = findViewById(R.id.fj_tv);
    }

    @Override
    public void initData() {
        super.initData();
        Song song = new Song("表白", "BB");
        String text = JSON.toJSONString(song); //序列化
        Song vo = JSON.parseObject(text, Song.class); //反序列化

        fj_tv.setText(text + "\n");
    }
}
