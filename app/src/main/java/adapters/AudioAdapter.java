package adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.developer.vktest.R;

import java.util.List;

import api.models.AudioItem;
import util.AudioPlayer;

public class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.ViewHolder> {

    private boolean playPause;
    private List<AudioItem> audioList;
    private String mArtist = "Artist: ";
    private String mTitle = "Title: ";

    public AudioAdapter(List<AudioItem> audioList) {
        this.audioList = audioList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.audio_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.d("TAG", "onBindViewHolder ");
        holder.mAudioArtist.setText(mArtist + audioList.get(position).getArtist());
        holder.mAudioTitle.setText(mTitle + audioList.get(position).getTitle());
        holder.mPlayAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (audioList.get(position).getUrl() != null) {
                    AudioPlayer.playAudio(audioList.get(position).getUrl());
                    Log.d("TAG", "play audio " + position);
                } else
                    Log.d("TAG", "cannot play audio ");
            }
        });
    }

    @Override
    public int getItemCount() {
        return audioList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mAudioArtist, mAudioTitle;
        private Button mPlayAudio;

        public ViewHolder(View v) {
            super(v);
            mAudioArtist = (TextView) v.findViewById(R.id.audioArtist);
            mAudioTitle = (TextView) v.findViewById(R.id.audioTitle);
            mPlayAudio = (Button) v.findViewById(R.id.button_play);
        }
    }
}