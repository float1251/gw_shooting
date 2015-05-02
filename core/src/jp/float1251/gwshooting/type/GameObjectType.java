package jp.float1251.gwshooting.type;

/**
 * Created by takahiroiwatani on 2015/05/02.
 */
public enum GameObjectType {
    PLAYER,
    ENEMY,
    BULLET;

    public int getFlag() {
        int flag = 0;
        switch (this) {
            case PLAYER:
                flag = Integer.parseInt("0001", 2);
                break;
            case ENEMY:
                flag = Integer.parseInt("0010", 2);
                break;
            case BULLET:
                flag = Integer.parseInt("0100", 2);
                break;
        }
        return flag;
    }
}
