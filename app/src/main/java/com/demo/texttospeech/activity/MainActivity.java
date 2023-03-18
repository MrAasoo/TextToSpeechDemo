package com.demo.texttospeech.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.TextView;

import com.demo.texttospeech.R;
import com.demo.texttospeech.manager.SpeakToTextManager;
import com.demo.texttospeech.manager.TextToSpeechManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener, ActivityResultCallback<ActivityResult> {


    private TextView tvText;
    private FloatingActionButton fabRecord;
    private FloatingActionButton fabSpeech;
    private TextToSpeech tts;

    private ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvText = findViewById(R.id.tvText);
        fabRecord = findViewById(R.id.fabRecord);
        fabSpeech = findViewById(R.id.fabSpeech);

        fabRecord.setOnClickListener(this::onClick);
        fabSpeech.setOnClickListener(this::onClick);

        tts = TextToSpeechManager.getInstance(this, this::onInit);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fabRecord:
                Intent intent = SpeakToTextManager.speak();
                launcher.launch(intent);
                break;
            case R.id.fabSpeech:
                TextToSpeechManager.playTextToSpeech(tts, tvText.getText());
                break;
        }
    }

    @Override
    public void onInit(int status) {
        if (status != TextToSpeech.ERROR) {
            tts.setLanguage(Locale.forLanguageTag("hi"));
        }
    }

    @Override
    public void onActivityResult(ActivityResult result) {
        if(result.getResultCode() == Activity.RESULT_OK && result.getData() != null){
            ArrayList<String> stringArrayList = result.getData().getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            tvText.setText(stringArrayList.get(0));
        }
    }
}