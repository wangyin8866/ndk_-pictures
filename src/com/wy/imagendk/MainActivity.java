package com.wy.imagendk;

import java.io.InputStream;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView mImageView;
	private Bitmap mBitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mImageView = (ImageView) findViewById(R.id.iv);
		// mBitmap=readBitmap(this, R.drawable.wy);
		mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.a);
	}

	public void click(View view) {
		mImageView.setImageBitmap(mBitmap);

	}

	// java¥¶¿Ì
	public void javaClick(View view) {
		Bitmap bitmap = ImageJavaUtils.getImage(mBitmap);
		mImageView.setImageBitmap(bitmap);

	}

	public void ndkClick(View view) {
		int width = mBitmap.getWidth();
		int height = mBitmap.getHeight();
		int[] buffer = new int[width * height];
		mBitmap.getPixels(buffer, 0, width, 1, 1, width - 1, height - 1);
		int[] result = ImageNdkUtils.getImage(buffer, width, height);
		Bitmap bitmap = Bitmap.createBitmap(result, width, height,
				Bitmap.Config.RGB_565);
		mImageView.setImageBitmap(bitmap);
	}
}
