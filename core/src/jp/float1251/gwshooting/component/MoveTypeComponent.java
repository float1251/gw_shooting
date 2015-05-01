package jp.float1251.gwshooting.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by t-iwatani on 2015/05/01.
 */
public class MoveTypeComponent extends Component {
    public final Type type;
    public final Vector2 target;

    public enum Type {
        TargetPlayer,
        Velocity;
    }

    public MoveTypeComponent(Type type, Vector2 target) {
        this.type = type;
        this.target = target;
    }
}
