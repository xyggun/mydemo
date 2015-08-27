package com.xyggun.mydemo.baidumap;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.xyggun.baidumaplibrary.MyLocationListener;
import com.xyggun.baselibrary.inject.InjectView;
import com.xyggun.baselibrary.inject.SetContentView;
import com.xyggun.baselibrary.inject.base.BaseActivity;
import com.xyggun.mydemo.R;
import com.xyggun.mydemo.util.DialogHelp;

@SetContentView(R.layout.activity_baidu_map_demo)
public class BaiduMapDemoActivity extends BaseActivity implements View.OnClickListener {

    @InjectView(R.id.my_map_view)
    MapView mMapView;
    @InjectView(R.id.radio_menu)
    LinearLayout radioMenu;
    @InjectView(R.id.btn_map_normal)
    Button btnMapNormal;
    @InjectView(R.id.btn_map_satelltle)
    Button btnMapSateLltle;
    @InjectView(R.id.btn_map_traffic_enabled)
    Button btnMapTrafficEnabled;
    @InjectView(R.id.btn_heat_map_enabled)
    Button btnMapHeatEnabled;
    @InjectView(R.id.btn_location)
    Button btnMapLocation;

    BaiduMap mBaiduMap;
    Dialog WaitDialog;
    AlertDialog.Builder MessageDialogBuilder;

    // 定位
    BitmapDescriptor mCurrentMarker;
    MyLocationConfiguration.LocationMode mCurrentMode;

    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new BDLocationListener() {
        @Override
        public void onReceiveLocation(final BDLocation bdLocation) {
            StringBuffer sb = MyLocationListener.getBDString(bdLocation);
            dismssDialog(WaitDialog);
            MessageDialogBuilder = DialogHelp.getMessageDialog(BaiduMapDemoActivity.this, sb.toString(),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            MessageDialogBuilder.show();
            markedPoint(bdLocation.getLatitude(),bdLocation.getLongitude());
            setMapStatus(bdLocation);
            mLocationClient.stop();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.supportInvalidateOptionsMenu();

        initHeader();

        mBaiduMap = mMapView.getMap();
        //普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

        // 初始化LocationClient类
        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);    //注册监听函数

        initListener();
    }

    private void initHeader() {
        TextView back = (TextView) findViewById(R.id.back_title);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishs();
            }
        });
        TextView left = (TextView) findViewById(R.id.left_title);
        left.setVisibility(View.VISIBLE);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleRadioMenu();
            }
        });
    }

    private void toggleRadioMenu() {
        if (radioMenu.getVisibility() == View.VISIBLE) {
            radioMenu.setVisibility(View.GONE);
        } else {
            radioMenu.setVisibility(View.VISIBLE);
        }
    }

    private void initListener() {
        btnMapNormal.setOnClickListener(this);
        btnMapSateLltle.setOnClickListener(this);
        btnMapHeatEnabled.setOnClickListener(this);
        btnMapTrafficEnabled.setOnClickListener(this);
        btnMapLocation.setOnClickListener(this);
    }

    /**
     * 配置定位SDK参数
     */
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(com.baidu.location.LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 0;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认false，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }

    /**
     * 设置location
     * @param bdLocation
     */
    private void setLocation(BDLocation bdLocation){


        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 构造定位数据
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(bdLocation.getRadius())
                        // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(100).latitude(bdLocation.getLatitude())
                .longitude(bdLocation.getLongitude()).build();
        // 设置定位数据
        mBaiduMap.setMyLocationData(locData);
        // 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
        mCurrentMarker = BitmapDescriptorFactory
                .fromResource(R.drawable.loading_9);
        MyLocationConfiguration config = new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker);
        mBaiduMap.setMyLocationConfigeration(config);
        // 当不需要定位图层时关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
    }

    /**
     * 标记坐标点
     * @param latutyde
     * @param longitude
     */
    private void markedPoint(double latutyde,double longitude){
        //定义Maker坐标点
        LatLng point = new LatLng(latutyde, longitude);
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.loading_9);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);
    }

    private void setMapStatus(BDLocation bdLocation){
        // 设定中心点坐标
        LatLng cenpt =  new LatLng(bdLocation.getLatitude(),bdLocation.getLongitude());


        // 定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(cenpt)
                .zoom(16)
                .build();

        // 定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        // 改变地图状态
        mBaiduMap.setMapStatus(mMapStatusUpdate);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_map_normal) {
            mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        }
        if (id == R.id.btn_map_satelltle) {
            mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
        }
        if (id == R.id.btn_map_traffic_enabled) {
            if (mBaiduMap.isTrafficEnabled()) {
                mBaiduMap.setTrafficEnabled(false);
            } else {
                mBaiduMap.setTrafficEnabled(true);
            }
        }
        if (id == R.id.btn_heat_map_enabled) {
            if (mBaiduMap.isBaiduHeatMapEnabled()) {
                mBaiduMap.setBaiduHeatMapEnabled(false);
            } else {
                mBaiduMap.setBaiduHeatMapEnabled(true);
            }
        }
        if (id == R.id.btn_location) {
            WaitDialog = DialogHelp.getWaitDialog(this, "定位中...");
            WaitDialog.show();
            initLocation();
            mLocationClient.start();
        }
        toggleRadioMenu();
    }

    private void dismssDialog(Dialog dialog) {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismssDialog(WaitDialog);
        mLocationClient.stop();
        mLocationClient.unRegisterLocationListener(myListener);
    }
}
