package jp.float1251.gwshooting.log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by takahiroiwatani on 2015/05/10.
 */
public class GWLog {

    public static void logVector2List(ArrayList<Vector2> list) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            sb.append("" + list.get(i).y);
            if (i < list.size() - 1) {
                sb.append(", ");
            }
        }
        Gdx.app.log("GWShooting", sb.toString());
    }

}
