package com.example.dell.mathmayhem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.app.Activity;
import android.content.Intent;

import com.example.dell.mathmayhem.R;

public class MainActivity extends Activity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button buttonPlay =
                (Button)findViewById(R.id.buttonPlay);//associate button buttonPlay with thig having id buttonPlay
        final Button buttonhscore =
                (Button)findViewById(R.id.hscorebutton);
        final Button buttonquit =
                (Button)findViewById(R.id.quitbutton);

        buttonPlay.setOnClickListener(this);//enable clicking on buttonPlay
        buttonhscore.setOnClickListener(this);
        buttonquit.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {//click detecting method with input view of type View
        switch (view.getId()){
            case R.id.buttonPlay:
                Intent i;//variable denoting some sort of activity
                i = new Intent(this, GameActivity.class);//associate clicking of buttonPlay with launching of GameActivity class
                startActivity(i);// start above activity
                break;

            case R.id.hscorebutton:
                Intent j = new Intent(this, ScoreviewActivity.class);
                startActivity(j);
                finish();
                break;
            case R.id.quitbutton:
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(startMain);
                break;
        }
    }
}