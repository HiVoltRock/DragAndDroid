package com.DragAndDroid.TestBed;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class TestBed extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
    	
    }

	public void button1_onClick(View view)
	{

	}

	private SeekBar.OnSeekBarChangeListener seekBarChangeListener =  new SeekBar.OnSeekBarChangeListener()
	{

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser)
		{
		//TODO: Your Code Here
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) 
		{
		//TODO: Your Code Here
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) 
		{
		//TODO: Your Code Here
		}

	};

}