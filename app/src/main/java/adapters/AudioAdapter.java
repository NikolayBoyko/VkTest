package adapters;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.audio_item, parent, false);
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

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mAudioArtist, mAudioTitle;
        private SeekBar mSeekBar;

        public ViewHolder(View v) {
            super(v);
            mAudioArtist = (TextView) v.findViewById(R.id.audio_artist);
            mAudioTitle = (TextView) v.findViewById(R.id.audio_title);
            mSeekBar = (SeekBar) v.findViewById(R.id.my_seek_bar);
        }

    }

}
