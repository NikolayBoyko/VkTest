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

import api.Api;
import api.ResponseAudio;
import api.VkService;
import adapters.AudioAdapter;
import api.models.AudioItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AudioFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private AudioAdapter mAdapter;
    private String mToken;

    private List<AudioItem> mAudioList;

    public static AudioFragment newInstance(Integer integer) {

        AudioFragment mainListQuestionFragment = new AudioFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", integer);
        mainListQuestionFragment.setArguments(args);
        return mainListQuestionFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mToken = getmToken("KEY", getContext());
        Log.d("TAG", "mToken " + mToken);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        getAudio();
    }

    public void getAudio() {

        VkService service = Api.getClient().create(VkService.class);
        Call<ResponseAudio> responseAudioVkCall = service.getAudio("19393076", "15", mToken, "5.53");
        responseAudioVkCall.enqueue(new Callback<ResponseAudio>() {
            @Override
            public void onResponse(Call<ResponseAudio> call, Response<ResponseAudio> response) {

                Log.d("TAG", "3 responseAudio " + response.body().getResponse().getAudioList().toString());
                mAudioList = response.body().getResponse().getAudioList();
                Log.d("TAG", "mAudioList " + mAudioList);

                mLayoutManager = new LinearLayoutManager(getContext());
                mRecyclerView.setLayoutManager(mLayoutManager);

                if (mAudioList != null) {
                    mAdapter = new AudioAdapter(mAudioList);
                    mRecyclerView.setAdapter(mAdapter);
                    Log.d("TAG", "mAudioList != null ");
                } else {
                    Log.d("TAG", "mAudioList = null ");
                }

            }

            @Override
            public void onFailure(Call<ResponseAudio> call, Throwable t) {
                Log.d("TAG", "onFailure responseAudioVkCall " + t.getMessage());
            }
        });
    }

    public String getmToken(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

}