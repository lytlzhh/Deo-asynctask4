package com.example.llw.deo_asynctask4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.image);
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MYasynctask mYasynctask = new MYasynctask();
                mYasynctask.execute("http://tu.114la.com/static/dlimg/4/45/f5010e1b39dfc5778090c721246a3324_b.jpg" ,"http://tu.114la.com/static/dlimg/3/44/a9c11aeb913b496d9657e15376a73fbf_b.jpg",
                        "http://tu.114la.com/static/dlimg/6/47/b863b1c767fa389907c4a1b82a742898_b.jpg");
            }
        });
    }


    class  MYasynctask extends AsyncTask<String,Integer,Bitmap>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            System.out.println("you can download picturic from net!!!");
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            URL url = null;
            Bitmap bitmap = null;
            try {
                url = new URL(params[0]);
               URLConnection urlConnection  = url.openConnection();
                InputStream inputStream =  urlConnection.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);

               // inputStream.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return bitmap;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

        }


        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
                imageView.setImageBitmap(bitmap);
        }

        @Override
        protected void onCancelled(Bitmap bitmap) {
            super.onCancelled(bitmap);
        }
    }

}
