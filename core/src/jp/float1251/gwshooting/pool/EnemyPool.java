package jp.float1251.gwshooting.pool;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

import jp.float1251.gwshooting.component.CircleCollisionComponent;
import jp.float1251.gwshooting.component.MoveTypeComponent;
import jp.float1251.gwshooting.component.PositionComponent;
import jp.float1251.gwshooting.listener.EnemyDestroyListener;
import jp.float1251.gwshooting.type.GameObjectType;
import jp.float1251.gwshooting.type.MovingType;

/**
 * Created by takahiroiwatani on 2015/05/02.
 */
public class EnemyPool extends Pool<Entity> implements EnemyDestroyListener {
    @Override
    protected Entity newObject() {
        Entity enemy = new Entity();
        enemy.flags = GameObjectType.ENEMY.getFlag();
        enemy.add(new PositionComponent(0, 0));
        enemy.add(new CircleCollisionComponent(10));
        enemy.add(new MoveTypeComponent(MovingType.TARGET));
        return enemy;
    }

    public Entity obtain(Vector2 pos, Vector2 target) {
        Entity enemy = this.obtain();
        enemy.getComponent(PositionComponent.class).setPosition(pos);
        enemy.getComponent(MoveTypeComponent.class).target = target;
        return enemy;
    }

    @Override
    public void destroyEnemy(Entity enemy) {
        this.free(enemy);
    }
}
