package com.example.songsaves;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnShow;
    EditText etTitle, etSinger, etYear;
    RadioGroup radioGroup;
    ListView lv;
    ArrayAdapter<Song> aa;
    ArrayList<Song> songList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnShow = findViewById(R.id.btnShow);
        etTitle = findViewById(R.id.etTitle);
        etSinger = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);
        radioGroup = findViewById(R.id.radioGroup);
        lv = findViewById(R.id.lv);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the DbHelper object, passing in the activity's Context
                DBHelper dbHelper = new DBHelper(MainActivity.this);

                // Insert a song
                String title = etTitle.getText().toString();
                String singer = etSinger.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                int rating = getSelectedRating();

                int id = 0;
                Song song = new Song(id,title, singer, year, rating);
                dbHelper.insertSong(song);
                dbHelper.close();
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the DbHelper object, passing in the activity's Context
                DBHelper dbHelper = new DBHelper(MainActivity.this);

                // Retrieve the song list from the database
                songList = dbHelper.getAllSongs();
                dbHelper.close();

                // Update the ListView
                aa = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, songList);
                lv.setAdapter(aa);
            }
        });
    }

    private int getSelectedRating() {
        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        View radioButton = radioGroup.findViewById(radioButtonId);
        int index = radioGroup.indexOfChild(radioButton);
        return index + 1; // Since ratings start from 1
    }
}
