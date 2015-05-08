package jp.float1251.gwshooting.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import jp.float1251.gwshooting.component.BulletEmissionComponent;
import jp.float1251.gwshooting.component.PositionComponent;
import jp.float1251.gwshooting.factory.BulletFactory;
import jp.float1251.gwshooting.pool.ObjectPool;
import jp.float1251.gwshooting.util.ComponentUtils;

/**
 * Created by takahiroiwatani on 2015/05/02.
 */
public class BulletEmissionSystem extends IteratingSystem {

    private final ObjectPool manager;
    private Engine engine;

    public BulletEmissionSystem(ObjectPool manager) {
        super(Family.all(BulletEmissionComponent.class, PositionComponent.class).get());
        this.manager = manager;
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
            Entity bullet = BulletFactory.createFactory(manager);
            ComponentUtils.getPositionComponent(bullet).setPosition(pc.getPosition().cpy());
            engine.addEntity(bullet);
            bec.time = 0;
        }
    }
}
