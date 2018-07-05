package de.semester6.wwi15b5.dhbw.mymemories;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;

public class MainMemoBrowse extends AppCompatActivity {

    private RecyclerView recView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    static ArrayList<String> text = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_memo_browse);

        //Only for test
        recView = (RecyclerView) findViewById(R.id.recView1);
        mLayoutManager = new LinearLayoutManager(this);
        recView.setLayoutManager(mLayoutManager);

        //Filenamen auslesen
        loadFileList();

        //Refresh RecycleView
        mAdapter = new MyAdapter(text);
        recView.setAdapter(mAdapter);
    }

    private void loadFileList(){
        File dir;
        dir = getDir("memos", MODE_PRIVATE);
        text.clear();
        File[] subFiles = dir.listFiles();

        if (subFiles != null) {
            int i = 0;
            for (File file : subFiles) {
                text.add(file.getName());
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

    //TODO Implement Cards with a RecyclerView to display existent Memos
}