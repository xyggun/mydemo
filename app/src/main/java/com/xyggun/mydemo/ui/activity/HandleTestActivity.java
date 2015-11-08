package com.xyggun.mydemo.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xyggun.baselibrary.base.BaseActivity;
import com.xyggun.mydemo.R;


/**
 * handle 测试类
 */
public class HandleTestActivity extends BaseActivity {

    private Button btnTest;
    private TextView textView;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_test);

        initHeader();

        btnTest = (Button) this.findViewById(R.id.btn_01);
        textView = (TextView) this.findViewById(R.id.view_01);

        btnTest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                method_A();
            }
        });

    }

    private void initHeader() {
        TextView back = (TextView) findViewById(R.id.back_title);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishs();
            }
        });
    }

    private void method_A() {
        Looper looper = Looper.getMainLooper(); // 主线程的Looper对象
        // 这里以主线程的Looper对象创建了handler ，
        // 所以，这个handler 发送的Message会被传递给主线程的MessageQueue。
        handler = new MyHandler(looper);
        handler.removeMessages(0);
        // 构建Message对象
        // 第一个参数：是自己指定的message代号，方便在handler 选择性地接收
        // 第二三个参数没有什么意义
        // 第四个参数需要封装的对象
        Message msg = handler.obtainMessage(1, 1, 1, "主线程发消息了");

        handler.sendMessage(msg); // 发送消息
    }

    private void method_B() {
        // 可以看出这里启动了一个线程来操作消息的封装和发送的工作
        // 这样原来主线程的发送就变成了其他线程的发送，简单吧？呵呵
        new MyThread().start();
    }

    private void method_C() {
        // 这里handler 的实例化在线程中
        // 线程启动的时候就已经实例化了
        Message msg = handler.obtainMessage(1, 1, 1, "主线程发送的消息");
        handler.sendMessage(msg);
    }

    class MyHandler extends Handler {

        public MyHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            textView.setText("我是主线程的Handler，收到了消息：" + (String) msg.obj);
        }
    }

    //加了一个线程类
    class MyThread extends Thread {

        public void run() {
            Looper looper = Looper.getMainLooper(); // 主线程的Looper对象
            // 这里以主线程的Looper对象创建了handler ，
            // 所以，这个handler 发送的Message会被传递给主线程的MessageQueue。
            handler = new MyHandler(looper);

            // 构建Message对象
            // 第一个参数：是自己指定的message代号，方便在handler 选择性地接收
            // 第二三个参数没有什么意义
            // 第四个参数需要封装的对象
            Message msg = handler.obtainMessage(1, 1, 1, "其他线程发消息了");

            handler.sendMessage(msg); //发送消息
        }
    }

    class MyThreadC extends Thread {

        public void run() {
            Looper.prepare(); // 创建该线程的Looper对象
            // 这里Looper.myLooper()获得的就是该线程的Looper对象了
            handler = new ThreadHandler(Looper.myLooper());
            Message msg = handler.obtainMessage(1, 1, 1, "我自己");
            handler.sendMessage(msg);

            Looper.loop();

        }

        //定义线程类中的消息处理类
        class ThreadHandler extends Handler {

            public ThreadHandler(Looper looper) {
                super(looper);
            }

            public void handleMessage(Message msg) {
                // 这里对该线程中的MessageQueue中的Message进行处理
                // 这里我们再返回给主线程一个消息
                // 加入判断看看是不是该线程自己发的信息
                if (msg.what == 1 && msg.obj.equals("我自己")) {

                    handler = new MyHandler(Looper.getMainLooper());

                    Message msg2 = handler.obtainMessage(1, 1, 1, "禀告主线程:我收到了自己发给自己的Message");

                    handler.sendMessage(msg2);
                }

            }
        }
    }
}
