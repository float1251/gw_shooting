package jp.float1251.gwshooting.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by takahiroiwatani on 2015/04/30.
 */
public class VelocityComponent extends Component{
    private Vector2 velocity;

    public VelocityComponent(){
        velocity = new Vector2();
    }

    public Vector2 getVelocity(){
        return velocity;
    }
}
