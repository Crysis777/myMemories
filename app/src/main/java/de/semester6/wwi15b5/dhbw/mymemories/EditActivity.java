package de.semester6.wwi15b5.dhbw.mymemories;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

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
}
