package com.example.debttracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CurrentDebts extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_current_debts);
		MySQLiteHelper db = new MySQLiteHelper(this);
		AutoCompleteTextView editText = (AutoCompleteTextView) findViewById(R.id.editText);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, db.getPersonNames());
		editText.setAdapter(adapter);	
	}
	public void Change(View view){
		MySQLiteHelper db = new MySQLiteHelper(this);
		AutoCompleteTextView editText = (AutoCompleteTextView) findViewById(R.id.editText);
		EditText editText3 = (EditText) findViewById(R.id.editText3);
		int monez=Integer.parseInt(editText3.getText().toString());
		if(db.checkPerson(editText.getText().toString())==0)
		db.addPerson(new Person(Integer.parseInt(editText3.getText().toString()), editText.getText().toString()));
		else{
			Person p =new Person();
			p=db.getPerson(editText.getText().toString());
			p.setMoney(monez+p.getMoney());
			db.updatePerson(p);
		}
		Context context = getApplicationContext();
		Toast toast = Toast.makeText(context, "Updated!", Toast.LENGTH_SHORT);
		toast.show();
		Intent intent = new Intent(this,MainActivity.class);
		intent.putExtra("flag", "A");
		intent.putExtra("Money", editText3.getText().toString());
		startActivity(intent);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.current_debts, menu);
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
