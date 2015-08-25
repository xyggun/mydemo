package com.xyg.mydemo.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetImageTaskOnImageView extends AsyncTask<String, Void, Bitmap> {
	InputStream is = null;
	ImageView mImageAndTextView = null;
	Context Context = null;
	int mScreenWidth, mImageHeight;// 屏幕宽度，图片高度
	String ImgUrl;// 图片地址

	public GetImageTaskOnImageView(Context context, ImageView imgView,
			String imgUrl, int width, int height) {
		mImageAndTextView = imgView;
		Context = context;
		ImgUrl = imgUrl;
		mScreenWidth = width;
		mImageHeight = height;
	}

	@Override
	protected Bitmap doInBackground(String... params) {
		URL myFileUrl = null;
		Bitmap bitmap = null;
		InputStream is = null;
		HttpURLConnection conn = null;
		try {
			myFileUrl = new URL(ImgUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			conn = (HttpURLConnection) myFileUrl.openConnection();
			conn.setDoInput(true);
			conn.connect();
			is = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (conn != null) {
					conn.disconnect();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bitmap;
	}

	@Override
	protected void onPreExecute() {
		// Drawable drawable =
		// Context.getResources().getDrawable(R.drawable.loading);
		/*Bitmap bitmap = BitmapFactory.decodeResource(Context.getResources(),
				R.drawable.loading);// 将资源id转化为bitmap
		mImageAndTextView.setImageBitmap(bitmap);*/
		//mImageAndTextView.setImageResource(R.drawable.loading);
		super.onPreExecute();
	}

	@Override
	protected void onCancelled() {
		super.onCancelled();
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		mImageAndTextView.setImageBitmap(result);
		mImageAndTextView.postInvalidate(0, 0, mScreenWidth, mImageHeight + 30); // 只更新稍比图片大一些的区域
		super.onPostExecute(result);
	}
}
