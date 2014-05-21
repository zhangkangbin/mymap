package com.xiaobao.adapter;

import java.util.List;



import com.xiaobao.info.SearchInfo;
import com.xiaobao.map.R;



import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MapAdapter extends BaseAdapter {

	private static final String TAG = "Adapter";
	private Context context;
	private LayoutInflater inflater;
	List<SearchInfo>  infos;

	private static TextView tv_name;
	private static TextView tv_size;
	private static TextView tv_date;



	public MapAdapter (Context context,	List<SearchInfo>  infos) {
		this.context = context;
		this.infos=infos;
		inflater = LayoutInflater.from(context);

	}

	public int getCount() {
		// TODO Auto-generated method stub
		return infos.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// getview�ķ����������˶��ٴ�?
		// 9
		// gridview �ؼ�bug 
		// won't fix 
		// ʹ�þ�̬�ı������� �����ڴ�����������õĸ��� 

		Log.i(TAG,"getview "+ position);
		View view = inflater.inflate(R.layout.mapadapter, null);

		tv_name =  (TextView) view.findViewById(R.id.textView1);
		tv_size =  (TextView) view.findViewById(R.id.textView2);
		tv_date =  (TextView) view.findViewById(R.id.textView3);

		tv_name.setText(infos.get(position).getKey());
		tv_size.setText(infos.get(position).getCity()+infos.get(position).getAddress());
		tv_date.setText(infos.get(position).getCity()+infos.get(position).getAddress());

		return view;
	}

}
