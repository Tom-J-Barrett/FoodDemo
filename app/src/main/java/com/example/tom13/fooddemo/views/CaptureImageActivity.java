package com.example.tom13.fooddemo.views;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.tom13.fooddemo.R;
import com.example.tom13.fooddemo.presenters.CaptureImagePresenter;

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

    public void cancelButton(View view) {
        captureImagePresenter.cancel();
    }

    public void sendImage(View view) {
        Button b1 = findViewById(R.id.button3);
        Button b2 = findViewById(R.id.button4);
        b1.setVisibility(View.INVISIBLE);
        b2.setVisibility(View.INVISIBLE);

        ProgressBar progressBar = findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.VISIBLE);

        captureImagePresenter.sendImage();
    }

    public void onResponse() {
        ProgressBar progressBar = findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.INVISIBLE);
    }
}
