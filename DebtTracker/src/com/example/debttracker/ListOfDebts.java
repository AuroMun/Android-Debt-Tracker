package com.example.debttracker;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

public class ListOfDebts extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_of_debts);
		final Context p = this;
		MySQLiteHelper DB = new MySQLiteHelper(this);
		SQLiteDatabase db = DB.getWritableDatabase();
		Person person = null;
		String query = "SELECT * FROM money ORDER BY money DESC";
		Cursor cursor = db.rawQuery(query, null);
		LinearLayout layout = (LinearLayout) findViewById(R.id.loal);
		//ScrollView layout = (ScrollView) findViewById(R.id.lol);
		//TableLayout layout = (TableLayout) findViewById(R.id.lol);
		//TableRow row = (TableRow) findViewById(R.id.row);
		//DB.checkPerson("Auros");
		//textView.setText("trolol");
		//layout.addView(textView);
		/*if(cursor.moveToFirst())
		{
			do{
				final TableRow row = new TableRow(this);
				person = new Person();
				person.setName(cursor.getString(2));
				person.setMoney(Integer.parseInt(cursor.getString(1)));
				TextView textView = new TextView(this);
				textView.setText(person.getName());
				row.addView(textView,0);
				TextView textName = new TextView(this);
				textName.setText(String.valueOf(person.getMoney()));//person.getMoney())
				textName.layout(0, 0, 1, 0);
				row.addView(textName,1);
				row.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						//row.setVisibility(v.GONE);
						TextView po = (TextView) row.getChildAt(0);
						float Size = po.getTextSize() * 120/100;
						po.setTextSize(TypedValue.COMPLEX_UNIT_PX, Size);
						TextView textView1 = new TextView(p);
						textView1.setText("YAY");
						row.addView(textView1,2);
						Log.i("TAG", "YAY");
					}
				});
				layout.addView(row);
			}while(cursor.moveToNext());
		}*/
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, DB.getPersonDetails("money",0));
		ListView view = new ListView(this);
		view.setId(1234);
		view.setAdapter(adapter);
		
		view.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		layout.addView(view);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_of_debts, menu);
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
