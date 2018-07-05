package de.semester6.wwi15b5.dhbw.mymemories;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainMemoBrowse extends AppCompatActivity {

    private RecyclerView recView;
    private EditText editTextSearch;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    static ArrayList<String> textArray = new ArrayList<>();
    static ArrayList<String> textArrayOriginal = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_memo_browse);

        recView = findViewById(R.id.recView1);
        editTextSearch = findViewById(R.id.EditTextSearch);

        mLayoutManager = new LinearLayoutManager(this);
        recView.setLayoutManager(mLayoutManager);
    }

    @Override
    public void onStart() {
        super.onStart();

        //Read out filenames
        loadFileList();

        reloadRecylcer();
    }

    private void reloadRecylcer() {
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
        textArrayOriginal = (ArrayList<String>) textArray.clone();
    }

    public void onRecordClick(View view) {
        Intent intent = new Intent(this, RecordMemo.class);
        startActivity(intent);
    }

    public void onFilterClick(View view) {
        String searchString = editTextSearch.getText().toString().trim();

        if(searchString.isEmpty()) {
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(context, R.string.empty_search_string, duration).show();

            textArray.clear();
            textArray = (ArrayList<String>) textArrayOriginal.clone();
            reloadRecylcer();

        } else {
            ArrayList<String> tempTextArray = new ArrayList<>();
            for (String singleString: textArray) {
                if(singleString.contains(searchString)) {
                    tempTextArray.add(singleString);
                }
            }
            textArray.clear();
            textArray = (ArrayList<String>) tempTextArray.clone();
            tempTextArray.clear();
            reloadRecylcer();
        }
    }
}