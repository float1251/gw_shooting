package jp.float1251.gwshooting.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by takahiroiwatani on 2015/04/30.
 */
public class PositionComponent extends Component {
    private Vector2 position;

    public PositionComponent() {
        this(0, 0);
    }

    public PositionComponent(float x, float y) {
        position = new Vector2(x, y);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(float x, float y) {
        this.position.set(x, y);
    }

    public void setPosition(Vector2 pos) {
        this.position.set(pos);
    }
}
