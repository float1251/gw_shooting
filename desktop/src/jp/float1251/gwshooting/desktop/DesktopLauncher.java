package jp.float1251.gwshooting.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import jp.float1251.gwshooting.GWShooting;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.height = 960;
        config.width = 640;
		new LwjglApplication(new GWShooting(), config);
	}
}
