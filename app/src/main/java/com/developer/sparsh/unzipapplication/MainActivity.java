package com.developer.sparsh.unzipapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button bUnzip = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bUnzip = (Button) findViewById(R.id.bUnzip);
        final String zipPath = "/storage/sdcard1/voice_instructions.zip";
        final String destinationDir = "/storage/emulated/0/Unzipped";
        bUnzip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new UnzipTask().execute(zipPath , destinationDir);
            }
        });

    }
}
