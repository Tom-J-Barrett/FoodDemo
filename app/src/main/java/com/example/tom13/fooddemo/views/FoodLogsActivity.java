package com.example.tom13.fooddemo.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tom13.fooddemo.R;
import com.example.tom13.fooddemo.presenters.FoodLogsPresenter;

import java.util.Date;

public class FoodLogsActivity extends AppCompatActivity {

    private FoodLogsPresenter foodLogsPresenter;
    private String currentSortType;
    private Date dateToQuery = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_logs);
        foodLogsPresenter = new FoodLogsPresenter(this);

        populateSpinner();
        populateListView();

        TextView textView = findViewById(R.id.textView8);
        textView.setText(new Date().toString());
    }

    private void populateSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, foodLogsPresenter.getSpinnerContents());

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = findViewById(R.id.spinner);
        sItems.setAdapter(adapter);
        sItems.setSelection(0);

        currentSortType = sItems.getSelectedItem().toString();

        sItems.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                        currentSortType = sItems.getSelectedItem().toString();
                        onSpinnerChange();
                    }
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
    }

    public void onSpinnerChange() {
        populateListView();
    }

    private void populateListView() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, foodLogsPresenter.getListViewContents(currentSortType, dateToQuery));
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    private void updateDate() {
        dateToQuery = foodLogsPresenter.updateDateOnFling(dateToQuery);
        populateListView();
        TextView textView = findViewById(R.id.textView8);
        textView.setText(foodLogsPresenter.getDate());
    }

    public void onFling() {
        updateDate();
    }

}
