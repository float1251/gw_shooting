package jp.float1251.gwshooting.input;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;

import jp.float1251.gwshooting.util.ComponentUtils;

/**
 * Created by takahiroiwatani on 2015/05/04.
 */
public class GameInputProcessor implements InputProcessor {
    private final Entity player;
    private final FitViewport viewport;
    public Vector2 startPosition;

    public GameInputProcessor(Entity player, FitViewport viewport) {
        this.player = player;
        this.viewport = viewport;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        startPosition = ComponentUtils.getPositionComponent(player).getPosition();
        Gdx.app.log("Screen", String.format("%d: %d", screenX, screenY));
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector2 pos = viewport.unproject(new Vector2(screenX, screenY));
        ComponentUtils.getPositionComponent(player).setPosition(pos.x, pos.y);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
