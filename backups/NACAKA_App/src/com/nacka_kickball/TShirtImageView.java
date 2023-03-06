package com.nacka_kickball;

import com.nacka_kickball.Data.TShirtColor;

import android.content.Context;
import android.widget.ImageView;

public class TShirtImageView extends ImageView {

	private TShirtColor color;
	
	/**
	 * Default Constructor. Sets Tshirt Color to Unknown.
     * @param context
     */
    public TShirtImageView(Context context)
    {
        super(context);
        this.color = TShirtColor.WHITE;
        // TODO Auto-generated constructor stub
        setImage();
    }
    
    /**
     * @param context
     */
    public TShirtImageView(Context context, TShirtColor color)
    {
        super(context);
        this.color = color;        
        setImage();
    }
    
    private void setImage()
    {
    	
    }

	public TShirtColor getColor()
    {
    	return this.color;
    }
    
    public void setColor( TShirtColor color )
    {
    	this.color = color;
    }
}
