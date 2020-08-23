package tabian.com.instagramclone2.Home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import tabian.com.instagramclone2.R;


public class CameraFragment extends Fragment {
    private static final String TAG = "CameraFragment";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"setup CameraFragment");
        return inflater.inflate(R.layout.fragment_camera, container, false);
    }
}
