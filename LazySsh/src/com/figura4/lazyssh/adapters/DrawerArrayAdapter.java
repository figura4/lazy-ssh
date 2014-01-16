package com.figura4.lazyssh.adapters;

import com.figura4.lazyssh.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DrawerArrayAdapter extends BaseAdapter {
	private final Context context;
	private final String[] titles;
	private final String[] subititles;
	private final String[] icons;

	public DrawerArrayAdapter(Context context, String[] titles, String[] subtitles, String[] icons) {
	    this.context = context;
	    this.titles = titles;
	    this.subititles = subtitles;
	    this.icons = icons;
	}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    	View rowView = inflater.inflate(R.layout.drawer_list_item, parent, false);
    	
    	TextView textView1 = (TextView) rowView.findViewById(R.id.firstLine);
    	TextView textView2 = (TextView) rowView.findViewById(R.id.secondLine);
    	ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
    	
    	textView1.setText(titles[position]);
    	textView2.setText(subititles[position]);
    	
    	int identifier = context.getResources().getIdentifier(icons[position], "drawable", context.getPackageName());
    	imageView.setImageResource(identifier);
    	return rowView;
    }

	@Override
	public int getCount() {
		return titles.length;
	}

	@Override
	public Object getItem(int position) {
		return titles[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
}
