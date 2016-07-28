package com.developer.sparsh.unzipapplication;

import android.os.AsyncTask;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by sparsh on 28/7/16.
 */
public class UnzipTask extends AsyncTask<String , Void , Void> {

    private static final String TAG = UnzipTask.class.getSimpleName();

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(TAG, "onPreExecute: onPreExecute Called");
    }

    @Override
    protected Void doInBackground(String... strings) {

        final String path = strings[0];
        final String destination = strings[1];

        Log.d(TAG, "doInBackground: " + path);

        final File file = new File(path);
        final File destinationDir = new File(destination);


        if(file.exists() && file.isFile()){
            try {
                InputStream inputStream = new FileInputStream(file);
                ZipInputStream zipInputStream = new ZipInputStream(inputStream);

                byte buffer[] = new byte[4096];
                int bytesRead;

                ZipEntry zipEntry = null;
                while((zipEntry = zipInputStream.getNextEntry())!=null){
                    if(!destinationDir.exists())
                        destinationDir.mkdir();

                    if (zipEntry.isDirectory()){
                        File directory = new File(destinationDir , zipEntry.getName());
                        if(!directory.exists()){
                            directory.mkdirs();
                        }
                        Log.d(TAG, "[DIR] "+zipEntry.getName());
                    }else {
                        File destinationFile = new File(destinationDir , zipEntry.getName());
                        FileOutputStream fileOutputStream = new FileOutputStream(destinationFile);

                        while((bytesRead = zipInputStream.read(buffer)) != -1){
                            fileOutputStream.write(buffer , 0 ,bytesRead);
                        }
                        fileOutputStream.close();
                        Log.d(TAG, "[FILE] "+zipEntry.getName());
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.d(TAG, "onPostExecute: " + "task completed");
    }
}
