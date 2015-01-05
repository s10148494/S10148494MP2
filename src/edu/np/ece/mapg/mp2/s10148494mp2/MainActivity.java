package edu.np.ece.mapg.mp2.s10148494mp2;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	EditText etName;
	EditText etEmail;
	EditText etDate;
	EditText etTime;
	RadioGroup rgPurpose;
	CheckBox cbMobile;
	CheckBox cbInternet;
	Button btNext;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		etName = (EditText) this.findViewById(R.id.etName);
		etEmail = (EditText) this.findViewById(R.id.etEmail);
		etDate = (EditText) this.findViewById(R.id.etDate);
		etTime = (EditText) this.findViewById(R.id.etTime);
		rgPurpose = (RadioGroup) this.findViewById(R.id.rgPurpose);
		rgPurpose.setOnCheckedChangeListener(radiogroupListener);
		cbMobile = (CheckBox) this.findViewById(R.id.cbMobile);
		cbInternet = (CheckBox) this.findViewById(R.id.cbInternet);
		btNext = (Button) this.findViewById(R.id.btNext);		
		etDate.setOnClickListener(listener);
		etTime.setOnClickListener(listener);
		btNext.setOnClickListener(listenerView);
		cbMobile.setOnCheckedChangeListener(checkboxListener);
		cbInternet.setOnCheckedChangeListener(checkboxListener);
	}
	
	private OnCheckedChangeListener checkboxListener = new OnCheckedChangeListener(){
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
			if(isChecked){
				Toast.makeText(MainActivity.this, "You have checked " + buttonView.getText().toString(), Toast.LENGTH_SHORT).show();
			}
		}
	};
	
	RadioGroup.OnCheckedChangeListener radiogroupListener = new RadioGroup.OnCheckedChangeListener(){
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId){
			if (checkedId == R.id.rbCompliment){
				Toast.makeText(MainActivity.this,"You have choosen to Complement", Toast.LENGTH_SHORT).show();
			}
			else{
				Toast.makeText(MainActivity.this,"You have choosen to Complain", Toast.LENGTH_SHORT).show();
			}
		}
	};
	
	OnClickListener listener = new OnClickListener(){
		@Override
		public void onClick(View v){
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH);
			int day = cal.get(Calendar.DAY_OF_MONTH);
			int hour = cal.get(Calendar.HOUR_OF_DAY);
			int minute = cal.get(Calendar.MINUTE);
			
			switch(v.getId()){
			case R.id.etDate:
				DatePickerDialog d = new DatePickerDialog(MainActivity.this, dateListener, year, month, day);
				d.show();
				break;
			case R.id.etTime:
				TimePickerDialog t = new TimePickerDialog(MainActivity.this, timeListener, hour, minute, false);
				t.show();
				break;
			}
		}
	};
	
	View.OnClickListener listenerView = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			String strName = etName.getText().toString();
			String strEmail = etEmail.getText().toString();
			String strDateTime = etDate.getText().toString() + ", " + etTime.getText().toString();
			int checkedId = rgPurpose.getCheckedRadioButtonId();
			RadioButton rb = (RadioButton) findViewById(checkedId);
			String strRadioBt = rb.getText().toString();
			String strCb = null;
			
			if(cbMobile.isChecked() && cbInternet.isChecked()){
				strCb = "Mobile Service, Internet Service";
			}
			else if (cbInternet.isChecked()){
				strCb = "Internet Service";
			}
			else{
				strCb = "Mobile Service";
			}
			
			
			Intent i = new Intent(getBaseContext(), SecondActivity.class);
			
			i.putExtra("Name", strName);
			i.putExtra("Email", strEmail);
			i.putExtra("DateTime", strDateTime);
			i.putExtra("RadioBt", strRadioBt);
			i.putExtra("CheckBox", strCb);
			
			Toast.makeText(MainActivity.this,"Enter your Feedback", Toast.LENGTH_SHORT).show();
			
			startActivity(i);
		}
	};
	
 	OnDateSetListener dateListener = new OnDateSetListener(){
 		@Override
 		public void onDateSet(DatePicker view, int year, int month, int day){
 			Calendar c = Calendar.getInstance();
 			c.set(Calendar.YEAR, year);
 			c.set(Calendar.MONDAY, month);
 			c.set(Calendar.DAY_OF_MONTH, day);
 			SimpleDateFormat f = new SimpleDateFormat("yyyy-MMM-dd");
 			String str = f.format(c.getTime());
 			etDate.setText(str);
 		}
 	};
 	
 	OnTimeSetListener timeListener = new OnTimeSetListener(){
 		@Override
 		public void onTimeSet(TimePicker view, int hour, int minute){
 			Calendar c = Calendar.getInstance();
 			c.set(Calendar.HOUR_OF_DAY, hour);
 			c.set(Calendar.MINUTE, minute);
 			SimpleDateFormat f = new SimpleDateFormat("hh:mm");
 			String str = f.format(c.getTime());
 			etTime.setText(str);
 		}
 	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
