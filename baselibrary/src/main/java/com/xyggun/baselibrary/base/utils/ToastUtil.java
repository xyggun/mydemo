package com.xyggun.baselibrary.base.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.xyggun.baselibrary.R;

/**
 * toast帮助类
 * @author: xyggun
 * @Date: 2015-09-16 下午3:36
 * @Version: 1.0
 */
public class ToastUtil {

    /**
     * 显示LENGTH_LONG（3.5秒）的 toast
     * @param context
     * @param msg
     * @return
     */
    public static void showLongToast(Context context, String msg){
        new Toast(context).makeText(context,msg,Toast.LENGTH_LONG).show();
    }

    /**
     * LENGTH_SHORT（2.0秒）的 toast
     * @param context
     * @param msg
     * @return
     */
    public static void showShortToast(Context context, String msg){
        new Toast(context).makeText(context,msg,Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示自定义位置的 toast
     * @param context
     * @param msg:显示信息
     * @param gravity:显示位置 @link android.view.Gravity
     * @param longOrShort:Toast.LENGTH_SHORT及Toast.LENGTH_LONG
     * @return
     */
    public static void showCustomGravityToast(Context context, String msg, int gravity, int longOrShort){
        Toast toast = Toast.makeText(context,
                msg, longOrShort);
        toast.setGravity(gravity, 0, 0);
        toast.show();
    }

    /**
     * 显示加载中的 toast
     * @param context
     * @param msg
     * @param gravity
     * @param longOrShort
     */
    public static void showCustomLoadingToast(Context context, String msg, int gravity, int longOrShort){
        Toast toast = Toast.makeText(context,
                msg, longOrShort);
        toast.setGravity(gravity, 0, 0);

        // 获取 toast 布局
        LinearLayout toastView = (LinearLayout) toast.getView();

        // 设置imageView
        ImageView imageCodeProject = new ImageView(context);
        imageCodeProject.setImageResource(R.drawable.ic_load);

        // 设置动画化
        Animation operatingAnim = AnimationUtils.loadAnimation(context, R.anim.rotate_load);

        // 旋转效果，可自定义 LinearInterpolator为匀速效果，Accelerateinterpolator为加速效果、DecelerateInterpolator为减速效果
        LinearInterpolator lin = new LinearInterpolator();

        // 为动画设置旋转速率
        operatingAnim.setInterpolator(lin);
        imageCodeProject.setAnimation(operatingAnim);

        // 将 imageview 添加到布局中
        toastView.addView(imageCodeProject, 0);
        toast.show();
    }

    /**
     * 显示加载中的 toast
     * @param context
     * @param msg
     * @param gravity
     * @param longOrShort
     */
    public static void showCustomLoadingToast2(Context context, String msg, int gravity, int longOrShort){
        Toast toast = Toast.makeText(context,
                msg, longOrShort);
        toast.setGravity(gravity, 0, 0);

        // 获取 toast 布局
        LinearLayout toastView = (LinearLayout) toast.getView();

        // 设置ProgressBar
        ProgressBar progressBar = new ProgressBar(context);

        Drawable drawable = context.getResources().getDrawable(R.drawable.anim_rotate_load);
        progressBar.setIndeterminateDrawable(drawable);

        progressBar.setIndeterminate(false);

        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        progressBar.setLayoutParams(lp);

        // 将 ProgressBar 添加到布局中
        toastView.addView(progressBar, 0);
        toast.show();
    }
}
