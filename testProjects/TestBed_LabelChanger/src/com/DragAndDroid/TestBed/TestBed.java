package com.DragAndDroid.TestBed;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class TestBed extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }


	public void btn1_onClick(View view)
	{
		TextView tv = (TextView) findViewById(R.id.label_);
		EditText et = (EditText) findViewById(R.id.txtBox);
		
		tv.setText( et.getText().toString());
	}
}