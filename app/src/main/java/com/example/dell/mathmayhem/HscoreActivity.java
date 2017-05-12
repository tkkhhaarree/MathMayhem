package com.example.dell.mathmayhem;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.dell.mathmayhem.ScoreviewActivity;


public class HscoreActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_hscore);
        TextView hshead= (TextView) findViewById(R.id.hsHeader);
        TextView hsscore= (TextView) findViewById(R.id.hsText);
        Intent intent = getIntent();
        int hs = intent.getIntExtra("highscore", 0);
        super.onCreate(savedInstanceState);
        hshead.setText("Score");
        hsscore.setText(""+hs);
        ScoreviewActivity instance=new ScoreviewActivity();
        if (instance.curhscore<hs){
            SharedPreferences mPrefs = getSharedPreferences("hsvalue", 0);
//Give any name for //preference as I have given "hsvalue" and value 0.
            SharedPreferences.Editor editor = mPrefs.edit();
            editor.putInt("hskey", hs);
// give key value as "hskey" you mentioned and value what you want to store
            editor.commit();
        }
        final Button mainmenu =
                (Button)findViewById(R.id.hsButton);
        mainmenu.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainmenuintent= new Intent(HscoreActivity.this, MainActivity.class);
                HscoreActivity.this.startActivity(mainmenuintent);
                finish();
            }
        });

    }

}