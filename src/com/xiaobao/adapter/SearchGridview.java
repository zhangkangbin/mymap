package com.xiaobao.adapter;

import com.xiaobao.map.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class SearchGridview extends BaseAdapter {

	String[] s={"��ʳ","��ʳ","��ʳ","����վ","����վ","����վ","�Ƶ�","�Ƶ�","�Ƶ�","KTV","KTV","KTV"};
	int[] ico={R.drawable.poi_chi,R.drawable.poi_chi,R.drawable.poi_chi,R.drawable.poi_xing,
			R.drawable.poi_xing,R.drawable.poi_xing,R.drawable.poi_zhu,R.drawable.poi_zhu,R.drawable.poi_zhu,R.drawable.poi_wan,R.drawable.poi_wan,R.drawable.poi_wan};
	private LayoutInflater inflater;
	private Context context;
	ImageView image;
	TextView text;
	public SearchGridview(Context context){

		this.context = context;

		inflater = LayoutInflater.from(context);
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return s.length;
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

		Log.i("grid","getview "+ position);
		View view = inflater.inflate(R.layout.night_item, null);

		image= (ImageView) view.findViewById(R.id.ItemImage);
		text=  (TextView) view.findViewById(R.id.ItemText);
		image.setImageResource(ico[position]);
		text.setText(s[position]);


		return view;
	}

}
