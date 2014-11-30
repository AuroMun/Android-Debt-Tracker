package com.example.debttracker;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;


public class MainActivity extends ActionBarActivity {
	public final static String MESSAGE="lol";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        TextView textView = (TextView) findViewById(R.id.textView1);
        String name = sharedPref.getString("NAME", "User");
        textView.setText(name);
        Intent intent1 = getIntent();
       // textView.setText(intent1.getStringExtra("flag"));
        textView.setText("Hello, "+name+".");
        String s = intent1.getStringExtra("flag");
         if(s!=null&&s.equals("A")){
        	 int money = Integer.parseInt(intent1.getStringExtra("Money"));
        	 money=0-money;
     	QRCodeEncoder qrCodeEncoder = new QRCodeEncoder(String.valueOf(money) +"."+name, 
	             null, 
	             Contents.Type.TEXT,  
	             BarcodeFormat.QR_CODE.toString(), 
	             1200);
		Bitmap bitmap;
		try {
			bitmap = (Bitmap) qrCodeEncoder.encodeAsBitmap();
			ImageView myImage = (ImageView) findViewById(R.id.imageView1);
		    myImage.setImageBitmap(bitmap);
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }
    }


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
        if(id==R.id.barUpdate){
        	Intent intent= new Intent(this,CurrentDebts.class);
        	startActivity(intent);
        	return true;
        }
        if(id==R.id.action_search2){
        	/*Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        	intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
    		startActivityForResult(intent, 0);*/
        	NameUpdate(findViewById(R.id.Main));
        }
        return super.onOptionsItemSelected(item);
    }
    public void Go(View view){
    	
    	Intent intent= new Intent(this,ListOfDebts.class);
    	intent.putExtra(MESSAGE, "LOL");
    	startActivity(intent);
    }
    public void Check(View view){
    	Intent intent= new Intent(this,CurrentDebts.class);
    	intent.putExtra(MESSAGE, "LOL");
    	startActivityForResult(intent,0);
    }
    public void Gen_QR(View view){
    	QRCodeEncoder qrCodeEncoder = new QRCodeEncoder("Hello\nLol", 
	             null, 
	             Contents.Type.TEXT,  
	             BarcodeFormat.QR_CODE.toString(), 
	             500);
		Bitmap bitmap;
		try {
			bitmap = (Bitmap) qrCodeEncoder.encodeAsBitmap();
			ImageView myImage = (ImageView) findViewById(R.id.imageView1);
		    myImage.setImageBitmap(bitmap);
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	public void QR(View view){
    	Intent intent = new Intent("com.google.zxing.client.android.SCAN");
    	intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
		startActivityForResult(intent, 0);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        String contents = null;
        super.onActivityResult(requestCode, resultCode, intent);
		MySQLiteHelper db = new MySQLiteHelper(this);
        TextView textView = (TextView) findViewById(R.id.textView1);
        if (requestCode == 0) {
              if (resultCode == RESULT_OK) {
                 contents = intent.getExtras().getString("SCAN_RESULT");
                 String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                //textView.setText(intent.getStringExtra("SCAN_RESULT") + "lol");
                String[] sep = intent.getStringExtra("SCAN_RESULT").split("\\.");
                if(db.checkPerson(sep[1])==0)
                db.addPerson(new Person(Integer.parseInt((sep[0])), sep[1]));
        		else{
        			Person p =new Person();
        			p=db.getPerson(sep[1]);
        			p.setMoney(Integer.parseInt(sep[0])+p.getMoney());
        			db.updatePerson(p);
        		}
                Context context = getApplicationContext();
        		Toast toast = Toast.makeText(context, "Updated!", Toast.LENGTH_SHORT);
        		toast.show();
                 // Handle successful scan
              } else if (resultCode == RESULT_CANCELED) {
                 // Handle cancel
              }
        }

    }
    @SuppressWarnings("deprecation")
    public void NameUpdate(View view){
	AlertDialog alertDialog = new AlertDialog.Builder(this).create();
	alertDialog.setTitle("Update Name");
	alertDialog.setMessage("Enter your new name");
	
	final EditText input = new EditText (this);
	
	final Activity activity = this;
	//activity = this;

	alertDialog.setView(input);
	alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
		
		@Override
		public void onClick(final DialogInterface dialog, final int which) {
			final TextView textView = (TextView) findViewById(R.id.textView1);
			SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = sharedPref.edit();
			editor.putString("NAME", input.getText().toString());
			if(!input.getText().toString().isEmpty())
			editor.commit();
			//textView.setText(input.getText().toString());
		}
	});
	alertDialog.show();
}
}
