package com.hebiwen.luffy.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.hebiwen.luffy.event.OnItemClickListener;
import com.hebiwen.luffy.event.OnLongClickListener;

import java.util.List;

public abstract class BaseAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected Context mContext;
    protected List<T> mData;
    protected OnItemClickListener mListener;
    protected OnLongClickListener mLongClickListener;

    public BaseAdapter(Context context, List<T> data) {
        mContext = context;
        mData = data;
    }

    public void setListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public void setLongClickListener(OnLongClickListener longClickListener) {
        mLongClickListener = longClickListener;
    }

    public void setData(List<T> data) {
        mData = data;
    }

    public void addData(List<T> data) {
        mData.addAll(data);
    }

    public void clearData() {
        mData.clear();
    }

    public void addOneData(T data) {
        mData.add(0, data);
    }

    public void delOneData(int index) {
        mData.remove(index);
    }

    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
}
