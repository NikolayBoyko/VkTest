package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.developer.vktest.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import api.models.FriendItem;

import static java.lang.System.load;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ViewHolder> {

    private List<FriendItem> mFrienList;
    private Context mContext;

    public FriendsAdapter(Context context, List<FriendItem> mFrienList) {
        this.mFrienList = mFrienList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mFiendFirstName.setText(mFrienList.get(position).getFirstName());
        holder.mFriendSecondNAme.setText(mFrienList.get(position).getLastName());
        Picasso.with(mContext)
                .load(mFrienList.get(position).getPhoto())
                .placeholder(R.drawable.photo_user_50x50)
                .into(holder.mFriendPhoto);
    }

    @Override
    public int getItemCount() {
        return mFrienList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mFiendFirstName, mFriendSecondNAme;
        private ImageView mFriendPhoto;

        public ViewHolder(View itemView) {
            super(itemView);
            mFiendFirstName = (TextView) itemView.findViewById(R.id.friend_first_name);
            mFriendSecondNAme = (TextView) itemView.findViewById(R.id.friend_second_name);
            mFriendPhoto = (ImageView) itemView.findViewById(R.id.friend_photo);
        }
    }
}