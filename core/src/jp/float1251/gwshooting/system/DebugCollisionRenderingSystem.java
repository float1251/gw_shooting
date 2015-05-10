package jp.float1251.gwshooting.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Iterator;

import jp.float1251.gwshooting.component.CircleCollisionComponent;
import jp.float1251.gwshooting.component.PositionComponent;

/**
 * Created by takahiroiwatani on 2015/04/30.
 */
public class DebugCollisionRenderingSystem extends IteratingSystem {

    private static ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    private static ComponentMapper<CircleCollisionComponent> cm = ComponentMapper.getFor(CircleCollisionComponent.class);
    private final ShapeRenderer shapeRenderer;
    private final OrthographicCamera camera;

    public DebugCollisionRenderingSystem(OrthographicCamera camera) {
        super(Family.all(PositionComponent.class, CircleCollisionComponent.class).get());
        this.shapeRenderer = new ShapeRenderer();
        shapeRenderer.setColor(new Color(155/255f, 89/255f, 182/255f, 1));
        this.camera = camera;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Iterator<Entity> iter = getEntities().iterator();
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        while (iter.hasNext()) {
            Entity entity = iter.next();
            PositionComponent pc = pm.get(entity);
            CircleCollisionComponent cc = cm.get(entity);
            shapeRenderer.circle(pc.getPosition().x, pc.getPosition().y, cc.r, 10);
        }
        shapeRenderer.end();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
    }
}
