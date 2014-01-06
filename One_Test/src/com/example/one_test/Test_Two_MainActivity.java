package com.example.one_test;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Test_Two_MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test__two__main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test__two__main, menu);
		return true;
	}

}
