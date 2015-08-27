package com.xyggun.mydemo.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.xyggun.mydemo.R;

/**
 * �첽����ͼƬ������
 * 
 * @author guolin
 */
public class LoadImageTask extends AsyncTask<String, Void, Bitmap> {
	
		private static Context context;
		
		/**
	     * ��¼�����������ػ�ȴ����ص�����
	     */
	    private static Set<LoadImageTask> taskCollection;
	    
		/**
	     * ��ͼƬ���й���Ĺ�����
	     */
	    private ImageLoader imageLoader;
	    
	    /**
	     * ÿһ�еĿ��
	     */
	    private int columnWidth;

	    /**
	     * ��ǰ��һ�еĸ߶�
	     */
	    private int firstColumnHeight;

	    /**
	     * ��ǰ�ڶ��еĸ߶�
	     */
	    private int secondColumnHeight;

	    /**
	     * ��ǰ�����еĸ߶�
	     */
	    private int thirdColumnHeight;
	
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
         * ͼƬ��URL��ַ
         */
        private String mImageUrl;

        /**
         * ���ظ�ʹ�õ�ImageView
         */
        private ImageView mImageView;
        
        /**
         * ��¼���н����ϵ�ͼƬ�����Կ�����ʱ���ƶ�ͼƬ���ͷš�
         */
        private List<ImageView> imageViewList = new ArrayList<ImageView>();        

        public LoadImageTask(Context contexts,Set<LoadImageTask> task,LinearLayout lf,LinearLayout ls,LinearLayout lt) {
        	context=contexts;
        	taskCollection = task;
        	firstColumn = lf;
            secondColumn = ls;
            thirdColumn = lt;
            columnWidth = firstColumn.getWidth();
        }

        /**
         * �����ظ�ʹ�õ�ImageView����
         * 
         * @param imageView
         */
        public LoadImageTask(Context contexts,ImageView imageView,ImageLoader imageLoaders,Set<LoadImageTask> task,LinearLayout lf,LinearLayout ls,LinearLayout lt) {
        		context=contexts;
        		imageLoader = imageLoaders;
        		taskCollection = task;
        		firstColumn = lf;
                secondColumn = ls;
                thirdColumn = lt;
	            columnWidth = firstColumn.getWidth();
                mImageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
                mImageUrl = params[0];
                imageLoader=ImageLoader.getInstance();
                Bitmap imageBitmap = imageLoader
                                .getBitmapFromMemoryCache(mImageUrl);
                if (imageBitmap == null) {
                        imageBitmap = loadImage(mImageUrl);
                }
                return imageBitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
                if (bitmap != null) {
                        double ratio = bitmap.getWidth() / (columnWidth * 1.0);
                        int scaledHeight = (int) (bitmap.getHeight() / ratio);
                        addImage(bitmap, columnWidth, scaledHeight);
                }
                taskCollection.remove(this);
        }

        /**
         * ���ݴ����URL����ͼƬ���м��ء��������ͼƬ�Ѿ�������SD���У���ֱ�Ӵ�SD�����ȡ������ʹ����������ء�
         * 
         * @param imageUrl
         *            ͼƬ��URL��ַ
         * @return ���ص��ڴ��ͼƬ��
         */
        private Bitmap loadImage(String imageUrl) {
                File imageFile = new File(getImagePath(imageUrl));
                if (!imageFile.exists()) {
                        downloadImage(imageUrl);
                }
                if (imageUrl != null) {
                        Bitmap bitmap = ImageLoader.decodeSampledBitmapFromResource(
                                        imageFile.getPath(), columnWidth);
                        if (bitmap != null) {
                                imageLoader.addBitmapToMemoryCache(imageUrl, bitmap);
                                return bitmap;
                        }
                }
                return null;
        }

