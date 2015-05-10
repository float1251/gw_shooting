package jp.float1251.gwshooting.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by takahiroiwatani on 2015/04/30.
 */
public class VelocityComponent extends Component{
    private Vector2 velocity;

    public VelocityComponent(){
        this(0, 0);
    }

    public VelocityComponent(float x, float y){
        velocity = new Vector2(x, y);
    }


    public Vector2 getVelocity(){
        return velocity;
    }
}
