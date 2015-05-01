package jp.float1251.gwshooting.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;

import jp.float1251.gwshooting.component.MoveTypeComponent;
import jp.float1251.gwshooting.component.PositionComponent;

/**
 * Created by t-iwatani on 2015/05/01.
 */
public class MovementSystem extends IteratingSystem {
    public MovementSystem() {
        super(Family.all(MoveTypeComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        MoveTypeComponent mc = entity.getComponent(MoveTypeComponent.class);
        switch (mc.type) {
            case TargetPlayer:
                handleTargetPlayerMove(entity, deltaTime);
                break;
            case Velocity:
                break;

        }
    }

    private void handleTargetPlayerMove(Entity entity, float deltaTime) {
        MoveTypeComponent mc = entity.getComponent(MoveTypeComponent.class);
        Vector2 from = entity.getComponent(PositionComponent.class).getPosition();
        Vector2 to = mc.target;
        Vector2 velocity = to.cpy().sub(from).nor().scl(50 * deltaTime);
        entity.getComponent(PositionComponent.class).getPosition().add(velocity);
    }
}
