package jp.float1251.gwshooting.screen;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.Iterator;

import jp.float1251.gwshooting.GWShooting;
import jp.float1251.gwshooting.component.CircleCollisionComponent;
import jp.float1251.gwshooting.component.MoveTypeComponent;
import jp.float1251.gwshooting.component.PositionComponent;
import jp.float1251.gwshooting.system.DebugCollisionRenderingSystem;
import jp.float1251.gwshooting.system.MovementSystem;
import jp.float1251.gwshooting.type.MovingType;

/**
 * Created by takahiroiwatani on 2015/04/30.
 */
public class InGameScreen implements Screen {

    private final GWShooting game;
    private final SpriteBatch batch;
    private Engine engine;
    private FitViewport viewport;
    private Entity player;

    public InGameScreen(GWShooting game) {
        this.game = game;
        this.batch = game.getSpriteBatch();
        this.viewport = new FitViewport(640, 960);

        initialize();
    }

    private void initialize() {
        engine = new Engine();
        // add System
        engine.addSystem(new MovementSystem());
        engine.addSystem(new DebugCollisionRenderingSystem((com.badlogic.gdx.graphics.OrthographicCamera) viewport.getCamera()));

        // add Component
        player = new Entity();
        player.add(new PositionComponent(100, 100));
        player.add(new CircleCollisionComponent(10));
        engine.addEntity(player);

        // tmxを読み込んでobjectから敵を出現させる
        TiledMap map = new TmxMapLoader().load("stage/stage.tmx");
        MapLayer layer = map.getLayers().get("Enemy");
        Iterator<MapObject> iter = layer.getObjects().iterator();
        while (iter.hasNext()) {
            MapObject obj = iter.next();
            Gdx.app.log("ENEMY", String.format("x: %f, y: %f", obj.getProperties().get("x"), obj.getProperties().get("y")));
            Entity enemy = new Entity();
            enemy.add(new PositionComponent((Float) obj.getProperties().get("x"), (Float) obj.getProperties().get("y")));
            enemy.add(new CircleCollisionComponent(10));
            enemy.add(new MoveTypeComponent(MovingType.TARGET, player.getComponent(PositionComponent.class).getPosition()));
            engine.addEntity(enemy);
        }
    }

    @Override
    public void show() {
        handleInput();
    }

    @Override
    public void render(float delta) {
        viewport.getCamera().update();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        engine.update(delta);

        batch.begin();
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
    }

    private void handleInput() {
        Gdx.input.setInputProcessor(new InputProcessor() {
            public Vector2 startPosition;

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
                startPosition = player.getComponent(PositionComponent.class).getPosition();
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                Vector2 pos = viewport.unproject(new Vector2(screenX, screenY));
                player.getComponent(PositionComponent.class).setPosition(pos.x, pos.y);
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
        });
    }
}
