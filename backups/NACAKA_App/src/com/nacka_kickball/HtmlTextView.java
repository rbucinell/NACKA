package com.nacka_kickball;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.util.AttributeSet;
import android.widget.TextView;

public class HtmlTextView extends TextView {

	public HtmlTextView(Context context) {
		super(context);
	}
	
	

	public HtmlTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public HtmlTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}	
	
	@Override
	public void setText(CharSequence text, BufferType type)
	{
		Spanned html = null;
		if(text != null) {
            html = Html.fromHtml(text.toString());
            super.setText(html, BufferType.SPANNABLE);
        } else {
           super.setText(text, type);
        }
	}

}
