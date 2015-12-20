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
    private DownloadImageTask imageAsyncTask;

    private final static String SMALL_IMAGE_URL = "https://i.ytimg.com/vi/BV_d7RDYdzw/maxresdefault.jpg";
    private final static String BIG_IMAGE_URL = "https://upload.wikimedia.org/wikipedia/commons/d/d6/Hawaii-Big-Island-TF.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ImageView) findViewById(R.id.ivLoadedImage);

        findViewById(R.id.btnLoadSmallImage).setOnClickListener(this);
        findViewById(R.id.btnLoadBigImage).setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnLoadSmallImage:
                imageAsyncTask = new DownloadImageTask(mImageView, this);
                imageAsyncTask.execute(SMALL_IMAGE_URL);
                break;
            case R.id.btnLoadBigImage:
                imageAsyncTask = new DownloadImageTask(mImageView, this);
                imageAsyncTask.execute(BIG_IMAGE_URL);
                break;
        }
    }

    private void imageThread()
    {
        new Thread(new Runnable() {
            public void run() {
                final Bitmap bitmap = Utils.loadImageFromNetwork(BIG_IMAGE_URL);
                mImageView.post(new Runnable() {
                    public void run() {
                        mImageView.setImageBitmap(bitmap);
                    }
                });
            }
        }).start();
    }
}
