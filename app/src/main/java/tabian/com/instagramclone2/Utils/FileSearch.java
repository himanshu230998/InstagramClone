package tabian.com.instagramclone2.Utils;

import java.io.File;
import java.util.ArrayList;

public class FileSearch {

    public static ArrayList<String> getDirectoryPaths(String directory){
        ArrayList<String> pathArray = new ArrayList<>();
        File file = new File(directory);
        File[] l = file.listFiles();

        assert l != null;
        for (int p = 0; p < l.length; p++) {
            if (l[p].isDirectory()) {
                pathArray.add(l[p].getAbsolutePath());
            }
        }
        return pathArray;
    }

    public static ArrayList<String> getFilePaths(String directory){
        ArrayList<String> pathArray = new ArrayList<>();
        File file = new File(directory);
        File[] l = file.listFiles();
        assert l != null;
        for (int p = 0; p < l.length; p++ ){
            if (l[p].isFile()) {
                pathArray.add(l[p].getAbsolutePath());
            }
        }
        return pathArray;
    }
}
