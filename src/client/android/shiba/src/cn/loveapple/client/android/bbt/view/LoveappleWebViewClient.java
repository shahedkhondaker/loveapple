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

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import cn.loveapple.client.android.shiba.ShibaFacadeActivity;
import cn.loveapple.client.android.util.StringUtils;

/**
 * WEBページを表示するビュー
 * 
 * @author $Author$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 */
public class LoveappleWebViewClient extends WebViewClient {
	/**
	 * 実行されるアクティビティ
	 */
	private ShibaFacadeActivity activity;
	/**
	 * プロキシーサーバに経由しなくてもアクセス可能ホスト一覧
	 */
	private Set<String> hostWhiteList;
	/**
	 * プロキシサーバに経由しなくてもアクセス可能なホスト一覧
	 */
	private Set<String> schemaWhiteList;
	public LoveappleWebViewClient(ShibaFacadeActivity activity) {
		super();
		this.activity = activity;
	}

	/**
	 * ページ読み込み開始時の動作
	 */
	@Override
	public void onPageStarted(WebView view, String url, Bitmap favicon) {
		super.onPageStarted(view, url, favicon);
		activity.setProgressBarIndeterminateVisibility(true);
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void onLoadResource(WebView view, String url){
		String baseUrl = activity.getProxyUrl();
		
		if(StringUtils.isEmpty(baseUrl) || isWhiteSchema(url) || isWhiteHost(url)){
			super.onLoadResource(view, url);
			Log.d(LOG_TAG, "Access URL:" + url);
			return ;
		}
		if(!url.startsWith(baseUrl)){
			StringBuilder sb = new StringBuilder(url.length() + baseUrl.length());
			sb.append(baseUrl);
			sb.append('/');
			if(url.startsWith(SCHEMA_HTTP_STR)){
				sb.append(url.substring(SCHEMA_HTTP_STR.length()));
				sb.insert(0, SCHEMA_HTTP_STR);
			}else if(url.startsWith(SCHEMA_HTTPS_STR)){
				sb.append(url.substring(SCHEMA_HTTPS_STR.length()));
				sb.insert(0, SCHEMA_HTTPS_STR);
			}else{
				sb.append(url);
				sb.insert(0, SCHEMA_HTTP_STR);
			}
			url = sb.toString();
		}
		super.onLoadResource(view, url);
		Log.d(LOG_TAG, "Access URL:" + url);
	}
	
	/**
	 *  ページ読み込み終了時の動作
	 */
	@Override
	public void onPageFinished(WebView view, String url) {
		super.onPageFinished(view, url);
		activity.setProgressBarIndeterminateVisibility(false);
	}
	
	private boolean isWhiteSchema(String url){
		if(schemaWhiteList == null){
			return false;
		}
		for (String schema : schemaWhiteList) {
			if(url.startsWith(schema)){
				return true;
			}
		}
		return false;
	}
	
	private boolean isWhiteHost(String url){
		String host = StringUtils.getHost(url);
		if(StringUtils.isEmpty(host)){
			return false;
		}
		for (String hostName : hostWhiteList) {
			if(host.endsWith(hostName)){
				return true;
			}
		}
		return false;
	}

	/**
	 * プロキシーサーバに経由しなくてもアクセス可能ホスト一覧を設定します。
	 * @param hostWhiteList プロキシーサーバに経由しなくてもアクセス可能ホスト一覧
	 */
	public void setHostWhiteList(String[] hostWhiteList) {
		this.hostWhiteList = new HashSet<String>();
		if(ArrayUtils.isEmpty(hostWhiteList)){
			return ;
		}
		for (String host : hostWhiteList) {
			this.hostWhiteList.add(host);
		}
	}

	/**
	 * プロキシサーバに経由しなくてもアクセス可能なホスト一覧を設定します。
	 * @param schemaWhiteList プロキシサーバに経由しなくてもアクセス可能なホスト一覧
	 */
	public void setSchemaWhiteList(String[] schemaWhiteList) {
	    this.schemaWhiteList = new HashSet<String>();
	    if(ArrayUtils.isEmpty(schemaWhiteList)){
	    	return ;
	    }
	    for (String schema : schemaWhiteList) {
			this.schemaWhiteList.add(schema);
		}
	}
}
