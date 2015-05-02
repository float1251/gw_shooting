package jp.float1251.gwshooting.pool;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by takahiroiwatani on 2015/05/02.
 */
public class EnemyPool extends Pool<Entity> {
    @Override
    protected Entity newObject() {
        return null;
    }


}
