package de.semester6.wwi15b5.dhbw.mymemories;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class RecordMemo extends AppCompatActivity {

    private static final String LOG_TAG = "AudioRecordTest";
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private static String mFileName = null;

    private MediaRecorder mRecorder = null;

    private MediaPlayer   mPlayer = null;

    private boolean mStartPlaying = true;
    private boolean mStartRecording = true;

    private Button playButton, recordButton, saveButton;
    private EditText editText;

    // Requesting permission to RECORD_AUDIO
    private boolean permissionToRecordAccepted = false;
    private String [] permissions = {Manifest.permission.RECORD_AUDIO};

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_RECORD_AUDIO_PERMISSION:
                permissionToRecordAccepted  = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (!permissionToRecordAccepted ) finish();
    }

    private void onRecord(boolean start) {
        if (start) {
            playButton.setClickable(false);
            playButton.setTextColor(Color.parseColor("#999999"));
            saveButton.setClickable(false);
            saveButton.setTextColor(Color.parseColor("#999999"));
            startRecording();
        } else {
            stopRecording();
            playButton.setClickable(true);
            playButton.setTextColor(Color.parseColor("#000000"));
            saveButton.setClickable(true);
            saveButton.setTextColor(Color.parseColor("#000000"));
        }
    }

    private void onPlay(boolean start) {
        if (start) {
            recordButton.setClickable(false);
            recordButton.setTextColor(Color.parseColor("#999999"));
            saveButton.setClickable(false);
            saveButton.setTextColor(Color.parseColor("#999999"));
            startPlaying();
        } else {
            stopPlaying();
            recordButton.setClickable(true);
            recordButton.setTextColor(Color.parseColor("#000000"));
            saveButton.setClickable(true);
            saveButton.setTextColor(Color.parseColor("#000000"));
        }
    }

    private void startPlaying() {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(mFileName);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    private void stopPlaying() {
        mPlayer.release();
        mPlayer = null;
    }

    private void startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
    }

    private void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }

    public void onRecordButtonClicked(View view) {
        onRecord(mStartRecording);
        if (mStartRecording) {
            recordButton.setText(R.string.mainmemobrowse_stop_recording);
        } else {
            recordButton.setText(R.string.mainmemobrowse_start_recording);
        }
        mStartRecording = !mStartRecording;
    }

    public void onPlayButtonClicked(View view) {
        onPlay(mStartPlaying);
        if (mStartPlaying) {
            playButton.setText(R.string.mainmemobrowse_stop_playing);
        } else {
            playButton.setText(R.string.mainmemobrowse_start_playing);
        }
        mStartPlaying = !mStartPlaying;
    }

    public void onSaveButtonClicked(View view) {
        if(editText.getText().toString().trim().isEmpty()) {
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(context, R.string.empty_title, duration).show();
        }
        else {

            Intent intent = new Intent(this, MainMemoBrowse.class);
            startActivity(intent);
        }
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        // Record to the external cache directory for visibility
        mFileName = getExternalCacheDir().getAbsolutePath();
        mFileName += "/memo.mp4";

        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);

        setContentView(R.layout.activity_record_memo);

        recordButton = findViewById(R.id.mRecordButton);
        playButton = findViewById(R.id.mPlayButton);
        editText = findViewById(R.id.mEditText);
        saveButton = findViewById(R.id.mSave);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
        }

        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }
}