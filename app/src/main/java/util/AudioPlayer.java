package util;

import android.media.AudioManager;
import android.media.MediaPlayer;

public class AudioPlayer extends MediaPlayer{

    private String mUrl;

    public AudioPlayer(String url) {
        this.mUrl = url;
    }

    @Override
    public void start() throws IllegalStateException {
        super.start();
    }

    @Override
    public void pause() throws IllegalStateException {
        super.pause();
    }

    public static void playAudio(String Url) {
        try {
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(Url);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}