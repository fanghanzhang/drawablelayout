package com.ironghui.mydrawlayoutdemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class GaodeActivity extends AppCompatActivity implements LocationSource, AMapLocationListener, TextWatcher, PoiSearch.OnPoiSearchListener {
    @BindView(R.id.mapView)
    MapView mapView;
    @BindView(R.id.search_edit_text)
    AutoCompleteTextView searchEditText;
    @BindView(R.id.search_edit_delete)
    ImageView searchEditDelete;
    private AMap map;
    private OnLocationChangedListener mListener;
    private AMapLocationClient mLocationClient;
    private AMapLocationClientOption mLocationOption;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaode);
        ButterKnife.bind(this);
        requestPermission();
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        initMap();
        initMyLocation();
        addtextChange();
    }

    private void addtextChange() {
        searchEditText.addTextChangedListener(this);
    }

    private void initMyLocation() {
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.interval(2000);
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW);
        map.setMyLocationStyle(myLocationStyle);
        map.setLocationSource(this);
        map.getUiSettings().setMyLocationButtonEnabled(true);
        map.setMyLocationEnabled(true);

    }

    private void initMap() {
        if (map == null) {
            map = mapView.getMap();
            map.moveCamera(CameraUpdateFactory.zoomTo(12));
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                }
                break;
        }
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

    @OnClick(R.id.search_edit_delete)
    public void onViewClicked() {

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (charSequence != null) {
            searchEditDelete.setVisibility(View.VISIBLE);
        } else {
            searchEditDelete.setVisibility(View.GONE);
        }
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
       /* String newText = charSequence.toString().trim();
        if ((newText != null) || (newText.trim().length() != 0)) {
            InputtipsQuery inputquery = new InputtipsQuery(newText, editCity.getText().toString());
            Inputtips inputTips = new Inputtips(this, inputquery);
            inputTips.setInputtipsListener(this);
            inputTips.requestInputtipsAsyn();
        }*/
        String content = charSequence.toString().trim();//获取自动提示输入框的内容
        InputtipsQuery inputtipsQuery = new InputtipsQuery(content, "");//初始化一个输入提示搜索对象，并传入参数
        Inputtips inputtips = new Inputtips(this, inputtipsQuery);//定义一个输入提示对象，传入当前上下文和搜索对象
        inputtips.setInputtipsListener(new Inputtips.InputtipsListener() {
            @Override
            public void onGetInputtips(List<Tip> list, int errcode) {
               /* if (errcode == 1000 && list != null) {
                    ArrayList datas = new ArrayList<>();
                    for (int i = 0; i < list.size(); i++) {
                        LocationBean locationBean = new LocationBean();
                        Tip tip = list.get(i);
                        locationBean.latitude = tip.getPoint().getLatitude();
                        locationBean.longitude = tip.getPoint().getLongitude();
                        locationBean.snippet = tip.getName();
                        locationBean.title = tip.getDistrict();
                        datas.add(locationBean);
                    }
//                    searchCarAdapter.setNewData(datas);
                }*/
                if (errcode == AMapException.CODE_AMAP_SUCCESS) {// 正确返回
                    List<String> listString = new ArrayList<String>();
                    for (int i = 0; i < list.size(); i++) {
                        listString.add(list.get(i).getName());
                    }
                    ArrayAdapter<String> aAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.route_inputs, listString);
                    searchEditText.setAdapter(aAdapter);
                    aAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(GaodeActivity.this, errcode + "", Toast.LENGTH_SHORT).show();
                }

            }
        });//设置输入提示查询的监听，实现输入提示的监听方法onGetInputtips()
        inputtips.requestInputtipsAsyn();//输入查询提示的异步接口实现
    }

    @Override
    public void afterTextChanged(Editable editable) {
        doSearchQuery();
    }

    private PoiResult poiResult; // poi返回的结果
    private int currentPage = 0;// 当前页面，从0开始计数
    private PoiSearch.Query query;// Poi查询条件类
    private LatLonPoint latLonPoint;
    private PoiSearch poiSearch;
    private List<PoiItem> poiItems;// poi数据
    private String keyWord;
    //    private CommonAdapter adapter;
    private final int ADDRESS_LOCATION_GET = 3242;
    private String POI_SEARCH_TYPE = "汽车服务|汽车销售|" +
            "//汽车维修|摩托车服务|餐饮服务|购物服务|生活服务|体育休闲服务|医疗保健服务|" +
            "//住宿服务|风景名胜|商务住宅|政府机构及社会团体|科教文化服务|交通设施服务|" +
            "//金融保险服务|公司企业|道路附属设施|地名地址信息|公共设施";

    private void doSearchQuery() {
        latLonPoint = new LatLonPoint(116.472995, 39.993743);// 116.472995,39.993743

        keyWord = searchEditText.getText().toString().trim();
        currentPage = 0;
        //keyWord表示搜索字符串，
        //第二个参数表示POI搜索类型，二者选填其一，选用POI搜索类型时建议填写类型代码，码表可以参考下方（而非文字）
        //cityCode表示POI搜索区域，可以是城市编码也可以是城市名称，也可以传空字符串，空字符串代表全国在全国范围内进行搜索
        query = new PoiSearch.Query(keyWord, "", ADDRESS_LOCATION_GET + "");
        query.setPageSize(20);// 设置每页最多返回多少条poiItem
        query.setPageNum(currentPage);// 设置查第一页
        if (latLonPoint != null) {
            poiSearch = new PoiSearch(this, query);
            poiSearch.setOnPoiSearchListener(this);
            poiSearch.setBound(new PoiSearch.SearchBound(latLonPoint, 3000, true));//设置搜索范围
            poiSearch.searchPOIAsyn();// 异步搜索
        }
    }

    @Override
    public void onPoiSearched(PoiResult result, int code) {
        if (code == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getQuery() != null) {// 搜索poi的结果
                Log.d("result", result + "");
                Log.d("result", code + "");
                if (result.getQuery().equals(query)) {// 是否是同一次搜索
                    poiResult = result;
                    int country = poiResult.getPageCount();
                    List<PoiItem> poiItems = poiResult.getPois();
                    List<SuggestionCity> suggestionCities = poiResult.getSearchSuggestionCitys();// 当搜索不到poiitem数据时，会返回含有搜索关键字的城市信息
                    if (poiItems != null && poiItems.size() > 0) {
                        poiItems.clear();
                     /*   if (adapter != null) {
                            adapter.notifyDataSetChanged();
                        }*/
                    }
                    poiItems = poiResult.getPois();// 取得第一页的poiitem数据，页数从数字0开始

                    //通过listview显示搜索结果的操作省略

                }
            } else {
                Log.d("GaodeActivity", "onPoiSearched: ");

            }
        } else {
            Toast.makeText(this, "搜索出现错误", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }
}
