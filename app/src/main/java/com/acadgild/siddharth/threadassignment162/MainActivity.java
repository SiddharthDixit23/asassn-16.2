package com.acadgild.siddharth.threadassignment162;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    Button btn,btn2;
    ProgressBar mb1,mb2,mb3,mb4;
    ImageView miv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.button);
        mb1 = (ProgressBar) findViewById(R.id.progressBar);
        mb2 = (ProgressBar) findViewById(R.id.progressBar2);
        mb3 = (ProgressBar) findViewById(R.id.progressBar3);
        mb4 = (ProgressBar) findViewById(R.id.progressBar4);
        miv = (ImageView) findViewById(R.id.imageView);


        // setting up the listener
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new LoadImage().execute(R.mipmap.ic_launcher);
            }
        });
    }

    // async class
    class LoadImage extends AsyncTask<Integer,Integer,Bitmap>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mb1.setProgress(ProgressBar.VISIBLE);
            mb2.setProgress(ProgressBar.VISIBLE);
        }

        // make the progress bar invisible after image loads
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            mb1.setVisibility(ProgressBar.INVISIBLE);
            mb2.setVisibility(ProgressBar.INVISIBLE);
            miv.setImageBitmap(bitmap);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mb1.setProgress(values[0]);
            mb2.setProgress(values[0]);
        }

        // main background functioning
        @Override
        protected Bitmap doInBackground(Integer... integers) {
            Bitmap tmp = BitmapFactory.decodeResource(getResources(),integers[0]);
            for(int i=0;i<10;i++)
            {
                try {
                    Thread.sleep(500);
                    publishProgress(i*10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return tmp;
        }
    }


}
