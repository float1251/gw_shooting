package jp.float1251.gwshooting.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

import jp.float1251.gwshooting.component.BulletComponent;
import jp.float1251.gwshooting.component.CircleCollisionComponent;
import jp.float1251.gwshooting.component.HealthComponent;
import jp.float1251.gwshooting.component.ParticleEffectComponent;
import jp.float1251.gwshooting.component.PositionComponent;
import jp.float1251.gwshooting.pool.ObjectPool;
import jp.float1251.gwshooting.sound.SoundManager;
import jp.float1251.gwshooting.type.GameObjectType;
import jp.float1251.gwshooting.util.ComponentUtils;

/**
 * Created by takahiroiwatani on 2015/05/02.
 */
public class CollisionSystem extends EntitySystem {
    private final ObjectPool manager;
    private final SoundManager soundManager;
    private Engine engine;

    public CollisionSystem(ObjectPool manager, SoundManager soundManager) {
        super();
        this.manager = manager;
        this.soundManager = soundManager;
    }

    public void addedToEngine(Engine engine) {
        this.engine = engine;
        super.addedToEngine(engine);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        ImmutableArray<Entity> bullets = engine.getEntitiesFor(
                Family.all(
                        BulletComponent.class,
                        CircleCollisionComponent.class,
                        PositionComponent.class).get());
        ImmutableArray<Entity> targets = engine.getEntitiesFor(
                Family.all(CircleCollisionComponent.class, PositionComponent.class)
                        .exclude(BulletComponent.class).get());
        for (Entity bullet : bullets) {
            GameObjectType bulletTarget = bullet.getComponent(BulletComponent.class).target;
            for (Entity target : targets) {
                // targetと同じflagだったら衝突判定を行う
                // 衝突していたらダメージを与える
                // ダメージを与えた結果、0になっていたら消滅させる
                if (target.flags == bulletTarget.getFlag()) {
                    if (checkCollision(bullet, target)) {
                        HealthComponent hc = target.getComponent(HealthComponent.class);
                        hc.life -= bullet.getComponent(BulletComponent.class).damage;
                        if (hc.life < 0) {
                            // 爆発エフェクト
                            ParticleEffectComponent effect = ComponentUtils.createParticleEffectComponent(
                                    ComponentUtils.getPositionComponent(bullet).getPosition().cpy()
                            );
                            soundManager.playSoundEffect("destruction1.mp3");
                            engine.addEntity(new Entity().add(effect));
                            // 削除処理
                            manager.removeEntity(target);
                            engine.removeEntity(target);
                        }
                        manager.removeEntity(bullet);
                        engine.removeEntity(bullet);
                        break;
                    }
                }
            }
        }
    }

    private boolean checkCollision(Entity entity1, Entity entity2) {
        float r1 = entity1.getComponent(CircleCollisionComponent.class).r;
        Vector2 pos1 = ComponentUtils.getPositionComponent(entity1).getPosition();
        float r2 = entity2.getComponent(CircleCollisionComponent.class).r;
        Vector2 pos2 = ComponentUtils.getPositionComponent(entity2).getPosition();
        return Intersector.overlaps(new Circle(pos1, r1), new Circle(pos2, r2));
    }

}
