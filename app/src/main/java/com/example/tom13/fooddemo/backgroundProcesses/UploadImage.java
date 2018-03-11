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
import org.apache.http.entity.ContentType;
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
    private File file;

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
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(host.getUrl());

        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        multipartEntityBuilder.addBinaryBody("upfile", file, ContentType.DEFAULT_BINARY, "foodImage");

        HttpEntity httpEntity = multipartEntityBuilder.build();
        httppost.setEntity(httpEntity);
        try {
            Log.v("message", host.getUrl());
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

