package com.example.clive.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView resultTextView;
    TextView pointsTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    TextView questionTextView;

    TextView timerTextView;
    RelativeLayout gameRelativeLayout;

    ArrayList<Integer> order = new ArrayList<Integer>();
    int questionIndex = 0;

//    Dictionary of the questions and answers. Index of the correct answer given in final Arraylist element
    HashMap<Integer, ArrayList<String>> questions = new HashMap<Integer, ArrayList<String>> () {{
        put(1, new ArrayList<String>(){{ add("What is the name of the ratio defined as the return of the portfolio minus the risk-free rate all divided by the standard deviation of the portfolio’s excess return?"); add("Sharpe Ratio"); add("Sortino Ratio"); add("Treynor Ratio"); add("Information Ratio"); add("1");}});
        put(2, new ArrayList<String>(){{ add("What is the name of the ratio defined as the return of the portfolio minus the risk-free rate all divided by the standard deviation of the downside risk?"); add("Information Ratio"); add("Sharpe Ratio"); add("Sortino Ratio"); add("Treynor Ratio"); add("3");}});
        put(3, new ArrayList<String>(){{ add("What ratio is given by the Earnings Per Share divided by the Share Price"); add("P/E"); add("EV/EBITDA"); add("P/B"); add("ROE"); add("1");}});
        put(4, new ArrayList<String>(){{ add("What % of observations lie two standard deviations above the mean"); add("2.5%"); add("5%"); add("7.5%"); add("25%"); add("1");}});
        put(5, new ArrayList<String>(){{ add("A Zero-coupon bond which pays $25,000 in 10 years has an interest rate of 6%. What is the price of that bond today?"); add("$13,959.87"); add("$14,235.65"); add("$11,278.00"); add("$16,000"); add("1");}});
        put(6, new ArrayList<String>(){{ add("A group of 5 companies have profit margins of 55%, 25%, 10%, 15%, and 30%. What is the range of profit margins?"); add("25%"); add("45%"); add("15%"); add("12%"); add("2");}});
        put(7, new ArrayList<String>(){{ add("Which method would you use to choose k number of objects from a total of n, if order of choosing does not matter?"); add("binomial formula"); add("multinomial formula"); add("quadratic formula"); add("multiset theory"); add("1");}});
        put(8, new ArrayList<String>(){{ add("What is the current price of a 5yr bond with a $1,000 face value, a 7% annual coupon and an 8.5% YTM?"); add("$880.00"); add("$1,020.20"); add("$900.67"); add("$940.89"); add("4");}});
        put(9, new ArrayList<String>(){{ add("What is the current price of a 4yr bond with a $1,000 face value, a 4% annual coupon and an 5% YTM?"); add("$905.00"); add("$874.34"); add("$964.54"); add("$679.80"); add("3");}});
        put(10, new ArrayList<String>(){{ add("What is the current price of a 4yr bond with a $1,000 face value, a 4% annual coupon and an 3% YTM?"); add("$998.12"); add("$780.12"); add("$970.23"); add("$1,037.17"); add("4");}});
//        put(, new ArrayList<String>(){{ add(""); add(""); add(""); add(""); add(""); add("2");}});

    }};

    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button)findViewById(R.id.startButton);
        button0 = (Button)findViewById((R.id.button0));
        button1 = (Button)findViewById((R.id.button1));
        button2 = (Button)findViewById((R.id.button2));
        button3 = (Button)findViewById((R.id.button3));
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        pointsTextView = (TextView)findViewById(R.id.pointsTextView);
        timerTextView = (TextView)findViewById(R.id.timerTextView);
        playAgainButton = (Button)findViewById(R.id.playAgainButton);
        gameRelativeLayout = (RelativeLayout)findViewById(R.id.gameRelativeLayout);
        questionTextView = (TextView)findViewById(R.id.questionTextView);

        for(int i = 1; i <= 10; i++) {
            order.add(i);
        }
        Collections.shuffle(order);
    }

    public void playAgain (View view) {
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);

        generateQuestion(questionIndex);

        new CountDownTimer(30100,1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0");
                resultTextView.setText("Your score: ");

            }
        }.start();
    }

    public void generateQuestion(int num) {
        int i = order.get(num);

        locationOfCorrectAnswer = Integer.valueOf(questions.get(i).get(5));
        String question = questions.get(i).get(0);
        questionTextView.setText(question);

        button0.setText(questions.get(i).get(1));
        button1.setText(questions.get(i).get(2));
        button2.setText(questions.get(i).get(3));
        button3.setText(questions.get(i).get(4));
    }

    public void chooseAnswer(View view) {
        if (view.getTag().toString().equals(String.valueOf(locationOfCorrectAnswer))) {

            System.out.println(view.getTag().getClass().getName());
            System.out.println(locationOfCorrectAnswer);

            score++;
            resultTextView.setText("Correct!");

        } else {
            resultTextView.setText("Wrong!");
        }

        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        questionIndex++;
        if (questions.get(questionIndex+1) == null) {
            questionIndex = 0;
            Collections.shuffle(order);
        }
        generateQuestion(questionIndex);

    }

    public void start(View view) {
        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(View.VISIBLE);

        playAgain(findViewById(R.id.playAgainButton));

    }


}
