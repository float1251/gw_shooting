package jp.float1251.gwshooting.util;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.math.Vector2;

import jp.float1251.gwshooting.component.ParticleEffectComponent;
import jp.float1251.gwshooting.component.PositionComponent;
import jp.float1251.gwshooting.component.VelocityComponent;

/**
 * Created by takahiroiwatani on 2015/05/02.
 */
public class ComponentUtils {

    private static ComponentMapper<PositionComponent> pcm = ComponentMapper.getFor(PositionComponent.class);
    private static ComponentMapper<VelocityComponent> vcm = ComponentMapper.getFor(VelocityComponent.class);
    private static ComponentMapper<ParticleEffectComponent> pec = ComponentMapper.getFor(ParticleEffectComponent.class);

    private ComponentUtils() {

    }

    public static PositionComponent getPositionComponent(Entity entity) {
        return pcm.get(entity);
    }

    public static VelocityComponent getVelocityComponent(Entity entity) {
        return vcm.get(entity);
    }

    public static ParticleEffectComponent getParticleEffectComponent(Entity entity) {
        return pec.get(entity);
    }

    public static ParticleEffectComponent createParticleEffectComponent(Vector2 pos) {
        ParticleEffectComponent com = new ParticleEffectComponent();
        ParticleEffect effect = new ParticleEffect();
        effect.load(Gdx.files.internal("particle/effect.p"), Gdx.files.internal("particle"));
        effect.getEmitters().first().setPosition(pos.x, pos.y);
        // effect.setPosition(pos.x, pos.y);
        effect.start();
        com.effect = effect;
        return com;

    }
}
