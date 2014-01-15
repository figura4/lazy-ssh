package com.figura4.lazyssh.adapters;

import com.figura4.lazyssh.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DrawerArrayAdapter extends BaseAdapter {
	private final Context context;
	private final String[] values1;
	private final String[] values2;

	public DrawerArrayAdapter(Context context, String[] values1, String[] values2) {
	    this.context = context;
	    this.values1 = values1;
	    this.values2 = values2;
	}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    	View rowView = inflater.inflate(R.layout.drawer_list_item, parent, false);
    	
    	TextView textView1 = (TextView) rowView.findViewById(R.id.firstLine);
    	TextView textView2 = (TextView) rowView.findViewById(R.id.secondLine);
    	ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
    	
    	textView1.setText(values1[position]);
    	textView2.setText(values2[position]);
    	
    	Log.d("lazyssh", "R.drawable.ic_action_settings: "+ R.drawable.ic_action_settings);
    	Log.d("lazyssh", "getIdentifier():" + context.getResources().getIdentifier(values2[position], "drawable", context.getPackageName()));
    	int identifier = context.getResources().getIdentifier(values2[position], "drawable", context.getPackageName());
    	imageView.setImageResource(identifier);
    	return rowView;
    }

	@Override
	public int getCount() {
		return values1.length;
	}

	@Override
	public Object getItem(int position) {
		return values1[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
}
