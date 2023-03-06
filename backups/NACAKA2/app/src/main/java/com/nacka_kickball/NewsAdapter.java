package com.nacka_kickball;

import com.nacka_kickball.Data.NackaNewsItem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsAdapter extends ArrayAdapter<NackaNewsItem> {

	private int layoutResourceId;
	private Context context;
	private NackaNewsItem[] data;
	//private int TAG = 551;
	
	public NewsAdapter( Context context, int layoutResourceId, NackaNewsItem[] data)
	{
		super( context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}
	
	@Override
	public View getView( final int position, View convertView, ViewGroup parent)
	{
		View row = convertView;
		
		//Get data for view (already existing or new)
		if( row == null )
		{
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			
		}
		
		NackaNewsItem currentNewsItem = data[position];
		
		ImageView newsImageView 	= (ImageView)row.findViewById(R.id.news_item_image_iv);
		TextView titleTextView 		= (TextView)row.findViewById(R.id.news_item_title_tv);
		TextView contentTextView	= (TextView)row.findViewById(R.id.news_item_content_tv);
		
		try 
		{
			newsImageView.setImageBitmap(currentNewsItem.getImage());
		} catch (Exception e) 
		{
			newsImageView.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.nacka_news_article_template));
		}

		titleTextView.setText(Html.fromHtml(currentNewsItem.getTitle()));
		titleTextView.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String url = data[position].getURL();
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
				context.startActivity(browserIntent);
			}
		});
		
		contentTextView.setText(Html.fromHtml(currentNewsItem.getContent()));
		contentTextView.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String url = data[position].getURL();
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
				context.startActivity(browserIntent);
			}
		});
		return row;
	}

}


