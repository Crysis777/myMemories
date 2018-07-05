package de.semester6.wwi15b5.dhbw.mymemories;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.io.File;
import java.util.ArrayList;

public class MainMemoBrowse extends AppCompatActivity {

    private RecyclerView recView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    static ArrayList<String> textArray = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_memo_browse);

        recView = (RecyclerView) findViewById(R.id.recView1);
        mLayoutManager = new LinearLayoutManager(this);
        recView.setLayoutManager(mLayoutManager);
    }

    @Override
    public void onStart() {
        super.onStart();

        //Read out filenames
        loadFileList();

        //Refresh RecycleView
        mAdapter = new MyAdapter(textArray);
        recView.setAdapter(mAdapter);
    }

    private void loadFileList(){
        File dir;
        dir = getDir("memos", MODE_PRIVATE);
        textArray.clear();
        File[] subFiles = dir.listFiles();

        if (subFiles != null) {
            int i = 0;
            for (File file : subFiles) {
                textArray.add(file.getName());
                i++;
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void onRecordClick(View view) {
        Intent intent = new Intent(this, RecordMemo.class);
        startActivity(intent);
    }

    public void onFilterClick(View view) {
        //TODO Implement filter function.
        //This is just test code

    }


    //TODO Implement search functionality

}