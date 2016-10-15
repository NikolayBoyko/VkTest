package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.developer.vktest.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import api.Api;
import api.ResponseVk;
import api.VkService;
import api.models.Dialog;
import api.models.FriendItem;
import api.models.Message;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import util.Util;

public class DialogsAdapter extends RecyclerView.Adapter<DialogsAdapter.ViewHolder> {

    private List<Dialog> mDialogList;
    private String mUserPhoto = "photo_100";
    VkService service = Api.getClient().create(VkService.class);
    private Context mContext;

    public DialogsAdapter(Context context, List<Dialog> mDialogsList) {
        this.mDialogList = mDialogsList;
        this.mContext = context;
    }

    @Override
    public DialogsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_item, parent, false);
        return new DialogsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mLastMessage.setText(mDialogList.get(position).getMessage().getBody());
        holder.mDialogDate.setText(mDialogList.get(position).getMessage().getDate());

        Call<ResponseVk> responseVkCall = service.getUser(mDialogList.get(position).getMessage().getUserId(), mUserPhoto, Util.VK.VERSION);
        responseVkCall.enqueue(new Callback<ResponseVk>() {
            @Override
            public void onResponse(Call<ResponseVk> call, Response<ResponseVk> response) {

                Log.d("TAG", "onResponse " + response.body().getListUser().get(0).getFirstName());
                holder.mDialogFirstName.setText(response.body().getListUser().get(0).getFirstName());
                holder.mDialogSecondName.setText(response.body().getListUser().get(0).getLastName());
                Picasso.with(mContext)
                        .load(response.body().getListUser().get(0).getPhoto())
                        .placeholder(R.drawable.photo_user_50x50)
                        .into(holder.mDialogFriendPhoto);
            }

            @Override
            public void onFailure(retrofit2.Call<ResponseVk> call, Throwable t) {
                Log.d("TAG", "onFailure" + t);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDialogList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mDialogFirstName, mDialogSecondName, mDialogDate, mLastMessage;
        private ImageView mDialogFriendPhoto;

        public ViewHolder(View itemView) {
            super(itemView);
            mDialogFirstName = (TextView) itemView.findViewById(R.id.dialog_first_name);
            mDialogSecondName = (TextView) itemView.findViewById(R.id.dialog_last_name);
            mLastMessage = (TextView) itemView.findViewById(R.id.dialog_last_message);
            mDialogDate = (TextView) itemView.findViewById(R.id.dialog_date);
            mDialogFriendPhoto = (ImageView) itemView.findViewById(R.id.dialog_image);
        }
    }
}