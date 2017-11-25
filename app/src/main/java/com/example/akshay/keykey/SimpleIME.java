package com.example.akshay.keykey;

import android.content.Intent;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.media.AudioManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by AKSHAY on 11/16/2017.
 */

public class SimpleIME extends InputMethodService
        implements KeyboardView.OnKeyboardActionListener {

    private KeyboardView kv, kv2;
    private Keyboard keyboard, keyboard2;

 /*   @Override
    public View onCreateInputView() {
        kv = (KeyboardView)getLayoutInflater().inflate(R.layout.keyboard, null);
        keyboard = new Keyboard(this, R.xml.qwerty);
        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(this);
        return kv;
    }
*/
    @Override
    public View onCreateInputView() {

        final View root = getLayoutInflater().inflate(R.layout.keyboard, null);

        final Button button = (Button) root.findViewById(R.id.button);
        final Button newbutton = (Button) root.findViewById(R.id.newbutton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(view==button){
                   kv.setKeyboard(keyboard2);

                }


            }
        });

        newbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(view==newbutton){
                    kv.setKeyboard(keyboard);

                }


            }
        });

        kv = (KeyboardView) root.findViewById(R.id.keyboard);
        kv2 = (KeyboardView) root.findViewById(R.id.keyboard2);
        keyboard = new Keyboard(this, R.xml.qwerty);
        keyboard2 = new Keyboard(this, R.xml.qwerty2);
        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(this);
        return root;
    }


    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection ic = getCurrentInputConnection();

        switch(primaryCode){
            case Keyboard.KEYCODE_DELETE :
                ic.deleteSurroundingText(1, 0);
                break;
            case Keyboard.KEYCODE_SHIFT:
                caps = !caps;
                keyboard.setShifted(caps);
                kv.invalidateAllKeys();
                break;
            case Keyboard.KEYCODE_DONE:
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                break;
            default:
                char code = (char)primaryCode;
                if(Character.isLetter(code) && caps){
                    code = Character.toUpperCase(code);
                }
                ic.commitText(String.valueOf(code),1);
        }
    }

    private boolean caps = false;


    @Override
    public void onPress(int primaryCode) {
    }

    @Override
    public void onRelease(int primaryCode) {
    }

    @Override
    public void onText(CharSequence text) {
    }

    @Override
    public void swipeDown() {
    }

    @Override
    public void swipeLeft() {
    }

    @Override
    public void swipeRight() {
    }

    @Override
    public void swipeUp() {
    }
}