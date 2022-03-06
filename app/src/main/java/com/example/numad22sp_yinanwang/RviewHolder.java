package com.example.numad22sp_yinanwang;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class RviewHolder extends RecyclerView.ViewHolder {
    public TextView itemName;
    public TextView itemDesc;

    public RviewHolder(View itemView, final LinkClickListener listener) {
        super(itemView);
        itemName = itemView.findViewById(R.id.link_name);
        itemDesc = itemView.findViewById(R.id.link_url);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int position = getLayoutPosition();
                    if (position != RecyclerView.NO_POSITION) {

                        listener.onLinkClick(position);
                    }
                }
            }
        });

    }
}
