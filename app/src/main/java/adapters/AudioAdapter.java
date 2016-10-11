package adapters;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.developer.vktest.R;

import java.util.List;

import api.models.AudioItem;

public class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.ViewHolder> {

    private List<AudioItem> audioList;
    private String mArtist = "Artist: ";
    private String mTitle = "Title: ";
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private int currentPosition = 0;


    public AudioAdapter(Context context, List<AudioItem> audioList) {
        this.audioList = audioList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.audio_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mAudioArtist.setText(mArtist + audioList.get(position).getArtist());
        holder.mAudioTitle.setText(mTitle + audioList.get(position).getTitle());

        holder.mPlayAudio.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                holder.mPlayAudio.setVisibility(view.GONE);
                holder.mPauseAudio.setVisibility(view.VISIBLE);

                if (mediaPlayer.getCurrentPosition() != 0) {
                    Log.d("TAG", "play from currentPosition " + mediaPlayer.getCurrentPosition());
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition());
                    mediaPlayer.start();
                } else {
                    playAudio(audioList.get(position).getUrl());
                    Log.d("TAG", "playAudio " + currentPosition);
                }
            }
        });

        holder.mPauseAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.mPlayAudio.setVisibility(view.VISIBLE);
                holder.mPauseAudio.setVisibility(view.GONE);

                mediaPlayer.pause();
                // currentPosition = mediaPlayer.getCurrentPosition();
                Log.d("TAG", "pause " + mediaPlayer.getCurrentPosition());


            }
        });
    }

    @Override
    public int getItemCount() {
        return audioList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mAudioArtist, mAudioTitle;
        private Button mPlayAudio, mPauseAudio;
        private SeekBar mSeekBar;

        public ViewHolder(View v) {
            super(v);
            mAudioArtist = (TextView) v.findViewById(R.id.audio_artist);
            mAudioTitle = (TextView) v.findViewById(R.id.audio_title);
            mPlayAudio = (Button) v.findViewById(R.id.button_play);
            mPauseAudio = (Button) v.findViewById(R.id.button_stop);
            mSeekBar = (SeekBar) v.findViewById(R.id.my_seek_bar);
        }
    }

    public void playAudio(String Url) {
        try {
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(Url);
            mediaPlayer.prepare();
            mediaPlayer.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}