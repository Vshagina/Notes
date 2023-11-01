package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> topicAndTextList = new ArrayList<>();
    private Button addTopicButton;

    private static final int REQUEST_CODE_ADD_NOTE = 1;
    private static final int REQUEST_CODE_EDIT_NOTE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        addTopicButton = findViewById(R.id.addTopicButton);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);

        addTopicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_NOTE);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedData = topicAndTextList.get(position);
                String[] parts = selectedData.split(": ", 2);
                String selectedTitle = parts[0];
                String selectedText = parts[1];

                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                intent.putExtra("topic", selectedTitle);
                intent.putExtra("text", selectedText);
                startActivityForResult(intent, REQUEST_CODE_EDIT_NOTE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ADD_NOTE && resultCode == RESULT_OK && data != null) {
            String title = data.getStringExtra("topic");
            String text = data.getStringExtra("text");
            String combinedData = title + ": " + text;

            topicAndTextList.add(combinedData);
            adapter.clear();
            adapter.addAll(topicAndTextList);
            adapter.notifyDataSetChanged();
        }
    }
}