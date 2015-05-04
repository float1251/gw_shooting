package jp.float1251.gwshooting.pool;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import jp.float1251.gwshooting.component.CircleCollisionComponent;
import jp.float1251.gwshooting.component.MoveTypeComponent;
import jp.float1251.gwshooting.component.PositionComponent;

/**
 * object pool 管理クラス
 */
public class PoolManager {

    private final EntityPool entityPool;

    public PoolManager() {
        entityPool = new EntityPool();
    }

    public Entity obtainEntity() {
        return entityPool.obtain();
    }

    public void removeEntity(Entity entity){
        for(Component com:entity.getComponents()){
            if(com instanceof PositionComponent) {
            }
            if(com instanceof CircleCollisionComponent){
            }
            if(com instanceof MoveTypeComponent){
            }
        }
        entityPool.free(entity);
    }
}
