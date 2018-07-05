package de.semester6.wwi15b5.dhbw.mymemories;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;

public class MainMemoBrowse extends AppCompatActivity {

    //Only for test
    private TextView textView;
    private boolean change = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_memo_browse);

        //Only for test
        textView = findViewById(R.id.textView4);
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
        change = !change;

        String text = null;
        File dir;
        if(change) {
            dir = getCacheDir();
            text = "Cache content: \n\n";
        } else {
            dir = getDir("memos",MODE_PRIVATE);
            text = "Files content: \n\n";
        }

        File[] subFiles = dir.listFiles();

        if (subFiles != null)
        {
            for (File file : subFiles)
            {
                text += file.getName() + "\n";
            }
        }
        textView.setText(text);
    }


    //TODO Implement search functionality

    //TODO Implement Cards with a RecyclerView to display existent Memos

    //TODO Share memo with other applications
}