package com.blundell.droidcondubai;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.wearable.watchface.CanvasWatchFaceService;
import android.text.format.Time;
import android.view.SurfaceHolder;

public class WatchFace extends CanvasWatchFaceService {

    @Override
    public Engine onCreateEngine() {
        return new Engine();
    }

    private class Engine extends CanvasWatchFaceService.Engine {

        private Time time;
        private Paint backgroundPaint;
        private Paint textPaint;

        @Override
        public void onCreate(SurfaceHolder holder) {
            time = new Time();

            backgroundPaint = new Paint();
            backgroundPaint.setColor(Color.WHITE);
            backgroundPaint.setAntiAlias(true);

            textPaint = new Paint();
            textPaint.setColor(Color.BLUE);
            textPaint.setAntiAlias(true);
            textPaint.setTextSize(20F);
        }

        @Override
        public void onDraw(Canvas canvas, Rect bounds) {
            time.setToNow();
            String timeFormatted = time.hour + ":" + time.minute;
            canvas.drawCircle(bounds.centerX(), bounds.centerY(), 100, backgroundPaint);
            canvas.drawText(timeFormatted, bounds.centerX() - 20, bounds.centerY(), textPaint);
        }
    }
}
