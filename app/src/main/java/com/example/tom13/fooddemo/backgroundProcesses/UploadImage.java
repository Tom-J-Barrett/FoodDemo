package com.example.tom13.fooddemo.backgroundProcesses;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.error.VolleyError;
import com.android.volley.request.SimpleMultiPartRequest;
import com.android.volley.toolbox.Volley;
import com.example.tom13.fooddemo.host.Host;
import com.example.tom13.fooddemo.presenters.CaptureImagePresenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by tom13 on 08/03/2018.
 */

public class UploadImage extends AsyncTask<Void, Void, String> {

    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    private Host host;
    private String image;
    private CaptureImagePresenter captureImagePresenter;
    private File file;
    //private String result = "";

    public UploadImage(Host host, String image, CaptureImagePresenter captureImagePresenter, File file) {
        this.host = host;
        this.image = image;
        this.captureImagePresenter = captureImagePresenter;
        this.file = file;
    }

    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... params) {
        String result = "";
        OkHttpClient client = new OkHttpClient();
        String test = image;
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", test)
                .build();

        Request request = new Request.Builder().url(host.getUrl())
                .post(requestBody).build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            result = response.body().string();
            //Log.v("request", response.body().string());
            response.body().close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    protected void onPostExecute(String result) {
        captureImagePresenter.responseFromSever(result);
        super.onPostExecute(result);
    }
}

