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
				// TODO 返回授权验证错误，通过错误代码判断原因，MKEvent中常量值。  
				// 0：  认证通过， 非零值表表示认证失败。   
				// -300: 无法建立与服务端的连接。  
				// -200: 服务端数据错误，无法解析验证服务器返回数据。  
				// 其他返回请参考： http://developer.baidu.com/map/lbs-appendix.htm  
				T.logMsg(Searchresult .this, "-网络错误-");
			}  

			public void onGetNetworkState(int iError) {  
				// TODO 返回网络错误，通过错误代码判断原因，MKEvent中常量值。 
				T.logMsg(Searchresult .this, "网络错误");
			}  
		};
		mBMapMan.init(ML); 
		mMKSearch = new MKSearch();
		mMKSearch.init(mBMapMan, new MySearchListener());
		//注意，MKSearchListener只支持一个，以最后一次设置为准
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchresult);
		
		listView = (ListView) findViewById(R.id.listView1);
		listView.setOnItemClickListener(this);  
		
		edt=(EditText) findViewById(R.id.editText1);
		
		
		
		gridview= (GridView) findViewById(R.id.gridview);  
	        
	   SearchGridview saImageItems=new SearchGridview(Searchresult.this);
	      //添加并且显示  
	      gridview.setAdapter(saImageItems);  
	      gridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if(id<=2){

					String address= "美食";
						
					
					Intent intent  = new Intent();
					intent.putExtra("address", address);
				
					setResult(0, intent);
					finish();
					
				}
			
				Log.i("test2","您选择了" + id);
				
			}
		});
	      
	      
	  
	   listView.setVisibility(View.INVISIBLE);
	      //显示
	    gridview.setVisibility(View.VISIBLE);
	  
	      //添加消息处理  
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		edt.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// 发生改变
				Log.i("test2", edt.getText().toString());
				
				if(edt.getText().toString().equals("")){
					//listView 隐藏
				      listView.setVisibility(View.INVISIBLE);
				      //gridview显示
				      gridview.setVisibility(View.VISIBLE);
					
				}//gridview 隐藏
			      gridview.setVisibility(View.INVISIBLE);
			      //list 显示
			      listView.setVisibility(View.VISIBLE);
				mMKSearch.suggestionSearch(edt.getText().toString(), "深圳");



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
			//返回地址信息搜索结果  
		}  
		@Override  
		public void onGetDrivingRouteResult(MKDrivingRouteResult result, int iError) {  
			//返回驾乘路线搜索结果  
		}  
		@Override  
		public void onGetPoiResult(MKPoiResult result, int type, int iError) {  
			//返回poi搜索结果  
		}  
		@Override  
		public void onGetTransitRouteResult(MKTransitRouteResult result, int iError) {  
			//返回公交搜索结果  
		}  
		@Override  
		public void onGetWalkingRouteResult(MKWalkingRouteResult result, int iError) {  
			//返回步行路线搜索结果  
		}  
		@Override      
		public void onGetBusDetailResult(MKBusLineResult result, int iError) {  
			//返回公交车详情信息搜索结果  
		}  
		public void onGetSuggestionResult(MKSuggestionResult res, int error) {  
			//返回联想词信息搜索结果  
			//
			// 错误号可参考MKEvent中的定义  
			if ( error == MKEvent.ERROR_RESULT_NOT_FOUND){ 

				Infos=new ArrayList<SearchInfo>();
				String city="木有找到地址，你的地址可能来自教授的地方";
				String address="木有找到地址，你的地址可能来自教授的地方";
				String key="木有找到地址，你的地址可能来自教授的地方";


				searchinfo = new SearchInfo(city, address,key);
				Infos.add(searchinfo);
				searchinfo = null;
				listView = (ListView) findViewById(R.id.listView1);
				adapter = new MapAdapter ( Searchresult .this,Infos);
				listView.setAdapter(adapter);

				Toast.makeText(Searchresult.this, "抱歉，未找到结果",Toast.LENGTH_LONG).show();  
				return ;  
			}  
			else if (error != 0 || res == null) {  

				Infos=new ArrayList<SearchInfo>();
				String city="木有找到地址，你的地址可能来自教授的地方";
				String address="木有找到地址，你的地址可能来自教授的地方";
				String key="木有找到地址，你的地址可能来自教授的地方";


				searchinfo = new SearchInfo(city, address,key);
				Infos.add(searchinfo);
				searchinfo = null;
				
				adapter = new MapAdapter ( Searchresult .this,Infos);
				listView.setAdapter(adapter);
				Toast.makeText(Searchresult.this, "搜索出错啦..", Toast.LENGTH_LONG).show();  
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
				String address="来自行星星的你";
				String key="木有找到地址，你的地址可能来自教授的地方";


				searchinfo = new SearchInfo(city, address,key);
				Infos.add(searchinfo);
				searchinfo = null;
				
				adapter = new MapAdapter ( Searchresult .this,Infos);
				listView.setAdapter(adapter);

			}

		}
		@Override 
		public void onGetShareUrlResult(MKShareUrlResult result , int type, int error) {
			//在此处理短串请求返回结果. 
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
		Log.i("test2","您选择了" + Infos.get(position).getKey());
		
	
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
