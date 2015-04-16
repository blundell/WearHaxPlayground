package com.blundell.droidcondubai;

import android.support.wearable.watchface.CanvasWatchFaceService;
import android.view.SurfaceHolder;

public class WatchFace extends CanvasWatchFaceService {

    @Override
    public Engine onCreateEngine() {
        return new Engine();
    }

    private class Engine extends CanvasWatchFaceService.Engine {

        @Override
        public void onCreate(SurfaceHolder holder) {
            super.onCreate(holder);
        }
    }
}
