package jp.float1251.gwshooting.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import jp.float1251.gwshooting.component.MoveTypeComponent;
import jp.float1251.gwshooting.component.PositionComponent;
import jp.float1251.gwshooting.component.VelocityComponent;
import jp.float1251.gwshooting.pool.PoolManager;
import jp.float1251.gwshooting.util.ComponentUtils;

/**
 * Created by t-iwatani on 2015/05/01.
 */
public class MovementSystem extends IteratingSystem {
    private final OrthographicCamera camera;
    private final PoolManager manager;
    private Engine engine;

    public MovementSystem(OrthographicCamera camera, PoolManager manager) {
        super(Family.all(MoveTypeComponent.class, PositionComponent.class).get());
        this.manager = manager;
        this.camera = camera;
    }


    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        this.engine = engine;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        MoveTypeComponent mc = entity.getComponent(MoveTypeComponent.class);
        float speed = 100;
        switch (mc.type) {
            case TARGET:
                moveToTarget(entity, deltaTime, speed);
                break;
            case VELOCITY:
                move(entity, deltaTime);
                break;
        }
        PositionComponent pc = ComponentUtils.getPositionComponent(entity);
        Vector2 pos = pc.getPosition();
        if (checkDestroyEntity(pos)) {
            // 削除する
            Gdx.app.log("Move", "delete entity");
            manager.removeEntity(entity);
            engine.removeEntity(entity);
        }
    }

    private void moveToTarget(Entity entity, float deltaTime, float speed) {
        MoveTypeComponent mc = entity.getComponent(MoveTypeComponent.class);
        Vector2 from = entity.getComponent(PositionComponent.class).getPosition();
        Vector2 to = mc.target;
        Vector2 velocity = to.cpy().sub(from).nor().scl(speed * deltaTime);
        entity.getComponent(PositionComponent.class).getPosition().add(velocity);
    }

    private void move(Entity entity, float delta) {
        PositionComponent pc = ComponentUtils.getPositionComponent(entity);
        VelocityComponent vc = ComponentUtils.getVelocityComponent(entity);
        pc.getPosition().add(vc.getVelocity().cpy().scl(delta));
    }

    private boolean checkDestroyEntity(Vector2 pos) {
        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();
        Vector3 screenPos = camera.project(new Vector3(pos.x, pos.y, 0));
        if (screenPos.x <= -400 || screenPos.x >= 1500 || screenPos.y <= -400 || screenPos.y >= 2000) {
            return true;
        }
        return false;
    }

}
