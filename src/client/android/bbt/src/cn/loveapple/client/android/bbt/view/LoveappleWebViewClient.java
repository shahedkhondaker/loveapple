/*
 * $HeadURL$
 * $Author$
 * $Revision$
 * $Date$
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
package cn.loveapple.client.android.bbt.view;

import static cn.loveapple.client.android.Constant.*;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * WEBページを表示するビュー
 * 
 * @author $Author$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 */
public class LoveappleWebViewClient extends WebViewClient {
	private Dialog dialog_;

	public LoveappleWebViewClient() {
		super();
		dialog_ = null;
	}

	/**
	 * ページ読み込み開始時の動作
	 */
	@Override
	public void onPageStarted(WebView view, String url, Bitmap favicon) {
		super.onPageStarted(view, url, favicon);
		// ダイアログを作成して表示
		dialog_ = new Dialog(view.getContext());
		dialog_.setTitle("待て、しかして希望せよ");
		dialog_.show();
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void onLoadResource(WebView view, String url){
		super.onLoadResource(view, url);
		Log.d(LOG_TAG, "Access URL:" + url);
	}
	/**
	 *  ページ読み込み終了時の動作
	 */
	@Override
	public void onPageFinished(WebView view, String url) {
		super.onPageFinished(view, url);
		// ダイアログを削除
		if(dialog_ != null){
			dialog_.dismiss();
		}
		dialog_ = null;
	}
}
