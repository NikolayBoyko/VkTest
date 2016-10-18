package adapters;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
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


    public AudioAdapter(List<AudioItem> audioList) {
        this.audioList = audioList;
    }

    @Override
    public AudioAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AudioAdapter.ViewHolder holder, int position) {
        holder.mAudioArtist.setText(audioList.get(position).getArtist());
        holder.mAudioTitle.setText(audioList.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return audioList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mAudioArtist, mAudioTitle;
        private Button mButtonPlay, mButtonPause;
        private SeekBar mSeekBar;
        private MediaPlayer mediaPlayer;

        public ViewHolder(View v) {
            super(v);
            mAudioArtist = (TextView) v.findViewById(R.id.audio_artist);
            mAudioTitle = (TextView) v.findViewById(R.id.audio_title);
            mButtonPlay = (Button) v.findViewById(R.id.button_play);
            mButtonPlay.setOnClickListener(this);
            mButtonPause = (Button) v.findViewById(R.id.button_stop);
            mButtonPause.setOnClickListener(this);
            mSeekBar = (SeekBar) v.findViewById(R.id.my_seek_bar);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_play:
                    mButtonPlay.setVisibility(View.GONE);
                    mButtonPause.setVisibility(View.VISIBLE);
                    try {
                        mediaPlayer = new MediaPlayer();
                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        mediaPlayer.setDataSource(audioList.get(getAdapterPosition()).getUrl());
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case R.id.button_stop:
                    mButtonPause.setVisibility(View.GONE);
                    mButtonPlay.setVisibility(View.VISIBLE);
                    mediaPlayer.pause();
                    break;

                default:
                    break;
            }
        }
    }
}
