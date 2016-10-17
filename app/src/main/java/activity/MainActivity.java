package activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.developer.vktest.R;
import com.squareup.picasso.Picasso;

import api.Api;
import api.ResponseVk;
import api.VkService;
import fragments.AudioFragment;
import fragments.FriendsFragment;
import fragments.DialogsOverallFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private FragmentTransaction fragmentTransaction;
    private String token;
    private TextView nav_name;
    private TextView nav_second_name;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onContentChanged() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getUser();
        token = getmValue("token", this);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );

        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View hView = navigationView.getHeaderView(0);
        nav_name = (TextView) hView.findViewById(R.id.drawer_header_textView_name);
        nav_second_name = (TextView) hView.findViewById(R.id.drawer_header_textView_secondName);
        mImageView = (ImageView) hView.findViewById(R.id.drawer_header_imageView);

        super.onContentChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        } else super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Audio:
                replaceFragment(AudioFragment.newInstance(1));
                Toast.makeText(this, "Click on Android", Toast.LENGTH_SHORT).show();
                break;

            case R.id.Friends:
                replaceFragment(FriendsFragment.newInstance(2));
                Toast.makeText(this, "Click on Java", Toast.LENGTH_SHORT).show();
                break;

            case R.id.Dialogs:
                replaceFragment(DialogsOverallFragment.newInstance(3));
                Toast.makeText(this, "Click on Settings", Toast.LENGTH_SHORT).show();
                break;

            case R.id.about:
                Toast.makeText(this, "Click on About", Toast.LENGTH_SHORT).show();
                break;
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void replaceFragment(Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.main_content, fragment).commit();
    }

    public String getmValue(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

   public void getUser() {
       VkService service = Api.getClient().create(VkService.class);
        Call<ResponseVk> responseVkCall = service.getUser("133508072", "bdate,photo_200", "5.53");
        responseVkCall.enqueue(new Callback<ResponseVk>() {
            @Override
            public void onResponse(Call<ResponseVk> call, Response<ResponseVk> response) {
                nav_name.setText(response.body().getListUser().get(0).getFirstName());
                nav_second_name.setText(response.body().getListUser().get(0).getLastName());
                Picasso.with(getBaseContext())
                        .load(response.body().getListUser().get(0).getPhoto_200())
                        .placeholder(R.drawable.drawer_header_image)
                        .into(mImageView);
            }

            @Override
            public void onFailure(retrofit2.Call<ResponseVk> call, Throwable t) {
                Log.d("TAG", "onFailure" + t);
            }

        });

    }

}