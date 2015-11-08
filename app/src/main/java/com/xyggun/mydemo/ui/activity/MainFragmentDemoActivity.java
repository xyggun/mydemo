package com.xyggun.mydemo.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.xyggun.baselibrary.base.BaseActivity;
import com.xyggun.mydemo.R;
import com.xyggun.mydemo.ui.fragment.HomeFragment;
import com.xyggun.mydemo.ui.fragment.Tab1Fragment;
import com.xyggun.mydemo.ui.fragment.Tab2Fragment;
import com.xyggun.mydemo.ui.fragment.Tab3Fragment;

/**
 * fragment 内嵌套 webview demo
 **/
public class MainFragmentDemoActivity extends BaseActivity {

    private FrameLayout view_content;

    private HomeFragment homeFragment;
    private Tab1Fragment tab1Fragment;
    private Tab2Fragment tab2Fragment;
    private Tab3Fragment tab3Fragment;

    private Fragment[] fragments;
    private Button[] mTabs;

    private int index;
    private int currentTabIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment_demo);

        initHeader();
        initView();
    }


    /**
     * 初始化组件
     */
    private void initFootBarView() {
        mTabs = new Button[4];
        mTabs[0] = (Button) findViewById(R.id.btn_main_home);
        mTabs[1] = (Button) findViewById(R.id.btn_main_temai);
        mTabs[2] = (Button) findViewById(R.id.btn_main_catg);
        mTabs[3] = (Button) findViewById(R.id.btn_main_my);
        mTabs[0].setSelected(true);
    }

    private void initView() {
        view_content = (FrameLayout) findViewById(R.id.view_content);

        fragments = new Fragment[]{homeFragment, tab1Fragment, tab2Fragment, tab3Fragment};
        // 添加显示第一个fragment
//        getSupportFragmentManager().beginTransaction().add(R.id.view_content, homeFragment).hide(homeFragment).show(homeFragment).commit();
        showFragment(0);

        initFootBarView();
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

    /**
     * button点击事件
     */
    public void onTabClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_main_home:
                setMainTap(0);
                break;
            case R.id.btn_main_temai:
                setMainTap(1);
                break;
            case R.id.btn_main_catg:
                setMainTap(2);
                break;
            case R.id.btn_main_my:
                setMainTap(3);
                break;
        }
    }

    private void setMainTap(int index) {
        this.index = index;
        showFragment(index);
        mTabs[currentTabIndex].setSelected(false);
        mTabs[index].setSelected(true);
        currentTabIndex = index;
    }

    public void showFragment(int index) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        // 想要显示一个fragment,先隐藏所有fragment，防止重叠
        hideFragments(ft);

        switch (index) {
            case 0:
                // 如果fragment1已经存在则将其显示出来
                if (homeFragment != null) {
                    Log.d("test", "showFragment homeFragment=" + homeFragment);
                    ft.show(homeFragment);
                    // 否则是第一次切换则添加fragment1，注意添加后是会显示出来的，replace方法也是先remove后add
                } else {
                    homeFragment = new HomeFragment();
                    Log.d("test", "showFragment homeFragment=" + homeFragment);
                    ft.add(R.id.view_content, homeFragment,"home");
                }

                // 这部分为通过获取到在 fragmentManager 中的 fragment 来调用 fragment 的方法
//                List<Fragment> list = getSupportFragmentManager().getFragments();
//                for (Fragment fment : list) {
//                    if (fment instanceof homeFragment) {
//                        Handler handler = ((HomeFragment) fment).changeContentHandler;
//                        Message message = new Message();
//
//                        handler.sendMessage(message);
//                    }
//                }
                break;
            case 1:
                if (tab1Fragment != null) {
                    Log.d("test", "showFragment tab1Fragment=" + tab1Fragment);
                    ft.show(tab1Fragment);
                } else {
                    tab1Fragment = new Tab1Fragment();
                    ft.add(R.id.view_content, tab1Fragment,"tab1");
                }
                tab1Fragment = (Tab1Fragment)getSupportFragmentManager().findFragmentByTag("tab1");
                break;
            case 2:
                if (tab2Fragment != null) {
                    Log.d("test", "showFragment tab2Fragment=" + tab2Fragment);
                    ft.show(tab2Fragment);
                } else {
                    tab2Fragment = new Tab2Fragment();
                    ft.add(R.id.view_content, tab2Fragment,"tab2");
                }
                tab2Fragment = (Tab2Fragment)getSupportFragmentManager().findFragmentByTag("tab2");
                break;
            case 3:
                if (tab3Fragment != null) {
                    Log.d("test", "showFragment tab3Fragment=" + tab3Fragment);
                    ft.show(tab3Fragment);
                } else {
                    tab3Fragment = new Tab3Fragment();
                    ft.add(R.id.view_content, tab3Fragment,"tab3");
                }
                tab3Fragment = (Tab3Fragment)getSupportFragmentManager().findFragmentByTag("tab3");
                break;
        }
        ft.commit();
    }

    // 当fragment已被实例化，就隐藏起来
    public void hideFragments(FragmentTransaction ft) {
        if (homeFragment != null)
            ft.hide(homeFragment);
        if (tab1Fragment != null)
            ft.hide(tab1Fragment);
        if (tab2Fragment != null)
            ft.hide(tab2Fragment);
        if (tab3Fragment != null)
            ft.hide(tab3Fragment);

    }

}
