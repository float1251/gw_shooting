package jp.float1251.gwshooting.screen;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;

import jp.float1251.gwshooting.GWShooting;
import jp.float1251.gwshooting.Stage;
import jp.float1251.gwshooting.component.BulletEmissionComponent;
import jp.float1251.gwshooting.component.CircleCollisionComponent;
import jp.float1251.gwshooting.component.HealthComponent;
import jp.float1251.gwshooting.component.OrbitalFlightComponent;
import jp.float1251.gwshooting.component.PositionComponent;
import jp.float1251.gwshooting.input.GameInputProcessor;
import jp.float1251.gwshooting.pool.ObjectPool;
import jp.float1251.gwshooting.sound.SoundManager;
import jp.float1251.gwshooting.system.BulletEmissionSystem;
import jp.float1251.gwshooting.system.CollisionSystem;
import jp.float1251.gwshooting.system.DebugCollisionRenderingSystem;
import jp.float1251.gwshooting.system.MovementSystem;
import jp.float1251.gwshooting.system.OrbitalFlightSystem;
import jp.float1251.gwshooting.system.ParticleEffectSystem;
import jp.float1251.gwshooting.type.GameObjectType;

/**
 * Created by takahiroiwatani on 2015/04/30.
 */
public class InGameScreen implements Screen {

    private final GWShooting game;
    private final SpriteBatch batch;
    private final ObjectPool pool;
    private final SoundManager soundManager;
    private Engine engine;
    private FitViewport viewport;
    private Entity player;
    private Stage stage;

    public InGameScreen(GWShooting game) {
        this.game = game;
        this.batch = game.getSpriteBatch();
        this.viewport = new FitViewport(640, 960);
        this.pool = new ObjectPool();

        this.soundManager = new SoundManager();
        soundManager.playMusic("bgm.mp3", true);

        initialize();
    }

    private void initialize() {
        engine = new Engine();
        // add System
        engine.addSystem(new OrbitalFlightSystem(pool));
        engine.addSystem(new MovementSystem((OrthographicCamera) viewport.getCamera(), pool));
        engine.addSystem(new BulletEmissionSystem(pool));
        engine.addSystem(new CollisionSystem(pool, soundManager));
        engine.addSystem(new DebugCollisionRenderingSystem((OrthographicCamera) viewport.getCamera()));
        engine.addSystem(new ParticleEffectSystem(batch, (OrthographicCamera) viewport.getCamera()));

        // add Component
        player = pool.createEntity();
        player.flags = GameObjectType.PLAYER.getFlag();
        player.add(new PositionComponent(0, 0));
        player.add(new CircleCollisionComponent(10));
        player.add(new BulletEmissionComponent(0.25f));
        engine.addEntity(player);

        // tmxから読み込んでenemyを作成する
        this.stage = new Stage(engine, "stage/stage.tmx", pool, player, viewport.getWorldHeight());

        // test orbital flight
        Entity enemy = new Entity();
        Vector2 pos = new Vector2(100, 100);
        enemy.flags = GameObjectType.ENEMY.getFlag();
        enemy.add(new PositionComponent(pos.x, pos.y));
        enemy.add(new CircleCollisionComponent(10));
        enemy.add(new HealthComponent(2));
        OrbitalFlightComponent ofc = new OrbitalFlightComponent();
        ofc.dataArray = new OrbitalFlightComponent.OrbitalFlightData[5];
        ofc.dataArray[0] = new OrbitalFlightComponent.OrbitalFlightData(3, -30, 3);
        ofc.dataArray[1] = new OrbitalFlightComponent.OrbitalFlightData(30, -30, 3);
        ofc.dataArray[2] = new OrbitalFlightComponent.OrbitalFlightData(-30, -30, 3);
        ofc.dataArray[3] = new OrbitalFlightComponent.OrbitalFlightData(-30, -30, 3);
        ofc.dataArray[4] = new OrbitalFlightComponent.OrbitalFlightData(30, 300, 3);
        enemy.add(ofc);
        engine.addEntity(enemy);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new GameInputProcessor(player, viewport));
    }

    @Override
    public void render(float delta) {
        viewport.getCamera().update();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.update(delta);
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
        soundManager.dispose();
    }

}
