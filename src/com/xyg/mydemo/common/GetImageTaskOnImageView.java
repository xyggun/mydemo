package com.xyggun.mydemo.common;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class GetImageTaskOnImageView extends AsyncTask<String, Void, Bitmap> {
	InputStream is = null;
	ImageView mImageAndTextView = null;
	Context Context = null;
	int mScreenWidth, mImageHeight;// ��Ļ��ȣ�ͼƬ�߶�
	String ImgUrl;// ͼƬ��ַ

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
				R.drawable.loading);// ����Դidת��Ϊbitmap
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
		mImageAndTextView.postInvalidate(0, 0, mScreenWidth, mImageHeight + 30); // ֻ�����Ա�ͼƬ��һЩ������
		super.onPostExecute(result);
	}
}
