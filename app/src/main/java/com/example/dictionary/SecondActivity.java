package com.example.dictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.location.GnssAntennaInfo;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    SearchView search_view;
    TextView textView_word;
    RecyclerView recycler_phonetics, recycler_meanings;

    ProgressDialog progressDialog;

    PhoneticsAdapter phoneticsAdapter;
    MeaningAdapter meaningAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        String message = getIntent().getStringExtra("inputword");

        search_view = findViewById(R.id.search_view);
        textView_word = findViewById(R.id.textView_word);
        recycler_phonetics = findViewById(R.id.recycler_phonetics);
        recycler_meanings = findViewById(R.id.recycler_meanings);
        progressDialog = new ProgressDialog(this);

        progressDialog.setTitle("Loading...");
        progressDialog.show();
        requestmanager manager = new requestmanager(SecondActivity.this);
        manager.getwordmeaning(Listener, message);

        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressDialog.setTitle("Fetching response for " + query);
                progressDialog.show();
                requestmanager manager = new requestmanager(SecondActivity.this);
                manager.getwordmeaning(Listener, query);


                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });






    }

    private final onfetchdatalistener Listener = new onfetchdatalistener() {
        @Override
        public void onfetchdata(response response, String message) {
            progressDialog.dismiss();
            if (response == null) {
                Toast.makeText(SecondActivity.this, "No data found!!", Toast.LENGTH_SHORT).show();
                return;
            }
            showData(response);

        }

        @Override
        public void onerror(String message) {

            progressDialog.dismiss();
            Toast.makeText(SecondActivity.this, message, Toast.LENGTH_SHORT).show();

        }
    };

    private void showData(response response) {
        textView_word.setText("Word " + response.getWord());
        recycler_phonetics.setHasFixedSize(true);
        recycler_phonetics.setLayoutManager(new GridLayoutManager(this, 1));

        phoneticsAdapter = new PhoneticsAdapter(this, response.getPhonetics());
        recycler_phonetics.setAdapter(phoneticsAdapter);

        recycler_meanings.setHasFixedSize(true);
        recycler_meanings.setLayoutManager(new GridLayoutManager(this, 1));
        meaningAdapter = new MeaningAdapter(this, response.getMeanings());
        recycler_meanings.setAdapter(meaningAdapter);

    }


}
