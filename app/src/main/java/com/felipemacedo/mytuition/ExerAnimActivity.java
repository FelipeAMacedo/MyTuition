package com.example.felipemacedo.mytuition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ExerAnimActivity extends AppCompatActivity {

    private LinearLayout llContent;
    private RelativeLayout rlContent;
    private Button btnInserir;
    private EditText etValor;
    private ArrayList<View> valueViews;
    private ArrayList<View> arrowViews;
    private int selectorAlpha = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exer_anim);

        initComponents();
        initListeners();
    }

    /**
     * Inicia os componentes visuais da tela.
     */
    private void initComponents() {
        llContent = (LinearLayout) findViewById(R.id.linearExerAnimContent);
        rlContent = (RelativeLayout) findViewById(R.id.relativeExerAnimContent);
        etValor = (EditText) findViewById(R.id.etExerAnimInValor);
        btnInserir = (Button) findViewById(R.id.btnExerAnimInsert);

        valueViews = new ArrayList<>();
        arrowViews = new ArrayList<>();
    }

    /**
     * Configura os listeners dos componentes da tela.
     */
    private void initListeners() {
        btnInserir.setOnClickListener((view) -> {
            String valor = etValor.getText().toString();

            LayoutInflater inflater = (LayoutInflater) ExerAnimActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.item_exer_anim, rlContent, false);

            TextView content = v.findViewById(R.id.txtItemExerValor);
            content.setText(valor);

            btnInserir.setEnabled(false);

//                llContent.addView(v);

            v.setX(500f);
            rlContent.addView(v);

            if (valueViews.size() > 0) {
                View viewAnterior = valueViews.get(valueViews.size() - 1);
                float deX = v.getX();
                float deY = v.getY();
                float paraX = viewAnterior.getX();
                float paraY = viewAnterior.getY();

//                    Seta seta = new Seta(ExerAnimActivity.this, deX, deY, paraX, paraY);

                View seta = inflater.inflate(R.layout.seta_layout, rlContent, false);
                rlContent.addView(seta);

                arrowViews.add(seta);
            }

            valueViews.add(v);

            moveAdd();
        });
    }

    private void moveAdd() {
        for (int x = 0; x < valueViews.size(); x++) {
            View v1 = valueViews.get(x);

            if (x != valueViews.size() - 1) {
                ObjectAnimator animation = ObjectAnimator.ofFloat(v1, "translationX", v1.getX() + 300f);

                animation.setDuration(1000);
                animation.start();

            } else {
                PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("x", 300f);
                PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("y", 200f);
                ObjectAnimator a = ObjectAnimator.ofPropertyValuesHolder(v1, pvhX, pvhY);
                a.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        btnInserir.setEnabled(true);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
                a.setDuration(1000).start();

            }
        }

        for (int x = 0; x < arrowViews.size(); x++) {
            View arrow = arrowViews.get(x);

            if (x == arrowViews.size() - 1) {
//                Toast.makeText(this, "Y before: " + arrow.getY(), Toast.LENGTH_SHORT).show();
                ObjectAnimator rotation = ObjectAnimator.ofFloat(arrow, "rotation", 90f);
                rotation.setDuration(3000);
                rotation.start();

                ObjectAnimator translation = ObjectAnimator.ofFloat(arrow, "translationX", 30f);
                translation.setDuration(3000);
                translation.start();


            } else {
//                ObjectAnimator animation = ObjectAnimator.ofFloat(arrow, "translationX", arrow.getX() + 300f);
//
//                animation.setDuration(1000);
//                animation.start();
            }
        }

        /*
         *
         * FadeOut effect without remove view from list
         *
         * */
//        if (valueViews.size() >= 4) {
//            ObjectAnimator animation = ObjectAnimator.ofFloat(valueViews.get(selectorAlpha), "alpha", 1f, 0f);
//            animation.setDuration(1000);
//            animation.start();
//            selectorAlpha++;
//        }
    }
}
