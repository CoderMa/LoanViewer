package com.example.one_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class PipelineActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pipeline);

		// Get the message from the intent
		Intent intent = getIntent();
		String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		// Create the text view
		TextView textView = new TextView(this);
		textView.setTextSize(40);
		textView.setText(message);
		// Set the text view as the activity layout
		setContentView(textView);

		// Button button = (Button) findViewById(R.id.button1);
		//
		// button.setOnClickListener(new OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// Intent _intent = new Intent(PipelineActivity.this,
		// SummaryActivity.class);
		// startActivityForResult(_intent, 100);
		// }
		// });

//		StringBuffer sb = new StringBuffer();
//
//		try {
//			String urlString = "http://10.10.2.2:8080/article/JSONDemo";
//			String body = getContent(urlString);
//
//			JSONArray array = new JSONArray(body);
//			for (int i = 0; i < array.length(); i++) {
//
//				JSONObject obj = array.getJSONObject(i);
//				sb.append("id").append(obj.getInt("id")).append("\t");
//
//				sb.append("name").append(obj.getString("name")).append("\r\n");
//
//				sb.append("gender").append(obj.getString("gender"))
//						.append("\t");
//				sb.append("email").append(obj.getString("email"))
//						.append("\r\n");
//
//				sb.append("-----------\r\n");
//			}
//
//			String strResult = sb.toString();
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

	private String getContent(String urlString) {
		StringBuilder sb = new StringBuilder();

		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpParams httpParams = httpclient.getParams();

			HttpConnectionParams.setConnectionTimeout(httpParams, 3000);
			// Set read data timeout
			HttpConnectionParams.setSoTimeout(httpParams, 5000);
			// set pull connection from connection pool timeout
			ConnManagerParams.setTimeout(httpParams, 3000);

			HttpGet request = new HttpGet(urlString);

			// request.addHeader("Accept", "text/html");
			request.addHeader("Accept", "text/json");
			// request.addHeader("Accept", "text/plain");
			HttpResponse response = httpclient.execute(new HttpGet(urlString));
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// strReturn=EntityUtils.toString(response.getEntity(),"UTF-8");
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(entity.getContent(), "UTF-8"),
							8192);

					String line = null;
					while ((line = reader.readLine()) != null) {
						sb.append(line + "\n");

					}
					reader.close();
				}

			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pipeline, menu);
		return true;
	}

}
