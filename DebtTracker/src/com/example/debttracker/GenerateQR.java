package com.example.debttracker;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

public class GenerateQR extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_generate_qr);
		//RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.lay);
		QRCodeEncoder qrCodeEncoder = new QRCodeEncoder("Hello\nLol", 
	             null, 
	             Contents.Type.TEXT,  
	             BarcodeFormat.QR_CODE.toString(), 
	             300);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.generate_qr, menu);
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
