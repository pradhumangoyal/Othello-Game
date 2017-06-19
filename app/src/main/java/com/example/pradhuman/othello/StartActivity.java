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
        nameTextView = (TextView) findViewById(R.id.textView);
        final SharedPreferences sharedPreferences = getSharedPreferences("Othello", MODE_PRIVATE);
        String name = sharedPreferences.getString("userName", null);
        if(name == null){
            nameTextView.setText("Welcome User ");
        }else{
            nameTextView.setText("Welcome " + name);
            editText.setText(name);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText.getText().toString();
                if(name == null)
                    return;
                if(name.isEmpty()){
                    Toast.makeText(StartActivity.this, "Enter name !!", Toast.LENGTH_SHORT).show();
                    return;
                }
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("userName", name);
                editor.commit();
                Intent i = new Intent(StartActivity.this, MainActivity.class);
                i.putExtra("username",name);
                startActivity(i);
            }
        });
    }
}
