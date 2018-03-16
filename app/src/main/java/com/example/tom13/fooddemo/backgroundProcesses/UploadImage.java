package com.example.tom13.fooddemo.backgroundProcesses;
import android.os.AsyncTask;

import com.example.tom13.fooddemo.host.Host;
import com.example.tom13.fooddemo.presenters.CaptureImagePresenter;

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

    public UploadImage(Host host, String image, CaptureImagePresenter captureImagePresenter) {
        this.host = host;
        this.image = image;
        this.captureImagePresenter = captureImagePresenter;
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

