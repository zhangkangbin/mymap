package com.xiaobao.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.MKEvent;
import com.baidu.mapapi.search.MKAddrInfo;
import com.baidu.mapapi.search.MKBusLineResult;
import com.baidu.mapapi.search.MKDrivingRouteResult;
import com.baidu.mapapi.search.MKPoiResult;
import com.baidu.mapapi.search.MKSearch;
import com.baidu.mapapi.search.MKSearchListener;
import com.baidu.mapapi.search.MKShareUrlResult;
import com.baidu.mapapi.search.MKSuggestionInfo;
import com.baidu.mapapi.search.MKSuggestionResult;
import com.baidu.mapapi.search.MKTransitRouteResult;
import com.baidu.mapapi.search.MKWalkingRouteResult;


import com.xiaobao.adapter.MapAdapter;
import com.xiaobao.adapter.SearchGridview;
import com.xiaobao.info.SearchInfo;
import com.xiaobao.map.R;

import com.xiaobao.tool.T;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class Searchresult extends Activity implements OnItemClickListener{
	BMapManager mBMapMan = null;
	MKSearch mMKSearch = null ;
	EditText edt;
	List<SearchInfo> Infos;
	private ListView listView;
	MapAdapter adapter;
	 GridView gridview;
	SearchInfo searchinfo;
	SearchGridview  griddata;
	
	protected void onCreate(Bundle savedInstanceState) {


		mBMapMan=new BMapManager(getApplication());
		MKGeneralListener ML= new MKGeneralListener() {  
			@Override  
			public void onGetPermissionState(int iError) {  
				// TODO ������Ȩ��֤����ͨ����������ж�ԭ��MKEvent�г���ֵ��  
				// 0��  ��֤ͨ���� ����ֵ���ʾ��֤ʧ�ܡ�   
				// -300: �޷����������˵����ӡ�  
				// -200: ��������ݴ����޷�������֤�������������ݡ�  
				// ����������ο��� http://developer.baidu.com/map/lbs-appendix.htm  
				T.logMsg(Searchresult .this, "-�������-");
			}  

			public void onGetNetworkState(int iError) {  
				// TODO �����������ͨ����������ж�ԭ��MKEvent�г���ֵ�� 
				T.logMsg(Searchresult .this, "�������");
			}  
		};
		mBMapMan.init(ML); 
		mMKSearch = new MKSearch();
		mMKSearch.init(mBMapMan, new MySearchListener());
		//ע�⣬MKSearchListenerֻ֧��һ���������һ������Ϊ׼
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchresult);
		
		listView = (ListView) findViewById(R.id.listView1);
		listView.setOnItemClickListener(this);  
		
		edt=(EditText) findViewById(R.id.editText1);
		
		
		
		gridview= (GridView) findViewById(R.id.gridview);  
	        
	   SearchGridview saImageItems=new SearchGridview(Searchresult.this);
	      //��Ӳ�����ʾ  
	      gridview.setAdapter(saImageItems);  
	      gridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if(id<=2){

					String address= "��ʳ";
						
					
					Intent intent  = new Intent();
					intent.putExtra("address", address);
				
					setResult(0, intent);
					finish();
					
				}
			
				Log.i("test2","��ѡ����" + id);
				
			}
		});
	      
	      
	  
	   listView.setVisibility(View.INVISIBLE);
	      //��ʾ
	    gridview.setVisibility(View.VISIBLE);
	  
	      //�����Ϣ����  
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		edt.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// �����ı�
				Log.i("test2", edt.getText().toString());
				
				if(edt.getText().toString().equals("")){
					//listView ����
				      listView.setVisibility(View.INVISIBLE);
				      //gridview��ʾ
				      gridview.setVisibility(View.VISIBLE);
					
				}//gridview ����
			      gridview.setVisibility(View.INVISIBLE);
			      //list ��ʾ
			      listView.setVisibility(View.VISIBLE);
				mMKSearch.suggestionSearch(edt.getText().toString(), "����");



				//Log.i("xiao",  Infos.get(1).getCity());

				//    listView = (ListView) findViewById(R.id.listView1);

				//adapter = new MapAdapter(Searchresult .this, Infos);
				//	listView.setAdapter(adapter);
				//				
				//				smsInfoService=new SmsInfoservice(this);
				//				smsinfos=smsInfoService.resmsList();
				//				listView = (ListView) findViewById(R.id.list);
				//				adapter = new ReSmsAdapter ( Smsrestore .this,smsinfos);
				//				listView.setAdapter(adapter);
				//
				//				service=new Searchservice();
				//				Infos=service.getSmsInfos();

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});





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
		public void onGetPoiResult(MKPoiResult result, int type, int iError) {  
			//����poi�������  
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
		public void onGetSuggestionResult(MKSuggestionResult res, int error) {  
			//�����������Ϣ�������  
			//
			// ����ſɲο�MKEvent�еĶ���  
			if ( error == MKEvent.ERROR_RESULT_NOT_FOUND){ 

				Infos=new ArrayList<SearchInfo>();
				String city="ľ���ҵ���ַ����ĵ�ַ�������Խ��ڵĵط�";
				String address="ľ���ҵ���ַ����ĵ�ַ�������Խ��ڵĵط�";
				String key="ľ���ҵ���ַ����ĵ�ַ�������Խ��ڵĵط�";


				searchinfo = new SearchInfo(city, address,key);
				Infos.add(searchinfo);
				searchinfo = null;
				listView = (ListView) findViewById(R.id.listView1);
				adapter = new MapAdapter ( Searchresult .this,Infos);
				listView.setAdapter(adapter);

				Toast.makeText(Searchresult.this, "��Ǹ��δ�ҵ����",Toast.LENGTH_LONG).show();  
				return ;  
			}  
			else if (error != 0 || res == null) {  

				Infos=new ArrayList<SearchInfo>();
				String city="ľ���ҵ���ַ����ĵ�ַ�������Խ��ڵĵط�";
				String address="ľ���ҵ���ַ����ĵ�ַ�������Խ��ڵĵط�";
				String key="ľ���ҵ���ַ����ĵ�ַ�������Խ��ڵĵط�";


				searchinfo = new SearchInfo(city, address,key);
				Infos.add(searchinfo);
				searchinfo = null;
				
				adapter = new MapAdapter ( Searchresult .this,Infos);
				listView.setAdapter(adapter);
				Toast.makeText(Searchresult.this, "����������..", Toast.LENGTH_LONG).show();  
				return;  
			} 
			if(res.getSuggestionNum()>0){
				Log.i("sum",res.getSuggestionNum()+"");

				Infos=new ArrayList<SearchInfo>();


				for(MKSuggestionInfo info : res.getAllSuggestions() ){  
					Log.i("xiao", info.city.toString()+"___"+info.district+info.key);
					String city=info.city;
					String address=info.district;
					info.toString();
					String key=info.key;


					searchinfo = new SearchInfo(city, address,key);
					Infos.add(searchinfo);
					searchinfo = null;


				}
				listView = (ListView) findViewById(R.id.listView1);
				adapter = new MapAdapter ( Searchresult .this,Infos);
				listView.setAdapter(adapter);

			}else{

				Infos=new ArrayList<SearchInfo>();
				Log.i("sum",res.getSuggestionNum()+"");
				String city="";
				String address="���������ǵ���";
				String key="ľ���ҵ���ַ����ĵ�ַ�������Խ��ڵĵط�";


				searchinfo = new SearchInfo(city, address,key);
				Infos.add(searchinfo);
				searchinfo = null;
				
				adapter = new MapAdapter ( Searchresult .this,Infos);
				listView.setAdapter(adapter);

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
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Log.i("test2","��ѡ����" + Infos.get(position).getKey());
		
	
			String address= Infos.get(position).getCity()+Infos.get(position).getAddress()
					+Infos.get(position).getKey();
			String city=Infos.get(position).getCity();
			Intent intent  = new Intent();
			intent.putExtra("address", address);
			intent.putExtra("city", city);
			setResult(0, intent);
			finish();
		
	}

}
