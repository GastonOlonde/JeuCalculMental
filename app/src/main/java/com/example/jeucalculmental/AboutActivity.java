package com.example.jeucalculmental;

import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import java.util.Random;

public class AboutActivity extends AppCompatActivity {

    private static final int NUM_IMAGES = 50;
    private static final int DELAY_MS = 350;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);
        Random random = new Random();


        Handler handler = new Handler(Looper.getMainLooper());


        for (int i = 0; i < NUM_IMAGES; i++) {
            final int index = i;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageView imageView = new ImageView(AboutActivity.this);
                    imageView.setImageResource(getRandomImageResource());
                    imageView.setId(View.generateViewId());

                    ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                            ConstraintLayout.LayoutParams.WRAP_CONTENT,
                            ConstraintLayout.LayoutParams.WRAP_CONTENT
                    );

                    layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
                    layoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
                    layoutParams.leftMargin = random.nextInt(2500);

                    int val = random.nextInt(40) + 35;
                    layoutParams.width = val;
                    layoutParams.height = val;
                    imageView.setLayoutParams(layoutParams);
                    constraintLayout.addView(imageView);

                    Animation fallAnimation = AnimationUtils.loadAnimation(AboutActivity.this, R.anim.fall_down);
                    Animation spinAnimation = AnimationUtils.loadAnimation(AboutActivity.this, R.anim.spin);

                    AnimationSet animationSet = new AnimationSet(true);

                    animationSet.addAnimation(spinAnimation);
                    animationSet.addAnimation(fallAnimation);

                    imageView.startAnimation(animationSet);
                }
            }, i * DELAY_MS);
        }
    }

    private int getRandomImageResource() {
        int[] imageResources = {R.drawable.ic_plus, R.drawable.ic_minus, R.drawable.ic_equal};
        Random random = new Random();
        return imageResources[random.nextInt(imageResources.length)];
    }
}
