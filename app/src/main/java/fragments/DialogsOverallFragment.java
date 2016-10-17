package fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import api.Api;
import api.models.Dialog;
import api.ResponseDialogs;
import api.VkService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import util.RecyclerItemClickListener;
import util.Util;

public class DialogsOverallFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private List<Dialog> mDialogsList;
    private String mToken;
    private RecyclerView.LayoutManager mLayoutManager;
    private DialogsAdapter mAdapter;

    public static DialogsOverallFragment newInstance(Integer integer) {

        DialogsOverallFragment dialogsOverallFragment = new DialogsOverallFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", integer);
        dialogsOverallFragment.setArguments(args);
        return dialogsOverallFragment;
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
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                replaceFragment(DialogHistoryFragment.newInstance(position, mDialogsList.get(position).getMessage().getUserId()));
            }
        }));
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        getDialogsList();
    }

    public void getDialogsList() {
        VkService service = Api.getClient().create(VkService.class);
        Call<ResponseDialogs> responseDialogsCall = service.getDialogs(mToken, Util.VK.VERSION);
        responseDialogsCall.enqueue(new Callback<ResponseDialogs>() {
            @Override
            public void onResponse(Call<ResponseDialogs> call, Response<ResponseDialogs> response) {
                if (response.errorBody() == null) {
                    mDialogsList = response.body().getDialogsResponse().getDialogsList();
                    mLayoutManager = new LinearLayoutManager(getContext());
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    mAdapter = new DialogsAdapter(getContext(), mDialogsList);
                    mRecyclerView.setAdapter(mAdapter);
                } else {
                    Toast.makeText(getContext(), "null", Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "mAudioList = null ");
                }
            }

            @Override
            public void onFailure(Call<ResponseDialogs> call, Throwable t) {
                Log.d("TAG", "onFailure " + t.toString());
            }
        });
    }

    public String getmToken(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.main_content, fragment).commit();
    }
}