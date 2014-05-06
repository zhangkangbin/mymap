package com.xiaobao.map;



import java.io.IOException;
import java.util.ArrayList;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.BDNotifyListener;//�����õ�λ�����ѹ��ܣ���Ҫimport����
//���ʹ�õ���Χ�����ܣ���Ҫimport������
import com.baidu.location.BDGeofence;
import com.baidu.location.BDLocationStatusCodes;
import com.baidu.location.GeofenceClient;
import com.baidu.location.GeofenceClient.OnAddBDGeofencesResultListener;
import com.baidu.location.GeofenceClient.OnGeofenceTriggerListener;
import com.baidu.location.GeofenceClient.OnRemoveBDGeofencesResultListener;
import com.baidu.mapapi.map.LocationData;
import com.baidu.mapapi.map.MKEvent;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.PoiOverlay;
import com.baidu.mapapi.map.PopupClickListener;
import com.baidu.mapapi.map.PopupOverlay;
import com.baidu.mapapi.search.MKAddrInfo;
import com.baidu.mapapi.search.MKBusLineResult;
import com.baidu.mapapi.search.MKDrivingRouteResult;
import com.baidu.mapapi.search.MKPoiInfo;
import com.baidu.mapapi.search.MKPoiResult;
import com.baidu.mapapi.search.MKSearch;
import com.baidu.mapapi.search.MKSearchListener;
import com.baidu.mapapi.search.MKShareUrlResult;
import com.baidu.mapapi.search.MKSuggestionInfo;
import com.baidu.mapapi.search.MKSuggestionResult;
import com.baidu.mapapi.search.MKTransitRouteResult;
import com.baidu.mapapi.search.MKWalkingRouteResult;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.xiaobao.tool.T;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity implements OnClickListener{

	BMapManager mBMapMan = null;
	MapView mMapView = null;
	boolean isRequest = false;//�Ƿ��ֶ���������λ
	boolean isFirstLoc = true;//�Ƿ��״ζ�λ
	public LocationClient mLocationClient = null;
	public BDLocationListener myListener = new MyLocationListener();
	ImageButton   requestLocButton;
	Button sousuo;
	Button search;
	MKSearch mMKSearch = null  ;
	LocationData locData = null;
	MapController mMapController;
	PopupOverlay pop ;
	protected void onCreate(Bundle savedInstanceState) {




		super.onCreate(savedInstanceState);
		mBMapMan=new BMapManager(getApplication());

		MKGeneralListener ML= new MKGeneralListener() {  
			@Override  
			public void onGetPermissionState(int iError) {  
				// TODO ������Ȩ��֤����ͨ����������ж�ԭ��MKEvent�г���ֵ��  
				// 0��  ��֤ͨ���� ����ֵ���ʾ��֤ʧ�ܡ�   
				// -300: �޷����������˵����ӡ�  
				// -200: ��������ݴ����޷�������֤�������������ݡ�  
				// ����������ο��� http://developer.baidu.com/map/lbs-appendix.htm  
				T.logMsg(MainActivity.this, "-�������-");
			}  

			public void onGetNetworkState(int iError) {  
				// TODO �����������ͨ����������ж�ԭ��MKEvent�г���ֵ�� 
				T.logMsg(MainActivity.this, "�������");
			}  
		};
		mBMapMan.init(ML); 



		mMKSearch = new MKSearch();  
		mMKSearch.init(mBMapMan, new MySearchListener());//ע�⣬MKSearchListenerֻ֧��һ���������һ������Ϊ׼  


		//ע�⣺��������setContentViewǰ��ʼ��BMapManager���󣬷���ᱨ��
		setContentView(R.layout.fragment_main);
		mMapView=(MapView)findViewById(R.id.bmapsView);
		mMapView.setBuiltInZoomControls(true);
		//�����������õ����ſؼ�
		mMapController=mMapView.getController();
		// �õ�mMapView�Ŀ���Ȩ,�����������ƺ�����ƽ�ƺ�����
		GeoPoint point =new GeoPoint((int)(39.915* 1E6),(int)(116.404* 1E6));
		//�ø����ľ�γ�ȹ���һ��GeoPoint����λ��΢�� (�� * 1E6)
		mMapController.setCenter(point);//���õ�ͼ���ĵ�
		mMapController.setZoom(12);//���õ�ͼzoom����

		mLocationClient = new LocationClient(getApplicationContext());     //����LocationClient��


		mLocationClient.registerLocationListener( myListener );    //ע���������
		//��λ��ʼ��
		mLocationClient = new LocationClient( this );
		locData = new LocationData();

		mLocationClient.registerLocationListener( myListener );
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);//��gps
		option.setCoorType("bd09ll");     //������������
		option.setScanSpan(1000);
		mLocationClient.setLocOption(option);
		mLocationClient.start();
		//��λͼ���ʼ��
		pop = new PopupOverlay(mMapView,new PopupClickListener() {


			public void onClickedPopup(int arg0) {
				// TODO Auto-generated method stub
				//�ڴ˴���pop����¼���indexΪ�����������,�����������������  

				T.logMsg(MainActivity.this, "click my location");

			}
		});
		search =(Button) findViewById(R.id.btn_search);
		
		requestLocButton=(ImageButton) findViewById(R.id.button1);
		sousuo=(Button) findViewById(R.id.button2);

		requestLocButton.setOnClickListener(this);
		sousuo.setOnClickListener(this);
		search.setOnClickListener(this);

	}

	@Override
	protected void onDestroy(){
		mMapView.destroy();
		if(mBMapMan!=null){
			mBMapMan.destroy();
			mBMapMan=null;
		}
		super.onDestroy();
	}
	@Override
	protected void onPause(){
		mMapView.onPause();
		if(mBMapMan!=null){
			mBMapMan.stop();
		}
		super.onPause();
	}
	@Override
	protected void onResume(){
		mMapView.onResume();
		if(mBMapMan!=null){
			mBMapMan.start();
		}
		super.onResume();
	}
	public class MyLocationListener implements BDLocationListener {
		@Override
		public void onReceiveLocation(BDLocation location) {
			if (location == null)
				return ;
			StringBuffer sb = new StringBuffer(256);
			sb.append("time : ");
			sb.append(location.getTime());
			sb.append("\nerror code : ");
			sb.append(location.getLocType());
			sb.append("\nlatitude : ");
			sb.append(location.getLatitude());
			sb.append("\nlontitude : ");
			sb.append(location.getLongitude());
			sb.append("\nradius : ");
			sb.append(location.getRadius());
			if (location.getLocType() == BDLocation.TypeGpsLocation){
				sb.append("\nspeed : ");
				sb.append(location.getSpeed());
				sb.append("\nsatellite : ");
				sb.append(location.getSatelliteNumber());
			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation){
				sb.append("\naddr : ");
				sb.append(location.getAddrStr());
				locData.latitude = location.getLatitude();
				locData.longitude = location.getLongitude();
				mMapController.setZoom(20);//���õ�ͼzoom����

				Bitmap[] bmps = new Bitmap[1];  
				try {  
					bmps[0] = BitmapFactory.decodeStream(getAssets().open("marker2.png"));  

				} catch (IOException e) {  
					e.printStackTrace();  
				}  

				if(isFirstLoc||isRequest){
					//��������λ��  
					GeoPoint ptTAM = new GeoPoint((int)(locData.latitude* 1e6), (int)(locData.longitude *  1e6));  
					//����pop,����pop  

					pop.showPopup(bmps, ptTAM, 32);  
					//���ص���  
					//  pop.hidePop();  
					mMapView.getController().animateTo(ptTAM);
					isFirstLoc=false;
					isRequest=false;
				}
			} 

			Log.i("test",sb.toString());
		}
		public void onReceivePoi(BDLocation poiLocation) {
			//�����¸��汾��ȥ��poi����
			if (poiLocation == null){
				return ;
			}
			StringBuffer sb = new StringBuffer(256);
			sb.append("Poi time : ");
			sb.append(poiLocation.getTime());
			sb.append("\nerror code : ");
			sb.append(poiLocation.getLocType());
			sb.append("\nlatitude : ");
			sb.append(poiLocation.getLatitude());
			sb.append("\nlontitude : ");
			sb.append(poiLocation.getLongitude());
			sb.append("\nradius : ");
			sb.append(poiLocation.getRadius());

			if (poiLocation.getLocType() == BDLocation.TypeNetWorkLocation){
				sb.append("\naddr : ");
				sb.append(poiLocation.getAddrStr());
			} 
			if(poiLocation.hasPoi()){
				sb.append("\nPoi:");
				sb.append(poiLocation.getPoi());
			}else{             
				sb.append("noPoi information");
			}
			Log.i("test",sb.toString());
		}



	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button1:
			isRequest=true;
			mLocationClient.start();
		Log.i("test2", "shou dong click");

			break;
		case R.id.button2:
			Log.i("test",locData.latitude+"---"+locData.longitude  );
			//	mMKSearch.poiSearchNearBy("KFC", new GeoPoint((int) (locData.latitude * 1E6), (int) (locData.longitude * 1E6)), 5000); 

			//mMKSearch.suggestionSearch("�ϵ»�", "����");
			Intent i=new Intent(this, Searchresult.class);
			startActivity(i);
			break;
		case R.id.btn_search:
			Intent search=new Intent(this, Searchresult.class);
			//����һ��������ֵ�Ľ���
			startActivityForResult(search, 0);
		default:
			break;
		}

	}

	public class MySearchListener implements MKSearchListener {  
		@Override  
		public void onGetAddrResult(MKAddrInfo result, int iError) {  
			//���ص�ַ��Ϣ�������  
		}  
		@Override  
		public void onGetDrivingRouteResult(MKDrivingRouteResult result, int iError) {  
			//���ؼݳ�·���������  
		}  
		@Override  
		public void onGetPoiResult(MKPoiResult res, int type, int error) {  
			//����poi�������  

			// ����ſɲο�MKEvent�еĶ���  
			if ( error == MKEvent.ERROR_RESULT_NOT_FOUND){  
				Toast.makeText(MainActivity.this, "��Ǹ��δ�ҵ����",Toast.LENGTH_LONG).show();  
				return ;  
			}  
			else if (error != 0 || res == null) {  
				Toast.makeText(MainActivity.this, "����������..", Toast.LENGTH_LONG).show();  
				return;  
			}  
			// ��poi�����ʾ����ͼ��  
			PoiOverlay poiOverlay = new PoiOverlay(MainActivity.this, mMapView);  
			poiOverlay.setData(res.getAllPoi());  
			mMapView.getOverlays().clear();  
			mMapView.getOverlays().add(poiOverlay);  
			mMapView.refresh();  
			//��ePoiTypeΪ2��������·����4��������·��ʱ�� poi����Ϊ��  
			for(MKPoiInfo info : res.getAllPoi() ){  
				if ( info.pt != null ){  

					Log.i("xiao", info.city+"----"+info.address+info.name.toString()+"--"+info.uid+"---");
					mMapView.getController().animateTo(info.pt);  
					break;  
				}  
			}  
		}  
		@Override  
		public void onGetTransitRouteResult(MKTransitRouteResult result, int iError) {  
			//���ع����������  
		}  
		@Override  
		public void onGetWalkingRouteResult(MKWalkingRouteResult result, int iError) {  
			//���ز���·���������  
		}  
		@Override      
		public void onGetBusDetailResult(MKBusLineResult result, int iError) {  
			//���ع�����������Ϣ�������  
		}  
		public void onGetSuggestionResult(MKSuggestionResult res, int iError) {  
			//�����������Ϣ�������  

			for(MKSuggestionInfo info : res.getAllSuggestions() ){  
				Log.i("xiao", info.city.toString()+"___"+info.district+info.key);





			}







		}
		@Override 
		public void onGetShareUrlResult(MKShareUrlResult result , int type, int error) {
			//�ڴ˴���̴����󷵻ؽ��. 
		}
		@Override
		public void onGetPoiDetailSearchResult(int arg0, int arg1) {
			// TODO Auto-generated method stub

		} 
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		super.onActivityResult(requestCode, resultCode, data);
		
		if(data!=null){
			String address = data.getStringExtra("address");
			String city=data.getStringExtra("city");
			mMKSearch.poiSearchInCity(city, address);  
			
		}

}
}
