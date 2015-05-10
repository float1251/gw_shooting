package jp.float1251.gwshooting.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import jp.float1251.gwshooting.component.OrbitalFlightComponent;
import jp.float1251.gwshooting.component.PositionComponent;
import jp.float1251.gwshooting.pool.ObjectPool;
import jp.float1251.gwshooting.util.ComponentUtils;

/**
 * Created by takahiroiwatani on 2015/05/05.
 */
public class OrbitalFlightSystem extends IteratingSystem {
    private final ObjectPool manager;
    private Engine engine;

    public OrbitalFlightSystem(ObjectPool manager) {
        super(Family.all(OrbitalFlightComponent.class, PositionComponent.class).get());
        this.manager = manager;
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        this.engine = engine;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        OrbitalFlightComponent ofc = entity.getComponent(OrbitalFlightComponent.class);
        PositionComponent pc = ComponentUtils.getPositionComponent(entity);
        int index = ofc.index;
        OrbitalFlightComponent.OrbitalFlightData data = ofc.dataArray[index];
        pc.getPosition().add(data.velocity.cpy().scl(deltaTime));
        ofc.time += deltaTime;
        if (ofc.time >= data.time) {
            ofc.index++;
            ofc.time = 0;
        }

        // 最後まで移動したら消滅させる
        if (ofc.index >= ofc.dataArray.length) {
            engine.removeEntity(entity);
            manager.removeEntity(entity);
        }
    }
}
