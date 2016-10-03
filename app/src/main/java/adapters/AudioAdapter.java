package adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.developer.vktest.R;

import java.util.List;

import api.models.AudioItem;

public class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.ViewHolder> {

    private List<AudioItem> audioList;
    private String mArtist = "Artist: ";
    private String mTitle = "Title: ";
    private String mUrl = "Url: ";

    public AudioAdapter(List<AudioItem> audioList) {
        this.audioList = audioList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.audio_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mAudioArtist.setText(mArtist + audioList.get(position).getArtist());
        holder.mAudioTitle.setText(mTitle + audioList.get(position).getTitle());
        holder.mAudioUrl.setText(mUrl + audioList.get(position).getUrl());
    }

    @Override
    public int getItemCount() {
        return audioList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mAudioArtist, mAudioTitle, mAudioUrl;

        public ViewHolder(View v) {
            super(v);
            mAudioArtist = (TextView) v.findViewById(R.id.audioArtist);
            mAudioTitle = (TextView) v.findViewById(R.id.audioTitle);
            mAudioUrl = (TextView) v.findViewById(R.id.audioUrl);
        }
    }

}