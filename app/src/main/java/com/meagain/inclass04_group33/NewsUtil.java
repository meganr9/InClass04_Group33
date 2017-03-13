package com.meagain.inclass04_group33;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/*
* Megan Reiffer, Molly-Marie Frye
*/
public class NewsUtil {
    public static class NewsJSONParser {
        static ArrayList<News> parseNews(String in) throws JSONException {
            ArrayList<News> newsList = new ArrayList<News>();

            JSONObject root = new JSONObject(in);
            JSONArray newsJSONArray = root.getJSONArray("articles");

            for (int i = 0; i < newsJSONArray.length(); i++) {
                JSONObject newsJSONObject = newsJSONArray.getJSONObject(i);
                News news = News.createNews(newsJSONObject);
                newsList.add(news);
            }

            return newsList;
        }
    }
}
