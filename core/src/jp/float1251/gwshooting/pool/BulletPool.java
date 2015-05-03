package jp.float1251.gwshooting.pool;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Pool;

import jp.float1251.gwshooting.component.BulletComponent;
import jp.float1251.gwshooting.component.CircleCollisionComponent;
import jp.float1251.gwshooting.component.MoveTypeComponent;
import jp.float1251.gwshooting.component.PositionComponent;
import jp.float1251.gwshooting.component.VelocityComponent;
import jp.float1251.gwshooting.listener.BulletDestroyListener;
import jp.float1251.gwshooting.type.BulletType;
import jp.float1251.gwshooting.type.GameObjectType;
import jp.float1251.gwshooting.type.MovingType;

/**
 * Created by takahiroiwatani on 2015/05/02.
 */
public class BulletPool extends Pool<Entity> implements BulletDestroyListener {
    @Override
    protected Entity newObject() {
        Entity bullet = new Entity();
        bullet.flags = GameObjectType.BULLET.getFlag();
        bullet.add(new PositionComponent(0, 0));
        bullet.add(new BulletComponent(BulletType.BULLET));
        bullet.add(new MoveTypeComponent(MovingType.VELOCITY));
        bullet.add(new CircleCollisionComponent(4));
        bullet.add(new VelocityComponent(0, 400));
        return bullet;
    }

    @Override
    public void destroyBullet(Entity bullet) {
        free(bullet);
    }
}
