package nl.hu.zrb.movieapp;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.method.BaseKeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText editText;
    MoviesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mAdapter = new MoviesAdapter();
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        editText = (EditText) findViewById(R.id.edittext1);
        editText.setOnKeyListener(new View.OnKeyListener(){


            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event){
                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction()== KeyEvent.ACTION_DOWN){
                    String text = editText.getText().toString();
                    text = "http://www.omdbapi.com/?s="+text;
                    mAdapter.clear();
                    new LoadInBackGround().execute(text.toString());
                    return true;
                }
                return false;
            }
        });

    }

    private class LoadInBackGround extends AsyncTask<String, Void, String >{
        protected String doInBackground(String... urls){
            StringBuffer sb = new StringBuffer();
            try {
                URL theUrl = new URL(urls[0]);
                HttpURLConnection con = (HttpURLConnection)theUrl.openConnection();
                con.setRequestProperty("Connection", "close");
                InputStream in = con.getInputStream();

                int i;
                while((i = in.read())!= -1){
                    sb.append((char)i);
                }

            }
            catch(IOException ioe){
                Log.e("MovieApp", ioe.toString());
            }
            finally {
                return sb.toString();
            }

        }

        protected void onPostExecute(String s){
            try {
                JSONObject obj = new JSONObject(s);
                if(!obj.has("Search")) return;
                JSONArray arr = obj.getJSONArray("Search");
                for (int i = 0; i < arr.length(); i++) {
                    Movie m = new Movie();
                    JSONObject mobj = arr.getJSONObject(i);
                    m.posterUrl = mobj.get("Poster").toString();
                    m.title = mobj.get("Title").toString();
                    m.year = mobj.get("Year").toString();
                    mAdapter.add(m);
                }
                            }
            catch(JSONException e){
                Log.e("MovieApp", s, e);
            }
        }

    }
}
