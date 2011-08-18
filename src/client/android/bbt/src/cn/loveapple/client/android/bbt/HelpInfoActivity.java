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
package cn.loveapple.client.android.bbt;

import cn.loveapple.client.android.bbt.view.LoveappleWebViewClient;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

/**
 * ヘルプ情報
 * 
 * @author $Author$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 * 
 */
public class HelpInfoActivity extends BaseActivity {
	private WebView web_;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		web_ = (WebView) findViewById(R.id.web);
		final Button button = (Button) findViewById(R.id.GoButton);
		final OnClickListener listener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				final EditText edit = (EditText) findViewById(R.id.UriText);
				final String uriString = edit.getText().toString();
				web_.loadUrl(uriString);
				web_.requestFocus();
			}
		};
		button.setOnClickListener(listener);

		web_.setWebViewClient(new LoveappleWebViewClient());
	}
}
