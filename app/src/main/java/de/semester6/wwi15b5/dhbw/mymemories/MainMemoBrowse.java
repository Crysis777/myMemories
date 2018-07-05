package de.semester6.wwi15b5.dhbw.mymemories;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainMemoBrowse extends AppCompatActivity {

    private RecyclerView recView;
    private SearchView searchView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    static ArrayList<String> textArray = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_memo_browse);

        recView = findViewById(R.id.recView1);
        //searchView = findViewById(R.id.searchView1);

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

    @Override
    public void onResume() {
        super.onResume();
    }

    private void loadFileList(){
        File dir;
        dir = getDir("memos", MODE_PRIVATE);
        textArray.clear();
        File[] subFiles = dir.listFiles();

        if (subFiles != null) {
            for (File file : subFiles) {
                textArray.add(file.getName().substring(0, file.getName().length() - 4));
            }
        }
    }

    public void onRecordClick(View view) {
        Intent intent = new Intent(this, RecordMemo.class);
        startActivity(intent);
    }

    //TODO Implement filter function.
    public void onFilterClick(View view) {
        //This is just test code
    }

    //TODO Implement search functionality
    public void onSearchClick(View view) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(context, "searched", duration).show();
    }
}