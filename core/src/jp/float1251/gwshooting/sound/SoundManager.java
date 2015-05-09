package jp.float1251.gwshooting.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Disposable;

import java.util.HashMap;

/**
 * Created by takahiroiwatani on 2015/05/09.
 */
public class SoundManager implements Disposable {


    public HashMap<String, Sound> soundMap = new HashMap<>();

    public HashMap<String, Music> musicMap = new HashMap<>();

    public SoundManager() {
        // initialize
        musicMap.put("bgm.mp3", Gdx.audio.newMusic(Gdx.files.internal("bgm.mp3")));
        soundMap.put("destruction1.mp3", Gdx.audio.newSound(Gdx.files.internal("destruction1.mp3")));

    }

    public void playMusic(String fileName, boolean loop) {
        Music music = musicMap.get(fileName);
        if (music.isPlaying())
            return;
        music.setVolume(0.5f);
        music.setLooping(loop);
        music.play();
    }

    public void playSoundEffect(String fileName) {
        Sound sound = soundMap.get(fileName);
        long id= sound.play();
        sound.setVolume(id, 0.2f);
    }

    public long playSoundEffect(String fileName, boolean loop) {
        Sound sound = soundMap.get(fileName);
        long id = sound.play();
        sound.setLooping(id, loop);
        return id;
    }


    @Override
    public void dispose() {
        for (String key : musicMap.keySet()) {
            musicMap.get(key).dispose();
        }
        for (String key : soundMap.keySet()) {
            soundMap.get(key).dispose();
        }
        soundMap.clear();
        musicMap.clear();
    }
}
