package com.example.lbltakefive.Databases;


import android.os.Environment;
import android.widget.Toast;
import com.example.lbltakefive.R;
import com.example.lbltakefive.Takefivepage2;
import java.io.File;


public class LocalbackUp {

    private Takefivepage2 activity;

    public LocalbackUp(Takefivepage2 activity) {
        this.activity = activity;
    }



    //ask to the user a name for the backup and perform it. The backup will be saved to a custom folder.
    public void performBackup(final DatabaseHelper db, final String outFileName) {

        File folder = new File(Environment.getExternalStorageDirectory() + File.separator + activity.getResources().getString(R.string.app_name));

        boolean success = true;
        if (!folder.exists())
            success = folder.mkdirs();
        if (success) {
                String out = outFileName + ".db";
                db.backup(out);
        } else
            Toast.makeText(activity, "Unable to create directory. Retry", Toast.LENGTH_SHORT).show();
    }

}
