package com.example.tom13.fooddemo.requests;

import android.util.Log;

import com.example.tom13.fooddemo.host.Host;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by tom13 on 09/03/2018.
 */

public class RequestFactory {

    private Host host;
    private List<NameValuePair> nameValuePairs;

    public RequestFactory(Host host, List<NameValuePair> nameValuePairs) {
        this.host = host;
        this.nameValuePairs = nameValuePairs;
    }

    public HttpUriRequest getRequest() throws UnsupportedEncodingException {
        Log.v("log_tag", "Pass 1");
        HttpPost httppost = new HttpPost(host.getUrl());
        Log.v("log_tag", "Pass 2");
        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        Log.v("log_tag", "Pass 3");
        return httppost;
    }
}
