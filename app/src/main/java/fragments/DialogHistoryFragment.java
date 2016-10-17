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

import adapters.DialogsAdapter;
import adapters.DialogsHistoryAdapter;
import api.Api;
import api.ResponseDialogHistory;
import api.VkService;
import api.models.Dialog;
import api.models.DialogHistoryMessage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import util.Util;

public class DialogHistoryFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private List<DialogHistoryMessage> mMessageList;
    private String mToken;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String mUserId;

    public static DialogHistoryFragment newInstance(Integer integer, String userId) {

        DialogHistoryFragment dialogFragment = new DialogHistoryFragment();
        mUserId = userId;
        Bundle args = new Bundle();
        args.putInt("someInt", integer);
        dialogFragment.setArguments(args);
        return dialogFragment;
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
        getDialogHistory();
    }

    public void getDialogHistory() {
        VkService service = Api.getClient().create(VkService.class);
        Call<ResponseDialogHistory> responseDialogHistoryCall = service.getDialogHistory(mUserId, mToken, Util.VK.VERSION);
        responseDialogHistoryCall.enqueue(new Callback<ResponseDialogHistory>() {
            @Override
            public void onResponse(Call<ResponseDialogHistory> call, Response<ResponseDialogHistory> response) {
                if (response.errorBody() == null) {
                    mMessageList = response.body().getDialogHistoryResponse().getDialogHIstoryList();
                    mLayoutManager = new LinearLayoutManager(getContext());
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    DialogsHistoryAdapter mAdapter = new DialogsHistoryAdapter(mMessageList, getContext());
                    mRecyclerView.setAdapter(mAdapter);
                } else {
                    Toast.makeText(getContext(), "null", Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "mAudioList = null ");
                }
            }

            @Override
            public void onFailure(Call<ResponseDialogHistory> call, Throwable t) {

            }
        });

    }

    public String getmToken(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

}
