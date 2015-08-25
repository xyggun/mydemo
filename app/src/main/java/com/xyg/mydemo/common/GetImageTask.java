package com.xyg.mydemo.common;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;

import com.xyg.mydemo.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetImageTask extends AsyncTask<String, Void, Bitmap> {
	InputStream is = null;
	ImageView mImageAndTextView = null;
	Context Context = null;
	int mScreenWidth, mImageHeight;// 屏幕宽度，图片高度
	String ImgUrl;// 图片地址
	private View loadingview;
	private Dialog dialog;

	@SuppressLint("InflateParams")
	public GetImageTask(Context context, ImageView imgView, String imgUrl,
			int width, int height) {
		mImageAndTextView = imgView;
		Context = context;
		ImgUrl = imgUrl;
		mScreenWidth = width;
		mImageHeight = height;

		loadingview = ((Activity) context).getLayoutInflater().inflate(
				R.layout.loading_view, null);
		if (dialog != null && dialog.isShowing()) {
			dialog.dismiss();
		} else {
			dialog = new AlertDialog.Builder(context).show();
		}
		dialog.setContentView(loadingview);
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
		super.onPreExecute();
	}

	@Override
	protected void onCancelled() {
		super.onCancelled();
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		if (dialog != null && dialog.isShowing()) {
			dialog.dismiss();
		}
		mImageAndTextView.setImageBitmap(result);
		mImageAndTextView.postInvalidate(0, 0, mScreenWidth, mImageHeight + 30); // 只更新稍比图片大一些的区域
		super.onPostExecute(result);
	}
}