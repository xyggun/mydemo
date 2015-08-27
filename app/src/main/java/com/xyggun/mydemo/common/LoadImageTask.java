package com.xyggun.mydemo.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.xyggun.mydemo.R;

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

/**
 * 异步下载图片的任务。
 * 
 * @author guolin
 */
public class LoadImageTask extends AsyncTask<String, Void, Bitmap> {
	
		private static Context context;
		
		/**
	     * 记录所有正在下载或等待下载的任务。
	     */
	    private static Set<LoadImageTask> taskCollection;
	    
		/**
	     * 对图片进行管理的工具类
	     */
	    private ImageLoader imageLoader;
	    
	    /**
	     * 每一列的宽度
	     */
	    private int columnWidth;

	    /**
	     * 当前第一列的高度
	     */
	    private int firstColumnHeight;

	    /**
	     * 当前第二列的高度
	     */
	    private int secondColumnHeight;

	    /**
	     * 当前第三列的高度
	     */
	    private int thirdColumnHeight;
	
	    /**
	     * 第一列的布局
	     */
	    private LinearLayout firstColumn;

	    /**
	     * 第二列的布局
	     */
	    private LinearLayout secondColumn;

	    /**
	     * 第三列的布局
	     */
	    private LinearLayout thirdColumn;
	    
        /**
         * 图片的URL地址
         */
        private String mImageUrl;

        /**
         * 可重复使用的ImageView
         */
        private ImageView mImageView;
        
        /**
         * 记录所有界面上的图片，用以可以随时控制对图片的释放。
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
         * 将可重复使用的ImageView传入
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
         * 根据传入的URL，对图片进行加载。如果这张图片已经存在于SD卡中，则直接从SD卡里读取，否则就从网络上下载。
         * 
         * @param imageUrl
         *            图片的URL地址
         * @return 加载到内存的图片。
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
         * 向ImageView中添加一张图片
         * 
         * @param bitmap
         *            待添加的图片
         * @param imageWidth
         *            图片的宽度
         * @param imageHeight
         *            图片的高度
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
         * 找到此时应该添加图片的一列。原则就是对三列的高度进行判断，当前高度最小的一列就是应该添加的一列。
         * 
         * @param imageView
         * @param imageHeight
         * @return 应该添加图片的一列
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
         * 将图片下载到SD卡缓存起来。
         * 
         * @param imageUrl
         *            图片的URL地址。
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
         * 获取图片的本地存储路径。
         * 
         * @param imageUrl
         *            图片的URL地址。
         * @return 图片的本地存储路径。
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

