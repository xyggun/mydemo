package com.xyggun.baselibrary.inject.common;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
/**
 * �첽�̼߳���ͼƬ������
 * ʹ��˵����
 * BitmapManager bmpManager;
 * bmpManager = new BitmapManager(BitmapFactory.decodeResource(context.getResources(), R.drawable.loading));
 * bmpManager.loadBitmap(imageURL, imageView);
 * @version 1.0
 * @created 2012-6-25
 */
public class BitmapManager {  
	  
    private static Map<String, SoftReference<Bitmap>> cache;  
    private static ExecutorService pool;  
    private static Map<ImageView, String> imageViews;  
    private Bitmap defaultBmp;  
    
    public final static String SAVE_IMAGE_PATH_TOSD = Environment.getExternalStorageDirectory()+ File.separator+ "TeacherCome/pictureDown"+ File.separator;
    public final static String SAVE_IMAGESMALL_PATH_TOSD = Environment.getExternalStorageDirectory()+ File.separator+ "TeacherCome/pictureSmallDown"+ File.separator;
    static {  
        cache = new ConcurrentHashMap<String, SoftReference<Bitmap>>();  
        pool = Executors.newFixedThreadPool(5);  //�̶��̳߳�
        imageViews = Collections.synchronizedMap(new WeakHashMap<ImageView, String>());
    }  
    
    public BitmapManager(){}
    
    public BitmapManager(Bitmap def) {
    	this.defaultBmp = def;
    }
    
    /**
     * ����Ĭ��ͼƬ
     * @param bmp
     */
    public void setDefaultBmp(Bitmap bmp) {  
    	defaultBmp = bmp;  
    }   
    
    /**
     * ����ͼƬ
     * @param url
     * @param imageView
     */
    public void loadBitmap(String url, ImageView imageView) {  
    	loadBitmap(url, imageView, this.defaultBmp, 0, 0,false,false,false,null,null);
    }
    
    /**
     * ����ͼƬ
     * @param url
     * @param imageView
     */
    public void loadDrawable(String url, ImageView imageView) {  
    	loadBitmaps(url, imageView);
    }
    
    /**
     * ����ͼƬ-����Сͼ��ʾ
     * @param url
     * @param imageView
     */
    public void loadSmallBitmap(String url, ImageView imageView,boolean isSmall) {  
    	loadBitmap(url, imageView, this.defaultBmp, 0, 0,false,false,isSmall,null,null);
    }
    
    /**
     * ����ͼƬ_�з��غ���
     * @param url
     * @param imageView
     */
    public void loadBitmap(String url, ImageView imageView,String fileName,ImageDownload imageDownCallback) {  
    	loadBitmap(url, imageView, this.defaultBmp, 0, 0,false,false,false,fileName,imageDownCallback);
    }
    
    /**
     * ����ͼƬ-�ƶ�ͼƬ��С
     * @param url
     * @param imageView
     */
    public void loadBitmap(String url, ImageView imageView,int width, int height) {  
    	loadBitmap(url, imageView, this.defaultBmp, width, height,false,false,true,null,null);
    }
    
    /**
     * xiangyg
     * ����ͼƬ-�ƶ�ͼƬ��С
     * @param url
     * @param imageView
     */
    public void loadBitmap(String url, ImageView imageView,int width, int height,String fileName) {  
    	loadBitmap(url, imageView, this.defaultBmp, width, height,false,false,true,fileName,null);
    }
    
    /**
     * ����ͼƬ-�ƶ�ͼƬ��С-�������ó�Բ��
     * @param url
     * @param imageView
     */
    public void loadBitmap(String url, ImageView imageView,int width, int height,boolean isFillet) {  
    	loadBitmap(url, imageView, this.defaultBmp, width, height,false,isFillet,false,null,null);
    }
	
    /**
     * ����ͼƬ-�����ü���ʧ�ܺ���ʾ��Ĭ��ͼƬ
     * @param url
     * @param imageView
     * @param defaultBmp
     */
    public void loadBitmap(String url, ImageView imageView, Bitmap defaultBmp) {  
    	loadBitmap(url, imageView, defaultBmp, 0, 0,false,false,false,null,null);
    }
    
