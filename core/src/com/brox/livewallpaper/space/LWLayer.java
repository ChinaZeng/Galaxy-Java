package com.brox.livewallpaper.space;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class LWLayer extends Sprite {
    float offset = 0;
    public LWLayer(Texture texture, float offset, LWSpaceScreen lwSpaceScreen) {
        super(texture);
        this.offset = offset;
        lwSpaceScreen.pool.add(this);
    }
}
