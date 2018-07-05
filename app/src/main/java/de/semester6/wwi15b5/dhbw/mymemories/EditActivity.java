package de.semester6.wwi15b5.dhbw.mymemories;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class EditActivity extends AppCompatActivity {

    public String oldFileName = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        String data= getIntent().getStringExtra("memoText");

        EditText myEdit = (EditText) findViewById(R.id.mEditText5);
        myEdit.setText(data);

        oldFileName = data;
    }

    public void onDeleteClickE(View view) {
        oldFileName += ".mp4";
        File file = new File(getDir("memos", MODE_PRIVATE), oldFileName);
        file.delete();
    }

    public void onSaveClickE(View view) {

    }
}
