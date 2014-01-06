package com.example.one_test;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
//import android.widget.Button;
import android.widget.ImageButton;
import android.content.Intent;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ImageButton imgbutton = (ImageButton) findViewById(R.id.imageButton);
		//Button button = (Button) findViewById(R.id.button1);
		// button.setOnClickListener(new LoginClickListener());
		
		

		
		

		imgbutton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent _intent = new Intent(MainActivity.this,
						Test3Activity.class);
				startActivityForResult(_intent, 100);
			}
		});

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
