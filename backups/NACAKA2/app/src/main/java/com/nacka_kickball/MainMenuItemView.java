package com.nacka_kickball;

import com.nacka_kickball.Data.MainMenuItemID;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

public class MainMenuItemView extends ImageButton {

	private MainMenuItemID menuInfo;
	
	/////////////////////CONSTRUCTORS//////////////////////////
	public MainMenuItemView(Context context) {
		super(context);
	}
	public MainMenuItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MainMenuItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	/////////////////////GETTER / SETTER //////////////////////////
	public MainMenuItemID getMenuInfo() {
		return menuInfo;
	}

	public void setMenuInfo(MainMenuItemID menuInfo) {
		this.menuInfo = menuInfo;
	}	
	
	/////////////////////METHODS//////////////////////////
	@Override
	protected void onMeasure( int widthMeasureSpec, int heightMeasureSpec )
	{
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight()); //snap to width
	}
	
	public void updateImageResource()
	{
		this.setImageResource(menuInfo.getImage());
	}

	

}
