package jp.float1251.gwshooting.factory;

import com.badlogic.ashley.core.Entity;

import jp.float1251.gwshooting.component.BulletComponent;
import jp.float1251.gwshooting.component.CircleCollisionComponent;
import jp.float1251.gwshooting.component.MoveTypeComponent;
import jp.float1251.gwshooting.component.PositionComponent;
import jp.float1251.gwshooting.component.VelocityComponent;
import jp.float1251.gwshooting.pool.PoolManager;
import jp.float1251.gwshooting.type.BulletType;
import jp.float1251.gwshooting.type.GameObjectType;
import jp.float1251.gwshooting.type.MovingType;

/**
 * Created by takahiroiwatani on 2015/05/04.
 */
public class BulletFactory {

    private BulletFactory() {
    }

    public static Entity createFactory(PoolManager manager) {
        Entity bullet = manager.obtainEntity();
        bullet.flags = GameObjectType.BULLET.getFlag();
        bullet.add(new PositionComponent(0, 0));
        bullet.add(new BulletComponent(BulletType.BULLET));
        bullet.add(new MoveTypeComponent(MovingType.VELOCITY));
        bullet.add(new CircleCollisionComponent(4));
        bullet.add(new VelocityComponent(0, 400));
        return bullet;
    }
}
