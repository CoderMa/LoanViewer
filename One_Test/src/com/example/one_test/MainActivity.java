package com.example.one_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

//import android.widget.Button;

public class MainActivity extends Activity {

	private EditText editTextClient;
	private EditText editTextUser;
	private EditText editTextPwd;
	private CheckBox checkBox;
	private ImageButton imgbutton;

	public final static String EXTRA_MESSAGE = "com.example.one_test.MESSAGE";
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);

			// Boolean isSuccess = (Boolean) msg.obj;
			// if (isSuccess) {
			//
			// Intent _intent = new Intent(MainActivity.this,
			// PipelineActivity.class);
			// startActivityForResult(_intent, 100);
			//
			// }

			String strReturn = msg.obj.toString();
			if (strReturn != null) {
				Intent _intent = new Intent(MainActivity.this,
						PipelineActivity.class);
				_intent.putExtra(EXTRA_MESSAGE, strReturn);
				// startActivityForResult(_intent, 100);
				startActivity(_intent);
			}
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test3);

		editTextClient = (EditText) findViewById(R.id.editTextClientID);
		editTextUser = (EditText) findViewById(R.id.editTextUserID);
		editTextPwd = (EditText) findViewById(R.id.editTextPwdID);
		checkBox = (CheckBox) findViewById(R.id.checkBox1);
		imgbutton = (ImageButton) findViewById(R.id.imageButton);

		// pull checkbox's status from local db, set checkbox's status and
		// EditTexts' value.

		checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
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
				myThread mythread = new myThread();
				mythread.start();
			}
		});

	}

	class myThread extends Thread {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();

			// String urlGetString = "http://localhost:61666/EpsAPI.svc/xml/"
			// + editTextUser.getText().toString().trim();

			String urlGetString = "http://10.10.73.28:61666/EpsAPI.svc/xml/"
					+ editTextUser.getText().toString().trim();

			String urlPostString = "http://localhost:61666/EpsAPI.svc/"
					+ "Login/?UserName="
					+ editTextUser.getText().toString().trim() + "&Password="
					+ editTextPwd.getText().toString().trim();
			//
			// Boolean isSuccess = postMethod(urlPostString);
			// Message message = new Message();
			// message.obj = isSuccess;

			String strReturn = getMethod(urlGetString);
			Message message = new Message();
			message.obj = strReturn;

			handler.sendMessage(message);
		}
	}

	public Boolean postMethod(String uriString) {

		try {
			HttpClient client = new DefaultHttpClient();
			HttpPost request = new HttpPost(uriString);

			// List<NameValuePair> paramsList=new ArrayList<NameValuePair>();
			// paramsList.add(new BasicNameValuePair("username", "admin"));
			// paramsList.add(new BasicNameValuePair("password", "123456"));
			// HttpEntity entity =new
			// UrlEncodedFormEntity(paramsList,HTTP.UTF_8);
			// request.setEntity(entity);

			HttpResponse response = client.execute(request);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String strReturn = EntityUtils.toString(response.getEntity(),
						"UTF-8");
			} else {

				Toast.makeText(MainActivity.this,
						EntityUtils.toString(response.getEntity()),
						Toast.LENGTH_SHORT).show();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public String getMethod(String urlString) {
		String strReturn = null;
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpConnectionParams.setConnectionTimeout(httpclient.getParams(),
					3000);
			// HttpConnectionParams.setSoTimeout(httpclient.getParams(), 3000);
			// // Set read data timeout
			// ConnManagerParams.setTimeout(httpclient.getParams(), 3000); //
			// set pull connection from connection pool timeout

			HttpGet request = new HttpGet(urlString);

			// request.addHeader("Accept", "text/html");
			request.addHeader("Accept", "text/xml");
			// request.addHeader("Accept", "text/plain");
			HttpResponse response = httpclient.execute(request);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// strReturn=EntityUtils.toString(response.getEntity(),"UTF-8");
				HttpEntity entity = response.getEntity();
				InputStream instream = entity.getContent();
				strReturn = read(instream);
			}

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

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
