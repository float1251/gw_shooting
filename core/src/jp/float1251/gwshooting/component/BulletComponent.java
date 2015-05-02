package jp.float1251.gwshooting.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import jp.float1251.gwshooting.type.BulletType;


/**
 * 弾の種類を保持しておくコンポーネント.
 * 弾それ自体に付けられることを想定している.
 */
public class BulletComponent extends Component {

    public Entity target;
    public BulletType type;

    public BulletComponent(BulletType type) {
        this(type, null);
    }

    public BulletComponent(BulletType type, Entity target) {
        this.type = type;
        this.target = target;
    }
}
