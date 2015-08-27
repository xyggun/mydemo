package com.xyggun.mydemo.weight;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.xyggun.mydemo.R;
import com.xyggun.mydemo.common.ImageLoader;
import com.xyggun.mydemo.common.LoadImageTask;
import com.xyggun.mydemo.util.Images;

@SuppressLint("ClickableViewAccessibility")
public class MyScrollView extends ScrollView implements OnTouchListener {

    /**
     * ÿҳҪ���ص�ͼƬ����
     */
    public static final int PAGE_SIZE = 15;

    /**
     * ��¼��ǰ�Ѽ��ص��ڼ�ҳ
     */
    private int page;
    
    /**
     * ��һ�еĲ���
     */
    private LinearLayout firstColumn;

    /**
     * �ڶ��еĲ���
     */
    private LinearLayout secondColumn;

    /**
     * �����еĲ���
     */
    private LinearLayout thirdColumn;

    /**
     * �Ƿ��Ѽ��ع�һ��layout������onLayout�еĳ�ʼ��ֻ�����һ��
     */
    private boolean loadOnce;

    /**
     * ��ͼƬ���й���Ĺ�����
     */
    private ImageLoader imageLoader;


    /**
     * ��¼�����������ػ�ȴ����ص�����
     */
    private static Set<LoadImageTask> taskCollection;

    /**
     * MyScrollView�µ�ֱ���Ӳ��֡�
     */
    private static View scrollLayout;

    /**
     * MyScrollView���ֵĸ߶ȡ�
     */
    private static int scrollViewHeight;

    /**
     * ��¼�ϴ�ֱ����Ĺ������롣
     */
    private static int lastScrollY = -1;

    /**
     * ��¼���н����ϵ�ͼƬ�����Կ�����ʱ���ƶ�ͼƬ���ͷš�
     */
    private List<ImageView> imageViewList = new ArrayList<ImageView>();

    /**
     * ��Handler�н���ͼƬ�ɼ��Լ����жϣ��Լ����ظ���ͼƬ�Ĳ�����
     */
    private static Handler handler = new Handler() {

            public void handleMessage(android.os.Message msg) {
                    MyScrollView myScrollView = (MyScrollView) msg.obj;
                    int scrollY = myScrollView.getScrollY();
                    // �����ǰ�Ĺ���λ�ú��ϴ���ͬ����ʾ��ֹͣ����
                    if (scrollY == lastScrollY) {
                            // ����������ײ������ҵ�ǰû���������ص�����ʱ����ʼ������һҳ��ͼƬ
                            if (scrollViewHeight + scrollY >= scrollLayout.getHeight()
                                            && taskCollection.isEmpty()) {
                                    myScrollView.loadMoreImages();
                            }
                            myScrollView.checkVisibility();
                    } else {
                            lastScrollY = scrollY;
                            Message message = new Message();
                            message.obj = myScrollView;
                            // 5������ٴζԹ���λ�ý����ж�
                            handler.sendMessageDelayed(message, 5);
                    }
            };

    };

    /**
     * MyScrollView�Ĺ��캯����
     * 
     * @param context
     * @param attrs
     */
    public MyScrollView(Context context, AttributeSet attrs) {
            super(context, attrs);
            imageLoader = ImageLoader.getInstance();
            taskCollection = new HashSet<LoadImageTask>();
            setOnTouchListener(this);
    }

    /**
     * ����һЩ�ؼ��Եĳ�ʼ����������ȡMyScrollView�ĸ߶ȣ��Լ��õ���һ�еĿ��ֵ���������￪ʼ���ص�һҳ��ͼƬ��
     */
    @SuppressLint("ClickableViewAccessibility")
	@Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
            super.onLayout(changed, l, t, r, b);
            if (changed && !loadOnce) {
                    scrollViewHeight = getHeight();
                    scrollLayout = getChildAt(0);
                    firstColumn = (LinearLayout) findViewById(R.id.first_column);
                    secondColumn = (LinearLayout) findViewById(R.id.second_column);
                    thirdColumn = (LinearLayout) findViewById(R.id.third_column);
                    loadOnce = true;
                    loadMoreImages();
            }
    }

    /**
     * �����û��Ĵ����¼�������û���ָ�뿪��Ļ��ʼ���й�����⡣
     */
    @SuppressLint("ClickableViewAccessibility")
	@Override
    public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                    Message message = new Message();
                    message.obj = this;
                    handler.sendMessageDelayed(message, 5);
            }
            return false;
    }

    /**
     * ��ʼ������һҳ��ͼƬ��ÿ��ͼƬ���Ὺ��һ���첽�߳�ȥ���ء�
     */
    public void loadMoreImages() {
            if (hasSDCard()) {
                    int startIndex = page * PAGE_SIZE;
                    int endIndex = page * PAGE_SIZE + PAGE_SIZE;
                    if (startIndex < Images.imageUrls.length) {
                            Toast.makeText(getContext(), "���ڼ���...", Toast.LENGTH_SHORT)
                                            .show();
                            if (endIndex > Images.imageUrls.length) {
                                    endIndex = Images.imageUrls.length;
                            }
                            for (int i = startIndex; i < endIndex; i++) {
                                    LoadImageTask task = new LoadImageTask(getContext(),taskCollection,firstColumn,secondColumn,thirdColumn);
                                    taskCollection.add(task);
                                    task.execute(Images.imageUrls[i]);
                            }
                            page++;
                    } else {
                            Toast.makeText(getContext(), "��û�и���ͼƬ", Toast.LENGTH_SHORT)
                                            .show();
                    }
            } else {
                    Toast.makeText(getContext(), "δ����SD��", Toast.LENGTH_SHORT).show();
            }
    }

    /**
     * ����imageViewList�е�ÿ��ͼƬ����ͼƬ�Ŀɼ��Խ��м�飬���ͼƬ�Ѿ��뿪��Ļ�ɼ���Χ����ͼƬ�滻��һ�ſ�ͼ��
     */
    public void checkVisibility() {
            for (int i = 0; i < imageViewList.size(); i++) {
                    ImageView imageView = imageViewList.get(i);
                    int borderTop = (Integer) imageView.getTag(R.string.border_top);
                    int borderBottom = (Integer) imageView
                                    .getTag(R.string.border_bottom);
                    if (borderBottom > getScrollY()
                                    && borderTop < getScrollY() + scrollViewHeight) {
                            String imageUrl = (String) imageView.getTag(R.string.image_url);
                            Bitmap bitmap = imageLoader.getBitmapFromMemoryCache(imageUrl);
                            if (bitmap != null) {
                                    imageView.setImageBitmap(bitmap);
                            } else {
                                    LoadImageTask task = new LoadImageTask(getContext(),imageView,imageLoader,taskCollection,firstColumn,secondColumn,thirdColumn);
                                    task.execute(imageUrl);
                            }
                    } else {
                            imageView.setImageResource(R.drawable.empty_photo);
                    }
            }
    }

    /**
     * �ж��ֻ��Ƿ���SD����
     * 
     * @return ��SD������true��û�з���false��
     */
    private boolean hasSDCard() {
            return Environment.MEDIA_MOUNTED.equals(Environment
                            .getExternalStorageState());
    }

}