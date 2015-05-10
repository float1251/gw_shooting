package jp.float1251.gwshooting.util;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by takahiroiwatani on 2015/05/02.
 */
public class TMXUtils {
    private TMXUtils() {

    }

    public static Vector2 getPosition(MapProperties props) {
        return new Vector2((Float) props.get("x"), (Float) props.get("y"));
    }

}
