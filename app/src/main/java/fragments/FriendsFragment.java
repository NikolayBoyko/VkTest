package fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.developer.vktest.R;

import java.util.List;

import adapters.AudioAdapter;
import adapters.FriendsAdapter;
import api.Api;
import api.ResponseAudio;
import api.ResponseFriends;
import api.VkService;
import api.models.FriendItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import util.Util;

public class FriendsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private List<FriendItem> mFriendsList;
    private String mToken;

    public static FriendsFragment newInstance(Integer integer) {

        FriendsFragment friendsFragment = new FriendsFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", integer);
        friendsFragment.setArguments(args);
        return friendsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mToken = getmToken("KEY", getContext());
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        getFriends();
    }

    public void getFriends() {
        VkService service = Api.getClient().create(VkService.class);
        Call<ResponseFriends> responseFriendsCall = service.getFriends("random", "photo_100", mToken, Util.VK.VERSION);
        responseFriendsCall.enqueue(new Callback<ResponseFriends>() {
            @Override
            public void onResponse(Call<ResponseFriends> call, Response<ResponseFriends> response) {
                Log.d("TAG", " getFriends onResponse" + response.body().getFrindsResponse().getFriendList().get(0).toString());

                if (response.errorBody() == null) {
                    mFriendsList = response.body().getFrindsResponse().getFriendList();
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    FriendsAdapter mAdapter = new FriendsAdapter(getContext(),mFriendsList);
                    mRecyclerView.setAdapter(mAdapter);
                } else {
                    Toast.makeText(getContext(), "null", Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "mFriendsList = null ");
                }
            }

            @Override
            public void onFailure(Call<ResponseFriends> call, Throwable t) {
                Log.d("TAG", " getFriends onFailure");
            }
        });
    }

    public String getmToken(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

}
