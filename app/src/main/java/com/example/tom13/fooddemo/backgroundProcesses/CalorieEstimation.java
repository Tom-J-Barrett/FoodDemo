package com.example.tom13.fooddemo.backgroundProcesses;

import android.os.AsyncTask;

import com.example.tom13.fooddemo.presenters.CaptureImagePresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import okhttp3.Response;

/**
 * Created by tom13 on 16/03/2018.
 * Background process to call the calorie estimation API.
 * A searchQuery i.e "banana" must be passed to the class to query the API with.
 */

public class CalorieEstimation extends AsyncTask<Void, Void, String> {

    private String searchQuery;
    private String url;
    private CaptureImagePresenter captureImagePresenter;

    public CalorieEstimation(String searchQuery, CaptureImagePresenter captureImagePresenter) {
        this.searchQuery = searchQuery;
        this.captureImagePresenter = captureImagePresenter;
        urlBuilder();
    }


    @Override
    protected String doInBackground(Void... params) {
        String result = "";
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(url)
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            result = response.body().string();
            response.body().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            JSONObject jsonObject = new JSONObject(result);;
            JSONArray jsonArray = jsonObject.getJSONArray("hits");
            JSONObject hits = jsonArray.getJSONObject(0);
            JSONObject fields = hits.getJSONObject("fields");
            String calories = fields.getString("nf_calories");
            result = calories;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void urlBuilder() {
        url = "https://api.nutritionix.com/v1_1/search/";
        url += searchQuery + "?";
        url += "results=0%3A1&fields=item_name%2Citem_id%2Cnf_calories&appId=30cf5e40&appKey=451ee6d5f9faec286741d29bdcfcf2e9";
    }

    protected void onPostExecute(String result) {
        captureImagePresenter.updateUI(result);
        super.onPostExecute(result);
    }
}
