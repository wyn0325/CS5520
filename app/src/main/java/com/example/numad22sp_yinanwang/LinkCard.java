package com.example.numad22sp_yinanwang;

import android.content.Intent;
import android.net.Uri;

public class LinkCard implements LinkClickListener {

    private final String linkName;
    private final String linkUrl;

    //Constructor
    public LinkCard(String linkName, String linkUrl) {
        this.linkName = linkName;
        this.linkUrl = linkUrl;
    }


    public String getLinkUrl() {
        return linkUrl;
    }

    public String getLinkName() {
        return linkName;
    }


    @Override
    public void onLinkClick(int position) {

    }


}