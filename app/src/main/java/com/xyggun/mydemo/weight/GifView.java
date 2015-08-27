package com.xyggun.mydemo.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.util.AttributeSet;
import android.view.View;

import com.xyggun.mydemo.R;

//这个类是用来显示Gif图片的
public class GifView extends View {

	public Movie gifMovie;

	public GifView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray ta = context.obtainStyledAttributes(attrs,
				R.styleable.GifView); // 这里用到了attrs.xml里面定义的GifView
		int taCount = ta.length(); // 获得属性的个数

		for (int i = 0; i < taCount; i++) { // 处理所有属性，由于我只定义了一个src属性，所以就只处理src属性
			if (R.styleable.GifView_src == ta.getIndex(i)) {
				int id = ta.getResourceId(R.styleable.GifView_src, 0); // 这里的参数是前面的GifView
																		// + _ +
																		// src链接起来
				if (0 != id) {
					setSrc(id); // 对应的函数，即在xml里面设置了src，相应的处理就会在setSrc函数里面进行
				}
			}
		}
		ta.recycle();
	}

	// 下面是setSrc函数：
	public void setSrc(int id) {

		gifMovie = Movie.decodeStream(getResources().openRawResource(id)); // gifMovie为Movie类型
	}

	long lStartTime;

	// 接下来是view的onDraw函数，这也是绘画动画的关键
	public void onDraw(Canvas canvas) {
		long now = android.os.SystemClock.uptimeMillis(); // 获得当前时间

		if (lStartTime == 0) { // first time
			lStartTime = now;
		}
		if (gifMovie != null) {
			int dur = gifMovie.duration(); // 获得gif文件的动画周期
			if (dur == 0) {
				dur = 1000;
			}
			int relTime = (int) ((now - lStartTime) % dur);
			gifMovie.setTime(relTime); // 设置播放时间点
			gifMovie.draw(canvas, getWidth() - gifMovie.width(), // 播放（即绘画）
					getHeight() - gifMovie.height());
			invalidate();
		}
	}

}