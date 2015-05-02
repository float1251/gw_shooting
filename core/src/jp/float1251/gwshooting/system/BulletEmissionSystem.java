package jp.float1251.gwshooting.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import jp.float1251.gwshooting.component.BulletEmissionComponent;
import jp.float1251.gwshooting.component.PositionComponent;

/**
 * Created by takahiroiwatani on 2015/05/02.
 */
public class BulletEmissionSystem extends IteratingSystem {
    public BulletEmissionSystem() {
        super(Family.all(BulletEmissionComponent.class, PositionComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BulletEmissionComponent bec = entity.getComponent(BulletEmissionComponent.class);
        PositionComponent pc = entity.getComponent(PositionComponent.class);

    }
}
