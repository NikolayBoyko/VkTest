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

import com.example.developer.vktest.R;

import java.io.IOException;

import api.VkService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import util.Util;

public class SplashActivity extends Activity {

    private String mToken;
    private String BASE_URL = "https://oauth.vk.com/access_token";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        mToken = getmToken("KEY", this);
        Log.d("TAG", "onCreate mToken " + mToken);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mToken != null && !TextUtils.equals("", mToken)) {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    intent.putExtra("token", mToken);
                    startActivity(intent);
                    finish();
                } else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }
            }
        }, 2000);
    }

    public void getClientToken() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://oauth.vk.com/access_token&client_id=5610917&client_secret=so4s92L8QlsVSUqi841y&v=5.58&grant_type=client_credentials")
                .build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Log.d("TAG", "onFailure");
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                Log.d("TAG", "onResponse " + response.toString());
            }
        });
    }

    public String getmToken(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

}


