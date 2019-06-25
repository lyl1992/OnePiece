package com.hebiwen.luffy.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class AdventurerBaseAdapter<T, VH extends RecyclerView.ViewHolder> extends BaseAdapter<T, VH> {

    public AdventurerBaseAdapter(Context context, List<T> data) {
        super(context, data);
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(getViewId(), parent, false);
        return getViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        setView(holder, position);
    }

    protected abstract void setView(VH holder, int position);

    protected abstract int getViewId();

    protected abstract VH getViewHolder(View view);
}
