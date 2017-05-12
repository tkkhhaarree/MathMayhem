package com.example.dell.mathmayhem;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends Activity implements View.OnClickListener {//GameActivity has things with clicking ability
    int correctAnswer;
    Button buttonObjectChoice1;
    Button buttonObjectChoice2;
    Button buttonObjectChoice3;
    TextView textObjectPartA;
    TextView textObjectPartB;
    TextView operator;
    TextView textObjectScore;
    TextView textObjectLevel;
    public static int currentScore = 0;
    int currentLevel = 1;
    int answerGiven;
    int wrongAnswer1;
    int wrongAnswer2;
    TextView texthint;
    boolean correctTrueOrFalse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        textObjectPartA =
                (TextView) findViewById(R.id.textPartA);//associate variable textObjectPartA to "thing with id textPartA"
        textObjectPartB =
                (TextView) findViewById(R.id.textPartB);
        operator =
                (TextView) findViewById(R.id.textOperator);
        texthint = (TextView) findViewById(R.id.hint);
        textObjectScore = (TextView) findViewById(R.id.textScore);
        textObjectLevel = (TextView) findViewById(R.id.textLevel);
        buttonObjectChoice1 =
                (Button) findViewById(R.id.buttonChoice1);//associate variable buttonObjectChoice1 to "thing with id buttonChoice1"
        buttonObjectChoice2 =
                (Button) findViewById(R.id.buttonChoice2);
        buttonObjectChoice3 =
                (Button) findViewById(R.id.buttonChoice3);


        buttonObjectChoice1.setOnClickListener(this);//enable clicking on buttonObjectChoice1
        buttonObjectChoice2.setOnClickListener(this);
        buttonObjectChoice3.setOnClickListener(this);
        setQuestion();

    }

    @Override
    public void onClick(View view) {// method onClick with parameter view of type View
        //declare a new int to be used in all the cases

        switch (view.getId()) {//get id of clicked thing
            case R.id.buttonChoice1:
//initialize a new int with the value contained in buttonObjectChoice1
//Remember we put it there ourselves previously
                answerGiven = Integer.parseInt("" + buttonObjectChoice1.getText());
                break;
            case R.id.buttonChoice2:
//same as previous case but using the next button
                answerGiven = Integer.parseInt("" +
                        buttonObjectChoice2.getText());
                break;
            case R.id.buttonChoice3:
                //same as previous case but using the next button
                answerGiven = Integer.parseInt("" +
                        buttonObjectChoice3.getText());

                break;

        }
        updateScoreAndLevel(answerGiven);
        if (correctTrueOrFalse){
            setQuestion();
        }
        else{
            Intent myIntent = new Intent(GameActivity.this, HscoreActivity.class);
            myIntent.putExtra("highscore", currentScore);
            GameActivity.this.startActivity(myIntent);
            finish();
        }
    }

    void setQuestion() {
        //generate the parts of the question
        int numberRange = currentLevel * 3;
        Random randInt = new Random();
        int partA = (numberRange - 3) + randInt.nextInt(6);
        partA++;//don't want a zero value
        int partB = randInt.nextInt(numberRange);
        partB++;//don't want a zero value

        int symbol = randInt.nextInt(4);
        if (symbol == 0) {
            operator.setText("+");
            texthint.setText("");
            correctAnswer = partA + partB;
        }
        if (symbol == 1) {
            operator.setText("-");
            texthint.setText("");
            correctAnswer = partA - partB;
        }
        if (symbol == 2) {
            operator.setText("*");
            texthint.setText("");
            correctAnswer = partA * partB;
        }
        if (symbol == 3) {
            operator.setText("/");
            texthint.setText(" find quotient");
            correctAnswer = partA / partB;
        }

        do {
            wrongAnswer1 = randInt.nextInt(10) + (correctAnswer - 5);
            wrongAnswer2 = randInt.nextInt(10) + (correctAnswer - 5);
        }
        while (wrongAnswer1 == wrongAnswer2 || wrongAnswer1 == correctAnswer || wrongAnswer2 == correctAnswer);

        textObjectPartA.setText("" + partA);
        textObjectPartB.setText("" + partB);
        //A number between 0 and 2
        int buttonLayout = randInt.nextInt(3);
        switch (buttonLayout) {
            case 0:
                buttonObjectChoice1.setText("" + correctAnswer);
                buttonObjectChoice2.setText("" + wrongAnswer1);
                buttonObjectChoice3.setText("" + wrongAnswer2);
                break;
            case 1:
                buttonObjectChoice2.setText("" + correctAnswer);
                buttonObjectChoice3.setText("" + wrongAnswer1);
                buttonObjectChoice1.setText("" + wrongAnswer2);
                break;
            case 2:
                buttonObjectChoice3.setText("" + correctAnswer);
                buttonObjectChoice1.setText("" + wrongAnswer1);
                buttonObjectChoice2.setText("" + wrongAnswer2);
                break;
        }
    }

    void updateScoreAndLevel(int answerGiven) {
        if (isCorrect(answerGiven)) {
            for (int i = 1; i <= currentLevel; i++) {
                currentScore = currentScore + i;
            }
            currentLevel++;
        }

        textObjectScore.setText("score: " + currentScore);
        textObjectLevel.setText("level: " + currentLevel);
    }

    boolean isCorrect(int answerGiven) {
        if (answerGiven == correctAnswer) {//YAY!
            Toast.makeText(getApplicationContext(), "Well done!",
                    Toast.LENGTH_SHORT).show();
            correctTrueOrFalse = true;
        } else {//Uh-oh!
            correctTrueOrFalse = false;
        }
        return correctTrueOrFalse;
    }
}