        /**
         * ��ImageView�����һ��ͼƬ
         * 
         * @param bitmap
         *            ����ӵ�ͼƬ
         * @param imageWidth
         *            ͼƬ�Ŀ��
         * @param imageHeight
         *            ͼƬ�ĸ߶�
         */
        private void addImage(Bitmap bitmap, int imageWidth, int imageHeight) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                imageWidth, imageHeight);
                if (mImageView != null) {
                        mImageView.setImageBitmap(bitmap);
                } else {
                        ImageView imageView = new ImageView(context);
                        imageView.setLayoutParams(params);
                        imageView.setImageBitmap(bitmap);
                        imageView.setScaleType(ScaleType.FIT_XY);
                        imageView.setPadding(5, 5, 5, 5);
                        imageView.setTag(R.string.image_url, mImageUrl);
                        findColumnToAdd(imageView, imageHeight).addView(imageView);
                        imageViewList.add(imageView);
                }
        }

        /**
         * �ҵ���ʱӦ�����ͼƬ��һ�С�ԭ����Ƕ����еĸ߶Ƚ����жϣ���ǰ�߶���С��һ�о���Ӧ����ӵ�һ�С�
         * 
         * @param imageView
         * @param imageHeight
         * @return Ӧ�����ͼƬ��һ��
         */
        private LinearLayout findColumnToAdd(ImageView imageView,
                        int imageHeight) {
        		firstColumnHeight=firstColumn.getHeight();
        		secondColumnHeight=secondColumn.getHeight();
        		thirdColumnHeight=thirdColumn.getHeight();
                if (firstColumnHeight <= secondColumnHeight) {
                        if (firstColumnHeight <= thirdColumnHeight) {
                                imageView.setTag(R.string.border_top, firstColumnHeight);
                                firstColumnHeight += imageHeight;
                                imageView.setTag(R.string.border_bottom, firstColumnHeight);
                                return firstColumn;
                        }
                        imageView.setTag(R.string.border_top, thirdColumnHeight);
                        thirdColumnHeight += imageHeight;
                        imageView.setTag(R.string.border_bottom, thirdColumnHeight);
                        return thirdColumn;
                } else {
                        if (secondColumnHeight <= thirdColumnHeight) {
                                imageView.setTag(R.string.border_top, secondColumnHeight);
                                secondColumnHeight += imageHeight;
                                imageView
                                                .setTag(R.string.border_bottom, secondColumnHeight);
                                return secondColumn;
                        }
                        imageView.setTag(R.string.border_top, thirdColumnHeight);
                        thirdColumnHeight += imageHeight;
                        imageView.setTag(R.string.border_bottom, thirdColumnHeight);
                        return thirdColumn;
                }
        }

        /**
         * ��ͼƬ���ص�SD������������
         * 
         * @param imageUrl
         *            ͼƬ��URL��ַ��
         */
        private void downloadImage(String imageUrl) {
                HttpURLConnection con = null;
                FileOutputStream fos = null;
                BufferedOutputStream bos = null;
                BufferedInputStream bis = null;
                File imageFile = null;
                try {
                        URL url = new URL(imageUrl);
                        con = (HttpURLConnection) url.openConnection();
                        con.setConnectTimeout(5 * 1000);
                        con.setReadTimeout(15 * 1000);
                        con.setDoInput(true);
                        con.setDoOutput(true);
                        bis = new BufferedInputStream(con.getInputStream());
                        imageFile = new File(getImagePath(imageUrl));
                        fos = new FileOutputStream(imageFile);
                        bos = new BufferedOutputStream(fos);
                        byte[] b = new byte[1024];
                        int length;
                        while ((length = bis.read(b)) != -1) {
                                bos.write(b, 0, length);
                                bos.flush();
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                } finally {
                        try {
                                if (bis != null) {
                                        bis.close();
                                }
                                if (bos != null) {
                                        bos.close();
                                }
                                if (con != null) {
                                        con.disconnect();
                                }
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }
                if (imageFile != null) {
                        Bitmap bitmap = ImageLoader.decodeSampledBitmapFromResource(
                                        imageFile.getPath(), columnWidth);
                        if (bitmap != null) {
                                imageLoader.addBitmapToMemoryCache(imageUrl, bitmap);
                        }
                }
        }

        /**
         * ��ȡͼƬ�ı��ش洢·����
         * 
         * @param imageUrl
         *            ͼƬ��URL��ַ��
         * @return ͼƬ�ı��ش洢·����
         */
        private String getImagePath(String imageUrl) {
                int lastSlashIndex = imageUrl.lastIndexOf("/");
                String imageName = imageUrl.substring(lastSlashIndex + 1);
                String imageDir = Environment.getExternalStorageDirectory()
                                .getPath() + "/PhotoWallFalls/";
                File file = new File(imageDir);
                if (!file.exists()) {
                        file.mkdirs();
                }
                String imagePath = imageDir + imageName;
                return imagePath;
        }
}

