package tabian.com.instagramclone2.Utils;

import android.content.Context;
import android.os.Environment;

import tabian.com.instagramclone2.Share.GalleryFragment;

public class FilePaths {


    public static String FilePath(GalleryFragment context){
        String PICTURES = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getPath();
        String CAMERA =context.getExternalFilesDir(Environment.DIRECTORY_DCIM).getPath();


        String FIREBASE_STORY_STORAGE = "stories/users";
        String FIREBASE_IMAGE_STORAGE = "photos/users/";}

}