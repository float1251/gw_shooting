package jp.float1251.gwshooting;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import jp.float1251.gwshooting.screen.InGameScreen;

public class GWShooting extends Game {
    public SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new InGameScreen(this));
    }

    public SpriteBatch getSpriteBatch() {
        return batch;
    }
}
