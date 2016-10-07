package activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.example.developer.vktest.R;

import api.Api;
import api.ResponseVk;
import api.VkService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends Activity {

    private String mToken;
    private String mfirsName;
    private TextView mNameView;
    VkService service = Api.getClient().create(VkService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        mToken = getmToken("KEY", this);
        Log.d("TAG", "onCreate mToken " + mToken);
        getUser();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (mToken != null && !TextUtils.equals("", mToken)) {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    intent.putExtra("token", mToken);
                    intent.putExtra("name", mfirsName);
                    startActivity(intent);
                    finish();

                } else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }
            }
        }, 2000);
    }

    public String getmToken(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

    private void saveData(String key, String data) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, data).apply();
    }

    public void getUser() {
        Call<ResponseVk> responseVkCall = service.getUser("133508072", "bdate", "5.53");
        responseVkCall.enqueue(new Callback<ResponseVk>() {
            @Override
            public void onResponse(Call<ResponseVk> call, Response<ResponseVk> response) {
                mfirsName = response.body().getListUser().get(0).getFirstName();
                saveData("name",mfirsName);
                Log.d("TAG", "onResponse" + response.body().getListUser().get(0).getFirstName());
            }

            @Override
            public void onFailure(retrofit2.Call<ResponseVk> call, Throwable t) {
                Log.d("TAG", "onFailure" + t);
            }
        });
    }
}


