package com.example.notes;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class NoteActivity extends AppCompatActivity {

    private EditText Topic; // поле ввода темы
    private EditText Text; // поле ввода текста
    private Button ExitButton; // кнопка сохранения

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        Topic = findViewById(R.id.Topic);
        Text = findViewById(R.id.Text);
        ExitButton = findViewById(R.id.Exit);

        // получение данных о теме и тексте из вызывающего интента
        String selectedTopic = getIntent().getStringExtra("topic");
        String selectedText = getIntent().getStringExtra("text");
        // заполнение полей для ввода
        if (selectedTopic != null && selectedText != null) {
            Topic.setText(selectedTopic);
            Text.setText(selectedText);
        }
        //обработка нажатия на кнопку
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

        // создание интента для отправки сохраненных данных заметки обратно в вызывающую активность
        Intent resultIntent = new Intent();
        resultIntent.putExtra("topic", topic);
        resultIntent.putExtra("text", text);
        setResult(RESULT_OK, resultIntent);
    }
}