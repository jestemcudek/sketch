package com.example.photosketch;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.Toast;

public class EditPhotoActivity extends Activity {
	Paint paint = new Paint();
	DrawingView dView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_photo);
		Intent intent = getIntent();
		Bitmap bitmap = (Bitmap) intent.getParcelableExtra("BitmapImage");
		dView = (DrawingView) findViewById(R.id.drawingView1);
		dView.setImageBitmap(bitmap);
		SeekBar seekbar = (SeekBar) findViewById(R.id.seekBar1);
		paint.setStrokeWidth(seekbar.getProgress());
		// dView.setLayerPaint(paint);
		seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				dView.ChangeStroke((float) progress);

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}
		});

		final Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch (spinner.getSelectedItemPosition()) {
				case 0:
					dView.ChangeColor(Color.RED);
					break;
				case 1:
					dView.ChangeColor(Color.GREEN);
					break;
				case 2:
					dView.ChangeColor(Color.BLUE);
					break;
				case 3:
					dView.ChangeColor(Color.YELLOW);
					break;
				case 4:
					dView.ChangeColor(Color.BLACK);
					break;
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}

		});
		// dView.setLayerPaint(paint);
	}

	@Override
	protected void onStop() {
		Intent intent = new Intent();
		intent.putExtra("Edited", dView.getDrawingCache());
		setResult(RESULT_OK, intent);
		Toast.makeText(getApplicationContext(), "Koniec", Toast.LENGTH_SHORT)
				.show();
		super.onStop();
	}

	public void StopEditing(View v) {
		Intent intent = new Intent();
		intent.putExtra("Edited", dView.getDrawingCache());
		setResult(RESULT_OK, intent);
		
	this.finish();
	}

}
