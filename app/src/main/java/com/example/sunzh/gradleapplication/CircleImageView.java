package com.example.sunzh.gradleapplication;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by sunzh on 2017/3/28.
 */

public class CircleImageView extends ImageView {
    public CircleImageView(Context context) {
        this(context, null);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }
}
