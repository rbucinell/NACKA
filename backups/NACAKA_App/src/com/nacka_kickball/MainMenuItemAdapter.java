package com.nacka_kickball;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.nacka_kickball.Data.MainMenuItemID;

public class MainMenuItemAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater inflater;
	
	public MainMenuItemAdapter(Context c )
	{
		context = c;
		inflater = ((Activity)context).getLayoutInflater();
	}
	
	@Override
	public int getCount() {
		return MainMenuItemID.values.length;
	}

	@Override
	public Object getItem(int position) {
		//return menuItems.get(position).getMenuInfo();
		return MainMenuItemID.values[position].getImage();
	}

	@Override
	public long getItemId(int position) {
		return MainMenuItemID.values[position].ordinal();
	}

	@Override
	public View getView(final int position, View view, ViewGroup viewGroup) {
		View row;
		if( view == null )
		{
			//inflate
			row = inflater.inflate(R.layout.gridview_mainmenu_item,viewGroup,false);
		}else
		{
			row = view;
		}
		
		MainMenuItemView menuItemView = (MainMenuItemView) row.findViewById(R.id.main_menu_item);
		menuItemView.setImageResource(MainMenuItemID.values[position].getImage());
		
		menuItemView.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				MainMenuItemID menuItem = MainMenuItemID.values[position];
				//String toastMessage = "Menu[" + position + "] Activity: " + menuItem.getActivity().getName();
				//Toast.makeText( v.getContext(), toastMessage , Toast.LENGTH_SHORT).show();
				Intent intent = new Intent( context, menuItem.getActivity());
				context.startActivity( intent );
			}
		});

		return row;
		
		
		
		/*
		
		MainMenuItemView menuItemView;
		
		if( view == null)
		{
			view = inflater.inflate(R.layout.gridview_mainmenu_item, viewGroup, false);
			//menuItemView = (MainMenuItemView) inflater.inflate(R.layout.menuitemview_item, viewGroup, false);
			//theGridView.setTag( R.id.main_menu_item, theGridView.findViewById(R.id.menu_item_image));
			
			
			//menuItemView.setTag(R.id.menu_item_image, menuItemView.findViewById(R.id.menu_item_image));
			//view = inflater.inflate(R.layout.menuitemview_item, parent, false);
			//view.setTag(R.id.menu_item_image, view.findViewById(R.id.menu_item_image));
			
			//menuItemView = new MainMenuItemView(context);
			//menuItemView.setLayoutParams(new GridView.LayoutParams(LayoutParams.MATCH_PARENT,250));
			//menuItemView.setScaleType(ScaleType.CENTER_CROP);
			//menuItemView.setPadding(5, 20, 5, 20);
			//imageView.setPadding(1, 1, 1, 1);
			
		}
		else
		{
			//menuItemView = (MainMenuItemView)view;
		}

		menuItemView = (MainMenuItemView) view.findViewById(R.id.main_menu_item);
		menuItemView.setMenuInfo(MainMenuItemID.values[position]);
		menuItemView.updateImageResource();
		
		//imageView = (ImageView)v.getTag(R.id.menu_item_image);
		//menuItemView.setImageResource(MainMenuItemID.values[position].getImage());
		
		
		menuItemView.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				MainMenuItemID menuItem = MainMenuItemID.values[position];
				String toastMessage = "Menu[" + position + "] Activity: " + menuItem.getActivity().getName();
				Toast.makeText( v.getContext(), toastMessage , Toast.LENGTH_SHORT).show();
				//Intent intent = new Intent( context, ListAllTeamsActivity.class );
				//context.startActivity( new Intent( context, ListAllTeamsActivity.class ) );
			}
		});
		
		return menuItemView;*/
	}

}
