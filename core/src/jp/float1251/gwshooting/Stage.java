package jp.float1251.gwshooting;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import jp.float1251.gwshooting.factory.EnemyFactory;
import jp.float1251.gwshooting.log.GWLog;
import jp.float1251.gwshooting.pool.ObjectPool;
import jp.float1251.gwshooting.util.ComponentUtils;
import jp.float1251.gwshooting.util.TMXUtils;

/**
 * tmxデータを読み込んで敵を出現させる.
 * <p/>
 * Created by takahiroiwatani on 2015/05/09.
 */
public class Stage implements Disposable {

    private final TiledMap map;
    private final Engine engine;
    private final ObjectPool pool;
    private final Entity target;
    private float currentScroll;
    private final float scrollVelocity;

    private ArrayList<Vector2> posList;
    private float time;

    public Stage(Engine engine, String fileName, ObjectPool pool, Entity target, float viewportHeight) {
        this.engine = engine;
        this.pool = pool;
        this.map = new TmxMapLoader().load(fileName);
        this.currentScroll = viewportHeight;
        this.scrollVelocity = 100f;
        this.target = target;
        this.posList = new ArrayList<>();

        MapLayer layer = map.getLayers().get("Enemy");
        Iterator<MapObject> iter = layer.getObjects().iterator();
        while (iter.hasNext()) {
            MapObject obj = iter.next();
            Vector2 pos = TMXUtils.getPosition(obj.getProperties());
            posList.add(pos);
            Gdx.app.log("ENEMY", String.format("x: %f, y: %f", pos.x, pos.y));
        }

        // yの値でsort
        posList.sort(new Comparator<Vector2>() {
            @Override
            public int compare(Vector2 o1, Vector2 o2) {
                if (o1.y > o2.y)
                    return 1;
                if (o1.y < o2.y) {
                    return -1;
                }
                return 0;
            }
        });
        GWLog.logVector2List(posList);
    }

    public void update(float deltaTime) {
        currentScroll += scrollVelocity * deltaTime;
        time += deltaTime;

        // 0.5f経過していなかったら確認しない.
        if(time < 0.5f){
            return;
        }

        // objectのposをチェックして、規定値に到達していたら敵を出現させる
        createEnemies(currentScroll);
        time = 0;
    }

    /**
     * limitY以下のY座標のデータがあったら敵を出現させる.
     * @param limitY
     */
    public void createEnemies(float limitY){
         // objectのposをチェックして、規定値に到達していたら敵を出現させる
        int index = 0;
        ArrayList<Vector2> tmpList = new ArrayList<>();
        while (index < posList.size() && posList.get(index).y <= limitY) {
            Vector2 pos = posList.get(index);
            Entity enemy = EnemyFactory.createEnemy(pool, pos,
                    ComponentUtils.getPositionComponent(target).getPosition());
            engine.addEntity(enemy);
            tmpList.add(pos);
            index++;
        }
        posList.removeAll(tmpList);
    }

    @Override
    public void dispose() {
        map.dispose();
    }
}