    /**
     * ����ͼƬ��ͼƬ��С
     * @param url
     * @param imageView
     * @param defaultBmp
     */
    public void loadBitmap(String url, ImageView imageView,boolean isNarrow) {  
    	loadBitmap(url, imageView, defaultBmp,0,0,isNarrow,false,false,null,null);
    }
    
    
    /**
     * ����ͼƬ-��ָ����ʾͼƬ�ĸ߿�
     * @param url
     */
    public void  loadBitmaps(String url, ImageView imageView){
    	if(url == null||url.equals("")){
    		imageView.setBackgroundDrawable(ImageUtils.bitmapToDrawable(defaultBmp));//���ֵΪ�� ��ô�����ó�Ĭ��ͷ��
    		return;
    	}
    	imageViews.put(imageView, url);  
    	Bitmap bitmap = getBitmapFromCache(url);  
    	if (bitmap == null) {
    		String filename = FileUtils.getFileNameNoFormat(url);
        	String filepath = SAVE_IMAGE_PATH_TOSD + filename;;
    		File file = new File(filepath);
    		if(file.exists()){
    			imageView.setBackgroundDrawable(ImageUtils.bitmapToDrawable(ImageUtils.getBitmapByPath(filepath)));  
    		} else{
    			queueJobs(url, imageView);
        	}
    	}
    }
    
