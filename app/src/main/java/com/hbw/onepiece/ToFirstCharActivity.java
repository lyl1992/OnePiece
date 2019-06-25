package com.hbw.onepiece;

import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import com.hebiwen.luffy.BaseActivity;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToFirstCharActivity extends BaseActivity {

    TextInputEditText toTextInputEditText;
    TextView toTextView;
    HanyuPinyinOutputFormat format = null;

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_to_first_char;
    }

    @Override
    public void initView() {
        super.initView();
        toTextInputEditText = findViewById(R.id.toTextInputEditText);
        toTextView = findViewById(R.id.toTextView);
    }

    @Override
    public void initData() {
        super.initData();
        format = new HanyuPinyinOutputFormat();
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
