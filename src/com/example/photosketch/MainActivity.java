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

	private Bitmap bitmap;

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
				startActivity(intent);

			}
		});

		Button savePhoto = (Button) findViewById(R.id.savePhotoButton);
		savePhoto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				DrawingView dView = (DrawingView)findViewById(R.id.drawingView1);
				Bitmap bm =dView.getDrawingCache();

	               Intent imageIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
	                File imagesFolder = new File(Environment.getExternalStorageDirectory(), "Punch");
	                imagesFolder.mkdirs(); 
	                String fileName = "image"  + ".jpg";
	                File output = new File(imagesFolder, fileName);
	                Uri uriSavedImage = Uri.fromFile(output);
	                imageIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
	                OutputStream fos = null;

	                try {
	                    fos = getContentResolver().openOutputStream(uriSavedImage);
	                    bm.compress(CompressFormat.JPEG, 100, fos);
	                    fos.flush();
	                    fos.close();
	                    } 
	                catch (FileNotFoundException e) 
	                    {
	                    e.printStackTrace();
	                    } 
	                catch (IOException e)
	                {
	                    e.printStackTrace();
	                } 
	                finally
	                {}               
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
	}
}
