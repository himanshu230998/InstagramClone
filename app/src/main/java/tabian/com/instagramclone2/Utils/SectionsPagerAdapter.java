package tabian.com.instagramclone2.Utils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import org.jetbrains.annotations.NotNull;

import tabian.com.instagramclone2.Share.GalleryFragment;
import tabian.com.instagramclone2.Share.PhotoFragment;

public class SectionsPagerAdapter extends FragmentStateAdapter {

    public SectionsPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NotNull
    @Override
    public Fragment createFragment(int position) {

       if (position == 0) {
           return new GalleryFragment();
        }
       else {
           return new PhotoFragment();
       }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
