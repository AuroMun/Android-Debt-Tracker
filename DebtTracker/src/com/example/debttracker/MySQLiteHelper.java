package com.example.debttracker;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME="MoneyDB";
	private static final String TABLE_MONEY="money";
	private static final String KEY_ID="id";
	private static final String KEY_MONEY="money";
	private static final String KEY_NAME="name";
	private static final String[] COLUMNS= {KEY_ID,KEY_MONEY,KEY_NAME};
	
	public MySQLiteHelper(Context context){
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db){
		String CREATE_MONEY_TABLE= "CREATE TABLE money ( " + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "money INTEGER, "+"name TEXT )";
		db.execSQL(CREATE_MONEY_TABLE);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		db.execSQL("DROP TABLE IF EXISTS money");
		this.onCreate(db);
	}
	
	public void addPerson(Person person){
		Log.d("addPerson", person.toString());
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_MONEY, person.getMoney());
		values.put(KEY_NAME, person.getName());
		db.insert(TABLE_MONEY, null, values);
		db.close();
	}
	public void deletePerson(Person person){
		Log.d("deletePerson",person.toString());
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_MONEY, KEY_ID + " = ?", new String[] {String.valueOf( person.getId()) });
	}
	public Person getPerson(int  id){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = (Cursor) db.query(TABLE_MONEY, 
				COLUMNS,
				" id = ?",
				new String[] {String.valueOf(id)},
				null,null,null,null);
		if(cursor!=null)cursor.moveToFirst();
		Person person = new Person();
		person.setId(Integer.parseInt(cursor.getString(0)));
		person.setMoney(Integer.parseInt(cursor.getString(1)));
		person.setName(cursor.getString(2));
		Log.d("getBook("+id+")", person.toString());
		return person;
	}
	
	public Person getPerson(String name){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = (Cursor) db.query(TABLE_MONEY, 
				COLUMNS,
				" name = ?",
				new String[] {name},
				null,null,null,null);
		if(cursor!=null)cursor.moveToFirst();
		Person person = new Person();
		person.setId(Integer.parseInt(cursor.getString(0)));
		person.setMoney(Integer.parseInt(cursor.getString(1)));
		person.setName(cursor.getString(2));
		Log.d("getBook("+name+")", person.toString());
		return person;
	}
	public int checkPerson(String name){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = (Cursor) db.query(TABLE_MONEY,
				COLUMNS,
				" name = ?",
				new String[] {name},
				null,null,null,null);
		if(cursor.moveToFirst()==false)return 0;
		return 1;
	}
	public int updatePerson(Person person){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values= new ContentValues();
		values.put("name",person.getName());
		values.put("money", person.getMoney());
		int i = db.update(TABLE_MONEY, values, KEY_ID+" = ?", new String[] {String.valueOf(person.getId())});
		db.close();
		return i;
	}
	public List<Person> getAllPersons(){
		List<Person> persons = new LinkedList<Person>();
		return persons;
	}
	public String[] getPersonNames(){
		SQLiteDatabase db = this.getWritableDatabase();
		String query = "SELECT * FROM money";
		List<String> strlist = new ArrayList<String>();
		Cursor cursor = db.rawQuery(query, null);
		if(cursor.moveToFirst())
		{
			do{
				strlist.add(cursor.getString(2));
			}while(cursor.moveToNext());
		}
		return strlist.toArray(new String[0]);
	}
	
}
