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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.developer.vktest.R;

import java.util.List;

import adapters.DialogsAdapter;
import adapters.DialogsHistoryAdapter;
import api.Api;
import api.ResponseDialogHistory;
import api.ResponseSendMessage;
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
    private static String mUserId;
    private VkService service;
    private Button mButtonSendMessage;
    private EditText mEditTextMessage;

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
        return inflater.inflate(R.layout.chat, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mToken = getmToken("KEY", getContext());
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_dialog_list);
        mButtonSendMessage = (Button) view.findViewById(R.id.send_message);
        mEditTextMessage = (EditText) view.findViewById(R.id.message);
        mButtonSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(mUserId, mEditTextMessage.getText().toString(), mToken, Util.VK.VERSION);
                mEditTextMessage.setText("");
                getDialogHistory();
            }
        });
        getDialogHistory();
    }

    public void getDialogHistory() {
        service = Api.getClient().create(VkService.class);
        Call<ResponseDialogHistory> responseDialogHistoryCall = service.getDialogHistory(mUserId, mToken, Util.VK.VERSION);
        responseDialogHistoryCall.enqueue(new Callback<ResponseDialogHistory>() {
            @Override
            public void onResponse(Call<ResponseDialogHistory> call, Response<ResponseDialogHistory> response) {
                if (response.errorBody() == null) {
                    mMessageList = response.body().getDialogHistoryResponse().getDialogHIstoryList();
                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                    mLayoutManager.setReverseLayout(true);
                    mLayoutManager.setStackFromEnd(true);
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

    public void sendMessage(String userId, String message, String token, String version) {
        service = Api.getClient().create(VkService.class);
        Call<ResponseSendMessage> responseSendMessageCall = service.sendMessage(userId, message, token, version);
        responseSendMessageCall.enqueue(new Callback<ResponseSendMessage>() {
            @Override
            public void onResponse(Call<ResponseSendMessage> call, Response<ResponseSendMessage> response) {
                Log.d("TAG", " sendMessage onResponse " + response.toString());
            }

            @Override
            public void onFailure(Call<ResponseSendMessage> call, Throwable t) {
                Log.d("TAG", " sendMessage onFailure " + t);
            }
        });
    }

    public String getmToken(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

}
