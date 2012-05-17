/*
 * $HeadURL$
 * $Author$
 * $Revision$
 * $Date$
 *
 * ====================================================================
 *
 * Copyright (C) 2008 by loveapple.sourceforge.jp
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
package cn.loveapple.client.android.shiba.listener;


import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.AutoCompleteTextView;
import cn.loveapple.client.android.bbt.view.LoveappleWebView;
import cn.loveapple.client.android.shiba.ShibaFacadeActivity;
import cn.loveapple.client.android.util.StringUtils;

/**
 * 入力されたアドレスをリクエストするリスナー
 * 
 * @author loveapple
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
public class RequestListener implements OnClickListener, OnKeyListener {
	private LoveappleWebView webView;
	private AutoCompleteTextView address;
	
	public RequestListener(LoveappleWebView webView, AutoCompleteTextView address){
		this.webView = webView;
		this.address = address;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void onClick(View v) {
		final String uriString = address.getText().toString();
		webView.loadUrl(uriString);
		webView.requestFocus();
	}
	
	/**
	 * Enterキーを押下する場合、WEBアクセスを行う
	 */
	public boolean onKey(View view, int keyCode, KeyEvent event) {
		final String uriString = address.getText().toString();
		if(keyCode == KeyEvent.KEYCODE_ENTER){
			webView.loadUrl(StringUtils.getUrlWhitchSchema(uriString));
			webView.requestFocus();
		}
		((ShibaFacadeActivity)view.getContext()).setButtonEnabled();
		return false;
	}
}
