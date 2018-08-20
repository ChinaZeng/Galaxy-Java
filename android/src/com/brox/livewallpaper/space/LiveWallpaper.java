package com.brox.livewallpaper.space;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.AndroidLiveWallpaperService;
import com.badlogic.gdx.backends.android.AndroidWallpaperListener;

public class LiveWallpaper extends AndroidLiveWallpaperService {

    @Override
    public void onCreateApplication() {
        super.onCreateApplication();

        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useAccelerometer = true;
        config.useWakelock = true;
        config.useCompass = false;
        config.disableAudio = true;
        config.getTouchEventsForLiveWallpaper = false;
        ApplicationListener listener = new LWSpaceListener();
        initialize(listener, config);
    }

    @Override
    public Engine onCreateEngine() {
        return new AndroidWallpaperEngine() {
            @Override
            public void onPause() {
                super.onPause();
            }

            @Override
            public void onDestroy() {
                super.onDestroy();
            }

            @Override
            public void onResume() {
                super.onResume();
            }
        };
    }

    public static class LWSpaceListener extends LWSpace implements AndroidWallpaperListener, ApplicationListener {

        @Override
        public void offsetChange(float xOffset, float yOffset,
                                 float xOffsetStep, float yOffsetStep, int xPixelOffset,
                                 int yPixelOffset) {
        }

        @Override
        public void previewStateChange(boolean isPreview) {
            if (isPreview) {
                //TODO: Ovde reklame + more from us + remove ads button
            }
        }

        @Override
        public void iconDropped(int x, int y) {

        }
    }
}