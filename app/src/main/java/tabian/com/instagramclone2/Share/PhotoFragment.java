package tabian.com.instagramclone2.Share;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import tabian.com.instagramclone2.Profile.AccountSettingsActivity;
import tabian.com.instagramclone2.R;
import tabian.com.instagramclone2.Utils.Permissions;

public class PhotoFragment extends Fragment {
    private static final String TAG = "PhotoFragment";

    //constant
    private static final int PHOTO_FRAGMENT_NUM = 1;
    private static final int  CAMERA_REQUEST_CODE = 5;


    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo, container, false);
        Log.d(TAG, "hims onCreateView: started.");

        Button btnLaunchCamera = (Button) view.findViewById(R.id.btnLaunchCamera);
        btnLaunchCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "hims onClick: launching camera.");

                if(((ShareActivity) requireActivity()).getCurrentTabNumber() == PHOTO_FRAGMENT_NUM){
                    if(((ShareActivity) requireActivity()).checkPermissions(Permissions.CAMERA_PERMISSION[0])){
                        Log.d(TAG, "hims onClick: starting camera");
                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
                    }else{
                        Intent intent = new Intent(getActivity(), ShareActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                }
            }
        });

        return view;
    }

    private boolean isRootTask(){
        return ((ShareActivity) requireActivity()).getTask() == 0;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAMERA_REQUEST_CODE){
            Log.d(TAG, "hims onActivityResult: done taking a photo.");
            Log.d(TAG, "hims onActivityResult: attempting to navigate to final share screen.");

            Bitmap bitmap;
            bitmap = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");

            if(isRootTask()){
                try{
                    Log.d(TAG, "hims onActivityResult: received new bitmap from camera: " + bitmap);
                    Intent intent = new Intent(getActivity(), NextActivity.class);
                    intent.putExtra(getString(R.string.selected_bitmap), bitmap);
                    startActivity(intent);
                }catch (NullPointerException e){
                    Log.d(TAG, "hims onActivityResult: NullPointerException: " + e.getMessage());
                }
            }else{
               try{
                   Log.d(TAG, "hims onActivityResult: received new bitmap from camera: " + bitmap);
                   Intent intent = new Intent(getActivity(), AccountSettingsActivity.class);
                   intent.putExtra(getString(R.string.selected_bitmap), bitmap);
                   intent.putExtra(getString(R.string.return_to_fragment), getString(R.string.edit_profile_fragment));
                   startActivity(intent);
                   requireActivity().finish();
               }catch (NullPointerException e){
                   Log.d(TAG, "hims onActivityResult: NullPointerException: " + e.getMessage());
               }
            }

        }
    }
}
