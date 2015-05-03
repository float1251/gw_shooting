package jp.float1251.gwshooting.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import jp.float1251.gwshooting.component.ParticleEffectComponent;
import jp.float1251.gwshooting.util.ComponentUtils;

/**
 * Created by takahiroiwatani on 2015/05/03.
 */
public class ParticleEffectSystem extends IteratingSystem {

    private final OrthographicCamera camera;
    private SpriteBatch batch;
    private Engine engine;

    public ParticleEffectSystem(SpriteBatch batch, OrthographicCamera camera) {
        super(Family.all(ParticleEffectComponent.class).get());
        this.batch = batch;
        this.camera = camera;
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        this.engine = engine;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        Gdx.app.log("Particle", "process");
        ParticleEffectComponent pec = ComponentUtils.getParticleEffectComponent(entity);
        pec.effect.draw(batch, deltaTime);
        if (pec.effect.isComplete()) {
            Gdx.app.log("Particle", "complete");
            pec.effect.dispose();
            engine.removeEntity(entity);
        }
        batch.end();
    }
}
