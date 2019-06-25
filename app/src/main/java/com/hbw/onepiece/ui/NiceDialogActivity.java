package com.hbw.onepiece.ui;

import android.support.v7.widget.AppCompatButton;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.hbw.onepiece.R;
import com.hebiwen.luffy.BaseActivity;
import com.shehuan.nicedialog.BaseNiceDialog;
import com.shehuan.nicedialog.NiceDialog;
import com.shehuan.nicedialog.ViewConvertListener;
import com.shehuan.nicedialog.ViewHolder;

/**
 * https://github.com/SheHuan/NiceDialog
 */
public class NiceDialogActivity extends BaseActivity implements View.OnClickListener {
    AppCompatButton dialg_1, dialg_2;

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_nice_dialog;
    }

    @Override
    public void initView() {
        super.initView();
        dialg_1 = findViewById(R.id.dialg_1);
        dialg_2 = findViewById(R.id.dialg_2);
    }

    @Override
    public void initEvent() {
        super.initEvent();
        dialg_1.setOnClickListener(this);
        dialg_2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialg_1:
                showDialog1();
                break;
            case R.id.dialg_2:
                showDialog2();
                break;
            default:
                break;
        }
    }

    private void showDialog1() {
        NiceDialog.init()
                .setLayoutId(R.layout.dialog_1)     //设置dialog布局文件
                .setConvertListener(new ViewConvertListener() {     //进行相关View操作的回调
                    @Override
                    public void convertView(ViewHolder holder, final BaseNiceDialog dialog) {
                        TextView dialog_cancel = holder.getView(R.id.dialog_cancel);
                        dialog_cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        TextView dialog_confirm = holder.getView(R.id.dialog_confirm);
                        dialog_confirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                    }
                })
                .setDimAmount(0.3f)     //调节灰色背景透明度[0-1]，默认0.5f
                .setGravity(Gravity.CENTER)     //可选，设置dialog的位置，默认居中，可通过系统Gravity的类的常量修改，例如Gravity.BOTTOM（底部），Gravity.Right（右边），Gravity.BOTTOM|Gravity.Right（右下）
                .setMargin(30)     //dialog左右两边到屏幕边缘的距离（单位：dp），默认0dp
                //.setWidth(50)     //dialog宽度（单位：dp），默认为屏幕宽度，-1代表WRAP_CONTENT
                //.setHeight(50)     //dialog高度（单位：dp），默认为WRAP_CONTENT
                .setOutCancel(false)     //点击dialog外是否可取消，默认true
                //.setAnimStyle(R.style.EnterExitAnimation)     //设置dialog进入、退出的自定义动画；根据设置的Gravity，默认提供了左、上、右、下位置进入退出的动画
                .show(getSupportFragmentManager());     //显示dialog
    }

    private void showDialog2() {
        NiceDialog.init()
                .setLayoutId(R.layout.dialog_2)
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseNiceDialog dialog) {

                    }
                })
                .setGravity(Gravity.BOTTOM)
                .show(getSupportFragmentManager());
    }
}
