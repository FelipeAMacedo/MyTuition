package com.example.felipemacedo.mytuition;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * Created by felipemacedo on 21/10/17.
 */

public class Seta extends View {
    Context mContext;
    private int centerX;
    private int centerY;
    private int radius;
    private double arrLength;
    private double arrHeading;
    private int margin = 10;
    private float deX, deY, paraX, paraY;
    private Canvas canvas = new Canvas();

    public Seta(Context context, float deX, float deY, float paraX, float paraY) {
        super(context);
        this.deX = deX;
        this.deY = deY;
        this.paraX = paraX;
        this.paraY = paraY;
        setBackgroundColor(Color.YELLOW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.canvas = canvas;

        //Set vars for Arrow Paint
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);

        Path path = new Path();
        path.moveTo(deX, deY);
        path.lineTo(paraX, paraY);
        path.close();
        this.canvas.drawPath(path, paint);



//        this.getLayoutParams().height = ;
        this.getLayoutParams().width = 400;

        this.setLayoutParams(this.getLayoutParams());

    }

    public Canvas getCanvas() {
        return this.canvas;
    }
}
