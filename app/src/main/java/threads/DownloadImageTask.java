package threads;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import utils.Utils;

/**
 * Created by max on 18/12/15.
 */
public class DownloadImageTask extends AsyncTask<String, String, Bitmap> {

    private Activity activity;
    private ImageView imageView;
    private ProgressDialog progressDialog;

    public DownloadImageTask (ImageView imageView, Activity activity)
    {
        this.imageView = imageView;
        this.activity = activity;
    }

    protected void onPreExecute()
    {
        progressDialog = Utils.displayProgressBarDialog(activity, "Downloading file. Please wait...");
    }

    protected Bitmap doInBackground(String... urls)
    {
        long total = 0;
        byte data[] = new byte[1024];
        int bytesRead;

        ByteArrayOutputStream getBytes = new ByteArrayOutputStream();

        try
        {
            URL url = new URL(urls[0]);
            URLConnection con = url.openConnection();

            InputStream inputStream = (InputStream) url.getContent();

            int lenghtOfFile = con.getContentLength();

            while ((bytesRead = inputStream.read(data)) != -1)
            {
                getBytes.write(data, 0, bytesRead);
                total += bytesRead;
                publishProgress(String.valueOf(total * 100 / lenghtOfFile));
            }

            Bitmap bitmap = BitmapFactory.decodeByteArray(getBytes.toByteArray(), 0, lenghtOfFile);

            return bitmap;

        } catch(Exception e) { return null; }
    }

    protected void onProgressUpdate(String... progress)
    {
        // setting progress percentage
        progressDialog.setProgress(Integer.parseInt(progress[0]));
    }

    protected void onPostExecute(Bitmap result)
    {
        progressDialog.dismiss();

        if (result == null)
        {
            Utils.displayAlertDialog(activity, "Error!", "Failed to download the image.");
            return;
        }

        imageView.setImageBitmap(result);
    }
}