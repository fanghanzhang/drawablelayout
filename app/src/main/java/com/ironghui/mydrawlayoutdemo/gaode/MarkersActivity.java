package com.ironghui.mydrawlayoutdemo.gaode;

import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.Toast;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.Projection;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.ironghui.mydrawlayoutdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MarkersActivity extends AppCompatActivity implements AMap.OnMarkerClickListener{
    @BindView(R.id.map)
    MapView map;
    @BindView(R.id.clearMap)
    Button clearMap;
    @BindView(R.id.resetMap)
    Button resetMap;
    private AMap aMap;
    private LatLng latlng = new LatLng(39.91746, 116.396481);
    private MarkerOptions markerOption;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marker);
        ButterKnife.bind(this);
        map.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        aMap = map.getMap();
        aMap.setOnMarkerClickListener(this);
        addMarkersToMap();// 往地图上添加marker
    }

    private void addMarkersToMap() {
        markerOption = new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
                   .position(latlng)
                   .draggable(true);
        aMap.addMarker(markerOption);
    }

    @OnClick({R.id.clearMap, R.id.resetMap})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.clearMap:
                if (aMap != null) {
                    aMap.clear();
                }
                break;
            case R.id.resetMap:
                if (aMap != null) {
                    aMap.clear();
                    addMarkersToMap();
                }
                break;
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (aMap != null) {
            jumpPoint(marker);
        }
        Toast.makeText(MarkersActivity.this, "您点击了Marker", Toast.LENGTH_LONG).show();
        return true;
    }

    private void jumpPoint(Marker marker) {
        //点击跳动marker
       /* final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        Projection proj = aMap.getProjection();
        final LatLng markerLatlng = marker.getPosition();
        Point markerPoint = proj.toScreenLocation(markerLatlng);
        markerPoint.offset(0, -100);
        final LatLng startLatLng = proj.fromScreenLocation(markerPoint);
        final long duration = 1500;

        final Interpolator interpolator = new BounceInterpolator();
        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed
                        / duration);
                double lng = t * markerLatlng.longitude + (1 - t)
                        * startLatLng.longitude;
                double lat = t * markerLatlng.latitude + (1 - t)
                        * startLatLng.latitude;
                marker.setPosition(new LatLng(lat, lng));
                if (t < 1.0) {
                    handler.postDelayed(this, 16);
                }
            }
        });*/
        markerOption = new MarkerOptions().icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .position(latlng)
                .title("标题")
                .snippet("详细信息")
                .draggable(true);
        marker = aMap.addMarker(markerOption);
        marker.showInfoWindow();
    }

    @Override
    protected void onResume() {
        super.onResume();
        map.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        map.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        map.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        map.onSaveInstanceState(outState);
    }
}
