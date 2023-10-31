package com.example.notes;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
public class NoteActivity extends AppCompatActivity {

    private EditText Topic;
    private EditText Text;
    private Button ExitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        Topic = findViewById(R.id.Topic);
        Text = findViewById(R.id.Text);
        ExitButton = findViewById(R.id.Exit);

        ExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
                finish();
            }
        });
    }

    private void saveNote() {
        String topic = Topic.getText().toString();
        String text = Text.getText().toString();

        Intent resultIntent = new Intent();
        resultIntent.putExtra("topic", topic);
        resultIntent.putExtra("text", text);
        setResult(RESULT_OK, resultIntent);
    }
    }
