package com.hbw.onepiece.entity.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hbw.onepiece.R;

public class ToFirstCharViewHolder extends RecyclerView.ViewHolder {

    public TextView item_song_name, item_song_name_pinyin;
    public Button item_delete;

    public ToFirstCharViewHolder(@NonNull View itemView) {
        super(itemView);
        item_song_name = itemView.findViewById(R.id.item_song_name);
        item_song_name_pinyin = itemView.findViewById(R.id.item_song_name_pinyin);
        item_delete = itemView.findViewById(R.id.item_delete);
    }
}
