package com.demo.texttospeech.manager;

import android.content.Context;
import android.speech.tts.TextToSpeech;

/**
 * Created by Himanshu Srivastava on 17-Mar-23.
 */
public class TextToSpeechManager {
    public static TextToSpeech getInstance(Context context, TextToSpeech.OnInitListener listener) {
        return new TextToSpeech(context, listener);
    }

    public static void playTextToSpeech(TextToSpeech tts,  CharSequence text){
       tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
    }
}
