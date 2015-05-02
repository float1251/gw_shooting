package jp.float1251.gwshooting.util;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

import jp.float1251.gwshooting.component.PositionComponent;
import jp.float1251.gwshooting.component.VelocityComponent;

/**
 * Created by takahiroiwatani on 2015/05/02.
 */
public class ComponentUtils {

    private static ComponentMapper<PositionComponent> pcm = ComponentMapper.getFor(PositionComponent.class);
    private static ComponentMapper<VelocityComponent> vcm = ComponentMapper.getFor(VelocityComponent.class);

    private ComponentUtils() {

    }

    public static PositionComponent getPositionComponent(Entity entity) {
        return pcm.get(entity);
    }

    public static VelocityComponent getVelocityComponent(Entity entity) {
        return vcm.get(entity);
    }
}
