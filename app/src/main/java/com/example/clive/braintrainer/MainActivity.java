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
        put(1, new ArrayList<String>(){{ add("Harus menyesuaikan keadaan"); add("adapt"); add("Adjust "); add("Basically"); add("Anyway "); add("2");}});
        put(2, new ArrayList<String>(){{ add("Eh tadi sampai dimana sih obrolan kita?"); add("Anyways"); add("by the way"); add("Basically"); add("Anyway "); add("1");}});
        put(3, new ArrayList<String>(){{ add("Pertemanan/Sahabat"); add("Circle"); add("Friends"); add("Bestie"); add("Clingy"); add("1");}});
        put(4, new ArrayList<String>(){{ add("Pendapat yang sejujurnya"); add("Honestly"); add("Jujurly"); add("Wichiz"); add("Clingy"); add("1");}});
        put(5, new ArrayList<String>(){{ add("Pertemanan/Sahabat"); add("Circle"); add("Friends"); add("Bestie"); add("Clingy"); add("1");}});
        put(6, new ArrayList<String>(){{ add("Berbicara yang dihalus-haluskan"); add("Circle"); add("Sugar Coating"); add("Bestie"); add("Clingy"); add("2");}});

        /*
        put(4, new ArrayList<String>(){{ add("What % of observations lie two standard deviations above the mean"); add("2.5%"); add("5%"); add("7.5%"); add("25%"); add("1");}});
        put(5, new ArrayList<String>(){{ add("A Zero-coupon bond which pays $25,000 in 10 years has an interest rate of 6%. What is the price of that bond today?"); add("$13,959.87"); add("$14,235.65"); add("$11,278.00"); add("$16,000"); add("1");}});
        put(6, new ArrayList<String>(){{ add("A group of 5 companies have profit margins of 55%, 25%, 10%, 15%, and 30%. What is the range of profit margins?"); add("25%"); add("45%"); add("15%"); add("12%"); add("2");}});
        put(7, new ArrayList<String>(){{ add("Which method would you use to choose k number of objects from a total of n, if order of choosing does not matter?"); add("binomial formula"); add("multinomial formula"); add("quadratic formula"); add("multiset theory"); add("1");}});
        put(8, new ArrayList<String>(){{ add("What is the current price of a 5yr bond with a $1,000 face value, a 7% annual coupon and an 8.5% YTM?"); add("$880.00"); add("$1,020.20"); add("$900.67"); add("$940.89"); add("4");}});
        put(9, new ArrayList<String>(){{ add("What is the current price of a 4yr bond with a $1,000 face value, a 4% annual coupon and an 5% YTM?"); add("$905.00"); add("$874.34"); add("$964.54"); add("$679.80"); add("3");}});
        put(10, new ArrayList<String>(){{ add("What is the current price of a 4yr bond with a $1,000 face value, a 4% annual coupon and an 3% YTM?"); add("$998.12"); add("$780.12"); add("$970.23"); add("$1,037.17"); add("4");}});
        put(11, new ArrayList<String>(){{ add("Which of the following is a bond that the issuer can redeem before it reaches maturity?"); add("putable bond"); add("callable bond"); add("convertible bond"); add("zero-coupon bond"); add("2");}});
        put(12, new ArrayList<String>(){{ add("The risk free-rate is calculated by subtracting what from a matching-duration treasury bond?"); add("Yield-to-maturity"); add("Gilt rate"); add("Inflation rate"); add("Sovereign rate"); add("3");}});
        put(13, new ArrayList<String>(){{ add("To calculate the price of a bond you require the face value, the term length, the yield-to-maturity and what else?"); add("Coupon rate"); add("Risk-free rate"); add("Treasury rate"); add("LIBOR rate"); add("1");}});
        put(14, new ArrayList<String>(){{ add("Compared with passive fund management, active management involves what?"); add("Benchmarking"); add("Indexing"); add("Lower fees"); add("Stock Selection"); add("4");}});
        put(15, new ArrayList<String>(){{ add("What term describes the excess return on an investment relative to the benchmark return?"); add("Alpha"); add("Beta"); add("Gamma"); add("Delta"); add("1");}});
        put(16, new ArrayList<String>(){{ add("What term measures the volatility of a security compared the market as a whole?"); add("Alpha"); add("Beta"); add("Gamma"); add("Delta"); add("2");}});
        put(17, new ArrayList<String>(){{ add("What profitability ratio is calculated by dividing operating profit by sales?"); add("Net margin"); add("Pre-tax margin"); add("Operating margin"); add("Gross margin"); add("3");}});
        put(18, new ArrayList<String>(){{ add("What term describes the change in price of a derivative given a $1 change its underlying security?"); add("Alpha"); add("Beta"); add("Gamma"); add("Delta"); add("1"); add("4");}});
        put(19, new ArrayList<String>(){{ add("What ratio is calculated by summing a company's cash, marketable securities, and receivables and dividing by their current liabilities?"); add("Quick Ratio"); add("Current Ratio"); add("Cash Ratio"); add("Debt Ratio"); add("1");}});
        put(20, new ArrayList<String>(){{ add("What metric is calculated by dividing the company's net income by the dividend paid to shareholders"); add("Fixed charge coverage"); add("Leverage ratio"); add("Interest Ratio"); add("Coverage ratio"); add("4");}});
*/
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

        for(int i = 1; i <= questions.size(); i++) {
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
