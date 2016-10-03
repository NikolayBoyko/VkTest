package util;

import android.media.AudioManager;
import android.media.MediaPlayer;

public class AudioPlayer {

    private String mUrl;

    public AudioPlayer(String url) {
        this.mUrl = url;
    }

    public static void playAudio(String Url) {
        try {
            MediaPlayer player = new MediaPlayer();
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);

            player.setDataSource(Url);
            player.prepare();
            player.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}