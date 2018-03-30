package com.example.tom13.fooddemo.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tom13.fooddemo.R;
import com.example.tom13.fooddemo.presenters.CaptureImagePresenter;

/**
 * View class for capturing an image and sending it to a host.
 */
public class CaptureImageActivity extends AppCompatActivity {

    private CaptureImagePresenter captureImagePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_image);

        Bundle bundle = getIntent().getExtras();
        captureImagePresenter = new CaptureImagePresenter(this, bundle.getString("medium"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK)
            captureImagePresenter.onActivityResult(requestCode, resultCode, data);
        else if(resultCode == RESULT_CANCELED)
            captureImagePresenter.cancel();
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

        Thread t = new Thread(() -> captureImagePresenter.sendImage());
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
        editText.setText(prediction.toUpperCase());

        EditText editText2 = findViewById(R.id.editText5);
        editText2.setVisibility(View.VISIBLE);
        editText2.setText(calories);

        Button submitButton = findViewById(R.id.button5);
        submitButton.setVisibility(View.VISIBLE);
    }

    public void submit(View view) {
        EditText editText = findViewById(R.id.editText3);
        EditText editText2 = findViewById(R.id.editText5);

        captureImagePresenter.writeToLogs(editText.getText().toString(), editText2.getText().toString());
    }
}
