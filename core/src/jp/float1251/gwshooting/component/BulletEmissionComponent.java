package jp.float1251.gwshooting.component;

import com.badlogic.ashley.core.Component;

/**
 * Created by takahiroiwatani on 2015/05/02.
 */
public class BulletEmissionComponent extends Component {

    public float interval;
    public float time;

    public BulletEmissionComponent(float interval) {
        this.interval = interval;
        this.time = 0;
    }

    // TODO 発生させる弾の種類を指定できるようにする

}
