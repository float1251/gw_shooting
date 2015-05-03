package jp.float1251.gwshooting.screen;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.Iterator;

import jp.float1251.gwshooting.GWShooting;
import jp.float1251.gwshooting.component.BulletEmissionComponent;
import jp.float1251.gwshooting.component.CircleCollisionComponent;
import jp.float1251.gwshooting.component.PositionComponent;
import jp.float1251.gwshooting.pool.EnemyPool;
import jp.float1251.gwshooting.system.BulletEmissionSystem;
import jp.float1251.gwshooting.system.CollisionSystem;
import jp.float1251.gwshooting.system.DebugCollisionRenderingSystem;
import jp.float1251.gwshooting.system.MovementSystem;
import jp.float1251.gwshooting.system.ParticleEffectSystem;
import jp.float1251.gwshooting.type.GameObjectType;
import jp.float1251.gwshooting.util.ComponentUtils;
import jp.float1251.gwshooting.util.TMXUtils;

/**
 * Created by takahiroiwatani on 2015/04/30.
 */
public class InGameScreen implements Screen {

    private final GWShooting game;
    private final SpriteBatch batch;
    private Engine engine;
    private FitViewport viewport;
    private Entity player;
    private EnemyPool enemyPool;

    public InGameScreen(GWShooting game) {
        this.game = game;
        this.batch = game.getSpriteBatch();
        this.viewport = new FitViewport(640, 960);

        initialize();
    }

    private void initialize() {
        enemyPool = new EnemyPool();

        engine = new Engine();
        // add System
        engine.addSystem(new MovementSystem());
        engine.addSystem(new BulletEmissionSystem());
        engine.addSystem(new CollisionSystem(enemyPool, engine.getSystem(BulletEmissionSystem.class).bulletPool));
        engine.addSystem(new DebugCollisionRenderingSystem((OrthographicCamera) viewport.getCamera()));
        engine.addSystem(new ParticleEffectSystem(batch, (OrthographicCamera) viewport.getCamera()));

        // add Component
        player = new Entity();
        player.flags = GameObjectType.PLAYER.getFlag();
        player.add(new PositionComponent(0, 0));
        player.add(new CircleCollisionComponent(10));
        player.add(new BulletEmissionComponent(0.25f));
        engine.addEntity(player);

        // tmxを読み込んでobjectから敵を出現させる
        TiledMap map = new TmxMapLoader().load("stage/stage.tmx");
        MapLayer layer = map.getLayers().get("Enemy");
        Iterator<MapObject> iter = layer.getObjects().iterator();
        while (iter.hasNext()) {
            MapObject obj = iter.next();
            Vector2 pos = TMXUtils.getPosition(obj.getProperties());
            Gdx.app.log("ENEMY", String.format("x: %f, y: %f", pos.x, pos.y));
            Entity enemy = enemyPool.obtain(pos,
                    ComponentUtils.getPositionComponent(player).getPosition());
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
                startPosition = ComponentUtils.getPositionComponent(player).getPosition();
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
        });
    }
}
