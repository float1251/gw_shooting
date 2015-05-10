package jp.float1251.gwshooting.factory;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

import jp.float1251.gwshooting.component.CircleCollisionComponent;
import jp.float1251.gwshooting.component.HealthComponent;
import jp.float1251.gwshooting.component.MoveTypeComponent;
import jp.float1251.gwshooting.component.PositionComponent;
import jp.float1251.gwshooting.pool.ObjectPool;
import jp.float1251.gwshooting.type.GameObjectType;
import jp.float1251.gwshooting.type.MovingType;

/**
 * Created by takahiroiwatani on 2015/05/02.
 */
public class EnemyFactory {

    private EnemyFactory(){}


    public static Entity createEnemy(ObjectPool manager, Vector2 pos, Vector2 target) {
        Entity enemy = manager.createEntity();
        enemy.flags = GameObjectType.ENEMY.getFlag();
        enemy.add(new PositionComponent(pos.x, pos.y));
        enemy.add(new CircleCollisionComponent(10));
        enemy.add(new MoveTypeComponent(MovingType.TARGET, target));
        enemy.add(new HealthComponent(5));
        return enemy;
    }

}
