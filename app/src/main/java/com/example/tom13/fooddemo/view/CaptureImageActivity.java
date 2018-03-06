package com.example.tom13.fooddemo.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tom13.fooddemo.R;
import com.example.tom13.fooddemo.presenter.CaptureImagePresenter;

public class CaptureImageActivity extends AppCompatActivity {

    private CaptureImagePresenter captureImagePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_image);

        captureImagePresenter = new CaptureImagePresenter(this);
        captureImagePresenter.takePicture();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        captureImagePresenter.onActivityResult(requestCode, resultCode, data);
    }
}
