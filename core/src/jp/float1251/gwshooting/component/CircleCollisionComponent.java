package jp.float1251.gwshooting.component;

import com.badlogic.ashley.core.Component;

/**
 * Created by takahiroiwatani on 2015/04/30.
 */
public class CircleCollisionComponent extends Component{
    public float r;

    public CircleCollisionComponent(float r){
        this.r = r;
    }

    public CircleCollisionComponent(){
        this. r = 0;
    }

}
