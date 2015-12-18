package threads;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import utils.Utils;

/**
 * Created by max on 18/12/15.
 */
public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

    private ImageView imageView;

    public DownloadImageTask (ImageView imageView)
    {
        this.imageView = imageView;
    }

    protected Bitmap doInBackground(String... urls)
    {
        return Utils.loadImageFromNetwork(urls[0]);
    }

    protected void onPostExecute(Bitmap result)
    {
        if (result != null) { imageView.setImageBitmap(result); }
    }
}