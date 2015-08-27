package com.xyggun.mydemo.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.util.AttributeSet;
import android.view.View;

import com.xyggun.mydemo.R;

//�������������ʾGifͼƬ��
public class GifView extends View {

	public Movie gifMovie;

	public GifView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray ta = context.obtainStyledAttributes(attrs,
				R.styleable.GifView); // �����õ���attrs.xml���涨���GifView
		int taCount = ta.length(); // ������Եĸ���

		for (int i = 0; i < taCount; i++) { // �����������ԣ�������ֻ������һ��src���ԣ����Ծ�ֻ����src����
			if (R.styleable.GifView_src == ta.getIndex(i)) {
				int id = ta.getResourceId(R.styleable.GifView_src, 0); // ����Ĳ�����ǰ���GifView
																		// + _ +
																		// src��������
				if (0 != id) {
					setSrc(id); // ��Ӧ�ĺ���������xml����������src����Ӧ�Ĵ���ͻ���setSrc�����������
				}
			}
		}
		ta.recycle();
	}

	// ������setSrc������
	public void setSrc(int id) {

		gifMovie = Movie.decodeStream(getResources().openRawResource(id)); // gifMovieΪMovie����
	}

	long lStartTime;

	// ��������view��onDraw��������Ҳ�ǻ滭�����Ĺؼ�
	public void onDraw(Canvas canvas) {
		long now = android.os.SystemClock.uptimeMillis(); // ��õ�ǰʱ��

		if (lStartTime == 0) { // first time
			lStartTime = now;
		}
		if (gifMovie != null) {
			int dur = gifMovie.duration(); // ���gif�ļ��Ķ�������
			if (dur == 0) {
				dur = 1000;
			}
			int relTime = (int) ((now - lStartTime) % dur);
			gifMovie.setTime(relTime); // ���ò���ʱ���
			gifMovie.draw(canvas, getWidth() - gifMovie.width(), // ���ţ����滭��
					getHeight() - gifMovie.height());
			invalidate();
		}
	}

}