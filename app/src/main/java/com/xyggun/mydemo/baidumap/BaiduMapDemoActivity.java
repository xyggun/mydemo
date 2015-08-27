package com.xyggun.mydemo.baidumap;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.xyggun.baselibrary.inject.InjectView;
import com.xyggun.baselibrary.inject.SetContentView;
import com.xyggun.baselibrary.inject.base.BaseActivity;
import com.xyggun.mydemo.R;

@SetContentView(R.layout.activity_baidu_map_demo)
public class BaiduMapDemoActivity extends BaseActivity {

    @InjectView(R.id.my_map_view)
    MapView mMapView;

    BaiduMap mBaiduMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.supportInvalidateOptionsMenu();

        initHeader();

        mBaiduMap = mMapView.getMap();
        //普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
    }

    private void initHeader() {
        TextView back = (TextView)findViewById(R.id.back_title);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishs();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_baidu_map_demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_normal) {
            mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        }
        if (id == R.id.action_satelltle) {
            mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
        }
        if (id == R.id.action_traffic_enabled) {
            if (mBaiduMap.isTrafficEnabled()) {
                mBaiduMap.setTrafficEnabled(false);
            } else {
                mBaiduMap.setTrafficEnabled(true);
            }
        }
        if (id == R.id.action_heat_map_enabled) {
            if (mBaiduMap.isBaiduHeatMapEnabled()) {
                mBaiduMap.setBaiduHeatMapEnabled(false);
            } else {
                mBaiduMap.setBaiduHeatMapEnabled(true);
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
