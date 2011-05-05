package com.DragAndDroid.TestBed;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class TestBed extends Activity {
	
	SeekBar seek;
	Button btn;
	EditText text;
	TextView label;
	RelativeLayout rl;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);   
        
        seek = (SeekBar) findViewById(R.id.slider);
        
        btn = (Button) findViewById(R.id.Btn);
        text = (EditText) findViewById(R.id.text);
        label = (TextView) findViewById(R.id.label_);
        rl = (RelativeLayout)findViewById(R.id.myLayout);
    }


	private SeekBar.OnSeekBarChangeListener seekBarChangeListener =  new SeekBar.OnSeekBarChangeListener()
	{

		public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser)
		{
		//TODO: Your Code Here
		}

		public void onStartTrackingTouch(SeekBar seekBar) 
		{
		//TODO: Your Code Here
		}

		public void onStopTrackingTouch(SeekBar seekBar) 
		{
		//TODO: Your Code Here
		}

	};

	public void Btn_onClick(View view)
	{
		label.setText( text.getText().toString() );
		int val = seek.getProgress()*256/100;
		
		rl.setBackgroundColor( Color.rgb(val, val, val) );
	}
	
}