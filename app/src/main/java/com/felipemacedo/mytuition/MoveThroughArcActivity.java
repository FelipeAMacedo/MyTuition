package com.felipemacedo.mytuition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MoveThroughArcActivity extends AppCompatActivity {

    private TextView txtValue;
    private AnimatorSet animSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_through_arc);

        initComponents();
        initListeners();
    }

    private void initComponents() {
        txtValue = (TextView) findViewById(R.id.txtPilhaValue);

        animSet = new AnimatorSet();



        Path path1 = new Path();
        RectF oval1 = new RectF(-250, 0, 250, 800);
        path1.addArc(oval1, 270, 90);

        ObjectAnimator va = ObjectAnimator.ofFloat(txtValue, "x", "y", path1);
        va.setDuration(2500l);


        Path path2 = new Path();
//        RectF oval2 = new RectF(250, 800, 750, 0);
        RectF oval2 = new RectF(250, 0, 750, 800);
        path2.addArc(oval2, 180, 90);

        ObjectAnimator va2 = ObjectAnimator.ofFloat(txtValue, "x", "y", path2);
        va2.setStartDelay(5000l);
        va2.setDuration(2500l);

//        animSet.play(va2);
        animSet.play(va).before(va2);
        animSet.start();
    }

    private void initListeners() {
        animSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                animSet.start();
            }


            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }
}
