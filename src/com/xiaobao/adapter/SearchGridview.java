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

	String[] s={"美食","美食","美食","公交站","公交站","公交站","酒店","酒店","酒店","KTV","KTV","KTV"};
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
		// getview的方法被调用了多少次?
		// 9
		// gridview 控件bug 
		// won't fix 
		// 使用静态的变量引用 减少内存中申请的引用的个数 

		Log.i("grid","getview "+ position);
		View view = inflater.inflate(R.layout.night_item, null);

		image= (ImageView) view.findViewById(R.id.ItemImage);
		text=  (TextView) view.findViewById(R.id.ItemText);
		image.setImageResource(ico[position]);
		text.setText(s[position]);


		return view;
	}

}
