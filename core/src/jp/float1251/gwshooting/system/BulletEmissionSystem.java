package jp.float1251.gwshooting.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import jp.float1251.gwshooting.component.BulletEmissionComponent;
import jp.float1251.gwshooting.component.PositionComponent;
import jp.float1251.gwshooting.pool.BulletPool;
import jp.float1251.gwshooting.util.ComponentUtils;

/**
 * Created by takahiroiwatani on 2015/05/02.
 */
public class BulletEmissionSystem extends IteratingSystem {

    public BulletPool bulletPool = new BulletPool();
    private Engine engine;

    public BulletEmissionSystem() {
        super(Family.all(BulletEmissionComponent.class, PositionComponent.class).get());
    }


    @Override
    public void addedToEngine(Engine engine) {
        this.engine = engine;
        super.addedToEngine(engine);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BulletEmissionComponent bec = entity.getComponent(BulletEmissionComponent.class);
        PositionComponent pc = entity.getComponent(PositionComponent.class);

        bec.time += deltaTime;
        if (bec.time >= bec.interval) {
            // instantiate bullet
            Entity bullet = bulletPool.obtain();
            ComponentUtils.getPositionComponent(bullet).setPosition(pc.getPosition().cpy());
            engine.addEntity(bullet);
            bec.time = 0;
        }
    }
}
