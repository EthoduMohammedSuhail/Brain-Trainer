package com.example.braintrainer;


import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdRequest;

import java.util.ArrayList;
import java.util.Random;
import com.example.braintrainer.R;
public class MainActivity extends Activity {
	Button startButton; 
	RelativeLayout relativeLayout;
	Button playAgain;
	TextView questionTextView;
	TextView timerTextView;
	int score = 0;
	int numberOfQuestions = 0;
	TextView resultTextView;
	TextView pointsTextView;
	int correctAnswerPosition;
	Random randomGenerator = new Random();
	ArrayList<Integer> answers = new ArrayList<Integer>();
	Button button0;
	Button button1 ;
	Button button2;
	Button button3 ;
	public void playAgain(View view)
	{
		score = 0;
		
		numberOfQuestions = 0;
		timerTextView.setText("30s");
		pointsTextView.setText("0/0") ;
		resultTextView.setText("");
		
		 playAgain.setVisibility(View.INVISIBLE);
		generateQuestion();
		new CountDownTimer(30100, 1000)
		{
			public void onTick(long milliSecondsUntilFinished)
			{
				timerTextView.setText(String.valueOf(milliSecondsUntilFinished/1000) + "s");
			}
			public void onFinish()
			{
				timerTextView.setText("0s");
				resultTextView.setText("Done" + " Your score is " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
	           playAgain.setVisibility(View.VISIBLE);		
			}
		}.start();
		
	}
	public void generateQuestion()
	{
		int a = randomGenerator.nextInt(21);
		int b = randomGenerator.nextInt(21);
		int c = a + b;
		questionTextView.setText(Integer.toString(a) + "+ " + Integer.toString(b));
		 correctAnswerPosition = randomGenerator.nextInt(4);
		 answers.clear();
		for(int i = 0; i<4; i++)
		{
			if(i== correctAnswerPosition)
			{
				answers.add(c);
			}
			else
			{
		   int inCorrectAnswer = randomGenerator.nextInt(21);
				while(c== inCorrectAnswer)
				{
				inCorrectAnswer= randomGenerator.nextInt(21);
				}
           answers.add(inCorrectAnswer);
			}
		}
			button0.setText(Integer.toString(answers.get(0)));
		button1.setText(Integer.toString(answers.get(1)));
		button2.setText(Integer.toString(answers.get(2)));
		button3.setText(Integer.toString(answers.get(3)));
		
	}
	public void start(View view){
		startButton.setVisibility(View.INVISIBLE);
		relativeLayout.setVisibility(View.VISIBLE);
		playAgain(findViewById(R.id.playAgain));
		
	}
	public void chooseAnswer(View view)
	{
		 if(timerTextView.getText() != "0s")
		 {
		numberOfQuestions++;
		
		if(view.getTag().toString().equals(Integer.toString(correctAnswerPosition)))
		{
			resultTextView.setText("Correct");
			score++;
			
		}
		else
		{
			resultTextView.setText("Incorrect");
			
		}
		pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
	
	 
		generateQuestion();
		 }
	 
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		startButton = (Button) findViewById(R.id.start);
		timerTextView = (TextView) findViewById(R.id.timeTextView);
		pointsTextView = (TextView) findViewById(R.id.pointsTextView);
		resultTextView = (TextView) findViewById(R.id.resultTextView);
		 questionTextView = (TextView)findViewById(R.id.questionTextView);
		playAgain = (Button) findViewById(R.id.playAgain);
		relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
		 button0 =(Button) findViewById(R.id.button0);
		 button1 =(Button) findViewById(R.id.button1);
		 button2 =(Button) findViewById(R.id.button2);
		 button3 =(Button) findViewById(R.id.button3); 
		 AdView adView = (AdView) findViewById(R.id.adView);
			AdRequest adRequest = new AdRequest.Builder().build();
			adView.loadAd(adRequest);
		
		 Toast.makeText(getApplicationContext(), "Developer : Mohammed Suhail", Toast.LENGTH_LONG).show();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
