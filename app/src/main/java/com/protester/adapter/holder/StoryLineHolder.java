package com.protester.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.protester.R;

public class StoryLineHolder extends RecyclerView.ViewHolder {
    public TextView headline;
    public ImageView backgroundImage;
    public RelativeLayout storyContainer;

    public StoryLineHolder(View itemView) {
        super(itemView);
        headline = (TextView)itemView.findViewById(R.id.headline);
        backgroundImage = (ImageView) itemView.findViewById(R.id.background_image);
        storyContainer = (RelativeLayout) itemView.findViewById(R.id.story_container);
    }
}