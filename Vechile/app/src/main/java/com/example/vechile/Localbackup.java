package com.example.vechile;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;

public class Localbackup {

    Context context;

    public Localbackup(Context context){
        this.context = context;
    }

    public void performBackup(final DatabaseHelper db, final String outFileName) {

        File folder = new File(Environment.getExternalStorageDirectory() + File.separator + context.getResources().getString(R.string.app_name));

        boolean success = true;
        if (!folder.exists())
            success = folder.mkdirs();
        if (success) {
            String out = outFileName + ".db";
            db.backup(out);
        } else
            Toast.makeText(context, "Unable to create directory. Retry", Toast.LENGTH_SHORT).show();
    }
}
