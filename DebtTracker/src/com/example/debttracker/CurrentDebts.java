package com.example.debttracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CurrentDebts extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_current_debts);
		Intent intent = getIntent();
		String lol=intent.getStringExtra(MainActivity.MESSAGE);
		TextView textView = (TextView) findViewById(R.id.textView);
		textView.setText(lol);
		textView.setTextSize(40);
		MySQLiteHelper db= new MySQLiteHelper(this);
		/*//db.addPerson(new Person(5,"Narayanan"));
		Person p = (Person) db.getPerson("Subramanian");
		textView.setText(p.getName());
		p.setName("Subramanian");
		db.updatePerson(p);
		//db.addPerson(new Person(10,"Auro"));*/
		
	}
	public void Change(View view){
		MySQLiteHelper db = new MySQLiteHelper(this);
		TextView textView = (TextView) findViewById(R.id.textView);
		EditText editText = (EditText) findViewById(R.id.editText3);
		EditText editText3 = (EditText) findViewById(R.id.editText);
		//Person p = (Person) db.getPerson(editText.getText().toString());
		//int money = p.getMoney();
		//textView.setText( String.valueOf(money));
		int monez=Integer.parseInt(editText.getText().toString());
		if(db.checkPerson(editText3.getText().toString())==0)
		db.addPerson(new Person(Integer.parseInt(editText.getText().toString()), editText3.getText().toString()));
		else{
			Person p =new Person();
			p=db.getPerson(editText3.getText().toString());
			p.setMoney(monez+p.getMoney());
			db.updatePerson(p);
		}
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
