package com.limeplaces.app.screens;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;
import com.limeplaces.app.R;
import com.limeplaces.app.ui.Ui;

public class MapCanvasView extends View {
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public MapCanvasView(Context context) {
        super(context);
        setBackgroundColor(Ui.color(context, R.color.cream));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Ui.color(getContext(), R.color.mint));
        canvas.drawOval(-100, 160, 260, 520, paint);
        paint.setColor(0xFFCEE6E3);
        canvas.drawOval(450, -100, 850, 750, paint);

        paint.setColor(0xFFFFFFFF);
        paint.setStrokeWidth(Ui.dp(getContext(), 15));
        paint.setStyle(Paint.Style.STROKE);
        Path road = new Path();
        road.moveTo(-40, 320);
        road.cubicTo(220, 240, 410, 390, 760, 220);
        canvas.drawPath(road, paint);
        canvas.drawLine(300, -50, 390, 950, paint);

        paint.setStyle(Paint.Style.FILL);
        drawPin(canvas, getWidth() * .25f, getHeight() * .30f, false);
        drawPin(canvas, getWidth() * .72f, getHeight() * .39f, true);
        drawPin(canvas, getWidth() * .43f, getHeight() * .57f, false);
    }

    private void drawPin(Canvas canvas, float x, float y, boolean selected) {
        paint.setColor(Ui.color(getContext(), selected ? R.color.violet : R.color.surface));
        canvas.drawCircle(x, y, Ui.dp(getContext(), selected ? 24 : 19), paint);
        paint.setColor(Ui.color(getContext(), selected ? R.color.surface : R.color.violet));
        canvas.drawCircle(x, y, Ui.dp(getContext(), 6), paint);
    }
}
