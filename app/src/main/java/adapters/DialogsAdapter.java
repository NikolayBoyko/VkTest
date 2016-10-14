package adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.developer.vktest.R;

import java.util.List;

import api.models.Message;

public class DialogsAdapter extends RecyclerView.Adapter<DialogsAdapter.ViewHolder> {

    private List<Message> mMessageList;

    public DialogsAdapter(List<Message> mMessageList) {
        this.mMessageList = mMessageList;
    }

    @Override
    public DialogsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_item, parent, false);
        return new DialogsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mDialogFirstName, mDialogSecondName, mDialogDate;
        private ImageView mDialogFriendPhoto;

        public ViewHolder(View itemView) {
            super(itemView);
            mDialogFirstName = (TextView) itemView.findViewById(R.id.dialog_first_name);
            mDialogSecondName = (TextView) itemView.findViewById(R.id.dialog_last_name);
            mDialogDate = (TextView) itemView.findViewById(R.id.dialog_date);
            mDialogFriendPhoto = (ImageView) itemView.findViewById(R.id.dialog_image);
        }

    }

}