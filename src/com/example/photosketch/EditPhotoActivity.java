package com.example.photosketch;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SeekBar;

public class EditPhotoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Paint paint = new Paint();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_photo);
		Intent intent = getIntent();
		Bitmap bitmap = (Bitmap) intent.getParcelableExtra("BitmapImage");
		DrawingView dView = (DrawingView)findViewById(R.id.drawingView1);
		dView.setImageBitmap(bitmap);
		SeekBar seekbar = (SeekBar)findViewById(R.id.seekBar1);
		paint.setStrokeWidth(seekbar.getProgress());
		dView.setLayerPaint(paint);
		
	}
}
