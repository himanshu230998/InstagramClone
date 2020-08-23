package tabian.com.instagramclone2.Share;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import tabian.com.instagramclone2.R;
import tabian.com.instagramclone2.Utils.BottomNavigationViewHelper;
import tabian.com.instagramclone2.Utils.Permissions;
import tabian.com.instagramclone2.Utils.SectionsPagerAdapter;

public class ShareActivity extends AppCompatActivity{
    private static final String TAG = "ShareActivity";
    public static final int BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT = 1;
    private static final int ACTIVITY_NUM = 2;
    private static final int VERIFY_PERMISSIONS_REQUEST = 1;
    private ViewPager2 mViewPager;
    private Context mContext = ShareActivity.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        Log.d(TAG, "hims onCreate: ShareActivity started.");

        if(checkPermissionsArray(Permissions.PERMISSIONS)){
            setupViewPager();
        }else{
            verifyPermissions(Permissions.PERMISSIONS);
        }
    }

    public int getCurrentTabNumber(){
        return mViewPager.getCurrentItem();
    }

    private void setupViewPager(){
        SectionsPagerAdapter adapter =  new SectionsPagerAdapter(this);
        mViewPager = findViewById(R.id.viewpager_container);
        mViewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabsBottom);
      //  tabLayout.setupWithViewPager(mViewPager);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, mViewPager, true, new TabLayoutMediator.TabConfigurationStrategy() {
            public void onConfigureTab(@NotNull TabLayout.Tab tab, int position) {
                // position of the current tab and that tab
            }
        });
        tabLayoutMediator.attach();

        Objects.requireNonNull(tabLayout.getTabAt(0)).setText(getString(R.string.gallery));
        Objects.requireNonNull(tabLayout.getTabAt(1)).setText(getString(R.string.photo));
    }

    public int getTask(){
        Log.d(TAG, "hims getTask: TASK: " + getIntent().getFlags());
        return getIntent().getFlags();
    }

    public void verifyPermissions(String[] permissions){
        Log.d(TAG, "hims verifyPermissions: verifying permissions.");

        ActivityCompat.requestPermissions(
                ShareActivity.this,
                permissions,
                VERIFY_PERMISSIONS_REQUEST
        );
    }

    public boolean checkPermissionsArray(String[] permissions){
        Log.d(TAG, "hims checkPermissionsArray: checking permissions array.");

        for (String check : permissions) {
            if (!checkPermissions(check)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkPermissions(String permission){
        Log.d(TAG, "hims checkPermissions: checking permission: " + permission);

        int permissionRequest = ActivityCompat.checkSelfPermission(ShareActivity.this, permission);

        if(permissionRequest != PackageManager.PERMISSION_GRANTED){
            Log.d(TAG, "hims checkPermissions: \n Permission was not granted for: " + permission);
            return false;
        }
        else{
            Log.d(TAG, "hims checkPermissions: \n Permission was granted for: " + permission);
            return true;
        }
    }


    private void setupBottomNavigationView(){
        Log.d(TAG, "hims setupBottomNavigationView: setting up BottomNavigationView in ShareActivity");
        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, this,bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}
