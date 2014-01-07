package com.example.one_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
//import android.widget.Button;

public class MainActivity extends Activity {

	private EditText editTextClientID;
	private EditText editTextUserID;
	private EditText editTextPwd;
	private CheckBox checkBox;
	private ImageButton imgbutton; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test3);
		
		editTextClientID=(EditText)findViewById(R.id.editTextClientID);
		editTextUserID=(EditText)findViewById(R.id.editTextUserID);
		editTextPwd=(EditText)findViewById(R.id.editTextPwdID);
		checkBox=(CheckBox)findViewById(R.id.checkBox1);
		imgbutton = (ImageButton) findViewById(R.id.imageButton);
		
		// pull checkbox's status from local db, set checkbox's status and EditTexts' value.
		
		
		checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				checkBox.setChecked(isChecked);
			}
		});
		

		imgbutton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// the value of the checkbox has changed and its value is
				// different from value from db
				if (checkBox.isChecked()) {
					// Store the status to local db
				}
//				String uriString = "http://www.douban.com/feed/review/latest";
//				Uri uri = Uri.parse(uriString);
//				String strReturn = getMessage(uri,uriString);
//				editTextClientID.setText(strReturn);
				Intent _intent = new Intent(MainActivity.this,
						PipelineActivity.class);
				startActivityForResult(_intent, 100);
			}
		});

	}
	
	/**
	 * 
	 */
	public String getMessage(Uri uri,String uriString) {
		String strReturn = null;
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet request = new HttpGet(uriString);
			// "http://192.168.1.68:7001/AndroidJAX-RS/jaxrs/helloworld");

			// request.addHeader("Accept", "text/html");
			// request.addHeader("Accept", "text/xml");
			request.addHeader("Accept", "text/plain");
			HttpResponse response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();
			InputStream inputStream = entity.getContent();
			strReturn = read(inputStream);

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return strReturn;
	}
	
	
	private static String read(InputStream instream) {
        StringBuilder sb = null;
        try {
             sb = new StringBuilder();
             BufferedReader r = new BufferedReader(new InputStreamReader(
                       instream));
        for (String line = r.readLine(); line != null; line = r.readLine()) {
                 sb.append(line);
          }

          instream.close();

        } catch (IOException e) {
        }
        return sb.toString();
   }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
