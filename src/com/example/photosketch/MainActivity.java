package com.example.photosketch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	static final int REQUEST_IMAGE_CAPTURE = 1;
	static final int EDITED_PHOTO = 2;

	private Bitmap bitmap;
	private Bitmap editedBitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button makePhoto = (Button) findViewById(R.id.makePhotoButton);
		makePhoto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				if (intent.resolveActivity(getPackageManager()) != null) {
					startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
				}
			}
		});

		Button editPhoto = (Button) findViewById(R.id.editPhotoButton);
		editPhoto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						EditPhotoActivity.class);
				intent.putExtra("BitmapImage", bitmap);
				startActivityForResult(intent, 2);

			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
			Bundle extras = data.getExtras();
			bitmap = (Bitmap) extras.get("data");
			// if(imageBitmap != null)
			// Toast.makeText(getApplicationContext(), "Zrobiono zdjêcie",
			// Toast.LENGTH_SHORT).show();
			// else
			// Toast.makeText(getApplicationContext(), "Nie zrobiono zdjêcia",
			// Toast.LENGTH_SHORT).show();

		}
		else if (resultCode == RESULT_OK) {
			Bundle extras = data.getExtras();
			editedBitmap = (Bitmap) extras.get("Edited");
			if(editedBitmap!=null)
				Toast.makeText(getApplicationContext(), "Jest bitmapa", Toast.LENGTH_SHORT).show();
			else
				Toast.makeText(getApplicationContext(),"Nie ma bitmapy",Toast.LENGTH_SHORT).show();
			
		}
		super.onActivityResult(requestCode, resultCode, data);

	}
	public void SaveClick(View v) {
		DrawingView dv = (DrawingView)findViewById(R.id.drawingView1);
		Bitmap bmp = dv.getDrawingCache();
		if (bmp != null) {
			Toast.makeText(getApplicationContext(), "Coœ tam jest", Toast.LENGTH_SHORT).show();
			Bitmap bm = bmp;

			File root = Environment.getExternalStorageDirectory();
			File file = new File(root.getAbsolutePath()
					+ "/DCIM/Camera/img.jpg");
			try {
				file.createNewFile();
				FileOutputStream ostream = new FileOutputStream(file);
				bm.compress(CompressFormat.JPEG, 100, ostream);
				ostream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
			Toast.makeText(getApplicationContext(), "Brak bitmapy", Toast.LENGTH_SHORT).show();
	}
}
