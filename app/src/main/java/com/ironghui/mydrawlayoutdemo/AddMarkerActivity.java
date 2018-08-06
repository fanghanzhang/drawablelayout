package com.ironghui.mydrawlayoutdemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddMarkerActivity extends AppCompatActivity implements LocationSource, AMapLocationListener {
    @BindView(R.id.mapView)
    MapView mapView;
    @BindView(R.id.search_edit_text)
    AutoCompleteTextView searchEditText;
    @BindView(R.id.search_edit_delete)
    ImageView searchEditDelete;
    private AMap aMap;
    private OnLocationChangedListener mListener;
    private AMapLocationClient mLocationClient;
    private AMapLocationClientOption mLocationOption;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaode);
        ButterKnife.bind(this);
        mapView.onCreate(savedInstanceState);
        aMap = mapView.getMap();
        requestPermission();
//        initMyLocation();
        addMaker();

    }

    private void addMaker() {
        LatLng latlng = new LatLng(39.91746, 116.396481);
        ArrayList<BitmapDescriptor> giflist = new ArrayList<BitmapDescriptor>();
        View view =LayoutInflater.from(this).inflate(R.layout.gaodeitem,null);
        giflist.add(BitmapDescriptorFactory.fromResource(R.drawable.xingview));
        MarkerOptions markerOption1 = new MarkerOptions()
                .anchor(0.5f, 0.5f)//定义marker 图标的锚点。
                .position(latlng).title("定位标志")//设置 Marker 的标题
                .snippet("文字片段")//设置 Marker 上的 snippet即文字片段
//                .icon(BitmapDescriptorFactory.fromBitmap(convertViewToBitmap(view)))//从布局中扩充的icon图标比较大
//                .icons(giflist)//fromResource中定位图标较小
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.xingview))//fromResource中定位图标较小
                .draggable(true)//设置标记是否可拖动。
                .period(50);//设置多少帧刷新一
        aMap.addMarker(markerOption1);


       /* MarkerOptions markerOption = new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
                .position(latlng)
                .draggable(true);
        aMap.addMarker(markerOption);*/
    }

    private void initMyLocation() {
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.interval(2000);
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW);
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setLocationSource(this);
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        aMap.setMyLocationEnabled(true);

    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
        if (mLocationClient == null) {
            mLocationClient = new AMapLocationClient(this);
            mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mLocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mLocationClient.setLocationOption(mLocationOption);
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mLocationClient.startLocation();
        }
    }

    @Override
    public void deactivate() {
        mListener = null;
        if (mLocationClient != null) {
            mLocationClient.stopLocation();
            mLocationClient.onDestroy();
        }
        mLocationClient = null;
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mListener != null && aMapLocation != null) {
            if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点
            } else {
                String str = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
                Log.e("tag", str);
            }
        }
    }

    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
        } else {//申请权限
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
    }

    public static Bitmap convertViewToBitmap(View view) {

        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        view.buildDrawingCache();

        Bitmap bitmap = view.getDrawingCache();

        return bitmap;

    }

    private Marker drawMarkerOnMap(LatLng point, Bitmap markerIcon, String id) {

        if (aMap != null && point != null) {

            Marker marker = aMap.addMarker(new MarkerOptions().anchor(0.5f, 1)

                    .position(point)

                    .title(id)

                    .icon(BitmapDescriptorFactory.fromBitmap(markerIcon)));


            return marker;

        }

        return null;

    }
}
