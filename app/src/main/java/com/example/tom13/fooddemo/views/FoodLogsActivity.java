package com.example.tom13.fooddemo.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.tom13.fooddemo.R;
import com.example.tom13.fooddemo.presenters.FoodLogsPresenter;

public class FoodLogsActivity extends AppCompatActivity {

    private FoodLogsPresenter foodLogsPresenter;
    private String currentSortType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_logs);
        foodLogsPresenter = new FoodLogsPresenter();
    }

    private void populateSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, foodLogsPresenter.spinnerContents());

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = findViewById(R.id.spinner);
        sItems.setAdapter(adapter);

        currentSortType = sItems.getSelectedItem().toString();
    }

    public void onSpinnerChange(View view) {

    }

}
