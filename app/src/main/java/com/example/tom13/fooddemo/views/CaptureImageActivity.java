package com.example.tom13.fooddemo.views;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.couchbase.lite.CouchbaseLiteException;
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

        Thread t = new Thread(new Runnable() { public void run() {
            captureImagePresenter.sendImage();
        }});
        t.start();
    }

    public void onResponse(String prediction, String calories) {
        ProgressBar progressBar = findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.INVISIBLE);

        TextView textView = findViewById(R.id.textView);
        textView.setVisibility(View.VISIBLE);

        TextView textView2 = findViewById(R.id.textView4);
        textView2.setVisibility(View.VISIBLE);

        EditText editText = findViewById(R.id.editText3);
        editText.setVisibility(View.VISIBLE);
        editText.setText(prediction);

        EditText editText2 = findViewById(R.id.editText5);
        editText2.setVisibility(View.VISIBLE);
        editText2.setText(calories);

        Button submitButton = findViewById(R.id.button5);
        submitButton.setVisibility(View.VISIBLE);
    }

    public void submit(View view) throws CouchbaseLiteException {
        EditText editText = findViewById(R.id.editText3);
        EditText editText2 = findViewById(R.id.editText5);

        captureImagePresenter.writeToLogs(editText.getText().toString(), editText2.getText().toString());
    }
}
