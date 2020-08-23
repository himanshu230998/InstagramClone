package tabian.com.instagramclone2.Utils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import org.jetbrains.annotations.NotNull;

import tabian.com.instagramclone2.Home.CameraFragment;
import tabian.com.instagramclone2.Home.HomeFragment;
import tabian.com.instagramclone2.Home.MessagesFragment;

public class SectionsPagerAdapterforMainActivity extends FragmentStateAdapter {

    public SectionsPagerAdapterforMainActivity(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NotNull
    @Override
public Fragment createFragment(int position) {

    if (position == 0) {
        return new CameraFragment();
    }
    else if(position == 1)
    {
        return new HomeFragment();
    }
    else {
        return new MessagesFragment();
    }

}

    @Override
    public int getItemCount() {
        return 3;
    }
}
