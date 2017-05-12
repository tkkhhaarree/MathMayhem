package com.example.dell.mathmayhem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreviewActivity extends AppCompatActivity {
    public int curhscore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreview);
        TextView textscore=(TextView) findViewById(R.id.scoretext);

        SharedPreferences mPrefs = getSharedPreferences("hsvalue",0);
        int x = mPrefs.getInt("hskey", 0);
        textscore.setText(""+String.valueOf(x));

        curhscore=Integer.parseInt("" + textscore.getText());
        final Button back= (Button) findViewById(R.id.backbutton);
        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backintent= new Intent(ScoreviewActivity.this, MainActivity.class);
                ScoreviewActivity.this.startActivity(backintent);
                finish();
            }
        });
    }
}
