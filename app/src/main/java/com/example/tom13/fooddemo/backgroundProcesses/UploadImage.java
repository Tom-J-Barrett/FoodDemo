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
//        HttpClient httpclient = new DefaultHttpClient();
//        HttpPost httppost = new HttpPost(host.getUrl());
//
//        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
//        multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
//        multipartEntityBuilder.addBinaryBody("file", file, ContentType.DEFAULT_BINARY, "file");
//
//        HttpEntity httpEntity = multipartEntityBuilder.build();
//        httppost.setEntity(httpEntity);
//        try {
//            Log.v("message", host.getUrl());
//            HttpResponse httpResponse = httpclient.execute(httppost);
//            Log.v("message", String.valueOf(httpResponse.getStatusLine().getStatusCode()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        OkHttpClient client = new OkHttpClient();

//        RequestBody requestBody = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("file", "image.jpg", RequestBody.create(MediaType.parse("image/jpg"), file))
//                .build();
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
            Log.v("request", response.body().string());
            response.body().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Log.v("request", image.substring(0, 1000000));

//        SimpleMultiPartRequest smr = new SimpleMultiPartRequest(com.android.volley.Request.Method.POST, host.getUrl(),
//                new com.android.volley.Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.d("Response", response);
//                        try {
//                            JSONObject jObj = new JSONObject(response);
//                            String message = jObj.getString("message");
//                            Log.v("request", message);
//
//                        } catch (JSONException e) {
//                            // JSON error
//                            e.printStackTrace();
//                        }
//                    }
//                }, new com.android.volley.Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.v("request", error.getMessage());
//            }
//        });
//
//        smr.addFile("image", file.getAbsolutePath());
//        RequestQueue rQueue = Volley.newRequestQueue(captureImagePresenter.getActivity());
//        rQueue.add(smr);

        return "YAY";
    }

    protected void onPostExecute(String result) {
        captureImagePresenter.responseFromSever(result);
        super.onPostExecute(result);
    }
}

