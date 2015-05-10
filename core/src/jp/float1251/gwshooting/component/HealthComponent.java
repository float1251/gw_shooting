package jp.float1251.gwshooting.component;

import com.badlogic.ashley.core.Component;

/**
 * Created by takahiroiwatani on 2015/05/05.
 */
public class HealthComponent extends Component{
    public float life;

    public HealthComponent(float life){
        this.life = life;
    }
}
