package com.example.numad22sp_yinanwang;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RviewAdapter extends RecyclerView.Adapter<RviewHolder> {

    private final ArrayList<LinkCard> itemList;
    private LinkClickListener listener;

    //Constructor
    public RviewAdapter(ArrayList<LinkCard> itemList) {
        this.itemList = itemList;
    }

    public void setOnItemClickListener(LinkClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new RviewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(RviewHolder holder, int position) {
        LinkCard currentItem = itemList.get(position);

        holder.itemName.setText(currentItem.getLinkName());
        holder.itemDesc.setText(currentItem.getLinkUrl());

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}

