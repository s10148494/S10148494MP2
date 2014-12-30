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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;

public class MainActivity extends Activity {
	
	EditText etName;
	EditText etEmail;
	EditText etDate;
	EditText etTime;
	RadioButton rbCompliment;
	RadioButton rbComplain;
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
		rbCompliment = (RadioButton) this.findViewById(R.id.rbCompliment);
		rbComplain = (RadioButton) this.findViewById(R.id.rbComplain);
		cbMobile = (CheckBox) this.findViewById(R.id.cbMobile);
		cbInternet = (CheckBox) this.findViewById(R.id.cbInternet);
		btNext = (Button) this.findViewById(R.id.btNext);
		
		
		etDate.setOnClickListener(listener);
		etTime.setOnClickListener(listener);
		btNext.setOnClickListener(listenerView);
	}
	
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
			Intent i = new Intent(getBaseContext(), SecondActivity.class);
			i.putExtra("Name", strName);
			i.putExtra("Email", strEmail);
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
