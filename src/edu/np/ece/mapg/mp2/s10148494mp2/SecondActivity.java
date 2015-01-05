package edu.np.ece.mapg.mp2.s10148494mp2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends Activity {
	
	TextView tvName;
	TextView tvEmail;
	TextView tvDateTime;
	TextView tvRadioBt;
	TextView tvCheckBox;
	EditText etFeedback;
	Button btEmail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		tvName = (TextView) this.findViewById(R.id.tvName);
		tvEmail = (TextView) this.findViewById(R.id.tvEmail);
		tvDateTime = (TextView) this.findViewById(R.id.tvDateTime);
		tvRadioBt = (TextView) this.findViewById(R.id.tvRadioBt);
		tvCheckBox = (TextView) this.findViewById(R.id.tvCheckBox);
		etFeedback = (EditText) this.findViewById(R.id.etFeedback);
		btEmail = (Button) this.findViewById(R.id.btEmail);
		
		btEmail.setOnClickListener(listener);
		
		Intent i = this.getIntent();
		
		if (i != null){
			String Name = i.getStringExtra("Name");
			String Email = i.getStringExtra("Email");
			String DateTime = i.getStringExtra("DateTime");
			String RadioBt = i.getStringExtra("RadioBt");
			String CheckBox = i.getStringExtra("CheckBox");
			
			tvName.setText("Name: " + Name);
			tvEmail.setText("Email: " + Email);
			tvDateTime.setText("Date: " +DateTime);
			tvRadioBt.setText("Feedback: " + RadioBt);
			tvCheckBox.setText("Service: " + CheckBox);
		}
	}
	
	private View.OnClickListener listener = new View.OnClickListener(){
		public void onClick(View v){			
			Intent i = new Intent(Intent.ACTION_SEND);
			i.setType("text/plain");
			i.putExtra(Intent.EXTRA_EMAIL, new String[]{ "s10148494@connect.np.edu.sg" });
			i.putExtra(Intent.EXTRA_SUBJECT, "Service Feedback");
			i.putExtra("body", tvName.getText() + "\n" + tvEmail.getText() + "\n" + tvDateTime.getText() + "\n" + tvRadioBt.getText() + "\n" + tvCheckBox.getText() + "\n\n" + etFeedback.getText().toString());
			startActivity(i);
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
