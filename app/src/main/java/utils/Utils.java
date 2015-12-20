package utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by max on 18/12/15.
 */
public class Utils {

    public static Bitmap loadImageFromNetwork(String url)
    {
        try {
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(url).getContent());
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void displayAlertDialog(Context context, String title, String message)
    {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, null)
                .show();
    }

    public static ProgressDialog displayProgressBarDialog(Context context, String message)
    {
        ProgressDialog progressDialog = new ProgressDialog(context);

        progressDialog.setMessage(message);
        progressDialog.setIndeterminate(false);
        progressDialog.setMax(100);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);
        progressDialog.show();

        return progressDialog;
    }
}
