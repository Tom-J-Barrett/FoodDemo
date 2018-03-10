package com.example.tom13.fooddemo.backgroundProcesses;
import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.example.tom13.fooddemo.host.Host;
import com.example.tom13.fooddemo.presenters.CaptureImagePresenter;
import com.example.tom13.fooddemo.requests.RequestFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.http.entity.mime.HttpMultipartMode.BROWSER_COMPATIBLE;

/**
 * Created by tom13 on 08/03/2018.
 */

public class UploadImage extends AsyncTask<Void, Void, String> {

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
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(host.getUrl());

        try {
            HttpResponse httpResponse = httpclient.execute(httppost);
            Log.v("message", String.valueOf(httpResponse.getStatusLine().getStatusCode()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "YAY";
    }

    protected void onPostExecute(String result) {
        captureImagePresenter.responseFromSever(result);
        super.onPostExecute(result);
    }

    
}

