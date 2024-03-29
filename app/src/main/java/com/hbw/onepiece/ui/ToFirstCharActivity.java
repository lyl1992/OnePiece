package com.hbw.onepiece.ui;

import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hbw.onepiece.R;
import com.hbw.onepiece.entity.dbtable.Song;
import com.hbw.onepiece.entity.viewholder.ToFirstCharViewHolder;
import com.hebiwen.luffy.base.AdventurerBaseAdapter;
import com.hebiwen.luffy.base.BaseActivity;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToFirstCharActivity extends BaseActivity {

    TextInputEditText toTextInputEditText;
    TextView toTextView;
    RecyclerView toRecyclerView;
    Button toSave;

    HanyuPinyinOutputFormat format = null;
    AdventurerBaseAdapter<Song, ToFirstCharViewHolder> mAdapter;
    List<Song> songs = new ArrayList<>();

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_to_first_char;
    }

    @Override
    public void initView() {
        super.initView();
        toTextInputEditText = findViewById(R.id.toTextInputEditText);
        toTextView = findViewById(R.id.toTextView);
        toRecyclerView = findViewById(R.id.toRecyclerView);
        toSave = findViewById(R.id.toSave);
    }

    @Override
    public void initData() {
        super.initData();
        format = new HanyuPinyinOutputFormat();

        songs = LitePal.findAll(Song.class);
        // 倒叙排序
        Collections.reverse(songs);
        mAdapter = new AdventurerBaseAdapter<Song, ToFirstCharViewHolder>(this, songs) {
            @Override
            protected void setView(ToFirstCharViewHolder holder, int position) {
                final Song bean = songs.get(position);
                holder.item_song_name.setText(bean.getSongName());
                holder.item_song_name_pinyin.setText(bean.getSongNameToPinYin());

                holder.item_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LitePal.deleteAll(Song.class, "SongName = ?" , bean.getSongName());
                        updateListData();
                    }
                });
            }

            @Override
            protected int getViewId() {
                return R.layout.item_to_first_char;
            }

            @Override
            protected ToFirstCharViewHolder getViewHolder(View view) {
                return new ToFirstCharViewHolder(view);
            }
        };
        toRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        toRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initEvent() {
        super.initEvent();
        toTextInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                toTextView.setText(ToFirstChar(filter(s.toString())).toUpperCase());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        toSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(toTextInputEditText.getText().toString())) {
                    Toast.makeText(ToFirstCharActivity.this, "歌名不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                Song song = new Song();
                song.setSongName(filter(toTextInputEditText.getText().toString()));
                song.setSongNameToPinYin(ToFirstChar(filter(toTextInputEditText.getText().toString())).toUpperCase());
                // 查询此订单是否已存在
                List<Song> mSongsOlder = LitePal.where("SongName = ? ", song.getSongName()).find(Song.class);
                if(mSongsOlder.size() == 0){
                    // 保存数据到本地数据库
                    song.save();
                    updateListData();
                }else{
                    Toast.makeText(ToFirstCharActivity.this, "不能重复保存", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void updateListData(){
        // 清除数据
        songs.clear();
        // 查询全部
        List<Song> list = LitePal.findAll(Song.class);
        Collections.reverse(list);
        songs.addAll(list);
        toTextInputEditText.setText("");
        // 更新adapter
        mAdapter.notifyDataSetChanged();
    }

    // 获取汉字的首字母拼音（小写）
    public String ToFirstChar(String chinese) {
        if (chinese == null || chinese.trim().length() == 0) return "";
        String pinyinStr = "";
        char[] newChar = chinese.toCharArray();  //转为单个字符
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < newChar.length; i++) {
            if (newChar[i] > 128) {
                try {
                    pinyinStr += PinyinHelper.toHanyuPinyinStringArray(newChar[i], format)[0].charAt(0);
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyinStr += newChar[i];
            }
        }
        return pinyinStr;
    }

    //过滤字符串中的一些特殊符号
    private String filter(String str) {
        if (str.trim().isEmpty()) {
            return str;
        }
        String pattern = "[\u4E00-\u9FA5]|[\\w]";// 汉字
        Pattern emoji = Pattern.compile(pattern);
        Matcher emojiMatcher = emoji.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (emojiMatcher.find()) {
            sb.append(emojiMatcher.group());
        }
        return sb.toString();
    }
}
