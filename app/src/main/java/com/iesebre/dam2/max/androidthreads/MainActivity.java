package com.iesebre.dam2.max.androidthreads;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import threads.DownloadImageTask;
import utils.Utils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImageView;
    private final static String IMAGE_URL = "https://i.ytimg.com/vi/BV_d7RDYdzw/maxresdefault.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ImageView) findViewById(R.id.ivLoadedImage);
        findViewById(R.id.btnLoadImage).setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnLoadImage:

                //imageThread();

                DownloadImageTask imageAsyncTask = new DownloadImageTask(mImageView);
                imageAsyncTask.execute(IMAGE_URL);

                break;
        }
    }

    private void imageThread()
    {
        new Thread(new Runnable() {
            public void run() {
                final Bitmap bitmap = Utils.loadImageFromNetwork(IMAGE_URL);
                mImageView.post(new Runnable() {
                    public void run() {
                        mImageView.setImageBitmap(bitmap);
                    }
                });
            }
        }).start();
    }
}
