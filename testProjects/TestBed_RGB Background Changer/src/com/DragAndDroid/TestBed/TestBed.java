package com.DragAndDroid.TestBed;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

public class TestBed extends Activity {
	
	private int seekR, seekG, seekB;
	SeekBar redSeekBar, greenSeekBar, blueSeekBar;
	RelativeLayout mScreen;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mScreen = (RelativeLayout) findViewById(R.id.myScreen);
        redSeekBar = (SeekBar) findViewById(R.id.Red);
        greenSeekBar = (SeekBar) findViewById(R.id.green);
        blueSeekBar = (SeekBar) findViewById(R.id.blue);
        updateBackground();

        redSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        greenSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        blueSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
    }

	private SeekBar.OnSeekBarChangeListener seekBarChangeListener =  new SeekBar.OnSeekBarChangeListener()
	{

		public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser)
		{
			updateBackground();
		}

		public void onStartTrackingTouch(SeekBar seekBar) 
		{
		
		}

		public void onStopTrackingTouch(SeekBar seekBar) 
		{
		
		}

	};
	
	private void updateBackground()
	{
	 seekR = redSeekBar.getProgress();
	 seekG = greenSeekBar.getProgress();
	 seekB = blueSeekBar.getProgress();
	 mScreen.setBackgroundColor(
	  0xff000000
	  + seekR * 0x10000
	  + seekG * 0x100
	  + seekB
	  );
	}

}