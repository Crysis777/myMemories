package de.semester6.wwi15b5.dhbw.mymemories;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class EditActivity extends AppCompatActivity {

    private EditText myEdit;
    public String oldFileName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        String data= getIntent().getStringExtra("memoText");

        myEdit = findViewById(R.id.mEditText5);
        myEdit.setText(data);

        oldFileName = data;
    }

    public void onDeleteClickE(View view) {
        oldFileName += ".mp4";
        File file = new File(getDir("memos", MODE_PRIVATE), oldFileName);
        file.delete();

        Intent intent = new Intent(this, MainMemoBrowse.class);
        startActivity(intent);
    }

    public void onSaveClickE(View view) throws IOException {
        String l_oldFileName = oldFileName + ".mp4";
        File fileOld = new File(getDir("memos", MODE_PRIVATE), l_oldFileName);
        String newFileName = myEdit.getText().toString().trim();

        if(newFileName.isEmpty()) {
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(context, R.string.empty_title, duration).show();
            return;
        }

        newFileName += ".mp4";
        File fileNew = new File(getDir("memos", MODE_PRIVATE), newFileName);
        try {
            copyMemo(fileOld, fileNew);
        } catch (IOException e) {
            e.printStackTrace();
        }

        fileOld.delete();

        Intent intent = new Intent(this, MainMemoBrowse.class);
        startActivity(intent);
    }

    public static void copyMemo(File src, File dst) throws IOException {
        try (InputStream in = new FileInputStream(src)) {
            try (OutputStream out = new FileOutputStream(dst)) {
                // Transfer bytes from in to out
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            }
        }
    }
}
