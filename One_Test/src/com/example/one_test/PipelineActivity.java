package com.example.one_test;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PipelineActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pipeline);
		
		
		Button button = (Button) findViewById(R.id.button1);
		// button.setOnClickListener(new LoginClickListener());

		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent _intent = new Intent(PipelineActivity.this,
						SummaryActivity.class);
				startActivityForResult(_intent, 100);
			}
		});

		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pipeline, menu);
		return true;
	}

}
