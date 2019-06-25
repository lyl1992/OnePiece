package com.hebiwen.luffy.event;

import android.view.View;

public interface OnItemClickListener {

    void onItemClick(View view, int position);

    /**
     * 删除按钮回调
     */
    void onDeleteClick(int position);
}
