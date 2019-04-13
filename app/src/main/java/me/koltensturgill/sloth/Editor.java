package me.koltensturgill.sloth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.EditText;

import java.util.ArrayList;

import me.koltensturgill.sloth.ui.editor.KeyboardButton;

public class Editor extends AppCompatActivity {

    public static final String EXTRA_EDITOR = "com.utamobi.android.sloth.EDITOR";

    ArrayList<KeyboardButton> buttons;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Utils.setThemeToActivity(this, false);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor_activity);

        //Setup for the keyboard button bar recycler view
        editText = (EditText) findViewById(R.id.et_editor);
        RecyclerView buttonBar = (RecyclerView) findViewById(R.id.rvKeyboardBar);
        buttons = KeyboardButton.createButtonList();
        KeyboardBarAdapter rvAdapter = new KeyboardBarAdapter(buttons, editText);
        buttonBar.setAdapter(rvAdapter);
        buttonBar.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL,false));
        }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent();
            // If our EditText is empty, exit
            if (TextUtils.isEmpty(editText.getText())){
                setResult(RESULT_CANCELED);
            }
            // else, send it back to MainActivity via Intent
            else {
                String note = editText.getText().toString();
                intent.putExtra(EXTRA_EDITOR, note);
                setResult(RESULT_OK, intent);
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
