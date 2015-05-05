package jp.float1251.gwshooting.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by takahiroiwatani on 2015/05/05.
 */
public class OrbitalFlightComponent extends Component {
    public OrbitalFlightData[] dataArray;
    public float time;
    public int index;

    public static class OrbitalFlightData {
        public Vector2 velocity;
        public float time;

        public OrbitalFlightData(float velocityX, float velocityY, float time) {
            velocity = new Vector2(velocityX, velocityY);
            this.time = time;
        }
    }
}
