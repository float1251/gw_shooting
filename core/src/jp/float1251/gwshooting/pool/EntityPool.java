package jp.float1251.gwshooting.pool;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by takahiroiwatani on 2015/05/04.
 */
public class EntityPool extends Pool<Entity> {
    @Override
    protected Entity newObject() {
        return new GameEntity();
    }

    public static class GameEntity extends Entity implements Poolable {

        @Override
        public void reset() {
            this.removeAll();
        }
    }
}
