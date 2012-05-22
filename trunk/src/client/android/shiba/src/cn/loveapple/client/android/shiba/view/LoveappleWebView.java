/*
 * $HeadURL:$
 * $Author:$
 * $Revision:$
 * $Date:$
 *
 * ====================================================================
 *
 * Copyright (C) 2008 by loveapple.cn
 *
 * All copyright notices regarding loveapple and loveapple CoreLib
 * MUST remain intact in the scripts, documents and source code.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public 
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * Correspondence and Marketing Questions can be sent to:
 * info at loveapple
 *
 * @author: loveapple
 */
package cn.loveapple.client.android.shiba.view;

import java.util.Map;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * LoveappleカスタマイズWebView
 * 
 * @author $Author:$
 * @version $Revision:$
 * @date $Date:$
 * @id $Id:$
 *
 */
public class LoveappleWebView extends WebView {

	public LoveappleWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public LoveappleWebView(Context context) {
		super(context);
	}

	public LoveappleWebView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/**
	 * {@inheritDoc}
	 */
	public void loadUrl(String url){
		super.loadUrl(url);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void loadUrl(String url, Map<String, String> extraHeaders){
		super.loadUrl(url, extraHeaders);
	}
	
}
