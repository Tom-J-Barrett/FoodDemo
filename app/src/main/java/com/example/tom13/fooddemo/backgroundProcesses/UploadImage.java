package com.example.tom13.fooddemo.backgroundProcesses;
import android.os.AsyncTask;
import android.util.Log;

import com.example.tom13.fooddemo.host.Host;
import com.example.tom13.fooddemo.requests.RequestFactory;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tom13 on 08/03/2018.
 */

public class UploadImage extends AsyncTask<Void, Void, String> {

    private Host host;
    private String image;

    public UploadImage(Host host, String image) {
        this.host = host;
        this.image = image;
    }

    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... params) {

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("base64", image));
        nameValuePairs.add(new BasicNameValuePair("ImageName", System.currentTimeMillis() + ".jpg"));
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = httpclient.execute(new RequestFactory(host, nameValuePairs).getRequest());
            String st = EntityUtils.toString(response.getEntity());
            Log.v("log_tag",response.toString());

            Log.v("log_tag", "In the try Loop" + st);

        } catch (Exception e) {
            Log.v("log_tag", "Error in http connection " + e.toString());
        }
        return "Success";
    }

    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}

