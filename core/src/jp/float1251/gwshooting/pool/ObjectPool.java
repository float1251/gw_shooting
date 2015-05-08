package jp.float1251.gwshooting.pool;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;

/**
 * Created by t-iwatani on 2015/05/08.
 */
public class ObjectPool {

    private final PooledEngine pooledEngine;

    public ObjectPool() {
        this.pooledEngine = new PooledEngine();
    }

    public Entity createEntity() {
        return pooledEngine.createEntity();
    }

    public void removeEntity(Entity entity){
        pooledEngine.removeEntity(entity);
    }

    public <T extends Component> T createComponent(Class<T> compoennt) {
        return pooledEngine.createComponent(compoennt);
    }


}
