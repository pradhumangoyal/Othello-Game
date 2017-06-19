package com.example.pradhuman.othello;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {
    TextView nameTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        final Button button = (Button) findViewById(R.id.button);
        final EditText editText = (EditText) findViewById(R.id.editText);
        final EditText editText1 = (EditText) findViewById(R.id.editText2);
        nameTextView = (TextView) findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText.getText().toString();
                String name2 = editText1.getText().toString();
                if (name == null || name2 == null)
                    return;
                if (name.isEmpty() || name2.isEmpty()) {
                    Toast.makeText(StartActivity.this, "Enter name !!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent i = new Intent(StartActivity.this, MainActivity.class);
                i.putExtra("player1", name);
                i.putExtra("player2", name2);
                startActivity(i);
            }
        });
    }
}
