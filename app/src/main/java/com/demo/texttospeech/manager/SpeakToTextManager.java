package com.demo.texttospeech.manager;

import android.content.Intent;
import android.speech.RecognizerIntent;

import com.demo.texttospeech.R;

import java.util.Locale;

/**
 * Created by Himanshu Srivastava on 17-Mar-23.
 */
public class SpeakToTextManager {
    public static Intent speak(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.forLanguageTag("hi"));
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Test in Hindi language");
        return intent;
    }
}
