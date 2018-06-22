package de.semester6.wwi15b5.dhbw.mymemories;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMemoBrowse extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_memo_browse);
    }

    public void onRecordClick(View view) {
        Intent intent = new Intent(this, RecordMemo.class);
        startActivity(intent);
    }
}