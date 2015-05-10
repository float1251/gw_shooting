package jp.float1251.gwshooting.component;

import com.badlogic.ashley.core.Component;

import jp.float1251.gwshooting.type.BulletType;
import jp.float1251.gwshooting.type.GameObjectType;


/**
 * 弾の種類を保持しておくコンポーネント.
 * 弾それ自体に付けられることを想定している.
 */
public class BulletComponent extends Component {

    public float damage;
    public GameObjectType target;
    public BulletType type;

    public BulletComponent(BulletType type) {
        this(type, GameObjectType.ENEMY);
    }

    public BulletComponent(BulletType type, GameObjectType target) {
        this.type = type;
        this.target = target;
        this.damage = 1f;
    }
}
