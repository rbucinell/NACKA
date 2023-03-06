package com.rbucinell.nacka_kickball;

import com.rbucinell.nacka_kickball.Data.TShirtColor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MatchupAdapter extends ArrayAdapter<MatchupListItem>{
	
	Context context;
	int layoutResourceId;
	MatchupListItem data[] = null;
	
	public MatchupAdapter( Context context, int layoutResourceId, MatchupListItem[] data)
	{
		super( context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}
	
	@Override
	public View getView( int position, View convertView, ViewGroup parent)
	{
		View row = null;
		MatchupHolder holder = null;
		
		//Get data for view (already existing or new)
		if( convertView == null )
		{
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(R.layout.listview_matchup_item, parent, false);
		}
		else
		{
			row = convertView;
			holder = (MatchupHolder)row.getTag();
		}
		
		holder = new MatchupHolder();
		holder.field_img_icon = (ImageButton)row.findViewById(R.id.fieldImg);
		holder.tshirt_img_icon = (ImageView)row.findViewById(R.id.tshirtImg);
		holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);
		
		//link data and view
		MatchupListItem matchup = data[position];
		holder.txtTitle.setText(matchup.title);
		holder.field_img_icon.setImageResource(matchup.field_icon);
		holder.tshirt_img_icon.setImageResource(matchup.shirt_icon);
		holder.bg_color = matchup.bgColor;
		
		//format view		
		row.setBackgroundColor(holder.bg_color);		
		if( holder.bg_color == context.getResources().getColor(R.color.nacka_gray_bg))
		{
			holder.field_img_icon.setImageAlpha(0);
		}
		
		if( matchup.shirtColor == TShirtColor.UNKNOWN)
		{
			Drawable d = context.getResources().getDrawable(R.drawable.unknown_shirt);
			holder.tshirt_img_icon.setBackground(d);
		}
		else if ( matchup.shirtColor == TShirtColor.RAINBOW )
		{
			Drawable d = context.getResources().getDrawable(R.drawable.rainbow);
			holder.tshirt_img_icon.setBackground(d);
		}
		else
		{
			holder.tshirt_img_icon.setBackgroundColor(matchup.shirtColor.getColorInt());
		}
		holder.field_img_icon.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent( context, FieldsActivity.class);
				context.startActivity( intent );				
			}
		});
		
		return row;
	}
	
	static class MatchupHolder
	{
		ImageButton field_img_icon;
		TextView txtTitle;
		ImageView tshirt_img_icon;
		int bg_color;
	}
}
