package nl.hu.zrb.movieapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by JZuurbier on 12-5-2017.
 */

public class ImageLoader extends AsyncTask <String, Void, Bitmap>{
    ImageView imageView;

    ImageLoader(ImageView iv){
        this.imageView = iv;
    }

    @Override
    protected Bitmap doInBackground(String... params) {

        //todo
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap b){
        if (b != null){

        }
    }


}
