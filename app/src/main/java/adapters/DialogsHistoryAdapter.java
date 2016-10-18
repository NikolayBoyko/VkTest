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
import api.models.DialogHistoryMessage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import util.Util;

public class DialogsHistoryAdapter extends RecyclerView.Adapter<DialogsHistoryAdapter.ViewHolder> {

    private List<DialogHistoryMessage> mMessageList;
    VkService service = Api.getClient().create(VkService.class);
    private String mUserPhoto = "photo_100";
    private Context mContext;

    public DialogsHistoryAdapter(List<DialogHistoryMessage> mMessageList, Context mContext) {
        this.mMessageList = mMessageList;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_item, parent, false);
        return new DialogsHistoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mDialogDate.setText(mMessageList.get(position).getmDate());
        holder.mDialogBody.setText(mMessageList.get(position).getmBody());
        Call<ResponseVk> responseVkCall = service.getUser(mMessageList.get(position).getmFrom_id(), mUserPhoto, Util.VK.VERSION);
        responseVkCall.enqueue(new Callback<ResponseVk>() {
            @Override
            public void onResponse(Call<ResponseVk> call, Response<ResponseVk> response) {
                holder.mDialogFirstName.setText(response.body().getListUser().get(0).getFirstName());
                Picasso.with(mContext)
                        .load(response.body().getListUser().get(0).getPhoto_100())
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
        return mMessageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mDialogFirstName, mDialogDate, mDialogBody;
        private ImageView mDialogFriendPhoto;

        public ViewHolder(View itemView) {
            super(itemView);
            mDialogFirstName = (TextView) itemView.findViewById(R.id.dialog_first_name);
            mDialogDate = (TextView) itemView.findViewById(R.id.dialog_date);
            mDialogBody = (TextView) itemView.findViewById(R.id.dialog_body);
            mDialogFriendPhoto = (ImageView) itemView.findViewById(R.id.dialog_image);
        }
    }
}