    public void queueJobs(final String url, final ImageView imageView) {  
        /* Create handler in UI thread. */  
        final Handler handler = new Handler() {  
            public void handleMessage(Message msg) {  
                String tag = imageViews.get(imageView);  
                if (tag != null && tag.equals(url)) {
                    if (msg.obj != null) {
                    	 imageView.setBackgroundDrawable(ImageUtils.bitmapToDrawable((Bitmap) msg.obj));  
                        try {
                        	ImageUtils.saveImageToSD(null, SAVE_IMAGE_PATH_TOSD+FileUtils.getFileNameNoFormat(url), (Bitmap) msg.obj, 80);
						} catch (IOException e) {
							e.printStackTrace();
						}
                    } 
                } 
            }  
        };  
  
        pool.execute(new Runnable() {   
            public void run() {  
                Message message = Message.obtain();  
                message.obj = downloadBitmap(url, 0, 0);  
                handler.sendMessage(message);  
            }  
        });  
    } 
    
    
    /**
     * ����ͼƬ-��ָ����ʾͼƬ�ĸ߿�
     * @param url
     * @param imageView
     * @param width
     * @param height
     */
    public void loadBitmap(String url, ImageView imageView, Bitmap defaultBmp, int width, int height,boolean isNarrow,boolean isFillet,boolean isSmall,String fileNameSend,ImageDownload imageDownCallback) {  
    	if(url == null||url.equals("")){
    		imageView.setImageBitmap(defaultBmp);//���ֵΪ�� ��ô�����ó�Ĭ��ͷ�� 
    		return;
    	}
        imageViews.put(imageView, url);  
        Bitmap bitmap = getBitmapFromCache(url);  
   
        if (bitmap != null) {
        	 //��ʾ����ͼƬ
        	if(isNarrow){
        		imageView.setImageBitmap(ImageUtils.NarrowBit(bitmap));
        		if(imageDownCallback !=null){
            		imageDownCallback.imageDownloadCallback(true);
            	}
			} else {
				if(width > 0 && height > 0) {
					if(isFillet){
						imageView.setImageBitmap(ImageUtils.getRoundedCornerBitmap(Bitmap.createScaledBitmap(bitmap, width, height, true),200));
					} else {
						imageView.setImageBitmap(Bitmap.createScaledBitmap(bitmap, width, height, true));
					}
				} else {
					 imageView.setImageBitmap(bitmap);  
				}
			}
        	if(imageDownCallback !=null){
        		imageDownCallback.imageDownloadCallback(true);
        	}
        } else {
        	String filename = FileUtils.getFileNameNoFormat(url);
        	if(fileNameSend!=null){
        		filename=fileNameSend;
        	}
        	String filepath = "";
        	if(!isSmall){
        		filepath = SAVE_IMAGE_PATH_TOSD + filename;
        	} else {
        		filepath = SAVE_IMAGESMALL_PATH_TOSD + filename;
        	}
    		File file = new File(filepath);
    		if(file.exists()){
    			if(isNarrow){
    				imageView.setImageBitmap(ImageUtils.FileCalculate(filepath,100,100));
    			} else {
    				Bitmap bitmaps = ImageUtils.getBitmapByPath(filepath);
    				if(bitmaps!=null){
	    				if(width > 0 && height > 0) {
	    					if(isFillet){
	    						imageView.setImageBitmap(ImageUtils.getRoundedCornerBitmap(Bitmap.createScaledBitmap(bitmaps, width, height, true),200));
	    					} else {
	    						imageView.setImageBitmap(Bitmap.createScaledBitmap(bitmaps, width, height, true));
	    					}
		   				} else {
		   					 imageView.setImageBitmap(bitmaps);  
		   				}
	    			}
    			}
    			if(imageDownCallback !=null){
            		imageDownCallback.imageDownloadCallback(true);
            	}
        	}else{
				//�̼߳�������ͼƬ
        		if(defaultBmp!=null){
        			imageView.setImageBitmap(defaultBmp);
        		}
        		queueJob(url, imageView, width, height,isNarrow,isFillet,isSmall,filepath,imageDownCallback);
        	}
        }  
    }  
    
    
    /**
     * �ӻ����л�ȡͼƬ
     * @param url
     */
    public Bitmap getBitmapFromCache(String url) {  
    	Bitmap bitmap = null;
        if (cache.containsKey(url)) {  
            bitmap = cache.get(url).get();  
        }  
        return bitmap;  
    }  
    
    
    /**
     * �������м���ͼƬ
     * @param url
     * @param imageView
     * @param width
     * @param height
     */
    public void queueJob(final String url, final ImageView imageView, final int width, 
    		final int height,final boolean isNarrow,final boolean isFillet,
    		final boolean isSmall,final String filepath,final ImageDownload imageDownCallback) {  
        /* Create handler in UI thread. */  
        final Handler handler = new Handler() {  
            public void handleMessage(Message msg) {  
                String tag = imageViews.get(imageView);  
                if (tag != null && tag.equals(url)) {
                    if (msg.obj != null) {
                    	if(isNarrow){
                    		imageView.setImageBitmap(ImageUtils.NarrowBit((Bitmap) msg.obj));  
            			} else {
            				if(width > 0 && height > 0) {
            					if(isFillet){
            						imageView.setImageBitmap(ImageUtils.getRoundedCornerBitmap(Bitmap.createScaledBitmap((Bitmap) msg.obj, width, height, true),200));
            					} else {
            						imageView.setImageBitmap(Bitmap.createScaledBitmap((Bitmap) msg.obj, width, height, true));  
            					}
            				} else {
            					 imageView.setImageBitmap((Bitmap) msg.obj);  
            				}
            			}
                    	//��SD����д��ͼƬ����
                        try {
                        	ImageUtils.saveImageToSD(null, filepath, (Bitmap) msg.obj, 80);
						} catch (IOException e) {
							e.printStackTrace();
						}
                        if(imageDownCallback !=null){
                    		imageDownCallback.imageDownloadCallback(true);
                    	}
                    } else {
                    	if(imageDownCallback !=null){
                    		imageDownCallback.imageDownloadCallback(false);
                    	}
                    }
                } 
            }  
        };  
  
        pool.execute(new Runnable() {   
            public void run() {  
                Message message = Message.obtain();  
                message.obj = downloadBitmap(url, width, height);  
                handler.sendMessage(message);  
            }  
        });  
    } 
  
    /**
     * ����ͼƬ-��ָ����ʾͼƬ�ĸ߿�
     * @param url
     * @param width
     * @param height
     */
    private Bitmap downloadBitmap(String url, int width, int height) {   
        URL m;
		InputStream i = null;
		Bitmap bitmap = null;
        try {
        	m = new URL(url);
			HttpURLConnection conn  = (HttpURLConnection)m.openConnection();
            conn.setDoInput(true);
            conn.connect(); 
            
			i = (InputStream) m.getContent();
			 BitmapFactory.Options newOpts  = ImageUtils.Inputcalculate(i,720,1280);
	        i = (InputStream) m.getContent();
	        
            bitmap = BitmapFactory.decodeStream(i,null, newOpts);  

			i.close();
			
//			if(width > 0 && height > 0) {
//				bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);//ָ����ʾͼƬ�ĸ߿�
//			} 
			cache.put(url, new SoftReference<Bitmap>(bitmap));//���뻺��
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return bitmap;  
    }  
    
    public interface ImageDownload {
        public void imageDownloadCallback(Boolean isSuccess);
    }
}