package com.meagain.inclass04_group33;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/*
* Megan Reiffer, Molly-Marie Frye
*/
public class MainActivity extends AppCompatActivity implements GetNewsAsyncTask.IData {
    private ArrayList<News> newsList;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void finishClicked(View view) {
        finish();
        System.exit(0);
    }

    public void getNewsButtonClicked(View view) {
        String newsSource = ((Spinner) findViewById(R.id.spinner)).getSelectedItem().toString().replace(" ", "-").toLowerCase();
        System.out.println("news source: " + newsSource);
        if (newsSource.equals("bbc")) {
            newsSource = "bbc-news";
        }
        String url = "https://newsapi.org/v1/articles?apiKey=12170035a7b2452a982cc39f90b30b1e&source=" + newsSource;
        System.out.println("news url: " + url);

        new GetNewsAsyncTask(this).execute(url);
    }

    @Override
    public void setupData(ArrayList<News> result) {
        newsList = result;
        index = 0;
        showNews();
        findViewById(R.id.finish_btn).setEnabled(true);
        findViewById(R.id.first_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = 0;
                showNews();
            }
        });
        findViewById(R.id.last_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                index = newsList.size() - 1;
                showNews();
            }
        });
        findViewById(R.id.next_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (index < newsList.size() - 1) {
                    index++;
                } else {
                    index = 0;
                }
                showNews();

            }
        });
        findViewById(R.id.prev_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (index > 0) {
                    index--;
                } else {
                    index = newsList.size() - 1;
                }
                showNews();
            }
        });
    }

    public void showNews() {
        News news = newsList.get(index);

        new GetImage().execute(news.getUrlToImage());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");

        Date date = null;
        try {
            date = simpleDateFormat.parse(news.getPublishedAt());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String newsText = "";
        try {
            newsText = news.getTitle();
        } catch (Exception e) {
        }
        try {
            newsText = newsText + "\n\nAuthor: " + news.getAuthor();
        } catch (Exception e) {
        }
        try {
            newsText = newsText + "\nPublished on: " + date.toString();
        } catch (Exception e) {
        }
        try {
            newsText = newsText + "\n\nDescription:\n" + news.getDescription();
        } catch (Exception e) {
        }
        ((TextView) findViewById(R.id.newsTextView)).setText(newsText);
    }

    @Override
    public Context getContext() {
        return this;
    }


    private class GetImage extends AsyncTask<String, Void, Bitmap> {

        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param params The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected Bitmap doInBackground(String... params) {

            try {
                URL url = new URL(params[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                Bitmap bitmap = BitmapFactory.decodeStream(con.getInputStream());
                return bitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        /**
         * <p>Runs on the UI thread after {@link #doInBackground}. The
         * specified result is the value returned by {@link #doInBackground}.</p>
         * <p>
         * <p>This method won't be invoked if the task was cancelled.</p>
         *
         * @param bitmap The result of the operation computed by {@link #doInBackground}.
         * @see #onPreExecute
         * @see #doInBackground
         * @see #onCancelled(Object)
         */
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                ((ImageView) findViewById(R.id.imageView)).setImageBitmap(bitmap);
            }

        }
    }
}
